package com.aconite.apm.gui.automation.records;



public class TokenApplicationProfileDataRecord {

    private String parentInstitution;
    private String parentIssuer;
    private String parentTokenProductGroupName;
    private String parentTokenProductName;
    private String parentTokenProductId;
    private String name;
    private String appSequenceNumber;
    private String status;
    private String defaultApp;
    private String pinRequired;
    private String pinLength;
    private String importEncryptionZone;
    private String exportEncryptionZone;
    private String returnInterface;
    private String independentTokenPins;
    private String pinMailerDelayHours;
    private String pinHeldBySeqNum;
    private String allowPinChange;
    private String allowOnlinePinChange;
    private String allowOnlinePinView;
    private String pukRequired;
    private String pukLength;
    private String allowPukChange;
    private String pukHeldBySeqNum;
    private String independentTokenPuks;
    private String pinVerificationMethod;
    private String pinVerificationKey;
    private String pinTries;
    private String verificationBackoff;
    private String backoffMultiplier;
    private String tokenImportPinDeliveryMethod;
    private String tokenOrderPinDeliveryMethod;
    private String forceTokenOrderPinDelivery;
    private String pinAdvicePinDeliveryMethod;
    private String forcePinAdvicePinDelivery;
    private String updatePinDeliveryMethod;
    private String vppPinDeliveryMethod;
    private String pinTemplate;
    private String secondaryPinTemplate;
    private String pukTemplate;
    private String passwordTemplate;
    private String messageTemplate;
    private String messageInterface;
    private String smsInterface;
    private String pinOverSMSDelayHours;
    private String smsPasswordDelayHours;
    private String smsSender;
    private String smsValidityHours;
    private String smsPasswordExpiryHours;
    private String smsClass;
    private String testOutput;

    public TokenApplicationProfileDataRecord(){
        testOutput = "";
    }

    public String getParentInstitution() {
        return parentInstitution;
    }

    public void setParentInstitution(String parentInstitution) {
        this.parentInstitution = parentInstitution;
    }

    public String getParentIssuer() {
        return parentIssuer;
    }

    public void setParentIssuer(String parentIssuer) {
        this.parentIssuer = parentIssuer;
    }

    public String getParentTokenProductGroupName() {
        return parentTokenProductGroupName;
    }

    public void setParentTokenProductGroupName(String parentTokenProductGroupName) {
        this.parentTokenProductGroupName = parentTokenProductGroupName;
    }

    public String getParentTokenProductName() {
        return parentTokenProductName;
    }

    public void setParentTokenProductName(String parentTokenProductName) {
        this.parentTokenProductName = parentTokenProductName;
    }

    public String getParentTokenProductId() {
        return parentTokenProductId;
    }

