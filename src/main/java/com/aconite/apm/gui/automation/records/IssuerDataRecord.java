package com.aconite.apm.gui.automation.records;

@SuppressWarnings("Annotation")
public class IssuerDataRecord
{

    private String id;
    private String issuerName;
    private String institutionName;
    private String country;
    private String testOutput;

    public IssuerDataRecord(){
        testOutput = "";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIssuerName()
    {
        return issuerName;
    }

    public void setIssuerName(String issuerName)
    {
        this.issuerName = issuerName;
    }

    public String getInstitutionName(){ return institutionName; }

    public void setInstitutionName(String institutionName){ this.institutionName = institutionName; }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }


    @Override
    public String toString()
    {
        return "Record: [ID=" + getId() + ", ISSUERNAME=" + getIssuerName() +
                ", INSTITUTIONNAME=" + getInstitutionName() + ", COUNTRY=" + getCountry() + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        final IssuerDataRecord other = (IssuerDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getIssuerName().equals(other.getIssuerName()))
        {
            return false;
        }

        if (!this.getInstitutionName().equals(other.getInstitutionName()))
        {
            return false;
        }

        if (!this.getCountry().equals(other.getCountry()))
        {
            return false;
        }

        return true;
    }
}