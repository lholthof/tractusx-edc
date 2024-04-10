import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {

    implementation(bdrsmock.bundles.jetty)
    implementation(bdrsmock.slf4j.simple)

    implementation(libs.nimbus.jwt)
    implementation(libs.jackson.databind)

    //testImplementation(testlibs.bundles.base)
    //testImplementation(testlibs.system.stubs.core)
    //testImplementation(testlibs.system.stubs.jupiter)
}

application {
    mainClass.set("com.sap.it.dsi.connector.bdrsmock.JettyStarter")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("${project.name}-image.jar")
    //archiveBaseName.set("${project.name}")
    //archiveVersion.set("${project.version}")
    //archiveClassifier.set("image")
    mergeServiceFiles()
}
