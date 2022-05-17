import Versions.NAV_VERSION
import Versions.ROOM_VERSION

object Versions {
    const val NAV_VERSION = "2.4.0-alpha10"
    const val ROOM_VERSION = "2.4.2"
}

object Kotlin {
    const val SDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21"
}

object AndroidX {
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.0-rc01"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.3.1"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:1.7.0-alpha01"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.4.0"
}

object NavComponent {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
    const val NAVIGATION_DYNAMIC_FEATURES_FRAGMENT = "androidx.navigation:navigation-dynamic-features-fragment:$NAV_VERSION"
    const val NAVIGATION_TESTING = "androidx.navigation:navigation-testing:$NAV_VERSION"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.4.0-alpha10"
}

object DaggerHilt {
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.40.5"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.40.5"
    const val DAGGER_HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val DAGGER_HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:1.0.0"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val CONVERTER_JAXB = "com.squareup.retrofit2:converter-jaxb:2.9.0"
    const val XMLConverter = "com.squareup.retrofit2:converter-simplexml:2.2.0"
}

object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object Room {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:$ROOM_VERSION"
    const val ROOM_COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    const val ROOM_KTX  = "androidx.room:room-ktx:$ROOM_VERSION"
}