
package jp.mixi.practice.dialog.med;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.add("hoge");
        adapter.add("fuga");
        adapter.add("piyo");
        adapter.add("foo");
        adapter.add("bar");
        adapter.add("baz");
        list.setAdapter(adapter);
        
        adapter.remove("hoge");
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerForContextMenu(findViewById(R.id.ListView));
    }

    @Override
    protected void onStop() {
        unregisterForContextMenu(findViewById(R.id.ListView));

        super.onStop();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // TODO: 長押しメニューに、削除・キャンセル、の 2 つの項目を表示する
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO: 長押しメニューの、削除、の項目の選択をハンドリングして、確認のためのダイアログを
        // ListItemSelectionDialogFragment を使用して表示する
        if (item.toString().equals("delete")) {
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            
            ListItemSelectionDialogFragment dialog = new ListItemSelectionDialogFragment();
            // 引数にFramentManagerとtagを設定します

            dialog.show(getSupportFragmentManager(), "my_dialog_fragment");
        }
        return super.onContextItemSelected(item);
    }
}