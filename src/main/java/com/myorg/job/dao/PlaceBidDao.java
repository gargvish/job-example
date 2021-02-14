package com.myorg.job.dao;

import java.util.List;

import com.myorg.job.model.Bid;
import com.myorg.job.model.JobBidResult;

/**
 * Provides data access for PlaceBid operations
 *
 * @author vg
 * @since Feb 2021
 */
public interface PlaceBidDao {

    JobBidResult getById(int id);

    JobBidResult placeBid(Bid bid);

    List<JobBidResult> getMostActive();
}
