# Fix Gradle Sync Error: "Cannot add extension with name 'kotlin'"

The project is using **Android Gradle Plugin (AGP) 9.2.1**, which introduces **Built-in Kotlin support**. This feature automatically registers the `kotlin` extension. The error occurs because the project is also attempting to apply the legacy `org.jetbrains.kotlin.android` plugin, which tries to register the same extension name.

## Proposed Changes

### Build Configuration

#### [MODIFY] [build.gradle.kts](file:///C:/AndroidProjects/edp-android-castro/build.gradle.kts)
- Remove the `alias(libs.plugins.kotlin.android) apply false` line as Kotlin support is now built into AGP 9.2+.

#### [MODIFY] [app/build.gradle.kts](file:///C:/AndroidProjects/edp-android-castro/app/build.gradle.kts)
- Remove `id("org.jetbrains.kotlin.android")` from the `plugins` block.
- Remove the redundant `kotlinOptions` block inside `android`, as AGP 9.2+ automatically inherits the JVM target from `compileOptions.targetCompatibility`.

### Dependency Management

#### [MODIFY] [libs.versions.toml](file:///C:/AndroidProjects/edp-android-castro/gradle/libs.versions.toml)
- Remove the `kotlin-android` plugin definition from the `[plugins]` section to clean up the project structure.

## Verification Plan

### Automated Tests
- Run `./gradlew help` to verify that the Gradle sync error is resolved.
- Run `./gradlew assembleDebug` to ensure the project still compiles correctly with the built-in Kotlin support.

### Manual Verification
- Sync the project in Android Studio to confirm the "Cannot add extension with name 'kotlin'" error no longer appears in the Sync tab.
