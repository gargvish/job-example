package com.myorg.job.dao;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.myorg.job.model.Bid;
import com.myorg.job.model.JobBidResult;

/**
 * In Memory data access from market place operations
 *
 * @author vg
 * @since Feb 2021
 */
public class InMemoryPlaceBidDao
    implements PlaceBidDao {

    private static final Map<Integer, JobBidResult> JOB_BID_RESULTS = new LinkedHashMap();

    private final JobBidResult balance = new JobBidResult();

    @Override
    public JobBidResult getById(int id) {
        return JOB_BID_RESULTS.get(id);
    }

    @Override
    public JobBidResult placeBid(Bid bid) {
        JobBidResult jobResult = getById(bid.getJob().getJobId());
        if (Instant.now().compareTo(bid.getJob().getExpirationDate().toInstant()) > 0) {
            JobBidResult failedJobResult = new JobBidResult();
            failedJobResult.setSuccess(false);
            return failedJobResult;
        }
        Duration res = Duration.between(Instant.now(), bid.getJob().getExpirationDate().toInstant());
        if (jobResult == null) {
            jobResult = new JobBidResult();
            jobResult.setBid(bid);
            jobResult.setBidCount(1);
            jobResult.setTimeRemaining(res.getSeconds());
            jobResult.setSuccess(true);
        }
        else if (jobResult.getBid().getAmount() > bid.getAmount()) {
            jobResult.setBid(bid);
            jobResult.setBidCount(jobResult.getBidCount() + 1);
            jobResult.setTimeRemaining(res.getSeconds());
            jobResult.setSuccess(true);
        }
        JOB_BID_RESULTS.put(bid.getJob().getJobId(), jobResult);
        return jobResult;
    }

    @Override
    public List<JobBidResult> getMostActive() {
        final List<JobBidResult> results = new ArrayList<>(JOB_BID_RESULTS.values());
        results.sort(Comparator.comparingInt(JobBidResult::getBidCount));
        return results.subList(0, Math.min(results.size(),10));
    }
}
