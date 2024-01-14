## Spring Virtual Thread Test

Read the article on the comparison between Reactive and Virtual Thread and find out who won: \
https://www.vincenzoracca.com/en/blog/framework/spring/virtual-threads-vs-webflux/

### Useful information:

#### Postgres 15 on Amazon Linux 23

https://linux.how2shout.com/how-to-install-postgresql-15-amazon-linux-2023/ \
https://stackoverflow.com/questions/74110708/postgres-15-permission-denied-for-schema-public

and add this:
`host    all             all             0.0.0.0/0            md5` \
in /var/lib/pgsql/data/pg_hba.conf.

#### JMX config
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.rmi.port=1099 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=127.0.0.1

#### Jmeter file
[Jmeter Test](vt-vs-webflux.jmx)

#### Command to run reactive app
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

#### Command to run vt app
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