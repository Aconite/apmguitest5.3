package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadPinMailer
 * This will call all the functions to put enough data in for a pin_mailer test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinAdvice;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadPinMailer
{
    public static String runDataLoadPinMailer() throws Exception {

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
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail(5));
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
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail(5));
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
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail(5));
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail(5));
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
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail(5));
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


        /*--------------------------------------------------------------
         * TOWPD_OT1_DM6
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_DM6: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_DM6: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_DM6: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_DM6: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail_DM1
         * PIN Advice By Pan Id
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1(5));
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
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: FAIL: ");
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
         * TOWPD_OT1_Mail_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1(5));
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
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM1 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail_DM1(5));
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
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: FAIL: ");
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
         * TOWPD_OT2_Mail_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1(5));
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
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
         * TOWPD_OT2_Mail_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1(5));
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



                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
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
         * TOWPD_OT2_Mail_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail_DM1(5));
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



                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
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
         * TOWPD_OT3_Mail_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
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
         * TOWPD_OT3_Mail_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1(1));
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
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
         * TOWPD_OT3_Mail_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail_DM1(1));
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
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
         * TOWPD_OT4_Mail_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1(1));
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
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
         * TOWPD_OT4_Mail_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
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
         * TOWPD_OT4_Mail_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
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
         * TOWPD_OT6_Mail_DM1
         * PIN Advice By Pan ID
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
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
         * TOWPD_OT6_Mail_DM1
         * PIN Advice By Pan Alias
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
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
         * TOWPD_OT6_Mail_DM1
         * PIN Advice By Pan - Seq Num - Expiry Date
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail_DM1(1));
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
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

        /*--------------------------------------------------------------
         * TOWPD OT 1 - Delivery Method 6 - PIN Advice
         *--------------------------------------------------------------*/

        /* TOWPD_OT1_DM6 - PIN Advice By Pan ID */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6: PANID=");
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }



        /* TOWPD_OT1_DM6 - PIN Advice By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6: PANID=");
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - IN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL: ");
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /* TOWPD_OT1_DM6 - PIN Advice By Pan */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_DM6());
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_DM6: PANID=");
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*
         * TOWPD OT 2 - Delivery Method 6 - PIN Advice
         */

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT2_DM6 - PIN Advice By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ALIAS FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT2_DM6 - PIN Advice By Pan */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*
         * TOWPD_OT3_DM6 - Delivery Method 6 - PIN Advice
         */

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT3_DM6 - PIN Advice By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT3_DM6 - PIN Advice By Pan */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*
         * TOWPD_OT4_DM6 - Delivery Method 6 - PIN Advice
         */

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT4_DM6 - PIN Advice By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_DM6());
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

                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT4_DM6 - PIN Advice By Pan */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_DM6());
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*
         * TOWPD OT 6 - Delivery Method 1 - PIN Advice
         */

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_DM6());
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN ID FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT6_DM6 - PIN Advice By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_DM6());
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(issuerPanAlias));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY ISSUER PAN ALIAS FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* TOWPD_OT6_DM6 - PIN Advice By Pan */
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_DM6());
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


                /* PIN Advice */
                xmlrsp = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panClear, panSeqNum, panExpDate));
                responseData = WebRequests.getResponseData(xmlrsp);
                for (int j = 0; j < responseData.size(); j++ ){

                    out.append("\n");

                    if (responseData.get(j).get("ResponseCode").equals("1")) {
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
                    }
                    else{
                        out.append("TRX_TYPE_ID 5 - PIN ADVICE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL:" );
                        out.append(responseData.get(j).get("ErrorDescription"));
                    }
                }

            }
            else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_DM6 FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        return(out.toString());
    }
}


