
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BookOpenHelper bookOpenHelper = new BookOpenHelper(getApplicationContext());
                insert(bookOpenHelper);
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BookOpenHelper bookOpenHelper = new BookOpenHelper(getApplicationContext());
                delete(bookOpenHelper);
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BookOpenHelper bookOpenHelper = new BookOpenHelper(getApplicationContext());
                update(bookOpenHelper);
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BookOpenHelper bookOpenHelper = new BookOpenHelper(getApplicationContext());
                query(bookOpenHelper);
            }
        });

    }

    private void insert(BookOpenHelper bookOpenHelper) {
        // TODO:ここにinsert処理を実装してください
        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // 書き込み用のSQLiteDatabaseを取得
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE1");
        values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER1");
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1");

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        long rowId = db.insert(Book.BOOK_TABLE_NAME, null, values);
        Toast.makeText(this, String.valueOf(rowId), Toast.LENGTH_SHORT).show();
    }

    private void delete(BookOpenHelper bookOpenHelper) {
        // TODO:ここにdelete処理を実装してください
        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };
        int deletedCount = db.delete(Book.BOOK_TABLE_NAME, selection, selectionArgs);
        Toast.makeText(this, String.valueOf(deletedCount), Toast.LENGTH_SHORT).show();
    }

    private void update(BookOpenHelper bookOpenHelper) {
        // TODO:ここにupdate処理を実装してください
        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // update情報を設定する
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "NEW_TITLE");

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_TITLE + " LIKE ?";
        String[] selectionArgs = {
                "TITLE%"
        };

        int updatedCount = db.update(Book.BOOK_TABLE_NAME, values, selection, selectionArgs);
        Toast.makeText(this, String.valueOf(updatedCount), Toast.LENGTH_SHORT).show();
    }

    private void query(BookOpenHelper bookOpenHelper) {
        // TODO:ここにquery処理を実装してください
        SQLiteDatabase db = bookOpenHelper.getReadableDatabase();
        
        // 取得する情報を指定
        String[] projection = {
                Book._ID,
                Book.COLUMN_NAME_BOOK_TITLE,
                Book.COLUMN_NAME_BOOK_PUBLISHER,
                Book.COLUMN_NAME_BOOK_PRICE
        };

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };

        Cursor cursor = db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if ( cursor.moveToFirst() ) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Book._ID));
            Toast.makeText(this, String.valueOf(itemId), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Select None", Toast.LENGTH_SHORT).show();            
        }
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
