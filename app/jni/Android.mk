LOCAL_PATH := $(call my-dir)

##
## Poco (Foundation)
##
include $(CLEAR_VARS)
LOCAL_MODULE := PocoFoundation
ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := libs/android/armeabi-v7a/libPocoFoundation.a
else
    LOCAL_SRC_FILES := libs/android/x86/libPocoFoundation.a
endif
LOCAL_EXPORT_C_INCLUDES := include
include $(PREBUILT_STATIC_LIBRARY)

##
## Poco (Net)
##
include $(CLEAR_VARS)
LOCAL_MODULE := PocoNet
ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := libs/android/armeabi-v7a/libPocoNet.a
else
    LOCAL_SRC_FILES := libs/android/x86/libPocoNet.a
endif
LOCAL_EXPORT_C_INCLUDES := include
LOCAL_STATIC_LIBRARIES:= PocoFoundation
include $(PREBUILT_STATIC_LIBRARY)

##
## libskycatch (gyp-generated)
##
FORCE_GYP := $(shell make -C ../libSkycatch GypAndroid.mk)
include ../libSkycatch/GypAndroid.mk
