# Retrofit + Kotlin Serialization
-keepattributes *Annotation*
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepattributes AnnotationDefault

# Keep Kotlin coroutines continuations for suspend functions
-keep class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.intrinsics.IntrinsicsKt

# Hilt / Dagger
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.** { *; }

# Kotlin Serialization
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.json.** { *; }

#ENUMS, PARCELABLES, SERIALIZABLES
-keepclassmembers,allowobfuscation enum * { *; }
-keep class * implements android.os.Parcelable { *; }
-keep class * implements java.io.Serializable { *; }

# Optional: ignore warnings for 3rd party libs
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn com.google.protobuf.**
-dontwarn com.fasterxml.jackson.**