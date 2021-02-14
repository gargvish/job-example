package com.myorg.job.model;

/**
 * Models the Market Place Bid
 *
 *
 * @author vg
 * @since Feb 2021
 */
public class Bid {

    Job job;
    User user;
    int amount;

    public Job getJob() {
        return job;
    }

    public void setJob(final Job job) {
        this.job = job;
    }

    public User getUser() {
        return this.user;
    }

    public int getAmount() {
        return amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid [ user: " + this.user + " amount: " + this.amount + " ]" + " job " + this.job;
    }
}
