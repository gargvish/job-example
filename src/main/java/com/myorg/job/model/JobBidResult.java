package com.myorg.job.model;

/**
 * Models the Job Bid Result
 *
 * @author vg
 * @since Feb 2021
 */
public class JobBidResult {

    boolean success;

    Bid bid;

    int bidCount;

    long timeRemaining;

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(final int bidCount) {
        this.bidCount = bidCount;
    }

    public Bid getBid() {
        return this.bid;
    }

    public void setBid(final Bid bid) {
        this.bid = bid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void adjust(Bid bid) {
        this.bidCount++;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(final long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    @Override
    public String toString() {
        return "JobBidResult [jobId=" + this.bid.job.getJobId() + ", bidCount=" + this.bidCount + ", timeRemaining "
                   + this.timeRemaining + ", user" + this.bid.user.getUserId() + "]";
    }

}
