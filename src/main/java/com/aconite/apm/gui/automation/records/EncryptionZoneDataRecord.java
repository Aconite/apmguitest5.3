package com.aconite.apm.gui.automation.records;

public class EncryptionZoneDataRecord {

    private String dbId;
    private String name;
    private String panEncrypted;
    private String panEncryptionKey;
    private String pinBlockFormat;
    private String pinEncryptionKey;
    private String excludedPins;
    private String pukEncrypted;
    private String pukBlockFormat;
    private String pukEncryptionKey;
    private String pinSession;
    private String pukSession;
    private String rsaKey;
    private String testOutput;

    public EncryptionZoneDataRecord(){
        testOutput = "";
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanEncrypted() {
        return panEncrypted;
    }

    public void setPanEncrypted(String panEncrypted) {
        this.panEncrypted = panEncrypted;
    }

    public String getPanEncryptionKey() {
        return panEncryptionKey;
    }

    public void setPanEncryptionKey(String panEncryptionKey) {
        this.panEncryptionKey = panEncryptionKey;
    }

    public String getPinBlockFormat() {
        return pinBlockFormat;
    }

    public void setPinBlockFormat(String pinBlockFormat) {
        this.pinBlockFormat = pinBlockFormat;
    }

    public String getPinEncryptionKey() {
        return pinEncryptionKey;
    }

    public void setPinEncryptionKey(String pinEncryptionKey) {
        this.pinEncryptionKey = pinEncryptionKey;
    }

    public String getExcludedPins() {
        return excludedPins;
    }

    public void setExcludedPins(String excludedPins) {
        this.excludedPins = excludedPins;
    }

    public String getPukEncrypted() {
        return pukEncrypted;
    }

    public void setPukEncrypted(String pukEncrypted) {
        this.pukEncrypted = pukEncrypted;
    }

    public String getPukBlockFormat() {
        return pukBlockFormat;
    }

    public void setPukBlockFormat(String pukBlockFormat) {
        this.pukBlockFormat = pukBlockFormat;
    }

    public String getPukEncryptionKey() {
        return pukEncryptionKey;
    }

    public void setPukEncryptionKey(String pukEncryptionKey) {
        this.pukEncryptionKey = pukEncryptionKey;
    }

    public String getPinSession() {
        return pinSession;
    }

    public void setPinSession(String pinSession) {
        this.pinSession = pinSession;
    }

    public String getPukSession() {
        return pukSession;
    }

    public void setPukSession(String pukSession) {
        this.pukSession = pukSession;
    }

    public String getRsaKey() {
        return rsaKey;
    }

    public void setRsaKey(String rsaKey) {
        this.rsaKey = rsaKey;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString() {
        return "ENCRYPTIONZONEDATARECORD{" +
                "DBID='" + dbId + '\'' +
                ", NAME='" + name + '\'' +
                ", PANENCRYPTED='" + panEncrypted + '\'' +
                ", PANENCRYPTIONKEY='" + panEncryptionKey + '\'' +
                ", PINBLOCKFORMAT='" + pinBlockFormat + '\'' +
                ", PINENCRYPTIONKEY='" + pinEncryptionKey + '\'' +
                ", EXCLUDEDPINS='" + excludedPins + '\'' +
                ", PUKENCRYPTED='" + pukEncrypted + '\'' +
                ", PUKBLOCKFORMAT='" + pukBlockFormat + '\'' +
                ", PUKENCRYPTIONKEY='" + pukEncryptionKey + '\'' +
                ", PINSESSION='" + pinSession + '\'' +
                ", PUKSESSION='" + pukSession + '\'' +
                ", RSAKEY='" + rsaKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final EncryptionZoneDataRecord other = (EncryptionZoneDataRecord) obj;

        if (!this.getDbId().equals(other.getDbId())) {
            return false;
        }

        if (!this.getName().equals(other.getName())) {
            return false;
        }

        if (!this.getPanEncrypted().equals(other.getPanEncrypted())) {
            return false;
        }

        if (!this.getPanEncryptionKey().equals(other.getPanEncryptionKey())) {
            return false;
        }

        if (!this.getPinBlockFormat().equals(other.getPinBlockFormat())) {
            return false;
        }

        if (!this.getPinEncryptionKey().equals(other.getPinEncryptionKey())) {
            return false;
        }

        if (!this.getExcludedPins().equals(other.getExcludedPins())) {
            return false;
        }

        if (!this.getPukEncrypted().equals(other.getPukEncrypted())) {
            return false;
        }

        if (!this.getPukBlockFormat().equals(other.getPukBlockFormat())) {
            return false;
        }

        if (!this.getPukEncryptionKey().equals(other.getPukEncryptionKey())) {
            return false;
        }

        if (!this.getPinSession().equals(other.getPinSession())) {
            return false;
        }

        if (!this.getPukSession().equals(other.getPukSession())) {
            return false;
        }

        if (!this.getRsaKey().equals(other.getRsaKey())) {
            return false;
        }

        return true;

    }
}
