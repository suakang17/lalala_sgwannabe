tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")
}
