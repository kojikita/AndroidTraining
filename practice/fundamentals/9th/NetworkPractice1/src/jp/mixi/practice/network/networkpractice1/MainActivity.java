package jp.mixi.practice.network.networkpractice1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String> {
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);

        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                // ローダの管理をするオブジェクト
                LoaderManager manager = getSupportLoaderManager();
                Bundle argsForLoader = new Bundle();
                // ローダを初期化して非同期処理を開始する
                manager.initLoader(0, argsForLoader, MainActivity.this);
                
            }
        });
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
            }
        });
    }
    
    // id に対応した Loader のインスタンスを作って返す
    // args は Loader に渡したい引数を Bundle に詰めたもの
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.v(TAG, "onCreateLoader");
        switch (id) {
            case 0:
                return new MyAsyncTaskLoader(this);
            default:
                return null;
        }
    }
    
    // 結果を受け取るコールバック
    // メインスレッドで動作する
    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Log.v(TAG, "onLoadFinished");

        TextView response = (TextView)findViewById(R.id.responce);
        response.setText(result);
    }

    // ローダがリセットされる時のコールバック
    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.v(TAG, "onLoaderReset");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
