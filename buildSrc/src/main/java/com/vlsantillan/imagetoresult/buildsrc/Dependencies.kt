package com.vlsantillan.imagetoresult.buildsrc

object Libs {
    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Compose {
            const val version = "1.2.0-rc01"

            const val ui = "androidx.compose.ui:ui:${version}"
            const val uiUtil = "androidx.compose.ui:ui-util:${version}"
            const val runtime = "androidx.compose.runtime:runtime:${version}"
            const val material = "androidx.compose.material:material:${version}"
            const val tooling = "androidx.compose.ui:ui-tooling:${version}"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${version}"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Lifecycle {
            private const val version = "2.4.1"

            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object ConstraintLayout {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha02"
        }

        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.4.2"
        }

        object Camera {
            private const val version = "1.0.2"

            const val camera2 = "androidx.camera:camera-camera2:$version"
            const val cameraLifecycle = "androidx.camera:camera-lifecycle:$version"
            const val cameraView = "androidx.camera:camera-view:1.0.0-alpha31"
        }
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
    }

    object Hilt {
        private const val version = "2.40"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object MLKit {
        const val textRecognition =
            "com.google.android.gms:play-services-mlkit-text-recognition:18.0.0"
    }

    object ExpressionEvaluator {
        const val evaluator = "net.objecthunter:exp4j:0.4.8"
    }

    object Java {
        const val javaxInject = "javax.inject:javax.inject:1"
        const val javaxJsr = "javax.annotation:jsr250-api:1.0"
    }

    object Accompanist {
        const val permission = "com.google.accompanist:accompanist-permissions:0.24.12-rc"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"

        object Ext {
            const val junit = "androidx.test.ext:junit-ktx:1.1.2"
        }

        object Mockito {
            const val mockito = "org.mockito:mockito-core:2.8.9"
            const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
        }
    }
}