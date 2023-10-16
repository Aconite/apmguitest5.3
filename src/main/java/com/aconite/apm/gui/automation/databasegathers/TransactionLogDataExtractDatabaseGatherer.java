package com.aconite.apm.gui.automation.databasegathers;

import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.records.ExtractLogDataExtractRecord;
import com.aconite.apm.gui.automation.records.TransactionLogDataExtractRecord;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import com.aconite.apm.gui.automation.webpages.AdminCommon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionLogDataExtractDatabaseGatherer
{


    private static final String SQL =   "SELECT PMDT.ID, PMDT.INTERFACE_ID, PMDT.TRX_TYPE_ID, PMDT.TRX_STATUS, " +
            "PMDT.SYSTEM_DATETIME, PMDT.REQ_DATETIME, PMDT.REQ_ORIG_ID, " +
            "PMDT.REQ_RN, PMDT.REQ_ISSUER_PIN_ID, PMDT.REQ_ISSUER_PUK_ID, PMDT.REQ_PUK_ID, " +
            "PMDT.REQ_PIN_EXPIRY_DATE, PMDT.REQ_PIN_ID,PMDT.REQ_PAN_ID, " +
            "PMDT.REQ_ISSUER_PAN_ALIAS, PMDT.REQ_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.REQ_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.REQ_TOKEN_APP_STATUS_ID, " +
            "PMDT.REQ_TOKEN_APP_SEQ_NUM, PMDT.REQ_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.REQ_PIN_DELIVERY_METHOD, PMDT.REQ_ADVICE_ALL_TOKEN_APP_FLAG, " +
            "PMDT.REQ_ADVICE_PIN_PUK_FLAG, PMDT.REQ_LANGUAGE_CODE, " +
            "PMDT.REQ_MAILING_CODE, PMDT.REQ_PRIORITY, PMDT.REQ_BRANCH_CODE, " +
            "PMDT.REQ_BRANCH_NAME, PMDT.REQ_NUMBER_OF_TOKENS, " +
            "PMDT.REQ_GENERATE_PAN_FLAG, PMDT.REQ_GENERATE_PIN_FLAG, " +
            "PMDT.REQ_GENERATE_PUK_FLAG, PMDT.REQ_ORDER_TYPE, " +
            "PMDT.REQ_FEEDBACK_REQUIRED_FLAG, PMDT.REQ_PREVIOUS_PAN_ID, " +
            "PMDT.REQ_PREVIOUS_PAN_SEQ_NUMBER, TO_CHAR(PMDT.REQ_PREVIOUS_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), " +
            "PMDT.RSP_RESPONSE_CODE, PMDT.RSP_ERROR_DESCR, PMDT.RSP_DATETIME, " +
            "PMDT.RSP_PIN_ID, PMDT.RSP_PUK_ID, PMDT.RSP_PAN_ID, PMDT.RSP_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.RSP_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.RSP_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.RSP_TOKEN_APP_SEQ_NUM, PMDT.RSP_SMS_STATUS, " +
            "INST.ID, INST.NAME,  " +
            "ISS.ID, ISS.NAME, " +
            "TAPP.TOKEN_PRODUCT_ID,  " +
            "TPRD.ISSUER_TOKEN_PRODUCT_CODE, TPRD.NAME, " +
            "TPRD.TOKEN_PRODUCT_GROUP_ID,  " +
            "TPGR.NAME, TPGR.GROUP_CODE, " +
            "TAPP.PAN_DISPLAY, " +
            "ISS.COUNTRY_CODE " +
            "FROM PM_D_TRX_LOG PMDT " +
            "INNER JOIN C_D_TOKEN_APPLICATION TAPP ON PMDT.REQ_PAN_ID = TAPP.ID " +
//            "INNER JOIN C_D_TOKEN_APPLICATION TAPP ON PMDT.RSP_PAN_ID = TAPP.ID " +
            "INNER JOIN C_D_TOKEN_PRODUCT TPRD ON TAPP.TOKEN_PRODUCT_ID = TPRD.ID " +
            "INNER JOIN C_D_TOKEN_PRODUCT_GROUP TPGR ON TPRD.TOKEN_PRODUCT_GROUP_ID = TPGR.ID " +
            "INNER JOIN C_D_ISSUER ISS ON TPGR.ISSUER_ID = ISS.ID " +
            "INNER JOIN C_D_INSTITUTION INST ON ISS.INSTITUTION_ID = INST.ID " +
            "WHERE PMDT.ID >= ? " +
            "UNION " +
            "SELECT PMDT.ID, PMDT.INTERFACE_ID, PMDT.TRX_TYPE_ID, PMDT.TRX_STATUS, " +
            "PMDT.SYSTEM_DATETIME, PMDT.REQ_DATETIME, PMDT.REQ_ORIG_ID, " +
            "PMDT.REQ_RN, PMDT.REQ_ISSUER_PIN_ID, PMDT.REQ_ISSUER_PUK_ID, PMDT.REQ_PUK_ID, " +
            "PMDT.REQ_PIN_EXPIRY_DATE, PMDT.REQ_PIN_ID,PMDT.REQ_PAN_ID, " +
            "PMDT.REQ_ISSUER_PAN_ALIAS, PMDT.REQ_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.REQ_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.REQ_TOKEN_APP_STATUS_ID, " +
            "PMDT.REQ_TOKEN_APP_SEQ_NUM, PMDT.REQ_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.REQ_PIN_DELIVERY_METHOD, PMDT.REQ_ADVICE_ALL_TOKEN_APP_FLAG, " +
            "PMDT.REQ_ADVICE_PIN_PUK_FLAG, PMDT.REQ_LANGUAGE_CODE, " +
            "PMDT.REQ_MAILING_CODE, PMDT.REQ_PRIORITY, PMDT.REQ_BRANCH_CODE, " +
            "PMDT.REQ_BRANCH_NAME, PMDT.REQ_NUMBER_OF_TOKENS, " +
            "PMDT.REQ_GENERATE_PAN_FLAG, PMDT.REQ_GENERATE_PIN_FLAG, " +
            "PMDT.REQ_GENERATE_PUK_FLAG, PMDT.REQ_ORDER_TYPE, " +
            "PMDT.REQ_FEEDBACK_REQUIRED_FLAG, PMDT.REQ_PREVIOUS_PAN_ID, " +
            "PMDT.REQ_PREVIOUS_PAN_SEQ_NUMBER, TO_CHAR(PMDT.REQ_PREVIOUS_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), " +
            "PMDT.RSP_RESPONSE_CODE, PMDT.RSP_ERROR_DESCR, PMDT.RSP_DATETIME, " +
            "PMDT.RSP_PIN_ID, PMDT.RSP_PUK_ID, PMDT.RSP_PAN_ID, PMDT.RSP_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.RSP_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.RSP_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.RSP_TOKEN_APP_SEQ_NUM, PMDT.RSP_SMS_STATUS, " +
            "NULL AS \"ID_1\", NULL AS \"NAME\",  " +
            "NULL AS \"ID_2\", NULL AS \"NAME_1\",  " +
            "NULL AS \"TOKEN_PRODUCT_ID\",  " +
            "NULL AS \"ISSUER_TOKEN_PRODUCT_CODE\", NULL AS \"NAME_2\", " +
            "NULL AS \"TOKEN_PRODUCT_GROUP_ID\",  " +
            "NULL AS \"NAME_3\", NULL AS \"GROUP_CODE\", " +
            "NULL AS \"PAN_DISPLAY\", " +
            "NULL AS \"COUNTRY_CODE\" " +
            "FROM PM_D_TRX_LOG PMDT " +
            "WHERE PMDT.ID >= ? " +
            "AND PMDT.TRX_TYPE_ID in (1,2,3,9,11,12,14,18,23) " +
            "AND PMDT.RSP_PAN_ID is null " +
            "AND PMDT.REQ_PAN_ID is null " +
            "UNION " +
            "SELECT PMDT.ID, PMDT.INTERFACE_ID, PMDT.TRX_TYPE_ID, PMDT.TRX_STATUS, " +
            "PMDT.SYSTEM_DATETIME, PMDT.REQ_DATETIME, PMDT.REQ_ORIG_ID, " +
            "PMDT.REQ_RN, PMDT.REQ_ISSUER_PIN_ID, PMDT.REQ_ISSUER_PUK_ID, PMDT.REQ_PUK_ID, " +
            "PMDT.REQ_PIN_EXPIRY_DATE, PMDT.REQ_PIN_ID,PMDT.REQ_PAN_ID, " +
            "PMDT.REQ_ISSUER_PAN_ALIAS, PMDT.REQ_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.REQ_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.REQ_TOKEN_APP_STATUS_ID, " +
            "PMDT.REQ_TOKEN_APP_SEQ_NUM, PMDT.REQ_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.REQ_PIN_DELIVERY_METHOD, PMDT.REQ_ADVICE_ALL_TOKEN_APP_FLAG, " +
            "PMDT.REQ_ADVICE_PIN_PUK_FLAG, PMDT.REQ_LANGUAGE_CODE, " +
            "PMDT.REQ_MAILING_CODE, PMDT.REQ_PRIORITY, PMDT.REQ_BRANCH_CODE, " +
            "PMDT.REQ_BRANCH_NAME, PMDT.REQ_NUMBER_OF_TOKENS, " +
            "PMDT.REQ_GENERATE_PAN_FLAG, PMDT.REQ_GENERATE_PIN_FLAG, " +
            "PMDT.REQ_GENERATE_PUK_FLAG, PMDT.REQ_ORDER_TYPE, " +
            "PMDT.REQ_FEEDBACK_REQUIRED_FLAG, PMDT.REQ_PREVIOUS_PAN_ID, " +
            "PMDT.REQ_PREVIOUS_PAN_SEQ_NUMBER, TO_CHAR(PMDT.REQ_PREVIOUS_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), " +
            "PMDT.RSP_RESPONSE_CODE, PMDT.RSP_ERROR_DESCR, PMDT.RSP_DATETIME, " +
            "PMDT.RSP_PIN_ID, PMDT.RSP_PUK_ID, PMDT.RSP_PAN_ID, PMDT.RSP_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.RSP_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.RSP_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.RSP_TOKEN_APP_SEQ_NUM, PMDT.RSP_SMS_STATUS, " +
            "INST.ID, INST.NAME,  " +
            "ISS.ID, ISS.NAME, " +
            "TAPP.TOKEN_PRODUCT_ID,  " +
            "TPRD.ISSUER_TOKEN_PRODUCT_CODE, TPRD.NAME, " +
            "TPRD.TOKEN_PRODUCT_GROUP_ID,  " +
            "TPGR.NAME, TPGR.GROUP_CODE, " +
            "TAPP.PAN_DISPLAY, " +
            "ISS.COUNTRY_CODE " +
            "FROM PM_D_TRX_LOG PMDT " +
            "INNER JOIN C_D_TOKEN_APPLICATION TAPP ON PMDT.REQ_PAN_ID = TAPP.ID " +
            "INNER JOIN C_D_TOKEN_PRODUCT TPRD ON TAPP.TOKEN_PRODUCT_ID = TPRD.ID " +
            "INNER JOIN C_D_TOKEN_PRODUCT_GROUP TPGR ON TPRD.TOKEN_PRODUCT_GROUP_ID = TPGR.ID " +
            "INNER JOIN C_D_ISSUER ISS ON TPGR.ISSUER_ID = ISS.ID " +
            "INNER JOIN C_D_INSTITUTION INST ON ISS.INSTITUTION_ID = INST.ID " +
            "WHERE PMDT.ID >= ? " +
            "AND PMDT.TRX_TYPE_ID in (2,3,16) " +
            "AND PMDT.REQ_PAN_ID is not null " +
            "UNION " +
            "SELECT PMDT.ID, PMDT.INTERFACE_ID, PMDT.TRX_TYPE_ID, PMDT.TRX_STATUS, " +
            "PMDT.SYSTEM_DATETIME, PMDT.REQ_DATETIME, PMDT.REQ_ORIG_ID, " +
            "PMDT.REQ_RN, PMDT.REQ_ISSUER_PIN_ID, PMDT.REQ_ISSUER_PUK_ID, PMDT.REQ_PUK_ID, " +
            "PMDT.REQ_PIN_EXPIRY_DATE, PMDT.REQ_PIN_ID,PMDT.REQ_PAN_ID, " +
            "PMDT.REQ_ISSUER_PAN_ALIAS, PMDT.REQ_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.REQ_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.REQ_TOKEN_APP_STATUS_ID, " +
            "PMDT.REQ_TOKEN_APP_SEQ_NUM, PMDT.REQ_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.REQ_PIN_DELIVERY_METHOD, PMDT.REQ_ADVICE_ALL_TOKEN_APP_FLAG, " +
            "PMDT.REQ_ADVICE_PIN_PUK_FLAG, PMDT.REQ_LANGUAGE_CODE, " +
            "PMDT.REQ_MAILING_CODE, PMDT.REQ_PRIORITY, PMDT.REQ_BRANCH_CODE, " +
            "PMDT.REQ_BRANCH_NAME, PMDT.REQ_NUMBER_OF_TOKENS, " +
            "PMDT.REQ_GENERATE_PAN_FLAG, PMDT.REQ_GENERATE_PIN_FLAG, " +
            "PMDT.REQ_GENERATE_PUK_FLAG, PMDT.REQ_ORDER_TYPE, " +
            "PMDT.REQ_FEEDBACK_REQUIRED_FLAG, PMDT.REQ_PREVIOUS_PAN_ID, " +
            "PMDT.REQ_PREVIOUS_PAN_SEQ_NUMBER, TO_CHAR(PMDT.REQ_PREVIOUS_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), " +
            "PMDT.RSP_RESPONSE_CODE, PMDT.RSP_ERROR_DESCR, PMDT.RSP_DATETIME, " +
            "PMDT.RSP_PIN_ID, PMDT.RSP_PUK_ID, PMDT.RSP_PAN_ID, PMDT.RSP_PAN_SEQ_NUMBER, " +
            "TO_CHAR(PMDT.RSP_PAN_EXPIRY_DATE, 'YYYY-MM-DD'), PMDT.RSP_ISSUER_TOKEN_PRODUCT_CODE, " +
            "PMDT.RSP_TOKEN_APP_SEQ_NUM, PMDT.RSP_SMS_STATUS, " +
            "NULL AS \"ID_1\", NULL AS \"NAME\",  " +
            "NULL AS \"ID_2\", NULL AS \"NAME_1\",  " +
            "NULL AS \"TOKEN_PRODUCT_ID\",  " +
            "NULL AS \"ISSUER_TOKEN_PRODUCT_CODE\", NULL AS \"NAME_2\", " +
            "NULL AS \"TOKEN_PRODUCT_GROUP_ID\",  " +
            "NULL AS \"NAME_3\", NULL AS \"GROUP_CODE\", " +
            "NULL AS \"PAN_DISPLAY\", " +
            "NULL AS \"COUNTRY_CODE\" " +
            "FROM PM_D_TRX_LOG PMDT " +
            "WHERE PMDT.ID >= ? " +
            "AND PMDT.TRX_TYPE_ID in (10) " +
//            "AND PMDT.REQ_PAN_ID is not null " +
            "ORDER BY 1";


    private Connection connection;
    public List <TransactionLogDataExtractRecord> records = new ArrayList<>();

    int rowCount = 0;

    private int recordCount = 0;

    private void setRecordCount(int count){
        this.recordCount = count;
    }

    public int getRecordCount(){
        return (this.recordCount);
    }

    public TransactionLogDataExtractDatabaseGatherer()
    {
//        connection = DatabaseConnection.getConnection();
    }

    public void gatherData()
    {
        try
        {
            connection = DatabaseConnection.getConnection();

            ExtractLogDataExtractRecord extractRecord = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TRANSACTIONLOGDATAEXTRACT);

            if(extractRecord.getIdFrom()==null || extractRecord.getIdFrom()=="null" || extractRecord.getIdFrom()==""){
                extractRecord.setIdFrom("1");
            }

            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, extractRecord.getIdFrom());
            preparedSelect.setString(2, extractRecord.getIdFrom());
            preparedSelect.setString(3, extractRecord.getIdFrom());
            preparedSelect.setString(4, extractRecord.getIdFrom());

            System.out.println("About to gather data for transactionlogdataextract with ID = " + extractRecord.getIdFrom() + " ...");

            ResultSet rs = preparedSelect.executeQuery();
            while (rs.next())
            {
                TransactionLogDataExtractRecord record = new TransactionLogDataExtractRecord();
                record.setId(rs.getString(1));
                record.setInterfaceId(rs.getString(2));
                record.setTrxTypeId(rs.getString(3));
                record.setTrxStatus(rs.getString(4));
                record.setSystemDateTime(AdminCommon.getStandardMilliseconds(rs.getString(5)));
                record.setReqDateTime(AdminCommon.getStandardMilliseconds(rs.getString(6)));
                record.setReqOrigId(rs.getString(7));
                record.setReqRn(rs.getString(8));
                record.setReqIssuerPinId(rs.getString(9));
                record.setReqIssuerPukId(rs.getString(10));
                record.setReqPukId(rs.getString(11));
                record.setReqPinExpiryDate(rs.getString(12));
                record.setReqPinId(rs.getString(13));
                record.setReqPanId(rs.getString(14));
                record.setReqIssuerPanAlias(rs.getString(15));
                record.setReqPanSeqNumber(rs.getString(16));
                record.setReqPanExpiryDate(rs.getString(17));
                record.setReqTokenAppStatusId(rs.getString(18));
                record.setReqTokenAppSeqNum(rs.getString(19));
                record.setReqIssuerTokenProductCode(rs.getString(20));
                record.setReqPinDeliveryMethod(rs.getString(21));
                record.setReqAdviceAllTokenAppFlag(rs.getString(22));
                record.setReqAdvicePinPukFlag(rs.getString(23));
                record.setReqLanguageCode(rs.getString(24));
                record.setReqMailingCode(rs.getString(25));
                record.setReqPriority(rs.getString(26));
                record.setReqBranchCode(rs.getString(27));
                record.setReqBranchName(rs.getString(28));
                record.setReqNumberofTokens(rs.getString(29));
                record.setReqGeneratePanFlag(rs.getString(30));
                record.setReqGeneratePinFlag(rs.getString(31));
                record.setReqGeneratePukFlag(rs.getString(32));
                record.setReqOrderType(rs.getString(33));
                record.setReqFeedbackRequiredFlag(rs.getString(34));
                record.setReqPreviousPanId(rs.getString(35));
                record.setReqPreviousPanSeqNumber(rs.getString(36));
                record.setReqPreviousPanExpiryDate(rs.getString(37));
                record.setRspResponseCode(rs.getString(38));
                record.setRspErrorDescr(rs.getString(39));
                record.setRspDateTime(AdminCommon.getStandardMilliseconds(rs.getString(40)));
                record.setRspPinId(rs.getString(41));
                record.setRspPukId(rs.getString(42));
                record.setRspPanId(rs.getString(43));
                record.setRspPanSeqNumber(rs.getString(44));
                record.setRspPanExpiryDate(rs.getString(45));
                record.setRspIssuerTokenProductCode(rs.getString(46));
                record.setRspTokenAppSeqNum(rs.getString(47));
                record.setRspSmsStatus(rs.getString(48));
                record.setExtInstitutionId(rs.getString(49));
                record.setExtInstitutionName(rs.getString(50));
                record.setExtIssuerId(rs.getString(51));
                record.setExtIssuerName(rs.getString(52));
                record.setExtTokenProductId(rs.getString(53));
                record.setExtIssuerTokenproductCode(rs.getString(54));
                record.setExtTokenProductName(rs.getString(55));
                record.setExtTokenProductGroupId(rs.getString(56));
                record.setExtTokenProductGroupName(rs.getString(57));
                record.setExtTokenProductGroupCode(rs.getString(58));
                record.setExtPanDisplay(rs.getString(59));
                record.setExtIssuerCountryCode(rs.getString(60));


                records.add(record);
                rowCount++;

            }
//            System.out.println("Data gathered for all transactionlogdataextract entries with ID = " + extractRecord.getIdFrom() + ": " + rowCount + " rows found");
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
        System.out.println("Print Records: TransactionLogDataExtractDatabaseGatherer");
        for (TransactionLogDataExtractRecord transactionLogDataExtractRecord : records)
        {
            System.out.println(transactionLogDataExtractRecord.toString());
        }
    }

}