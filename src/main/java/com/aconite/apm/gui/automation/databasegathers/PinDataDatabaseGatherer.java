package com.aconite.apm.gui.automation.databasegathers;

import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.PinDataRecord;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PinDataDatabaseGatherer
{
    int rowCount = 0;

    private int recordCount = 0;

    private void setRecordCount(int count){
        this.recordCount = count;
    }

    public int getRecordCount(){
        return (this.recordCount);
    }

    private static final String SQL =   "SELECT ID, STATUS, TO_CHAR(CREATION_DATETIME, 'YYYY-MM-DD'), " +
                                        "TO_CHAR(EXPIRY_DATETIME, 'YYYY-MM-DD'), ISSUER_PIN_ID, PIN_TYPE " +
                                        "FROM C_D_PIN " +
                                        "WHERE ID > ? " +
                                        "ORDER BY ID";

    private static final String SQL2 =   "SELECT ID, STATUS, TO_CHAR(CREATION_DATETIME, 'YYYY-MM-DD'), " +
                                        "TO_CHAR(EXPIRY_DATETIME, 'YYYY-MM-DD'), ISSUER_PIN_ID, PIN_TYPE " +
                                        "FROM C_D_PIN " +
                                        "WHERE LAST_UPDATE_DATETIME > (SELECT TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS.FF') FROM DUAL) " +
                                        "ORDER BY ID";

    private static final String housekeeperSQL =    "SELECT ID, STATUS, TO_CHAR(CREATION_DATETIME, 'YYYY-MM-DD'), " +
                                                    "TO_CHAR(EXPIRY_DATETIME, 'YYYY-MM-DD'), ISSUER_PIN_ID, PIN_TYPE " +
                                                    "FROM C_D_PIN " +
                                                    "WHERE EXPIRY_DATETIME <= to_timestamp((select CURRENT_TIMESTAMP from dual)-180)" +
                                                    "OR STATUS = 'D' " +
                                                    "ORDER BY ID";


    private static final String unusedPinPasswordCleanUpSQL =       "SELECT p.* " +
                                                                    "FROM C_D_PIN p " +
                                                                    "WHERE NOT EXISTS " +
                                                                    "(SELECT ta.pin_id from C_D_TOKEN_APPLICATION ta " +
                                                                    " WHERE ta.pin_id = p.id) " +
                                                                    "AND NOT EXISTS " +
                                                                    "(SELECT vpp.pin_id from C_D_VPP_PIN_ID vpp " +
                                                                    "WHERE vpp.pin_id = p.id) " +
                                                                    "AND p.EXPIRY_DATETIME < ((SELECT CURRENT_TIMESTAMP FROM DUAL)) " +
                                                                    "ORDER BY 1 ASC";

    private static final String housekeeperSQLCount =    "SELECT COUNT(*) FROM C_D_PIN";

    private Connection connection;
    public List <PinDataRecord> records = new ArrayList<>();
    public String createdPins = "";

    public PinDataDatabaseGatherer()
    {
//        connection = DatabaseConnection.getConnection();
    }


    public void gatherData(int thisRunFromId)
    {
        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setInt(1, thisRunFromId);

            System.out.println("About to gather data for all pin data extract entries with ID > " + thisRunFromId + " ...");

            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next())
            {
                PinDataRecord record = new PinDataRecord();
                record.setId(rs.getString(1));
                record.setStatus(rs.getString(2));
                record.setCreationDateTime(rs.getString(3));
                record.setExpiryDateTime(rs.getString(4));
                record.setIssuerPinId(rs.getString(5));
//                record.setPinPukFlag(rs.getString(6));

                records.add(record);
                rowCount++;
            }
            System.out.println("Data gathered for all pin data extract entries with ID > " + thisRunFromId + ": " + rowCount + " rows found");
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
        System.out.println("Print Records: PinDataDatabaseGatherer");
        for (PinDataRecord pinDataRecord : records)
        {
            System.out.println(pinDataRecord.toString());
        }
    }

    public void gatherDataByDate(ExtractLogDataExtractRecord extractRecord){

        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(SQL2);
            String lastRunTime = extractRecord.getFinishDateTime();
            if(lastRunTime==null){
                lastRunTime = "1900-01-01 00:00:00.00";
            }
            else{
                lastRunTime = extractRecord.getFinishDateTime();
            }
            preparedSelect.setString(1, lastRunTime);

//            System.out.println("About to gather data for all pin data extract entries updated since > " + lastRunTime + " ...");

            ResultSet rs = preparedSelect.executeQuery();
            int rowCount = 0;
            while (rs.next())
            {
                PinDataRecord record = new PinDataRecord();
                record.setId(rs.getString(1));
                record.setStatus(rs.getString(2));
                record.setCreationDateTime(rs.getString(3));
                record.setExpiryDateTime(rs.getString(4));
                record.setIssuerPinId(rs.getString(5));
//                record.setPinPukFlag(rs.getString(6));

                records.add(record);
                rowCount++;
            }
//            System.out.println("Data gathered for all pin data extract entries updated since > " + lastRunTime + ": " + rowCount + " rows found");
            this.setRecordCount(rowCount);

            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void gatherDataForHousekeeper()
    {
        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(housekeeperSQL);

//            TODO: Need to read retention period from token product table
            System.out.println("About to gather data for all pinsmspwdCleanUp entries older than 180 days...");

            ResultSet rs = preparedSelect.executeQuery();
            int rowCount = 0;
            while (rs.next())
            {
                PinDataRecord record = new PinDataRecord();
                record.setId(rs.getString(1));
                if (createdPins.equals("")){
                    createdPins = rs.getString(1);
                }
                else{
                    createdPins = createdPins + "," + rs.getString(1);
                }
                record.setStatus(rs.getString(2));
                record.setCreationDateTime(rs.getString(3));
                record.setExpiryDateTime(rs.getString(4));
                record.setIssuerPinId(rs.getString(5));
//                record.setPinPukFlag(rs.getString(6));

                records.add(record);
                rowCount++;
            }

            System.out.println("Data gathered for pinsmspwd CleanUp entries older than 180 days: " + rowCount + " rows found");

            connection.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void gatherDataForUnusedPinPasswordCleanUp()
    {
        try
        {
            connection = DatabaseConnection.getConnection();

            PreparedStatement preparedSelect = connection.prepareStatement(unusedPinPasswordCleanUpSQL);

//            TODO: Need to read retention period from token product table
//            System.out.println("About to gather data for all expired PINs not attached to token app profiles...");

            ResultSet rs = preparedSelect.executeQuery();
            int rowCount = 0;
            while (rs.next())
            {
                PinDataRecord record = new PinDataRecord();
                record.setId(rs.getString(1));
                if (createdPins.equals("")){
                    createdPins = rs.getString(1);
                }
                else{
                    createdPins = createdPins + "," + rs.getString(1);
                }
                record.setStatus(rs.getString(2));
                record.setCreationDateTime(rs.getString(4));
                record.setExpiryDateTime(rs.getString(5));
                record.setIssuerPinId(rs.getString(6));
//                record.setPinPukFlag(rs.getString(6));

                records.add(record);
                rowCount++;
            }

            System.out.println("Data gathered for all expired PINs not attached to token app profiles: " + rowCount + " rows found");

            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public int getRowCountForHousekeeper()
    {
        ResultSet rs;
        int outCount=-1;

        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(housekeeperSQLCount);
            rs = preparedSelect.executeQuery();
            while (rs.next()){
                outCount = Integer.parseInt(rs.getString(1));
            }
            connection.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
//        System.out.println(rs.toString());
        return outCount;
    }
}