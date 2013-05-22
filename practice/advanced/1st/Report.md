Practice Report for 3.1
------

実習のレポートを下記に記述してください

デバッグ

1. (実習) 実習プロジェクト`DebugPractice`をビルド・インストールし、起動してから画面が立ち上がるまでのメソッドのプロファイリングを実行し、どのメソッドに時間がかかっているかレポートしてください。

メソッド名                                                                      : Excl CPU Time %   : Excl Cpu Time
abdroid/text/StaticLayout.generate                                              : 22.1%             : 239.678
jp/co/canonsoftware/android/emoji/AnimatedEmojiFactory.nativeCheckEmojiUnicode  : 12.6%             : 136.718
jp/co/canonsoftware/android/emoji/AnimatedEmojiFactory.CheckEmojiUnicode        : 11.9%             : 129.100
android/text/StaticLayout.isIdeographic                                         : 10.2%             : 111.061
android/view/GLES20Canvas.nDrawDisplayList                                      :  7.0%             :  76.140

テキストやビュー、絵文字等、表示に関係するメソッドに時間がかかっているようだ。

自動ビルド

2. (実習) 適用したプロジェクトを ant でビルドし、ログを見て、ビルドに必要な手順をレポートしてください。

* インストール
koji.kita@90201708n:~/AndroidTraining$$ android update project -p practice/advanced/1st/DebugPractice/ -n DebugPractice
Updated local.properties
Added file practice/advanced/1st/DebugPractice/build.xml
Updated file practice/advanced/1st/DebugPractice/proguard-project.txt
It seems that there are sub-projects. If you want to update them
please use the --subprojects parameter.

koji.kita@90201708n:~/AndroidTraining$$ cd practice/advanced/1st/DebugPractice/
koji.kita@90201708n:~/AndroidTraining/practice/advanced/1st/DebugPractice$$ ant debug install

* テストの実行
koji.kita@90201708n:~/AndroidTraining$$ android update test-project -p projects/fundamentals/11th/TestSampleTest/ -m ../TestTargetSample/
Resolved location of main project to: /Users/koji.kita/AndroidTraining/projects/fundamentals/11th/TestTargetSample
Updated project.properties
Updated local.properties
No project name specified, using project folder name 'TestSampleTest'.
If you wish to change it, edit the first line of build.xml.
Added file projects/fundamentals/11th/TestSampleTest/build.xml
Updated file projects/fundamentals/11th/TestSampleTest/proguard-project.txt
Updated ant.properties

koji.kita@90201708n:~/AndroidTraining$$ cd projects/fundamentals/11th/TestSampleTest/
koji.kita@90201708n:~/AndroidTraining/projects/fundamentals/11th/TestSampleTest$$ ant debug install test
~~~~
test:
     [echo] Running tests ...
          [exec]
~~~~
     [exec] Test results for InstrumentationTestRunner=.F.F...F.F...........
     [exec] Time: 13.97
     [exec]
     [exec] FAILURES!!!
     [exec] Tests run: 17,  Failures: 4,  Errors: 0
