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

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod


##---------------Begin: proguard configuration for Gson ----------
 -keep public class com.google.gson.**
 -keep public class com.google.gson.** {public private protected *;}

 -keep public class com.project.mocha_patient.login.SignResponseData { private *; }

 ##---------------End: proguard configuration for Gson ----------


 ############ --------以下通用--------###########
  #指定代码的压缩级别
  -optimizationpasses 5

  #包明不混合大小写
  -dontusemixedcaseclassnames

  #不去忽略非公共的库类
  -dontskipnonpubliclibraryclasses

   #优化  不优化输入的类文件
  -dontoptimize

   #预校验
  -dontpreverify

   #混淆时是否记录日志
  -verbose

   # 混淆时所采用的算法
  -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

  #保护注解
-keepattributes *Annotation*

  # 保持哪些类不被混淆
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Activity
  -keep public class * extends android.support.v7.app.AppCompatActivity
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService
  #如果有引用v4包可以添加下面这行
  -keep public class * extends android.support.v4.app.Fragment

  ## 自定义控件
  -keep class com.example.apcnl.travel.widget.**{*;}


  #忽略警告
  -ignorewarning

  ##记录生成的日志数据,gradle build时在本项目根目录输出##
  #apk 包内所有 class 的内部结构
  -dump proguard/class_files.txt
  #未混淆的类和成员
  -printseeds proguard/seeds.txt
  #列出从 apk 中删除的代码
  -printusage proguard/unused.txt
  #混淆前后的映射
  -printmapping proguard/mapping.txt
  ########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

  #如果引用了v4或者v7包
  -dontwarn android.support.**

  ####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####



  #保持 native 方法不被混淆
  -keepclasseswithmembernames class * {
      native <methods>;
  }

  #保持自定义控件类不被混淆
  -keepclasseswithmembers class * {
      public <init>(android.content.Context, android.util.AttributeSet);
  }

  #保持自定义控件类不被混淆
  -keepclassmembers class * extends android.app.Activity {
     public void *(android.view.View);
  }

  -keep public class * extends android.view.View {
      public <init>(android.content.Context);
      public <init>(android.content.Context, android.util.AttributeSet);
      public <init>(android.content.Context, android.util.AttributeSet, int);
      public void set*(...);
  }

  #保持 Parcelable 不被混淆
  -keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
  }

  #保持 Serializable 不被混淆
  -keepnames class * implements java.io.Serializable

  #保持 Serializable 不被混淆并且enum 类也不被混淆
  -keepclassmembers class * implements java.io.Serializable {
      static final long serialVersionUID;
      private static final java.io.ObjectStreamField[] serialPersistentFields;
      !static !transient <fields>;
      !private <fields>;
      !private <methods>;
      private void writeObject(java.io.ObjectOutputStream);
      private void readObject(java.io.ObjectInputStream);
      java.lang.Object writeReplace();
      java.lang.Object readResolve();
  }

  #保持枚举 enum 类不被混淆
  -keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
  }

  -keepclassmembers class * {
      public void *ButtonClicked(android.view.View);
  }

  #不混淆资源类
  -keepclassmembers class **.R$* {
      public static <fields>;
  }

  #避免混淆泛型 如果混淆报错建议关掉
  #-keepattributes Signature

  #移除Log类打印各个等级日志的代码，打正式包的时候可以做为禁log使用，这里可以作为禁止log打印的功能使用，另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
  #-assumenosideeffects class android.util.Log {
  #    public static *** v(...);
  #    public static *** i(...);
  #    public static *** d(...);
  #    public static *** w(...);
  #    public static *** e(...);
  #}

  #############################################################################################
  ########################                 以上通用           ##################################
  #############################################################################################


# 保留实体类和成员不被混淆
-keep public class com.example.apcnl.travel.bean.** {
    *;
}

# 对WebView的处理
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, java.lang.String);
}

#自定义View
-keep public class com.example.apcnl.travel.widget.** {*;}

# 针对android-support-v4.jar的解决方案android.support.v4.app.Fragment
-dontwarn android.support.v4.**
-keep class android.support.v4.**  { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment


#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#glide.transformations
-keep class jp.wasabeef.glide.transformations.**

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#com.android.support:design
-keep class com.android.** { *; }




#agentweb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

#StatusBarUtil
-keep class com.jaeger.library.**{}

#友盟配置  start
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature
    #友盟配置  end


    # banner 的混淆代码
    -keep class com.youth.banner.** {
        *;
     }

     # photoview 的混淆代码
         -keep class com.bm.library.PhotoView.** {
             *;
          }

