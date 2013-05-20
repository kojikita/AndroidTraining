package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.MainActivity;
import android.app.Activity;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import jp.mixi.practice.test.target.R;


//ActivityInstrumentationTestCase2 を継承して、機能テストを書く
public class TestPractice3 extends ActivityInstrumentationTestCase2<MainActivity> {
    public TestPractice3() {
        this(MainActivity.class);
    }

    public TestPractice3(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void testCountUpScenario() throws Exception {
        // getActivity() の呼び出しで、テスト対象の Activity が立ち上がる
        Activity activity = getActivity();

        // UI 操作による View の状態を見るために、View のインスタンスを取り出す
        TextView title = (TextView) activity.findViewById(R.id.TitleLabel);

        // 最初は 0
        Log.d("MyApp", title.getText().toString());
        assertNull(title.getText().toString());
    }
}