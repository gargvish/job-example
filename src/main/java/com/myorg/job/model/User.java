package com.myorg.job.model;

/**
 * Models the MarketPlace User
 *
 * @author vg
 * @since Feb 2021
 */
public class User {

    private int userId;

    private String userEmail;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User [userId=" + this.userId + ", userEmail=" + this.userEmail + "]";
    }
}
