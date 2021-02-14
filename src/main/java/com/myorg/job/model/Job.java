package com.myorg.job.model;

import java.util.Date;

/**
 * Models the Job Post
 *
 * @author vg
 * @since Feb 2021
 */
public class Job {

    private int jobId;

    private String jobDescription;

    private User user;

    private Date expirationDate;

    public int getJobId() {
        return this.jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Job [jobId=" + this.jobId + ", jobDesc=" + this.jobDescription + ", startDate "
                   + this.expirationDate + ", user" + this.user + "]";
    }
}
