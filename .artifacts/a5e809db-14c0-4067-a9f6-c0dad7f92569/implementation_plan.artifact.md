# Implementation Plan - Fix Build and Sync Issues

The project is currently failing to sync due to a Kotlin Serialization plugin version mismatch. Additionally, the `app/build.gradle.kts` file uses hardcoded versions that differ from the central `libs.versions.toml` file.

## User Review Required

> [!IMPORTANT]
> I will be updating the Kotlin and AGP versions to match what is defined in your `libs.versions.toml` to ensure consistency across the project.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/AndroidProjects/edp-android-castro/gradle/libs.versions.toml)
- Add missing `kotlin-android` plugin definition.
- Add `navigation-compose` and `kotlinx-serialization-json` to the libraries section.

#### [MODIFY] [build.gradle.kts (root)](file:///C:/AndroidProjects/edp-android-castro/build.gradle.kts)
- Add `kotlin-android` plugin with `apply false`.

#### [MODIFY] [build.gradle.kts (app)](file:///C:/AndroidProjects/edp-android-castro/app/build.gradle.kts)
- Replace hardcoded versions with version catalog aliases.
- Use `alias(libs.plugins.kotlin.android)` instead of hardcoded `id`.

### Source Code Cleanup

#### [MODIFY] [MainActivity.kt](file:///C:/AndroidProjects/edp-android-castro/app/src/main/java/com/example/myapplication/MainActivity.kt)
- Fix lint warnings (missing trailing comma, lambda outside parentheses).

#### [MODIFY] [Screens.kt](file:///C:/AndroidProjects/edp-android-castro/app/src/main/java/com/example/myapplication/Screens.kt)
- Fix lint warnings (missing trailing comma).

#### [MODIFY] [Routes.kt](file:///C:/AndroidProjects/edp-android-castro/app/src/main/java/com/example/myapplication/Routes.kt)
- Fix lint warnings (missing trailing comma).

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to verify the build.
- Run `gradle sync` within Android Studio.

### Manual Verification
- Verify that the IDE no longer shows red squiggles or plugin resolution errors.
