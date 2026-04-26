package model;

public class JobTitle {
    private int jobTitleId;
    private String jobTitleName;

    public JobTitle(int jobTitleId, String jobTitleName) {
        this.jobTitleId = jobTitleId;
        this.jobTitleName = jobTitleName;
    }

    public JobTitle(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public int getJobTitleid() {
        return jobTitleId;
    }

    public String getJobTitleName()
    {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }
}