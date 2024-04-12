call gradlew clean edc-controlplane:edc-runtime-memory-dim:build
call gradlew clean bdrsmock:build -x checkstyleMain

echo "### Starting BDRS Mock"
start java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8092 -jar bdrsmock/build/libs/bdrsmock-image.jar

echo "### Starting Consumer Connector"
set EDC_FS_CONFIG=edc-controlplane/edc-runtime-memory-dim/consumer-configuration.properties
echo %EDC_FS_CONFIG%
start java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8090 -jar edc-controlplane/edc-runtime-memory-dim/build/libs/edc-runtime-memory-dim.jar

echo "### Starting Provider Connector"
set EDC_FS_CONFIG=edc-controlplane/edc-runtime-memory-dim/configuration.properties
echo %EDC_FS_CONFIG%
start java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8091 -jar edc-controlplane/edc-runtime-memory-dim/build/libs/edc-runtime-memory-dim.jar