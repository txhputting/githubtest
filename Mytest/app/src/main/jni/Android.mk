LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := jnidemo3
LOCAL_SRC_FILES := jnitools.c
LOCAL_LDLIBS :=  -L$(SYSROOT)/usr/lib -llog
include $(BUILD_SHARED_LIBRARY)

# LOCAL_PATH := $(call my-dir)
# include $(CLEAR_VARS)
# LOCAL_MODULE    := ndkdemotest-jni
# LOCAL_SRC_FILES := ndkdemotest.c
# include $(BUILD_SHARED_LIBRARY)
