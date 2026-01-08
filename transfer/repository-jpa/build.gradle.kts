dependencies {
    implementation(project(":common:repository-jpa"))
    implementation(project(":transfer:infrastructure"))
    implementation("com.github.f4b6a3:tsid-creator:5.2.6")

    integrationTestImplementation("org.testcontainers:junit-jupiter")
    integrationTestImplementation("org.testcontainers:mysql")
    integrationTestImplementation(testFixtures(project(":common:application-api")))
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    integrationTestRuntimeOnly("com.mysql:mysql-connector-j") {
        exclude(group = "com.google.protobuf", module = "protobuf-java")
    }
}
