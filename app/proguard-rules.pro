# Keep JNI entry points used by the native tun2socks bridge.
-keepclasseswithmembernames class * {
    native <methods>;
}

# ---------------------------------------------------------------------------
# R8 / resource shrinking ARE enabled for release since 1.6.0
# (isMinifyEnabled = true, isShrinkResources = true).
#
# 1.2.9-r3 left them off, and the reason recorded here was honest at the time:
# obfuscation is not a security control for an app whose source is published,
# the round could not build an APK, and a missing-class error against a native
# library is a plausible way to break a release. What changed is the third
# point, not the first two: the release now builds and runs on a device, so the
# flag flip can be verified instead of assumed. The saving is real - about 7 MB
# of dex - and comes mostly from unreachable Compose machinery and the unused
# half of material-icons-extended, not from renaming anything.
#
# Keep rules below this line exist only because something reaches the class by
# NAME rather than by reference.
# ---------------------------------------------------------------------------

# The engine talks to the native side through these classes' members by name.
-keep class studio.cluvex.aether.transport.** { *; }

# ZXing resolves some camera/config helpers reflectively and its embedded
# activity is started from the manifest, so both survive by name.
-keep class com.journeyapps.barcodescanner.** { *; }
-keep class com.google.zxing.** { *; }

# kotlinx.serialization is used through the JsonElement API only (no compiler
# plugin), but the library reflects on its own serializers at runtime.
-keepattributes *Annotation*, InnerClasses, Signature, RuntimeVisibleAnnotations
-dontnote kotlinx.serialization.**
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}

# Coroutines and DataStore ship consumer rules; silence the remaining notes so a
# clean build stays clean and a REAL missing class is not lost in the noise.
-dontwarn org.slf4j.**
-dontwarn javax.annotation.**
