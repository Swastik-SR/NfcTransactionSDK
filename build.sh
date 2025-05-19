#!/bin/bash

# Run SDK tests
echo "Running SDK tests..."
./gradlew :sdk:connectedAndroidTest

# Build debug APK
echo "Building debug APK..."
./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "APK location: app/build/outputs/apk/debug/app-debug.apk"
else
    echo "Build failed!"
    exit 1
fi 