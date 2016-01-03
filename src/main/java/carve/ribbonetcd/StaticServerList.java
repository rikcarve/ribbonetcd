package carve.ribbonetcd;

import java.util.ArrayList;
import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import com.netflix.loadbalancer.Server;

public class StaticServerList extends AbstractServerList<Server> {
    static List<Server> servers = new ArrayList<>();

    @Override
    public List<Server> getInitialListOfServers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        System.out.println("getUpdatedListOfServers");
        if (servers.size() == 0) {
            servers.add(new Server("localhost", 8180));
        }
        return servers;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }

}
