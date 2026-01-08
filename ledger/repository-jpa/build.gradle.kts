dependencies {
    implementation(project(":common:repository-jpa"))
    implementation(project(":ledger:infrastructure"))

    integrationTestImplementation("org.testcontainers:junit-jupiter")
    integrationTestImplementation("org.testcontainers:mysql")
    integrationTestImplementation(testFixtures(project(":common:application-api")))
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    integrationTestRuntimeOnly("com.mysql:mysql-connector-j") {
        exclude(group = "com.google.protobuf", module = "protobuf-java")
    }
}
