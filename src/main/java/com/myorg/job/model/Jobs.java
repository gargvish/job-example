package com.myorg.job.model;

import java.util.List;

/**
 * Provides a list of jobs
 *
 * @author vg
 * @since Feb 2021
 */
public class Jobs<T extends Job> {

    List<T> jobList;

    public List<T> getJobList() {
        return this.jobList;
    }

    public void setJobList(List<T> jobList) {
        this.jobList = jobList;
    }

}
