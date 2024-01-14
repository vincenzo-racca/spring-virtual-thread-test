# Spring Virtual Thread Test

Read the article on the comparison between Reactive and Virtual Thread and find out who won: \
https://www.vincenzoracca.com/en/blog/framework/spring/virtual-threads-vs-webflux/

## Postgres 15 on Amazon Linux 23

https://linux.how2shout.com/how-to-install-postgresql-15-amazon-linux-2023/ \
https://stackoverflow.com/questions/74110708/postgres-15-permission-denied-for-schema-public

and add this:
`host    all             all             0.0.0.0/0            md5` \
in /var/lib/pgsql/data/pg_hba.conf.

## JMX config
```bash
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.rmi.port=1099 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=127.0.0.1
```

## Jmeter file
[Jmeter file](vt-vs-webflux.jmx)

## Command to run reactive app
```bash
nohup java    -Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.rmi.port=1099 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=<ip> \
-Dspring.r2dbc.username=myuser \
-Dspring.r2dbc.password=secret \
-Dspring.r2dbc.url=r2dbc:postgresql://<ip_pg>:5432/mydatabase \
-Dcustom.mockclient-url=<url_mock_client> \
-jar spring-virtual-thread-test/reactive/target/reactive-0.0.1-SNAPSHOT.jar &
```

## Command to run vt app
```bash
nohup java    -Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.rmi.port=1099 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=<ip> \
-Dspring.datasource.username=myuser \
-Dspring.datasource.password=secret \
-Dspring.datasource.url=jdbc:postgresql://<ip_pg>:5432/mydatabase \
-Dcustom.mockclient-url=<url_mock_client> \
-jar spring-virtual-thread-test/virtual-threads/target/virtual-threads-0.0.1-SNAPSHOT.jar &
```

## Test with reactive native image (using GRAALVM)

### Command

Build native image:
```bash
./mvnw clean spring-boot:build-image -DskipTests -Pnative
```
Run native image:
```bash
docker run --name reactive -p8080:8080 -p1099:1099 \
-e SPRING_R2DBC_USERNAME=myuser -eSPRING_R2DBC_PASSWORD=secret \
-e SPRING_R2DBC_URL=r2dbc:postgresql:///<ip_pg>:5432/mydatabase \
-e CUSTOM_MOCKCLIENT_URL=<url_mock_client> \
-e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.rmi.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.local.only=false -Djava.rmi.server.hostname=<ip>" \
-d vincenzoracca/reactive-native:0.0.1-SNAPSHOT

```
### Issues encountered:
1. https://github.com/spring-attic/spring-native/issues/1470
2. https://docs.spring.io/spring-framework/docs/6.0.0/reference/html/core.html#aot-hints-register-reflection-for-binding
   https://docs.spring.io/spring-framework/docs/6.0.0/reference/html/core.html#aot-hints-register-reflection-for-binding
   2024-01-14T14:50:10.320Z  WARN 1 --- [or-http-epoll-5] r.netty.http.client.HttpClientConnect    : [c75f04e4-1, L:/172.17.0.4:45894 - R:host.docker.internal/192.168.65.254:8092] The connection observed an error
   org.springframework.web.reactive.function.UnsupportedMediaTypeException: Content type '' not supported for bodyType=com.vincenzoracca.reactive.model.NameDTO



