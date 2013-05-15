package jp.mixi.practice.network.networkpractice2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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

        String result = new String();
        HttpClient client = new DefaultHttpClient();
        try {
            result = client.execute(new HttpGet("http://mixi.jp"),
                    new ResponseHandler<String>() {
                        public String handleResponse(HttpResponse response)
                                throws ClientProtocolException, IOException {
                            return EntityUtils.toString(response.getEntity());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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