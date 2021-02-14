package com.myorg.job.dao;

import com.myorg.job.model.User;

import java.util.List;

/**
 * Provides data access for User operations
 *
 * @author vg
 * @since Feb 2021
 */
public interface UserDao {

    List<User> getAll();

    User getById(int id);

    int add(User hello);

    boolean update(User hello);

    boolean delete(int id);
}
