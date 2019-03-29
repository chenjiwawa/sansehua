# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

############################ carbon ######################################
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
#-keep class android.support.v8.renderscript.** { *; }
#
#-dontwarn carbon.BR
#-dontwarn carbon.internal**
#-dontwarn java.lang.invoke**
#
#-dontwarn android.databinding.**
#-keep class android.databinding.** { *; }
#
############################ umeng ######################################
#
#
############################ PLDroidPlayer（七牛播放 ）######################################
#-keep class com.pili.pldroid.player.** { *; }
#-keep class com.qiniu.qplayer.mediaEngine.MediaPlayer{*;}

############################ 声网互动语音直播 ######################################
-keep class io.agora.**{*;}

############################ svga动画播放器 ######################################
-keep class com.squareup.wire.** { *; }
-keep class com.opensource.svgaplayer.proto.** { *; }

############################ 融云IM ######################################
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
 public *;
}

-keepattributes Exceptions,InnerClasses

-keep class io.rong.** {*;}

##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

##---------------End: proguard configuration for Gson ----------

-keep class **$Properties
-dontwarn org.eclipse.jdt.annotation.**

##--AMap
-keep com.amap.api.** {*;}

-keep com.amap.api.services.** {*;}

-keep amap.api.location.** {*;}
