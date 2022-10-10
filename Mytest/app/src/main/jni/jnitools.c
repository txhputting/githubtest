#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>


jint addNumber(JNIEnv *env,jclass clazz,jint a,jint b);

jint subNumber(JNIEnv *env,jclass clazz,jint a,jint b);

jint mulNumber(JNIEnv *env,jclass clazz,jint a,jint b);

jint divNumber(JNIEnv *env,jclass clazz,jint a,jint b);





JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved){

    //打印日志，说明已经进来了
    __android_log_print(ANDROID_LOG_DEBUG,"JNITag","enter jni_onload");

    JNIEnv* env = NULL;
    jint result = -1;
    __android_log_print(ANDROID_LOG_DEBUG,"JNITag","000000000000000000");
    // 判断是否正确
    if((*vm)->GetEnv(vm,(void**)&env,JNI_VERSION_1_6)!= JNI_OK){
        return result;
    }
    __android_log_print(ANDROID_LOG_DEBUG,"JNITag","1111111111111111111111");
    //注册四个方法，注意签名
    const JNINativeMethod method[]={
            {"add","(II)I",(void*)addNumber},
            {"sub","(II)I",(void*)subNumber},
            {"mul","(II)I",(void*)mulNumber},
            {"div","(II)I",(void*)divNumber}
    };
    __android_log_print(ANDROID_LOG_DEBUG,"JNITag","2222222222222222");
    //找到对应的JNITools类
    jclass jClassName=(*env)->FindClass(env,"yaoshun/com/AndroidTest/JNITools");

    //开始注册
    jint ret = (*env)->RegisterNatives(env,jClassName,method, 4);

     //如果注册失败，打印日志
    if (ret != JNI_OK) {
        __android_log_print(ANDROID_LOG_DEBUG, "JNITag", "jni_register Error");
        return -1;
    }

    return JNI_VERSION_1_6;
}


jint addNumber(JNIEnv *env,jclass clazz,jint a,jint b){
     return a+b;
}

jint subNumber(JNIEnv *env,jclass clazz,jint a,jint b){
     return a-b;
}

jint mulNumber(JNIEnv *env,jclass clazz,jint a,jint b){
     return a*b;
}

jint divNumber(JNIEnv *env,jclass clazz,jint a,jint b){
     return a/b;
}



