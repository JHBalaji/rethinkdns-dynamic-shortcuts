# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
#
# You can edit that file to add rules that you want to apply to all your
# Android projects.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If you build with ProGuard, you may be able to better optimize your
# code by analyzing it with a graphical inspection tool.
#
# After you build with ProGuard, you can find the generated mapping file at
# <your_project_dir>/bin/proguard/mapping.txt
#
# You can then analyze your code with the following command:
#
#   proguardgui -injars <your_project_dir>/bin/proguard/obfuscated.jar -outjar <your_project_dir>/bin/proguard/retrace.jar -libraryjars <your_project_dir>/libs -printmapping <your_project_dir>/bin/proguard/mapping.txt
#
# Note that if you specify the -keep option for an entire class, you may
# want to also use the -keepclassmembers option to keep all the members
impor 'org.gradle.api.tasks.testing.logging.TestLogEvent'

# Add any project specific keep options here.

# If you use retrofit, you may need to add rules like this
#-keep class retrofit.** { *; }
#-keep interface retrofit.** { *; }
#-dontwarn retrofit.**
