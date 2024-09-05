plugins {
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.springframework/spring-core
    implementation("org.springframework:spring-core:6.0.11")
// https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:6.0.11")
// https://mvnrepository.com/artifact/org.springframework/spring-aop
    implementation("org.springframework:spring-aop:6.0.11")
// https://mvnrepository.com/artifact/org.aspectj/aspectjtools
    implementation("org.aspectj:aspectjtools:1.9.20.1")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("app.Main")
}