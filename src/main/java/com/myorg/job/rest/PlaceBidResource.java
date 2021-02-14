package com.myorg.job.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myorg.job.dao.PlaceBidDao;
import com.myorg.job.model.JobBidResult;
import com.myorg.job.model.Bid;

/**
 * Provides REST operations for MarketPlace
 *
 * @author vg
 * @since Feb 2021
 */
@Path("marketplace")
public class PlaceBidResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceBidResource.class);

    private PlaceBidDao placeBidDaoService;

    @Inject
    PlaceBidResource(final PlaceBidDao placeBidDao) {
        this.placeBidDaoService = placeBidDao;
    }

    @POST
    @Path("/bid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response placeBid(Bid bid) {
        final JobBidResult jobBidResult = this.placeBidDaoService.placeBid(bid);
        return Response.ok(jobBidResult).build();
    }

    @GET
    @Path("/{jobId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobBidResult(@PathParam("jobId") int jobId) {
        return Response.ok(this.placeBidDaoService.getById(jobId)).build();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostRecent() {
        return Response.ok(this.placeBidDaoService.getMostActive()).build();
    }
}
