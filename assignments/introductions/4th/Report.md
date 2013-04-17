Assignment Report for 1.4
------

以下に、課題の回答を記入してください。

Android の基礎知識
======

4. `adb`コマンドを使って、下記の項目を実行してください（課題のファイルに、実行したコマンドを記録しておいてください）。

* 端末のSD カード領域に、ローカルにあるファイルを転送する
90201708n:~ koji.kita$ adb push kojikitaImage.jpg /sdcard/
773 KB/s (5840 bytes in 0.007s)

* 端末のSD カード領域から、ローカルにファイルを転送する
90201708n:~ koji.kita$ adb pull sdcard/kojikitaImage.jpg Pictures
1494 KB/s (5840 bytes in 0.003s)

* 課題用サンプルプロジェクトの apk ファイルをコマンド経由で端末にインストールする
90201708n:~ koji.kita$ adb install AndroidTraining/practice/introductions/3rd/practice01.apk
4021 KB/s (196034 bytes in 0.047s)
	pkg: /data/local/tmp/practice01.apk
Success

* インストールしたアプリを、コマンド経由でアンインストールする
90201708n:~ koji.kita$ adb uninstall com.example.practice01
Success

