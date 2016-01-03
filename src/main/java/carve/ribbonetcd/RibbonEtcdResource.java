package carve.ribbonetcd;

import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.netflix.ribbon.Ribbon;

import io.netty.buffer.ByteBuf;

@Path("/ribbon")
public class RibbonEtcdResource {

    static HelloService helloProxy = Ribbon.from(HelloService.class);

    @PostConstruct
    public void init() {
        // helloProxy = Ribbon.from(HelloService.class);
    }

    @GET
    @Path("world")
    @Produces("text/plain")
    public String world() {
        ByteBuf resp = helloProxy.sayWorld().execute();
        return resp.toString(StandardCharsets.ISO_8859_1);
    }
}
