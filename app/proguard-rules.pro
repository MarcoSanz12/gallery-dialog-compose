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
-dontwarn com.sun.source.tree.VariableTree
-dontwarn com.sun.tools.javac.tree.JCTree$JCModifiers
-dontwarn com.sun.tools.javac.tree.JCTree$JCVariableDecl
-dontwarn javax.lang.model.SourceVersion
-dontwarn javax.lang.model.element.AnnotationMirror
-dontwarn javax.lang.model.element.AnnotationValue
-dontwarn javax.lang.model.element.AnnotationValueVisitor
-dontwarn javax.lang.model.element.Element
-dontwarn javax.lang.model.element.ElementKind
-dontwarn javax.lang.model.element.ElementVisitor
-dontwarn javax.lang.model.element.ExecutableElement
-dontwarn javax.lang.model.element.Modifier
-dontwarn javax.lang.model.element.Name
-dontwarn javax.lang.model.element.PackageElement
-dontwarn javax.lang.model.element.TypeElement
-dontwarn javax.lang.model.element.TypeParameterElement
-dontwarn javax.lang.model.element.VariableElement
-dontwarn javax.lang.model.type.ArrayType
-dontwarn javax.lang.model.type.DeclaredType
-dontwarn javax.lang.model.type.ExecutableType
-dontwarn javax.lang.model.type.IntersectionType
-dontwarn javax.lang.model.type.PrimitiveType
-dontwarn javax.lang.model.type.TypeKind
-dontwarn javax.lang.model.type.TypeMirror
-dontwarn javax.lang.model.type.TypeVariable
-dontwarn javax.lang.model.type.TypeVisitor
-dontwarn javax.lang.model.util.ElementFilter
-dontwarn javax.lang.model.util.SimpleAnnotationValueVisitor8
-dontwarn javax.lang.model.util.SimpleElementVisitor8
-dontwarn javax.lang.model.util.SimpleTypeVisitor8
-dontwarn javax.lang.model.util.Types
-dontwarn javax.tools.Diagnostic$Kind
-dontwarn javax.tools.Diagnostic