# JBEAP-25269 Reproducer

Reproducer project for https://issues.redhat.com/browse/JBEAP-25269.

## Steps

1. Copy contents of the modules directory from this reproducer into the JBoss EAP modules directory: `cp modules/* $JBOSS_HOME/modules/`.
2. Build the project: `mvn package`.
3. Deploy the project: `cp target/repro-1.0-SNAPSHOT.war $JBOSS_HOME/standalone/deployments/`.
4. Run EAP: `$JBOSS_HOME/bin/standalone.sh`.
5. Visit <http://localhost:8080/repro-1.0-SNAPSHOT/HelloWorld>.

## Result

The output should contain exactly one line starting with "Manifest URL:" string, followed by the absolute path to the 
`MANIFEST.MF` file in the commons-io binary.

If the path leads to the deployment WAR, like in the example bellow, the behavior is correct:

```shell
Manifest URL: vfs:/content/repro-1.0-SNAPSHOT.war/WEB-INF/lib/commons-io-2.11.0.jar/META-INF/MANIFEST.MF
FileUtils classloader: deployment.repro-1.0-SNAPSHOT.war
```

If the path leads to the EAP module directory, like the example bellow, the behavior is incorrect:

```shell
Manifest URL: jar:file:/absolute/path/to/jboss-eap-7.4/modules/org/apache/commons/io/main/commons-io-2.12.0.jar!/META-INF/MANIFEST.MF
FileUtils classloader: org.apache.commons.io@2.12.0
```
