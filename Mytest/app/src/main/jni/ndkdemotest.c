//
// Created by Parker on 2022/10/9.
//

#include "yaoshun_com_AndroidTest_ndkTools.h"

JNIEXPORT jstring JNICALL Java_yaoshun_com_AndroidTest_ndkTools_getStringFromNDK(JNIEnv *env, jobject obj){
        return (*env)->NewStringUTF(env,"Hellow World，这是隔壁老李头的NDK的第一行代码");
  }