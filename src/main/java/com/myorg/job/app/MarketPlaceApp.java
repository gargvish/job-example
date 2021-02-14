package com.myorg.job.app;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.myorg.job.dao.InMemoryJobDao;
import com.myorg.job.dao.InMemoryPlaceBidDao;
import com.myorg.job.dao.InMemoryUserDao;
import com.myorg.job.dao.JobDao;
import com.myorg.job.dao.PlaceBidDao;
import com.myorg.job.dao.UserDao;

/**
 * Provides the injection services for the application
 *
 * @author vg
 * @since Feb 2021
 */
public class MarketPlaceApp
    extends ResourceConfig {

    private static final String APP_PATH1 = "com.myorg.job.rest";

    public MarketPlaceApp() {
        packages(APP_PATH1);
        register(new MyBinder());
    }

    /**
     * Binders bind the injections
     */
    private static class MyBinder
        extends AbstractBinder {

        @Override
        protected void configure() {
            bind(InMemoryJobDao.class).to(JobDao.class);
            bind(InMemoryPlaceBidDao.class).to(PlaceBidDao.class);
            bind(InMemoryUserDao.class).to(UserDao.class);
        }

    }
}
