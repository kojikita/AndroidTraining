Practice Report for 2/8
------

実習のレポートを下記に記述してください

### Service

1. サンプルプロジェクト (ServiceSample) に、サービスのライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように表示されるかをレポートしてください。

* Started Service Control
Start:
    05-15 14:03:25.357: V/StartedService(6453): onCreate
    05-15 14:03:25.358: V/StartedService(6453): onStartCommand
Stop:
    05-15 14:03:29.116: V/StartedService(6453): onDestroy

Start:
    05-15 14:03:31.102: V/StartedService(6453): onCreate
    05-15 14:03:31.102: V/StartedService(6453): onStartCommand
Start:
    05-15 14:03:31.861: V/StartedService(6453): onStartCommand
Stop:
    05-15 14:03:33.625: V/StartedService(6453): onDestroy
Stop:
    ログなし

Startすると、onCreateとonStartCommandが実行される。２回目以降はonStartCommandのみで、StopするとonDestroyが呼ばれ、今まで呼ばれていたものがすべて終了する。


* Bind Service Control
Bind:
    05-15 14:11:19.621: V/BoundService(6453): onCreate
    05-15 14:11:19.626: V/MainActivity(6453): onServiceConnected
Bind:
    ログなし
Unbind:
    05-15 14:11:22.149: V/BoundService(6453): onUnbind
    05-15 14:11:22.149: V/BoundService(6453): onDestroy
Unbind:
    クラッシュした

同じIntentをバインドしているので、２個以上呼び出せない（並列に起動できない）？
onStartCommandではなく、onServiceConnectedで起動し、onUnbindする必要がある。

* Intent Service Control
Call IntentService:
    05-15 14:12:40.229: V/MyIntentService(6453): onCreate
    05-15 14:12:40.229: V/MyIntentService(6453): onStartCommand
    05-15 14:12:40.230: V/MyIntentService(6453): onHandleIntent
    05-15 14:12:40.235: V/MyIntentService(6453): onDestroy
Call IntentService:
    05-15 14:12:41.838: V/MyIntentService(6453): onCreate
    05-15 14:12:41.838: V/MyIntentService(6453): onStartCommand
    05-15 14:12:41.839: V/MyIntentService(6453): onHandleIntent
    05-15 14:12:41.843: V/MyIntentService(6453): onDestroy

onCreateからonDestroyを順番に繰り返している。


### Loader

1. サンプルプロジェクト (LoaderSample) に、AsyncTaskLoader のライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように出力されているかをレポートしてください。

05-15 14:38:28.545: V/MainActivity(10448): onCreateLoader
05-15 14:38:28.546: V/MyAsyncTaskLoader(10448): onStartLoading
05-15 14:38:28.550: V/MyAsyncTaskLoader(10448): loadInBackground
05-15 14:38:29.552: V/MyAsyncTaskLoader(10448): deliverResult
05-15 14:38:29.552: V/MainActivity(10448): onLoadFinished
05-15 14:39:33.770: V/MyAsyncTaskLoader(10448): onStopLoading

MainActivityで行う処理と、MyAsyncTaskLoaderで行う処理がある。

### AsyncTask

1. `AsyncTask#doInBackground()` で、TextView の文字を変更するような、UI の処理を実行するとどうなるかを、理由を添えてレポートしてください。

doInBackgroiund()は、UIと異なるスレッドで動くため、UIを操作することができない。
UIを操作するには、非同期処理終了後に呼ばれるonPostExecuteメソッドか、非同期処理の進捗を受け取るonProgressUpdateメソッドで行う。
【参考】http://stackoverflow.com/questions/9838622/adding-textview-values-in-asynchtask
