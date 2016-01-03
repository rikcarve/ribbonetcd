package carve.ribbonetcd;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

@Path("/resteasy")
public class RestResource {
    static Client client = new ResteasyClientBuilder()
            .establishConnectionTimeout(2, TimeUnit.SECONDS)
            .socketTimeout(2, TimeUnit.SECONDS)
            .connectionPoolSize(20)
            .maxPooledPerRoute(5)
            .build();

    @GET
    @Path("world")
    @Produces("text/plain")
    public String world() {
        String response = client.target("http://localhost:8180/hello/v1/hello").path("/").request(MediaType.TEXT_PLAIN)
                .get(String.class);
        return response;
    }

}
