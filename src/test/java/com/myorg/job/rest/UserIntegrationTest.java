package com.myorg.job.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;

import java.util.List;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.server.Server;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.net.HostAndPort;
import com.myorg.job.model.User;
import com.myorg.job.model.Users;
import com.myorg.job.server.MarketPlaceServer;

/**
 * Provides the Market Place User Service Tests
 *
 * @author vg
 * @since Feb 2021
 */
public class UserIntegrationTest {

    private static final int PORT = 8080;

    private static String USER_URL = "http://localhost:" + PORT + "/api/v1";

    @Test
    public void testHellosEmpty()
        throws Exception {
        Server server = MarketPlaceServer.createServer(HostAndPort.fromString("localhost:8080"));
        try {
            server.start();

            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(USER_URL).path("users");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            Users hellos = response.readEntity(Users.class);
            List<User> listOfHellos = hellos.getUserList();
            assertThat(response.getStatus(), is(200));
            assertThat(listOfHellos, empty());
        }
        finally {
            if (server != null) {
                server.stop();
            }
        }
    }

    @Ignore
    public void testCrudHello()
        throws Exception {
        Server server = MarketPlaceServer.createServer(HostAndPort.fromString("localhost:8080"));
        try {
            server.start();

            Client client = ClientBuilder.newClient();

            // create
            WebTarget webTarget1 = client.target(USER_URL).path("users");
            Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
            User newHello = new User();
            newHello.setUserEmail("hello1");
            Response response1 = invocationBuilder1.post(Entity.entity(newHello, MediaType.APPLICATION_JSON));
            assertThat(response1.getStatus(), is(201));

            // read
            WebTarget webTarget2 = client.target(USER_URL).path("users").path("1");
            Invocation.Builder invocationBuilder2 = webTarget2.request(MediaType.APPLICATION_JSON);
            Response response2 = invocationBuilder2.get();
            User hello = response2.readEntity(User.class);
            assertThat(hello.getUserEmail(), is(newHello.getUserEmail()));

            // send
            hello.setUserEmail("hello2");
            WebTarget webTarget3 = client.target(USER_URL).path("users").path("" + hello.getUserId());
            Invocation.Builder invocationBuilder3 = webTarget3.request(MediaType.APPLICATION_JSON);
            Response response3 = invocationBuilder3.put(Entity.entity(hello, MediaType.APPLICATION_JSON));
            User hello3 = response3.readEntity(User.class);
            assertThat(hello.getUserEmail(), is(hello3.getUserEmail()));

            // delete
            WebTarget webTarget4 = client.target(USER_URL).path("users").path("" + hello.getUserId());
            Invocation.Builder invocationBuilder4 = webTarget4.request(MediaType.APPLICATION_JSON);
            Response response4 = invocationBuilder2.delete();
            assertThat(response4.getStatus(), is(Response.noContent()));

        }
        finally {
            if (server != null) {
                server.stop();
            }
        }
    }
}
