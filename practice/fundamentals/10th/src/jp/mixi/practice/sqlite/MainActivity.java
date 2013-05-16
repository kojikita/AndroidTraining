
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void insert() {
        // TODO:ここにinsert処理を実装してください
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE" + i);
            values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER" + i);
            values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE" + i);

            getContentResolver().insert(Book.CONTENT_URI, values);
        }
    }

    private void delete() {
        // TODO:ここにdelete処理を実装してください
        int delete = getContentResolver().delete(Book.CONTENT_URI, null, null);
        Log.d(TAG, "delete count:" + delete);
    }

    private void update() {
        // TODO:ここにupdate処理を実装してください
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE100000");
        int update = getContentResolver().update(Book.CONTENT_URI, values, null, null);
        Log.d(TAG, "update count:" + update);
    }

    private void query() {
        // TODO:ここにquery処理を実装してください
        Cursor cursor = getContentResolver().query(Book.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d(TAG, cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_TITLE)));
        }
        // 処理が完了したらCursorを閉じます
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
