package com.aconite.apm.gui.automation.records;

public class CountryDataRecord {

    private String dbId;
    private String countryName;
    private String countryCode;
    private String testOutput;

    public CountryDataRecord(){
        testOutput = "";
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }

    @Override
    public String toString()
    {
        return "Record: [ID=" + getDbId() +
                ", COUNTRYNAME=" + getCountryName() +
                ", COUNTRYCODE=" + getCountryCode() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final CountryDataRecord other = (CountryDataRecord) obj;

        if (!this.getDbId().equals(other.getDbId())) {
            return false;
        }

        if (!this.getCountryCode().equals(other.getCountryCode())) {
            return false;
        }

        if (!this.getCountryName().equals(other.getCountryName())) {
            return false;
        }

        return true;
    }


}
