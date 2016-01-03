package carve.ribbonetcd;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import com.netflix.loadbalancer.Server;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse.EtcdNode;

public class EtcdServerList extends AbstractServerList<Server> {
    private static final Logger logger = LoggerFactory.getLogger(EtcdServerList.class);

    private static EtcdClient etcd;
    private String clientName;

    {
        String etcdAddress = System.getProperty("ETCD_HOST", "192.168.99.100:4001");
        etcd = new EtcdClient(URI.create("http://" + etcdAddress));
    }

    @Override
    public List<Server> getInitialListOfServers() {
        return null;
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        List<Server> result = new ArrayList<>();
        List<EtcdNode> nodes = null;
        try {
            nodes = etcd.getDir("services/hello").recursive().send().get().node.nodes;
        } catch (IOException | EtcdException | TimeoutException | EtcdAuthenticationException e) {
            logger.warn("ETCD failure", e);
            return Collections.emptyList();
        }
        for (EtcdNode node : nodes) {
            result.add(new Server(node.value));
        }
        return result;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        clientName = clientConfig.getClientName();
    }

}
