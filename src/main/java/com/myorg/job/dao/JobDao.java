package com.myorg.job.dao;

import java.util.List;

import com.myorg.job.model.Job;
import com.myorg.job.model.User;

/**
 * Provides data access for Job operations
 *
 * @author vg
 * @since Feb 2021
 */
public interface JobDao {

    public List<Job> getAll();

    Job getById(int id);

    int add(Job job);

    boolean update(Job job);

    boolean delete(int id);
}
