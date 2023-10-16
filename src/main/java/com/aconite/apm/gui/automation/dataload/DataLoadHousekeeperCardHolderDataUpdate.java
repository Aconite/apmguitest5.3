package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadHousekeeperCardHolderDataUpdate
 * This will call all the functions to put enough data in for a pin_mailer test
 *===================================================================================================================*/


import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinAdvice;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DataLoadHousekeeperCardHolderDataUpdate
{
    public static String runDataLoadHousekeeperCardHolderDataUpdate() throws Exception {

        StringBuilder out = new StringBuilder();
        String xmlrsp;
        String pinEncrypted;
        String pinVerificationValue;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;

        HashMap<Integer, HashMap<String, String>> responseData;

        /*===============================================================
         * TOWPD
         *===============================================================*/
        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);
            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                System.out.println("TRX_TYPE_ID 9 - TOWPD_OT3_Mail FAIL: " + responseData.get(i).get("ErrorDescription"));
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }


        /*===============================================================
         * TOWPD with PIN Advice
         *===============================================================*/
        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail_DM1 - PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMailHousekeeper(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                    out.append("\n");
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail_DM1 - PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMailHousekeeper(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                    out.append("\n");
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail_DM1 - PIN Advice By Pan - Seq Num - Exp Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_Mail_DM1 - PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /* PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMailHousekeeper(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_Mail_DM1 - PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /* PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMailHousekeeper(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT2_Mail_DM1 - PIN Advice By Pan - Seq Num - Exp Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*   PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_Mail_DM1 - PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMailHousekeeper(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_Mail_DM1 - PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMailHousekeeper(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT3_Mail_DM1 - PIN Advice By Pan - Seq Num - Exp Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail_DM1 - PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMailHousekeeper(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail_DM1 - PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMailHousekeeper(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail_DM1 - PIN Advice By Pan - Seq Num - Exp Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_Mail_DM1 - PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMailHousekeeper(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_Mail_DM1 - PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMailHousekeeper(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        /*--------------------------------------------------------------
         * TOWPD_OT6_Mail_DM1 - PIN Advice By Pan - Seq Num - Exp Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1_Housekeeper());
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {

                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

                out.append("\n");

                /*  PIN Advice ***/
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        updateSystemDateTime();

        return(out.toString());

    }

    private static void updateSystemDateTime() throws Exception{


        String updateSystemDateTimeSQL = "UPDATE PM_D_TRX_LOG SET " +
                                         "SYSTEM_DATETIME = to_timestamp((select CURRENT_TIMESTAMP from dual) - 8), " +
                                         "REQ_DATETIME = to_timestamp((select CURRENT_TIMESTAMP from dual) - 8), " +
                                         "RSP_DATETIME = to_timestamp((select CURRENT_TIMESTAMP from dual) - 8)" +
                                         "WHERE REQ_TH_MIDDLE_NAME = 'cardHolderDataUpdate'";

        Connection connection;
        connection = DatabaseConnection.getConnection();

        PreparedStatement preparedSelect = connection.prepareStatement(updateSystemDateTimeSQL);
        ResultSet rs = preparedSelect.executeQuery();
//        System.out.println(rs.toString());

        connection.close();

    }
}


