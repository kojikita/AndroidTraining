Assignment Report for 1.3
------

以下に、課題の回答を記入してください。

署名済みアプリの作成
======

3. 作成した apk ファイルの中にあるファイル・ディレクトリを列挙してください。
90201708n:~ koji.kita$ ls -l AndroidTraining/practice/introductions/3rd/unzip_apk/
total 896
-rw-r--r--  1 koji.kita  788986963    1640  4 17 14:41 AndroidManifest.xml
drwxr-xr-x  5 koji.kita  788986963     170  4 17 14:54 META-INF
-rw-r--r--  1 koji.kita  788986963  448760  4 17 14:41 classes.dex
drwxr-xr-x  8 koji.kita  788986963     272  4 17 14:54 res
-rw-r--r--  1 koji.kita  788986963    2208  4 17 14:41 resources.arsc

4. 上記で列挙したファイル・ディレクトリについて、どのような役割を持っているか説明してください。
* AndroidManifest.xml
マニフェストファイル

* META-INFディレクトリ
署名関係のファイル

* resディレクトリ/resources.arsc
リソースファイル

* classes.dexはDalvik VM
バイトコード
