/********************************************************************************
 * Copyright (c) 2021,2022 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(libs.edc.spi.core)
    runtimeOnly(project(":edc-controlplane:edc-controlplane-base")) {
        exclude(module = "data-encryption")
        exclude(module = "ssi-miw-credential-client")
        exclude(module = "ssi-identity-core")
        exclude(module = "ssi-identity-extractor")
    }
    runtimeOnly(project(":edc-dataplane:edc-dataplane-base"))
    runtimeOnly(libs.edc.core.controlplane)
    testImplementation(libs.edc.junit)
    testImplementation(libs.edc.lib.boot)

    //implementation(project(":extensions:common:iam:decentralized-identity"))
    implementation(libs.edc.identity.core.did)

    //implementation(project(":extensions:common:iam:identity-trust"))
    implementation(libs.edc.identity.trust.issuers.configuration)
//    implemenation(libs.edc.identity.trust.transform)
//    implementation(libs.edc.identity.trust.jws2020)
//    implementation(libs.edc.identity.trust.vc.jwt)
//    implementation(libs.edc.identity.trust.vc.ldp)
    implementation(libs.edc.identity.core.trust)
    implementation(libs.edc.identity.trust.spi)

    //implementation(project(":extensions:common:vault:vault-filesystem"))
    implementation(libs.edc.vault.filesystem)
    //implementation(project(":extensions:common:iam:oauth2:oauth2-client"))
    implementation(libs.edc.auth.oauth2.client)
    //implementation(project(":extensions:common:iam:decentralized-identity:identity-did-web"))
    implementation(libs.edc.identity.did.web)

    implementation(project(":edc-extensions:iatp:tx-iatp-sts-dim"))
    implementation(project(":core:json-ld-core"))

}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xm")
    mergeServiceFiles()
    archiveFileName.set("${project.name}.jar")
}

application {
    mainClass.set("org.eclipse.edc.boot.system.runtime.BaseRuntime")
}
