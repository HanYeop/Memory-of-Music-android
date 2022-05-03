import Versions.NAV_VERSION

object Versions {
    const val NAV_VERSION = "2.4.0-alpha10"
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