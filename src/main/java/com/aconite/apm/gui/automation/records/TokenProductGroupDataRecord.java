package com.aconite.apm.gui.automation.records;

@SuppressWarnings("Annotation")
public class TokenProductGroupDataRecord
{
    private String parentInstitution;// for searches on data - not for comparison
    private String id;
    private String name;
    private String issuer;
    private String groupCode;
    private String testOutput;

    public TokenProductGroupDataRecord(){
        testOutput = "";
    }

    public String getParentInstitution()
    {
        return parentInstitution;
    }

    public void setParentInstitution(String parentInstitution)
    {
        this.parentInstitution = parentInstitution;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIssuer()
    {
        return issuer;
    }

    public void setIssuer(String issuer)
    {
        this.issuer = issuer;
    }

    public String getGroupCode()
    {
        return groupCode;
    }

    public void setGroupCode(String groupCode)
    {
        this.groupCode = groupCode;
    }

    public String getTestOutput() { return testOutput; }

    public void setTestOutput(String testOutput) { this.testOutput = this.testOutput + "\n" + testOutput; }



    @Override
    public String toString()
    {
        return "Record: [ID=" + getId() +
                ", INSTITUTION=" + getParentInstitution() +
                ", TOKENPRODUCTGROUPNAME=" + getName() +
                ", ISSUER=" + getIssuer() +
                ", GROUPCODE=" + getGroupCode() +
                "]";
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

        final TokenProductGroupDataRecord other = (TokenProductGroupDataRecord) obj;

        if (!this.getId().equals(other.getId()))
        {
            return false;
        }

        if (!this.getParentInstitution().equals(other.getParentInstitution()))
        {
            return false;
        }

        if (!this.getName().equals(other.getName()))
        {
            return false;
        }

        if (!this.getIssuer().equals(other.getIssuer()))
        {
            return false;
        }

        if (!this.getGroupCode().equals(other.getGroupCode()))
        {
            return false;
        }

        return true;
    }
}