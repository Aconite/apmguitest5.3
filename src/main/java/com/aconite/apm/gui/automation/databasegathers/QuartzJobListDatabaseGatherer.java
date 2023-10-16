package com.aconite.apm.gui.automation.databasegathers;

import com.aconite.apm.gui.automation.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuartzJobListDatabaseGatherer
{
    private static final String SQL =   "SELECT JOB_NAME FROM QRTZ_JOB_DETAILS";

    private static final String checkDeletedSQL =   "SELECT JOB_NAME FROM QRTZ_JOB_DETAILS WHERE JOB_NAME = CONCAT(?,'_job')";

    private Connection connection;
    public List <String> records = new ArrayList<>();
    private int recordCount;
    int rowCount = 0;

    public QuartzJobListDatabaseGatherer()
    {
//        connection = DatabaseConnection.getConnection();
    }

    private void setRecordCount(int count){
        this.recordCount = count;
    }

    public int getRecordCount(){
        return (this.recordCount);
    }

    public void gatherData()
    {

        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);

            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next())
            {
                records.add(rs.getString(1));
                rowCount++;
            }
            this.setRecordCount(rowCount);

            connection.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public boolean gatherDeletedData(String taskId)
    {
        boolean existingData = false;
        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(checkDeletedSQL);

            preparedSelect.setString(1, taskId);
            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next()){
                System.out.println("gatherDeletedData = >" + rs.getString(1) + "<");
                existingData = true;
            }

            connection.close();

            if(existingData){
                return false;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return true;
    }

    public void printRecords()
    {
        System.out.println("Print Records: QuartzJobListDatabaseGatherer");
        for (String item : records)
        {
            System.out.println(item);
        }
    }

}