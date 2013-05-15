package jp.mixi.practice.network.networkpractice1;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {
    public static final String TAG = MyAsyncTaskLoader.class.getSimpleName();
    private String mCachedData;

    public MyAsyncTaskLoader(Context context) {
        super(context);
    }

    // 非同期処理の中身
    @Override
    public String loadInBackground() {
        Log.v(TAG, "loadInBackground");

        HttpURLConnection connection = null;
        URL url;
        StringBuilder src = new StringBuilder();
        try {
            url = new URL("http://mixi.jp");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();

            while (true) {
                byte[] line = new byte[1024];
                int size = is.read(line);
                if (size <= 0)
                    break;
                src.append(new String(line, "euc-jp"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        
        return src.toString();
    }

    @Override
    public void deliverResult(String data) {
        Log.v(TAG, "deliverResult");
        // ローダがリセットされ、そのローダのライフサイクルが終了となる場合
        if (isReset()) {
            // キャッシュデータがある場合は、キャッシュを削除して、メモリから破棄可能にする
            if (mCachedData != null) {
                mCachedData = null;
            }
            return;
        }

        // 得られたデータをキャッシュする
        mCachedData = data;

        // ローダが開始されている場合、親にデータが得られたことを通知する
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        Log.v(TAG, "onStartLoading");
        // キャッシュがある場合はそちらを返す
        if (mCachedData != null) {
            deliverResult(mCachedData);
            return;
        }

        // データソースに変更があったり、キャッシュデータがない場合は loadInBackground() に行くようにする
        if (takeContentChanged() || mCachedData == null) {
            forceLoad();
        }
    }

    // ローダの非同期処理がストップする時のコールバック
    @Override
    protected void onStopLoading() {
        Log.v(TAG, "onStopLoading");
        cancelLoad();
        super.onStopLoading();
    }

    // ローダがリセットされる時のコールバック
    @Override
    protected void onReset() {
        Log.v(TAG, "onReset");
        onStopLoading();
        super.onReset();
    }
}