import com.vlsantillan.imagetoresult.buildsrc.Libs

plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    implementation(Libs.Coroutines.core)
    implementation(Libs.Java.javaxInject)
    compileOnly(Libs.Java.javaxJsr)

    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.Mockito.mockito)
    testImplementation(Libs.Test.Mockito.mockitoKotlin)
}