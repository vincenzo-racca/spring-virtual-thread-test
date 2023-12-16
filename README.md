Postgres 15 on Amazon Linux 23

https://linux.how2shout.com/how-to-install-postgresql-15-amazon-linux-2023/ \
https://stackoverflow.com/questions/74110708/postgres-15-permission-denied-for-schema-public

and add this:
`host    all             all             0.0.0.0/0            md5` \
in /var/lib/pgsql/data/pg_hba.conf.

JMX config:
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.rmi.port=1099 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.local.only=false \
-Djava.rmi.server.hostname=127.0.0.1