package com.aconite.apm.gui.automation.databasegathers;

import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.TokenDataRecord;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TokenDataDatabaseGatherer
{
    private static final String SQL =   "SELECT ID, STATUS, TOKEN_PRODUCT_ID, " +
                                        "DEFAULT_TOKEN_APP_ID, CUSTOMER_CODE " +
                                        "FROM C_D_TOKEN " +
                                        "WHERE ID >= ? " +
                                        "ORDER BY ID";

    private Connection connection;
    public List <TokenDataRecord> records = new ArrayList<>();

    private int recordCount = 0;

    private void setRecordCount(int count){
        this.recordCount = count;
    }

    public int getRecordCount(){
        return (this.recordCount);
    }

    public TokenDataDatabaseGatherer()
    {
//        connection = DatabaseConnection.getConnection();
    }

    public void gatherData()
    {
        try
        {
            connection = DatabaseConnection.getConnection();

            ExtractLogDataExtractRecord extractRecord = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENDATAEXTRACT);

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, extractRecord.getIdFrom());

            System.out.println("About to gather data for all token entries with ID > " + extractRecord.getIdFrom() + " ...");

            ResultSet rs = preparedSelect.executeQuery();
            int rowCount = 0;
            while (rs.next())
            {
                TokenDataRecord record = new TokenDataRecord();
                record.setId(rs.getString(1));
                record.setStatus(rs.getString(2));
                record.setTokenProductId(rs.getString(3));
                record.setDefaultTokenAppId(rs.getString(4));
                record.setCustomerCode(rs.getString(5));

                records.add(record);
                rowCount++;
            }
//            System.out.println("Data gathered for all accesslog entries with ID > " + extractRecord.getIdFrom() + ": " + rowCount + " rows found");
            this.setRecordCount(rowCount);

            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void printRecords()
    {
        System.out.println("Print Records: TokenDataDatabaseGatherer");
        for (TokenDataRecord tokenDataRecord : records)
        {
            System.out.println(tokenDataRecord.toString());
        }
    }

}