    public void setParentTokenProductId(String parentTokenProductId) {
        this.parentTokenProductId = parentTokenProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppSequenceNumber() {
        return appSequenceNumber;
    }

    public void setAppSequenceNumber(String appSequenceNumber) {
        this.appSequenceNumber = appSequenceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultApp() {
        return defaultApp;
    }

    public void setDefaultApp(String defaultApp) {
        this.defaultApp = defaultApp;
    }

    public String getPinRequired() {
        return pinRequired;
    }

    public void setPinRequired(String pinRequired) {
        this.pinRequired = pinRequired;
    }

    public String getPinLength() {
        return pinLength;
    }

    public void setPinLength(String pinLength) {
        this.pinLength = pinLength;
    }

    public String getImportEncryptionZone() {
        return importEncryptionZone;
    }

    public void setImportEncryptionZone(String importEncryptionZone) {
        this.importEncryptionZone = importEncryptionZone;
    }

    public String getExportEncryptionZone() {
        return exportEncryptionZone;
    }

    public void setExportEncryptionZone(String exportEncryptionZone) {
        this.exportEncryptionZone = exportEncryptionZone;
    }

    public String getReturnInterface() {
        return returnInterface;
    }

    public void setReturnInterface(String returnInterface) {
        this.returnInterface = returnInterface;
    }

    public String getPinMailerDelayHours() {
        return pinMailerDelayHours;
    }

    public void setPinMailerDelayHours(String pinMailerDelayHours) {
        this.pinMailerDelayHours = pinMailerDelayHours;
    }

    public String getPinHeldBySeqNum() {
        return pinHeldBySeqNum;
    }

    public void setPinHeldBySeqNum(String pinHeldBySeqNum) {
        this.pinHeldBySeqNum = pinHeldBySeqNum;
    }

    public String getAllowPinChange() {
        return allowPinChange;
    }

    public void setAllowPinChange(String allowPinChange) {
        this.allowPinChange = allowPinChange;
    }

    public String getAllowOnlinePinChange() {
        return allowOnlinePinChange;
    }

    public void setAllowOnlinePinChange(String allowOnlinePinChange) {
        this.allowOnlinePinChange = allowOnlinePinChange;
    }

    public String getAllowOnlinePinView() {
        return allowOnlinePinView;
    }

    public void setAllowOnlinePinView(String allowOnlinePinView) {
        this.allowOnlinePinView = allowOnlinePinView;
    }

    public String getPukRequired() {
        return pukRequired;
    }

    public void setPukRequired(String pukRequired) {
        this.pukRequired = pukRequired;
    }

    public String getPukLength() {
        return pukLength;
    }

    public void setPukLength(String pukLength) {
        this.pukLength = pukLength;
    }

    public String getAllowPukChange() {
        return allowPukChange;
    }

    public void setAllowPukChange(String allowPukChange) {
        this.allowPukChange = allowPukChange;
    }

    public String getPukHeldBySeqNum() {
        return pukHeldBySeqNum;
    }

    public void setPukHeldBySeqNum(String pukHeldBySeqNum) {
        this.pukHeldBySeqNum = pukHeldBySeqNum;
    }

    public String getPinVerificationMethod() {
        return pinVerificationMethod;
    }

    public void setPinVerificationMethod(String pinVerificationMethod) {
        this.pinVerificationMethod = pinVerificationMethod;
    }

    public String getPinVerificationKey() {
        return pinVerificationKey;
    }

    public void setPinVerificationKey(String pinVerificationKey) {
        this.pinVerificationKey = pinVerificationKey;
    }

    public String getPinTries() {
        return pinTries;
    }

    public void setPinTries(String pinTries) {
        this.pinTries = pinTries;
    }

    public String getVerificationBackoff() {
        return verificationBackoff;
    }

    public void setVerificationBackoff(String verificationBackoff) {
        this.verificationBackoff = verificationBackoff;
    }

    public String getBackoffMultiplier() {
        return backoffMultiplier;
    }

    public void setBackoffMultiplier(String backoffMultiplier) {
        this.backoffMultiplier = backoffMultiplier;
    }

    public String getTokenImportPinDeliveryMethod() {
        return tokenImportPinDeliveryMethod;
    }

    public void setTokenImportPinDeliveryMethod(String tokenImportPinDeliveryMethod) {
        this.tokenImportPinDeliveryMethod = tokenImportPinDeliveryMethod;
    }

    public String getTokenOrderPinDeliveryMethod() {
        return tokenOrderPinDeliveryMethod;
    }

    public void setTokenOrderPinDeliveryMethod(String tokenOrderPinDeliveryMethod) {
        this.tokenOrderPinDeliveryMethod = tokenOrderPinDeliveryMethod;
    }

    public String getForceTokenOrderPinDelivery() {
        return forceTokenOrderPinDelivery;
    }

    public void setForceTokenOrderPinDelivery(String forceTokenOrderPinDelivery) {
        this.forceTokenOrderPinDelivery = forceTokenOrderPinDelivery;
    }

    public String getPinAdvicePinDeliveryMethod() {
        return pinAdvicePinDeliveryMethod;
    }

    public void setPinAdvicePinDeliveryMethod(String pinAdvicePinDeliveryMethod) {
        this.pinAdvicePinDeliveryMethod = pinAdvicePinDeliveryMethod;
    }

    public String getForcePinAdvicePinDelivery() {
        return forcePinAdvicePinDelivery;
    }

    public void setForcePinAdvicePinDelivery(String forcePinAdvicePinDelivery) {
        this.forcePinAdvicePinDelivery = forcePinAdvicePinDelivery;
    }

    public String getUpdatePinDeliveryMethod() {
        return updatePinDeliveryMethod;
    }

    public void setUpdatePinDeliveryMethod(String updatePinDeliveryMethod) {
        this.updatePinDeliveryMethod = updatePinDeliveryMethod;
    }

    public String getVppPinDeliveryMethod() {
        return vppPinDeliveryMethod;
    }

    public void setVppPinDeliveryMethod(String vppPinDeliveryMethod) {
        this.vppPinDeliveryMethod = vppPinDeliveryMethod;
    }

    public String getPinTemplate() {
        return pinTemplate;
    }

    public void setPinTemplate(String pinTemplate) {
        this.pinTemplate = pinTemplate;
    }

    public String getSecondaryPinTemplate() {
        return secondaryPinTemplate;
    }

    public void setSecondaryPinTemplate(String secondaryPinTemplate) {
        this.secondaryPinTemplate = secondaryPinTemplate;
    }

    public String getPukTemplate() {
        return pukTemplate;
    }

    public void setPukTemplate(String pukTemplate) {
        this.pukTemplate = pukTemplate;
    }

    public String getPasswordTemplate() {
        return passwordTemplate;
    }

    public void setPasswordTemplate(String passwordTemplate) {
        this.passwordTemplate = passwordTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageInterface() {
        return messageInterface;
    }

    public void setMessageInterface(String messageInterface) {
        this.messageInterface = messageInterface;
    }

    public String getSmsInterface() {
        return smsInterface;
    }

    public void setSmsInterface(String smsInterface) {
        this.smsInterface = smsInterface;
    }

    public String getPinOverSMSDelayHours() {
        return pinOverSMSDelayHours;
    }

    public void setPinOverSMSDelayHours(String pinOverSMSDelayHours) {
        this.pinOverSMSDelayHours = pinOverSMSDelayHours;
    }

    public String getSmsPasswordDelayHours() {
        return smsPasswordDelayHours;
    }

    public void setSmsPasswordDelayHours(String smsPasswordDelayHours) {
        this.smsPasswordDelayHours = smsPasswordDelayHours;
    }

    public String getSmsSender() {
        return smsSender;
    }

    public void setSmsSender(String smsSender) {
        this.smsSender = smsSender;
    }

    public String getSmsValidityHours() {
        return smsValidityHours;
    }

    public void setSmsValidityHours(String smsValidityHours) {
        this.smsValidityHours = smsValidityHours;
    }

    public String getSmsPasswordExpiryHours() {
        return smsPasswordExpiryHours;
    }

    public void setSmsPasswordExpiryHours(String smsPasswordExpiryHours) {
        this.smsPasswordExpiryHours = smsPasswordExpiryHours;
    }

    public String getSmsClass() {
        return smsClass;
    }

    public void setSmsClass(String smsClass) {
        this.smsClass = smsClass;
    }

    public String getIndependentTokenPins() {
        return independentTokenPins;
    }

    public void setIndependentTokenPins(String independentTokenPins) {
        this.independentTokenPins = independentTokenPins;
    }

    public String getIndependentTokenPuks() {
        return independentTokenPuks;
    }

    public void setIndependentTokenPuks(String independentTokenPuks) {
        this.independentTokenPuks = independentTokenPuks;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    public void copyTestOutput(String testOutput) { this.testOutput = testOutput; }

    @Override
    public String toString() {
        return "Record: [PARENTINSTITUTION=" + getParentInstitution() +
                ", PARENTISSUER=" + getParentIssuer() +
                ", PARENTTOKENPRODUCTNAME=" + getParentTokenProductName() +
                ", PARENTTOKENPRODUCTID=" + getParentTokenProductId() +
                ", PARENTTOKENPRODUCTGROUPNAME=" + getParentTokenProductGroupName() +
                ", NAME=" + getName() +
                ", APPSEQUENCENUMBER=" + getAppSequenceNumber() +
                ", STATUS=" + getStatus() +
                ", DEFAULTAPP=" + getDefaultApp() +
                ", PINREQUIRED=" + getPinRequired() +
                ", PINLENGTH=" + getPinLength() +
//                ", VALIDITYINDAYS=" + getValidityInDays() +
                ", IMPORTENCRYPTIONZONE=" + getImportEncryptionZone() +
                ", EXPORTENCRYPTIONZONE=" + getExportEncryptionZone() +
                ", RETURNINTERFACE=" + getReturnInterface() +
                ", PINMAILERDELAYHOURS=" + getPinMailerDelayHours() +
                ", PINHELDBYSEQNUM=" + getPinHeldBySeqNum() +
                ", ALLOWPINCHANGE=" + getAllowPinChange() +
                ", ALLOWONLINEPINCHANGE=" + getAllowOnlinePinChange() +
                ", ALLOWONLINEPINVIEW=" + getAllowOnlinePinView() +
                ", INDEPENDENTTOKENPINS=" + getIndependentTokenPins() +
                ", PUKREQUIRED=" + getPukRequired() +
                ", PUKLENGTH=" + getPukLength() +
                ", ALLOWPUKCHANGE=" + getAllowPukChange() +
                ", PUKHELDBYSEQNUM=" + getPukHeldBySeqNum() +
                ", INDEPENDENTTOKENPUKS=" + getIndependentTokenPuks() +
                ", PINVERIFICATIONMETHOD=" + getPinVerificationMethod() +
                ", PINVERIFICATIONKEY=" + getPinVerificationKey() +
                ", PINTRIES=" + getPinTries() +
                ", VERIFICATIONBACKOFF=" + getVerificationBackoff() +
                ", BACKOFFMULTIPLIER=" + getBackoffMultiplier() +
                ", TOKENIMPORTPINDELIVERYMETHOD=" + getTokenImportPinDeliveryMethod() +
//                ", TOKENIMPORTOUTPUTINTERFACE=" + getTokenImportOutputInterface() +
                ", TOKENORDERPINDELIVERYMETHOD=" + getTokenOrderPinDeliveryMethod() +
                ", FORCETOKENORDERPINDELIVERY=" + getForceTokenOrderPinDelivery() +
//                ", TOKENORDEROUTPUTINTERFACE=" + getTokenOrderOutputInterface() +
                ", PINADVICEPINDELIVERYMETHOD=" + getPinAdvicePinDeliveryMethod() +
                ", FORCEPINADVICEPINDELIVERY=" + getForcePinAdvicePinDelivery() +
//                ", PINADVICEOUTPUTINTERFACE=" + getPinAdviceOutputInterface() +
                ", UPDATEPINDELIVERYMETHOD=" + getUpdatePinDeliveryMethod() +
//                ", UPDATEOUTPUTINTERFACE=" + getUpdateOutputInterface() +
                ", VPPPINDELIVERYMETHOD=" + getVppPinDeliveryMethod() +
//                ", VPPOUTPUTINTERFACE=" + getVppOutputInterface() +
                ", MESSAGEINTERFACE=" + getMessageInterface() +
                ", MESSAGETEMPLATE=" + getMessageTemplate() +
                ", SMSINTERFACE=" + getSmsInterface() +
                ", PINTEMPLATE=" + getPinTemplate() +
                ", SECONDARYPINTEMPLATE=" + getSecondaryPinTemplate() +
                ", PUKTEMPLATE=" + getPukTemplate() +
                ", PINOVERSMSDELAYHOURS=" + getPinOverSMSDelayHours() +
                ", SMSPASSWORDDELAYHOURS=" + getSmsPasswordDelayHours() +
                ", SMSSENDER=" + getSmsSender() +
                ", SMSVALIDITYHOURS=" + getSmsValidityHours() +
                ", PASSWORDTEMPLATE=" + getPasswordTemplate() +
                ", SMSPASSWORDEXPIRYHOURS=" + getSmsPasswordExpiryHours() +
                ", SMSCLASS=" + getSmsClass() +
                ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final TokenApplicationProfileDataRecord other = (TokenApplicationProfileDataRecord) obj;

        if (!this.getParentInstitution().equals(other.getParentInstitution())) {
            return false;
        }

        if (!this.getParentIssuer().equals(other.getParentIssuer())) {
            return false;
        }

        if (!this.getParentTokenProductName().equals(other.getParentTokenProductName())) {
            return false;
        }

        if (!this.getParentTokenProductGroupName().equals(other.getParentTokenProductGroupName())) {
            return false;
        }

        if (!this.getParentTokenProductId().equals(other.getParentTokenProductId())) {
            return false;
        }

        if (!this.getName().equals(other.getName())) {
            return false;
        }

        if (!this.getAppSequenceNumber().equals(other.getAppSequenceNumber())) {
            return false;
        }

        if (!this.getStatus().equals(other.getStatus())) {
            return false;
        }

        if (!this.getDefaultApp().equals(other.getDefaultApp())) {
            return false;
        }

        if (!this.getPinRequired().equals(other.getPinRequired())) {
            return false;
        }

        if (!this.getPinLength().equals(other.getPinLength())) {
            return false;
        }

        if (!this.getImportEncryptionZone().equals(other.getImportEncryptionZone())) {
            return false;
        }

        if (!this.getExportEncryptionZone().equals(other.getExportEncryptionZone())) {
            return false;
        }

        if (this.getReturnInterface() != null && other.getReturnInterface() != null) {
            if (!this.getReturnInterface().equals(other.getReturnInterface())) {
                return false;
            }
        }

        if (!this.getPinMailerDelayHours().equals(other.getPinMailerDelayHours())) {
            return false;
        }

        if (this.getPinHeldBySeqNum() != null && other.getPinHeldBySeqNum() != null) {
            if (!this.getPinHeldBySeqNum().equals(other.getPinHeldBySeqNum())) {
                return false;
            }
        }

        if (!this.getAllowPinChange().equals(other.getAllowPinChange())) {
            return false;
        }

        if (!this.getAllowOnlinePinChange().equals(other.getAllowOnlinePinChange())) {
            return false;
        }

        if (!this.getAllowOnlinePinView().equals(other.getAllowOnlinePinView())) {
            return false;
        }

        if (!this.getIndependentTokenPins().equals(other.getIndependentTokenPins())) {
            return false;
        }

        if (!this.getPukRequired().equals(other.getPukRequired())) {
            return false;
        }

        if (this.getPukLength() != null && other.getPukLength() != null) {
            if (!this.getPukLength().equals(other.getPukLength())) {
                return false;
            }
        }

        if (!this.getAllowPukChange().equals(other.getAllowPukChange())) {
            return false;
        }

        if (this.getPukHeldBySeqNum() != null && other.getPukHeldBySeqNum() != null) {
            if (!this.getPukHeldBySeqNum().equals(other.getPukHeldBySeqNum())) {
                return false;
            }
        }

        if (!this.getIndependentTokenPuks().equals(other.getIndependentTokenPuks())) {
            return false;
        }

        if (this.getPinVerificationMethod() != null && other.getPinVerificationMethod() != null) {
            if (!this.getPinVerificationMethod().equals(other.getPinVerificationMethod())) {
                return false;
            }
        }

        if (!this.getPinVerificationKey().equals(other.getPinVerificationKey())) {
            return false;
        }

        if (this.getPinTries() != null && other.getPinTries() != null) {
            if (!this.getPinTries().equals(other.getPinTries())) {
                return false;
            }
        }
        if (this.getVerificationBackoff() != null && other.getVerificationBackoff() != null) {
            if (!this.getVerificationBackoff().equals(other.getVerificationBackoff())) {
                return false;
            }
        }

        if (this.getBackoffMultiplier() != null && other.getBackoffMultiplier() != null) {
            if (!this.getBackoffMultiplier().equals(other.getBackoffMultiplier())) {
                return false;
            }
        }

        if (!this.getTokenImportPinDeliveryMethod().equals(other.getTokenImportPinDeliveryMethod())) {
            return false;
        }

        if (!this.getTokenOrderPinDeliveryMethod().equals(other.getTokenOrderPinDeliveryMethod())) {
            return false;
        }

        if (!this.getForceTokenOrderPinDelivery().equals(other.getForceTokenOrderPinDelivery())) {
            return false;
        }

        if (!this.getPinAdvicePinDeliveryMethod().equals(other.getPinAdvicePinDeliveryMethod())) {
            return false;
        }

        if (!this.getForcePinAdvicePinDelivery().equals(other.getForcePinAdvicePinDelivery())) {
            return false;
        }

        if (!this.getUpdatePinDeliveryMethod().equals(other.getUpdatePinDeliveryMethod())) {
            return false;
        }

        if (!this.getVppPinDeliveryMethod().equals(other.getVppPinDeliveryMethod())) {
            return false;
        }

        if (this.getMessageInterface() != null && other.getMessageInterface() != null) {
            if (!this.getMessageInterface().equals(other.getMessageInterface())) {
                return false;
            }
        }

        if (this.getMessageTemplate() != null && other.getMessageTemplate() != null) {
            if (!this.getMessageTemplate().equals(other.getMessageTemplate())) {
                return false;
            }
        }

        if (this.getSmsInterface() != null && other.getSmsInterface() != null) {
            if (!this.getSmsInterface().equals(other.getSmsInterface())) {
                return false;
            }
        }

        if (!this.getPinTemplate().equals(other.getPinTemplate())) {
            return false;
        }

        if (this.getSecondaryPinTemplate() != null && other.getSecondaryPinTemplate() != null) {
            if (!this.getSecondaryPinTemplate().equals(other.getSecondaryPinTemplate())) {
                return false;
            }
        }

        if (!this.getPukTemplate().equals(other.getPukTemplate())) {
            return false;
        }

        if (!this.getPinOverSMSDelayHours().equals(other.getPinOverSMSDelayHours())) {
            return false;
        }

        if (!this.getSmsPasswordDelayHours().equals(other.getSmsPasswordDelayHours())) {
            return false;
        }

        if (!this.getSmsSender().equals(other.getSmsSender())) {
            return false;
        }

        if (!this.getSmsValidityHours().equals(other.getSmsValidityHours())) {
            return false;
        }
        if (!this.getPasswordTemplate().equals(other.getPasswordTemplate())) {
            return false;
        }

        if (!this.getSmsPasswordExpiryHours().equals(other.getSmsPasswordExpiryHours())) {
            return false;
        }

        if (!this.getSmsClass().equals(other.getSmsClass())) {
            return false;
        }

        return true;

    }

}
