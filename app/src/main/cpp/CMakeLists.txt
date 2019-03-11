# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(distribution_DIR ../../../../libs)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#添加lib，SHARED类型，是IMPORTED 引入的库
add_library(libjpeg
        SHARED
        IMPORTED)

#设置 库的属性   里面是名称 ，属性：引入地址把我们的真实地址填写进去
set_target_properties(libjpeg
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/arm64-v8a/libjpeg.so)

#添加lib，SHARED类型，是IMPORTED 引入的库
add_library(libturbojpeg
        SHARED
        IMPORTED)

#设置 库的属性   里面是名称 ，属性：引入地址把我们的真实地址填写进去
set_target_properties(libturbojpeg
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/arm64-v8a/libturbojpeg.so)

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        native-lib
        libjpeg
        -ljnigraphics
        libturbojpeg
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})