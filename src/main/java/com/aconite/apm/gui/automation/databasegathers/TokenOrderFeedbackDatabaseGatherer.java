package com.aconite.apm.gui.automation.databasegathers;

import com.aconite.apm.gui.automation.crypto.PvvCalculator;
import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.databasegathers.ExtractLogDataGatherer;
import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.TokenOrderFeedbackRecord;
import com.aconite.apm.gui.automation.utilities.ConfigUtils;
import com.aconite.apm.gui.automation.utilities.CryptoUtils;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import org.apache.commons.lang.BooleanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenOrderFeedbackDatabaseGatherer
{
    static final String SQL =   "SELECT " +
                                    "MAX(TRXL.ID) AS RECORD_ID, " +
                                    "TAPR.PIN_VERIFICATION_METHOD_ID AS PIN_VERIFICATION_METHOD_ID, " +
                                    "TAPP.PAN_ALIAS AS PAN_ALIAS, " +
                                    "TAPR.PIN_LENGTH AS PIN_LENGTH, " +
                                    "TAPR.SCI_PIN_VERIFICATION_KEY_ID AS PIN_VERIFICATION_KEY_ID, " +
                                    "SCI.KEY_INDEX AS PVV_KEY_INDEX, " +
                                    "PIN.VALUE AS PIN, " +
                                    "TAPP.ID AS PAN_ID, " +
                                    "TAPP.PAN_ENCRYPTED AS PAN, " +
                                    "TAPP.PAN_SEQ_NUMBER AS PAN_SEQ_NUMBER, " +
                                    "TAPP.EXPIRY_DATE AS PAN_EXPIRY_DATE, " +
                                    "EZONE.PIN_BLOCK_FORMAT_ID AS PIN_BLOCK_FORMAT " +
                                    "FROM " +
                                    "PM_D_TRX_LOG TRXL " +
                                    "INNER JOIN C_D_TOKEN_APPLICATION TAPP ON TAPP.ID=TRXL.RSP_PAN_ID " +
                                    "INNER JOIN C_D_TOKEN_APP_PROFILE TAPR  ON TAPP.TOKEN_PRODUCT_ID=TAPR.TOKEN_PRODUCT_ID " +
                                    "AND TAPP.APP_SEQ_NUMBER=TAPR.APP_SEQ_NUMBER " +
                                    "INNER JOIN C_D_TOKEN TOKN ON TOKN.ID=TAPP.TOKEN_ID " +
                                    "INNER JOIN C_D_TOKEN_PRODUCT TPRD ON TPRD.ID=TOKN.TOKEN_PRODUCT_ID " +
                                    "INNER JOIN C_D_ISSUER ISSR ON ISSR.ID=TPRD.ISSUER_ID " +
                                    "INNER JOIN C_D_PIN PIN ON PIN.ID=TRXL.RSP_PIN_ID " +
                                    "INNER JOIN ENC_D_SCI SCI ON SCI.ID=TAPR.SCI_PIN_VERIFICATION_KEY_ID " +
                                    "INNER JOIN C_D_INSTITUTION INST ON INST.ID=ISSR.INSTITUTION_ID " +
                                    "INNER JOIN ENC_D_ENCRYPTION_ZONE EZONE ON INST.APM_LOCAL_ENC_ZONE_ID=EZONE.ID " +
                                    "WHERE TRXL.TRX_TYPE_ID = (9) " +
                                    "AND TRXL.TRX_STATUS=2 " +
                                    "AND " +
                                    "    ( " +
                                    "       TRXL.REQ_FEEDBACK_REQUIRED_FLAG ='Y' OR  " +
                                    "      (TRXL.REQ_FEEDBACK_REQUIRED_FLAG ='D' AND TPRD.FEEDBACK_REQUIRED_FLAG ='Y' AND TRXL.REQ_ORDER_TYPE IN (1, 3, 4)) " +
                                    "    ) " +
                                    "AND TRXL.ID >= ? " +
                                    "AND ISSR.ID = ? " +
                                    "GROUP BY " +
                                    "ISSR.ID, ISSR.NAME, TAPP.PIN_VERIFICATION_VALUE,TAPR.PIN_VERIFICATION_METHOD_ID,TAPR.PIN_LENGTH,TAPP.PAN_ALIAS, " +
                                    "TAPP.PVV_KEY_INDEX, TAPR.SCI_PIN_VERIFICATION_KEY_ID, SCI.KEY_INDEX, PIN.VALUE, TAPP.ID, " +
                                    "TAPP.PAN_ENCRYPTED, TAPP.PAN_SEQ_NUMBER, TAPP.EXPIRY_DATE, INST.APM_LOCAL_ENC_ZONE_ID, TAPR.IMPORT_ENC_ZONE_ID, " +
                                    "EZONE.PIN_ENCRYPTION_KEY_ID, EZONE.PIN_BLOCK_FORMAT_ID " +
                                    "ORDER BY RECORD_ID ";


    private Connection connection;
    public List<TokenOrderFeedbackRecord> records = new ArrayList<>();

    public TokenOrderFeedbackDatabaseGatherer()
    {
//        connection = DatabaseConnection.getConnection();
    }

    private int recordCount = 0;
    int rowCount = 0;
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

            int issuer = Integer.parseInt(ConfigUtils.cfgProperty("issuer"));
            String localZpk = ConfigUtils.cfgProperty("local_zpk");
            List<String> pvvList = Arrays.asList("D0AD2CB357193EDCAB317CD6AD077010", "5240B0DFA131C204D9EFD5FEB3EA1097", "F28FA8914664A8E37C43518029D9195D");
            String pvv="";
//            String pvv = ConfigUtils.cfgProperty("pvv");
//            String pvki = ConfigUtils.cfgProperty("pvki");

            String localZek = ConfigUtils.cfgProperty("local_zek");
            String importZek = ConfigUtils.cfgProperty("import_zek");
            String localPinBlockFormat = ConfigUtils.cfgProperty("local_pinblock_format");
            String localPanEncryptedFlag = ConfigUtils.cfgProperty("local_pan_encrypted");
            String importPanEncryptedFlag = ConfigUtils.cfgProperty("import_pan_encrypted");

            ExtractLogDataExtractRecord extractRecord = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENORDERFEEDBACK);

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, extractRecord.getIdFrom());
            preparedSelect.setInt(2, issuer);

            System.out.println("About to gather data for tokenorderfeedback task with IDs > " + extractRecord.getIdFrom() + " for issuer " + issuer);

            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next())
            {

                String dbPan = rs.getString(9);
                String dbPin = rs.getString(7);
                String pvki = rs.getString(6);

                switch(pvki){
                    case("1"):
                    case("4"):
                        pvv = pvvList.get(0);
                        break;

                    case("2"):
                        pvv = pvvList.get(1);
                        break;

                    case("3"):
                        pvv = pvvList.get(2);
                        break;
                }

                String clearPan;
                String clearPin;

                if (BooleanUtils.toBoolean(localPanEncryptedFlag))
                {
                    clearPan = CryptoUtils.getPanFromEncPanBlock(dbPan, localZek);
                }
                else
                {
                    clearPan = dbPan;
                }

                clearPin = CryptoUtils.extractPinFromPinBlock(dbPin, localPinBlockFormat, localZpk, clearPan);

                TokenOrderFeedbackRecord record = new TokenOrderFeedbackRecord();
                record.setPanId(rs.getString(8));
                record.setPan(CryptoUtils.translatePan(dbPan, localPanEncryptedFlag, importPanEncryptedFlag, localZek, importZek));
                record.setPanEncryptedFlag(importPanEncryptedFlag);
                record.setPanSequenceNumber(rs.getString(10));
                record.setPanExpiryDate(rs.getDate(11).toString());
                record.setIssuerPanAlias(rs.getString(3) == null ? "" : rs.getString(3));
                record.setPin(CryptoUtils.extractPinFromPinBlock(dbPin, localPinBlockFormat, localZpk, clearPan));
                record.setPinVerificationValue(PvvCalculator.generatePvv(clearPan, clearPin, pvv, pvki));
                record.setPvvKeyIndex(pvki);

                records.add(record);
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

    public void printRecords()
    {
        for (TokenOrderFeedbackRecord tokenOrderFeedbackRecord : records)
        {
            System.out.println(tokenOrderFeedbackRecord.toString());
        }
    }
}
