# ribbonetcd
A small app that load balances calls to a (hello) service through ribbon and etcd

### Docker setup
##### start etcd

```shell
docker run -d --name etcd -p 4001:4001 -p 7001:7001 quay.io/coreos/etcd -listen-client-urls http://0.0.0.0:4001,http://0.0.0.0:2379 -advertise-client-urls http://0.0.0.0:4001,http://0.0.0.0:2379
docker run -d --name=registrator --net=host -v //var/run/docker.sock:/tmp/docker.sock gliderlabs/registrator:latest -ip 192.168.99.100 etcd://192.168.99.100:4001/services

docker run -d -p :8080 hello
```
