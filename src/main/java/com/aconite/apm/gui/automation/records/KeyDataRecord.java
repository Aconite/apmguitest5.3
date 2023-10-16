package com.aconite.apm.gui.automation.records;

public class KeyDataRecord {

    private String dbId;
    private String status;
    private String keyType;
    private String index;
    private String decimalisationTable;
    private String decimalisationTableEncryption;
    private String validationPattern;
    private String name;
    private String hsmType;
    private String mkCheckValue;
    private String sciKeytype;
    private String keyAlgorithm;
    private String blockSize;
    private String keyValue;
    private String keyCheckValue;
    private String expiryDate;
    private String extension;
    private String testOutput;

    public KeyDataRecord(){
        testOutput = "";
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDecimalisationTable() {
        return decimalisationTable;
    }

    public void setDecimalisationTable(String decimalisationTable) {
        this.decimalisationTable = decimalisationTable;
    }

    public String getDecimalisationTableEncryption() {
        return decimalisationTableEncryption;
    }

    public void setDecimalisationTableEncryption(String decimalisationTableEncryption) {
        this.decimalisationTableEncryption = decimalisationTableEncryption;
    }

    public String getValidationPattern() {
        return validationPattern;
    }

    public void setValidationPattern(String validationPattern) {
        this.validationPattern = validationPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsmType() {
        return hsmType;
    }

    public void setHsmType(String hsmType) {
        this.hsmType = hsmType;
    }

    public String getMkCheckValue() {
        return mkCheckValue;
    }

    public void setMkCheckValue(String mkCheckValue) {
        this.mkCheckValue = mkCheckValue;
    }

    public String getSciKeytype() {
        return sciKeytype;
    }

    public void setSciKeytype(String sciKeytype) {
        this.sciKeytype = sciKeytype;
    }

    public String getKeyAlgorithm() {
        return keyAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public String getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(String blockSize) {
        this.blockSize = blockSize;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyCheckValue() {
        return keyCheckValue;
    }

    public void setKeyCheckValue(String keyCheckValue) {
        this.keyCheckValue = keyCheckValue;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString() {
        return "KeyDataRecord{" +
                "DBID='" + dbId + '\'' +
                ", STATUS='" + status + '\'' +
                ", KEYTYPE='" + keyType + '\'' +
                ", INDEX='" + index + '\'' +
                ", DECIMALISATIONTABLE='" + decimalisationTable + '\'' +
                ", DECIMALISATIONTABLEENCRYPTION='" + decimalisationTableEncryption + '\'' +
                ", VALIDATIONPATTERN='" + validationPattern + '\'' +
                ", NAME='" + name + '\'' +
                ", HSMTYPE='" + hsmType + '\'' +
                ", MKCHECKVALUE='" + mkCheckValue + '\'' +
                ", SCIKEYTYPE='" + sciKeytype + '\'' +
                ", KEYALGORITHM='" + keyAlgorithm + '\'' +
                ", BLOCKSIZE='" + blockSize + '\'' +
                ", KEYVALUE='" + keyValue + '\'' +
                ", KEYCHECKVALUE='" + keyCheckValue + '\'' +
                ", EXPIRYDATE='" + expiryDate + '\'' +
                ", EXTENSION='" + extension + '\'' +
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

        final KeyDataRecord other = (KeyDataRecord) obj;

        if (!this.getDbId().equals(other.getDbId())) {
            return false;
        }

        if (!this.getStatus().equals(other.getStatus())) {
            return false;
        }

        if (!this.getKeyType().equals(other.getKeyType())) {
            return false;
        }

        if(this.getIndex()==null || other.getIndex()==null) {
            if(!(this.getIndex()==null && other.getIndex()==null)) {
                return false;
            }
        }
        else if (!this.getIndex().equals(other.getIndex())) {
            return false;
        }

        if(this.getDecimalisationTable()==null || other.getDecimalisationTable()==null) {
            if(!(this.getDecimalisationTable()==null && other.getDecimalisationTable()==null)) {
                return false;
            }
        }
        else if (!this.getDecimalisationTable().equals(other.getDecimalisationTable())) {
            return false;
        }


        if (!this.getDecimalisationTableEncryption().equals(other.getDecimalisationTableEncryption())) {
            return false;
        }

        if(this.getValidationPattern()==null || other.getValidationPattern()==null) {
            if(!(this.getValidationPattern()==null && other.getValidationPattern()==null)) {
                return false;
            }
        }
        else if (!this.getValidationPattern().equals(other.getValidationPattern())) {
            return false;
        }

        if (!this.getName().equals(other.getName())) {
            return false;
        }

        if (!this.getHsmType().equals(other.getHsmType())) {
            return false;
        }

        if (!this.getMkCheckValue().equals(other.getMkCheckValue())) {
            return false;
        }

        if(this.getSciKeytype()==null || other.getSciKeytype()==null) {
            if(!(this.getSciKeytype()==null && other.getSciKeytype()==null)) {
                return false;
            }
        }
        else if (!this.getSciKeytype().equals(other.getSciKeytype())) {
            return false;
        }

        if (!this.getKeyAlgorithm().equals(other.getKeyAlgorithm())) {
            return false;
        }

        if (!this.getBlockSize().equals(other.getBlockSize())) {
            return false;
        }

        if (!this.getKeyValue().equals(other.getKeyValue())) {
            return false;
        }

        if(this.getKeyCheckValue()==null || other.getKeyCheckValue()==null) {
            if(!(this.getKeyCheckValue()==null && other.getKeyCheckValue()==null)) {
                return false;
            }
        }
        else if (!this.getKeyCheckValue().equals(other.getKeyCheckValue())) {
            return false;
        }

//        if (!this.getExpiryDate().equals(other.getExpiryDate())) {
//            return false;
//        }

        if(this.getExpiryDate()==null || other.getExpiryDate()==null) {
            if(!(this.getExpiryDate()==null && other.getExpiryDate()==null)) {
                return false;
            }
        }
        else if (!this.getExpiryDate().equals(other.getExpiryDate())) {
            return false;
        }

        if(this.getExtension()==null || other.getExtension()==null) {
            if(!(this.getExtension()==null && other.getExtension()==null)) {
                return false;
            }
        }
        else if (!this.getExtension().equals(other.getExtension())) {
            return false;
        }

        return true;
    }
}
