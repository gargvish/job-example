package com.myorg.job.rest;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myorg.job.dao.JobDao;
import com.myorg.job.model.Job;
import com.myorg.job.model.Jobs;
import com.myorg.job.model.Users;

/**
 * User REST resource to provide the basic CRUD operations via REST for job posts
 *
 * @author vg
 * @since Feb 2021
 */
@Path("jobs")
public class JobResource {

    private JobDao jobDaoService;

    @Inject
    JobResource(final JobDao jobDaoService) {
        this.jobDaoService = jobDaoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobs() {
        Jobs hellos = new Jobs();
        hellos.setJobList(this.jobDaoService.getAll());
        return Response.ok(hellos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJob(Job job) {
        int jobId = this.jobDaoService.add(job);
        return Response.created(URI.create("/api/v1/jobs/" + jobId)).build();
    }

    @GET
    @Path("/{jobId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJob(@PathParam("jobId") int jobId) {
        Job job = this.jobDaoService.getById(jobId);
        if (job != null) {
            return Response.ok(job).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJob(@PathParam("jobId") int jobId, Job job) {
        final Job updateHello = new Job();
        updateHello.setJobId(jobId);
        updateHello.setJobDescription(job.getJobDescription());

        boolean success = jobDaoService.update(updateHello);
        if (success) {
            return Response.ok(this.jobDaoService.getById(jobId)).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteHello(@PathParam("jobId") int jobId) {
        boolean success = jobDaoService.delete(jobId);
        if (success) {
            return Response.noContent().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
