Practice Report for 1.4
------

実習のレポートを下記に記述してください

1. (実習)Android SDK 内の、下記の 2 つのディレクトリにあるコマンドを列挙してください。
* sdk/tools/
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3498  2  5 16:50 android
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     1977  2  5 16:50 apkbuilder
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3286  2  5 16:50 ddms
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    58692  2  5 16:50 dmtracedump
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     1940  2  5 16:50 draw9patch
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    48308  2  5 16:50 emulator
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2625300  2  5 16:50 emulator-arm
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2511472  2  5 16:50 emulator-mips
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2532892  2  5 16:50 emulator-x86
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2881320  2  5 16:50 emulator64-arm
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2808504  2  5 16:50 emulator64-mips
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  2854440  2  5 16:50 emulator64-x86
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users   170880  2  5 16:50 etc1tool
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3464  2  5 16:50 hierarchyviewer
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    17544  2  5 16:50 hprof-conv
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     1845  2  5 16:50 jobb
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     2015  2  5 16:50 lint
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    13300  2  5 16:50 mksdcard
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     1293  2  5 16:50 monitor
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3176  2  5 16:50 monkeyrunner
-rw-rw-r--@  1 koji.kita  INTRA\Domain Users       68  2  5 16:50 source.properties
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users   674912  2  5 16:50 sqlite3
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3219  2  5 16:50 traceview
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users     3054  2  5 16:50 uiautomatorviewer
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    75828  2  5 16:50 zipalign

* sdk/platform-tools/
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users   1209064  2 13 07:54 aapt
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users   1256228  2 13 07:54 adb
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    279172  2 13 07:54 aidl
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    144136  2 13 07:54 dexdump
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users      2603  2 13 07:54 dx
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users    189440  2 13 07:54 fastboot
-rwxrwxr-x@  1 koji.kita  INTRA\Domain Users  29871896  2 13 07:54 llvm-rs-cc
-rw-rw-r--@  1 koji.kita  INTRA\Domain Users        37  2 13 07:54 source.properties

2. (実習)上記のディレクトリにパスを通し、下記のコマンドを実行してください。
90201708n:~ koji.kita$ adb devices
List of devices attached
5CD2000600000001	device

90201708n:~ koji.kita$ adb shell
shell@android:/ $ 

3. (実習)adb shellコマンドを使って、Android 内のファイルシステムにアクセスし、下記の項目を確認してください。
* /data/data以下のディレクトリでlsコマンドを打っても、拒否されること
90201708n:~ koji.kita$ adb shell
shell@android:/ $ pwd
/
shell@android:/ $ cd data/
shell@android:/data $ ls
opendir failed, Permission denied

* /sdcard/Android/data以下のディレクトリの中身を自由に読むことができること
shell@android:/sdcard/Android/data $ ls -l
drwxrwxr-x system   sdcard_rw          2012-11-15 01:02 com.android.gallery3d
drwxrwxr-x system   sdcard_rw          2011-01-01 00:00 com.cooliris.media
drwxrwxr-x system   sdcard_rw          2013-03-18 15:02 com.deploygate
drwxrwxr-x system   sdcard_rw          2012-01-01 15:15 com.dropbox.android
drwxrwxr-x system   sdcard_rw          2011-12-25 12:16 com.google.android.apps.maps
drwxrwxr-x system   sdcard_rw          2011-01-01 00:01 com.google.android.music
drwxrwxr-x system   sdcard_rw          2012-04-30 20:31 com.google.android.youtube
drwxrwxr-x system   sdcard_rw          2012-11-15 01:29 com.kddi.android.email
drwxrwxr-x system   sdcard_rw          2012-05-19 23:24 com.navitime.local.nttransfer
drwxrwxr-x system   sdcard_rw          2012-05-30 17:39 com.shazam.android
drwxrwxr-x system   sdcard_rw          2013-04-05 22:06 jp.mixi
drwxrwxr-x system   sdcard_rw          2013-04-16 21:29 jp.naver.line.android
