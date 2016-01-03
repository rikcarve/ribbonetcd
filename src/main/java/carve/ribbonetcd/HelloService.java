package carve.ribbonetcd;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.ClientProperties;
import com.netflix.ribbon.proxy.annotation.ClientProperties.Property;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import io.netty.buffer.ByteBuf;

@ClientProperties(properties = {
        @Property(name = "ReadTimeout", value = "2000"),
        @Property(name = "ConnectTimeout", value = "2000"),
        @Property(name = "ServerListRefreshInterval", value = "10000"),
        // @Property(name = "listOfServers", value = "localhost:8180"),
        @Property(name = "NIWSServerListClassName", value = "carve.ribbonetcd.EtcdServerList"),
})
public interface HelloService {

    @Http(method = HttpMethod.GET, uri = "/hello/v1/hello/")
    @Hystrix(fallbackHandler = HelloFallback.class)
    RibbonRequest<ByteBuf> sayWorld();

}
