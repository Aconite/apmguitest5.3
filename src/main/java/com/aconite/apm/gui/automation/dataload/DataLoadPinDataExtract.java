package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadPinDataExtract
 * This will call all the functions to put enough data in for a pin_data_extract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.dataload.xmlrequests.*;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadPinDataExtract
{
    public static String runDataLoadPinDataExtract() {

        StringBuilder out = new StringBuilder();
        String xmlrsp;
        String pinId;
        String pinEncrypted;
        String pinKeyIndex;
        String pinVerificationValue;
        String pinPvvKeyIndex;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;
        String encryptedPinBlock;
        String requestId;
        String issuerPinId;
        String pin;

        HashMap<Integer, HashMap<String, String>> responseData;

        /*--------------------------------------------------------------
         * Pin Import
         *--------------------------------------------------------------*/
        requestId = DataLoadDataGatherer.getIdentifier(10);
        issuerPinId = DataLoadDataGatherer.getIdentifier(18);
        pin = "0D899B5EF21237AB";

        xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportSingle(requestId, issuerPinId, pin));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinId = responseData.get(i).get("PINID");
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE: PINID=");
                out.append(pinId);
            }
            else{
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportMultiple(3));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinId = responseData.get(i).get("PINID");
                out.append("TRX_TYPE_ID 1 - PIN IMPORT MULTIPLE: PINID=");
                out.append(pinId);
            }
            else{
                out.append("TRX_TYPE_ID 1 - PIN IMPORT MULTIPLE FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        pinId = "";


        /*--------------------------------------------------------------
         * Pin Verify
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanId(3));
//        System.out.println("dataloadPinVerifyByPanId: " + xmlrsp);
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN ID: PASS");
            }
            else{
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN ID FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanSeqNoExpiryDate(3));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN - PAN SEQ NUM - PAN EXP DATE: PASS");
            }
            else{
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanAlias(3));
        System.out.println(xmlrsp);
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN ALIAS: PASS");
            }
            else{
                out.append("TRX_TYPE_ID 2 - PIN VERIFY BY PAN ALIAS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        /*--------------------------------------------------------------
         * Pin Delete
         *--------------------------------------------------------------*/

        /* Pin Import */
        requestId = DataLoadDataGatherer.getIdentifier(10);
        issuerPinId = DataLoadDataGatherer.getIdentifier(18);
        pin = "0D899B5EF21237AB";

        xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportSingle(requestId, issuerPinId, pin));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinId = responseData.get(i).get("PINID");
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE: PINID=");
                out.append(pinId);
            }
            else{
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Delete By Pin Id */
        xmlrsp = WebRequests.dataload(DataLoadPinDelete.dataloadPinDeleteByPinId(pinId));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 3 - PIN DELETE BY PIN ID: PASS");
            }
            else{
                out.append("TRX_TYPE_ID 3 - PIN DELETE BY PIN ID FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }
        pinId = "";

        /* Pin Import */
        requestId = DataLoadDataGatherer.getIdentifier(10);
        issuerPinId = DataLoadDataGatherer.getIdentifier(18);
        pin = "0D899B5EF21237AB";

        xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportSingle(requestId, issuerPinId, pin));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinId = responseData.get(i).get("PINID");
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE: PINID=");
                out.append(pinId);
            }
            else{
                out.append("TRX_TYPE_ID 1 - PIN IMPORT SINGLE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Delete By Issuer Pin Id */
        issuerPinId = DataLoadDataGatherer.dataloadGetIssuerPinAliasByPinId(pinId);
//        System.out.println(issuerPinId);

        xmlrsp = WebRequests.dataload(DataLoadPinDelete.dataloadPinDeleteByIssuerPinId(issuerPinId));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 3 - PIN DELETE BY ISSUER PIN ID: PASS");
            }
            else{
                out.append("TRX_TYPE_ID 3 - PIN DELETE BY ISSUER PIN ID FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }
            out.append("\n");
        }

        /*--------------------------------------------------------------
         * Pin Export
         *--------------------------------------------------------------*/

        /* Pin Export By Pan Id */
        xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanId(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinEncrypted = responseData.get(i).get("PIN");
                pinKeyIndex = responseData.get(i).get("PINKeyIndex");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN ID: PIN=");
                out.append(pinEncrypted);
                out.append(", PINKEYINDEX=");
                out.append(pinKeyIndex);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN ID FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Export By Pan - SeqNo - Expiry Date */
        xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanSeqNoExpiryDate(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinEncrypted = responseData.get(i).get("PIN");
                pinKeyIndex = responseData.get(i).get("PINKeyIndex");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN - PAN SEQ NUM - PAN EXP DATE: PIN=");
                out.append(pinEncrypted);
                out.append(", PINKEYINDEX=");
                out.append(pinKeyIndex);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Export By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanAlias(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinEncrypted = responseData.get(i).get("PIN");
                pinKeyIndex = responseData.get(i).get("PINKeyIndex");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN ALIAS: PIN=");
                out.append(pinEncrypted);
                out.append(", PINKEYINDEX=");
                out.append(pinKeyIndex);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 18 - PIN EXPORT BY PAN ALIAS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /*--------------------------------------------------------------
         * Pin Update
         *--------------------------------------------------------------*/

        /* Pin Update By Pan Id */
        xmlrsp = WebRequests.dataload(DataLoadPinUpdate.dataloadPinUpdateByPanId(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN ID: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN ID FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Update By Pan - SeqNo - ExpiryDate */
        xmlrsp = WebRequests.dataload(DataLoadPinUpdate.dataloadPinUpdateByPanSeqNoExpiryDate(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN - PAN SEQ NUM - PAN EXP DATE: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        /* Pin Update By Pan Alias */
        xmlrsp = WebRequests.dataload(DataLoadPinUpdate.dataloadPinUpdateByPanAlias(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){
            if(responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN ALIAS: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX=");
                out.append(pinPvvKeyIndex);
            }
            else{
                out.append("TRX_TYPE_ID 21 - PIN UPDATE BY PAN ALIAS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /*--------------------------------------------------------------
         * Token Import
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){

            if(responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");

                out.append("TRX_TYPE_ID 7 - TOKEN IMPORT: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
            }
            else{
                out.append("TRX_TYPE_ID 7 - TOKEN IMPORT FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /*===============================================================
         * TOWPD - SMS
         *===============================================================*/

        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS(5));
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


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
         * TOWPD_OT2_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS(5));
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
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
         * TOWPD_OT3_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS(5));
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS FAIL: ");
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
         * TOWPD_OT4_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS(5));
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS FAIL: ");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS(5));
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

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS: PANID=");
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
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
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail FAIL: ");out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        /*--------------------------------------------------------------
         * VPP
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPAN());
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN: PASS");
            } else {
                out.append("TRX_TYPE_ID 17 - SET VPP PIN ID BY PAN FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPANId());
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PAN ID: PASS");
            } else {
                out.append("TRX_TYPE_ID 13 - SET VPP PIN ID BY PAN ID FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByIssuerPinId());
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID: PASS");
            } else {
                out.append("TRX_TYPE_ID 14 - SET VPP PIN ID BY ISSUER PIN ID FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPanAlias());
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS: PASS");
            } else {
                out.append("TRX_TYPE_ID 22 - SET VPP PIN ID BY PAN ALIAS FAIL: ");
                out.append(responseData.get(1).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPSetPIN());
//        System.out.println("xmlrsp = " + xmlrsp);
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                out.append("TRX_TYPE_ID 11 - VPP SET PIN: PASS");
            } else {
//                System.out.println("responseData = " + responseData);
                out.append("TRX_TYPE_ID 11 - VPP SET PIN FAIL: ");
                out.append(responseData.get(0).get("ErrorDescription"));
            }
            out.append("\n");
        }

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPGetPIN());
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++) {
            if (responseData.get(i).get("ResponseCode").equals("1")) {
                encryptedPinBlock = responseData.get(i).get("EncryptedPinBlock");
                out.append("TRX_TYPE_ID 12 - VPP GET PIN: PASS: ENCRYPTEDPINBLOCK=");
                out.append(encryptedPinBlock);

            } else {
                out.append("TRX_TYPE_ID 12 - VPP GET PIN FAIL: ");
                out.append(responseData.get(0).get("ErrorDescription"));
            }
            out.append("\n");

        }


        /*
         * Pin Change Rollback
         */


        xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanId(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN ID: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX");
                out.append(pinPvvKeyIndex);
            } else {
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN ID FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanSeqNoExpiryDate(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN - PAN SEQ NUM - PAN EXP DATE: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX");
                out.append(pinPvvKeyIndex);
            } else {
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN - PAN SEQ NUM - PAN EXP DATE FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }


        xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanAlias(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pinPvvKeyIndex = responseData.get(i).get("PINPVVKeyIndex");
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN ALIAS: PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PINPVVKEYINDEX");
                out.append(pinPvvKeyIndex);
            } else {
                out.append("TRX_TYPE_ID 20 - PIN CHANGE ROLLBACK BY PAN ALIAS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        return(out.toString());
    }
}


