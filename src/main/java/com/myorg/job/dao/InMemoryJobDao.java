package com.myorg.job.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.myorg.job.model.Job;
import com.myorg.job.model.User;

/**
 * In Memory data access for user operations
 *
 * @author vg
 * @since Feb 2021
 */
public class InMemoryJobDao
    implements JobDao {

    private static final Map<Integer, Job> JOBS = new LinkedHashMap();

    private static final SequenceIdProvider ID_PROVIDER = new SequenceIdProvider();

    @Override
    public List<Job> getAll() {
        return new ArrayList<>(JOBS.values());
    }

    @Override
    public Job getById(int id) {
        return JOBS.get(id);
    }

    @Override
    public int add(Job job) {
        final int jobId = ID_PROVIDER.nextId();
        final Job newJob = new Job();
        newJob.setJobId(jobId);
        newJob.setUser(job.getUser());
        newJob.setJobDescription(job.getJobDescription());
        newJob.setExpirationDate(job.getExpirationDate());
        JOBS.put(jobId, newJob);
        return jobId;
    }

    @Override
    public boolean update(Job job) {
        final Job job1 = getById(job.getJobId());
        if (job1 != null) {
            job1.setJobDescription(job.getJobDescription());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        final Job job = getById(id);
        if (job != null) {
            JOBS.remove(job);
            return true;
        }
        return false;
    }

    /**
     *
     */
    interface IdProvider {

        int nextId();
    }

    /**
     *
     */
    private static final class SequenceIdProvider
        implements IdProvider {

        private final AtomicInteger sequence = new AtomicInteger();

        @Override
        public int nextId() {
            return sequence.incrementAndGet();
        }
    }

}
