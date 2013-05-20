package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.SubActivity;
import jp.mixi.practice.test.target.TestTarget1;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

public class TestPractice1 extends AndroidTestCase {
    // テストの前準備のメソッド。テストケースの実行ごとに呼ばれる。
    // 事前準備中に何らかの例外が起こる可能性があるので、例外をスローする宣言をする。
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    // テストの後始末のメソッド。テストケースの実行ごとに呼ばれる。
    // 後始末中に何らかの例外が起こる可能性があるので、例外をスローする宣言をする。
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // startSubActivity() を呼び出すテスト
    public void testStartSubActivity() throws Exception {
        TestTarget1 target = new TestTarget1();
        // AndroidTestCase が持っている ActivityContext ではなく、自分でモックした Context
        target.startSubActivity(new TestTarget1ContextForStartActivity(getContext()), "hogehoge");
    }
    
    public void testIsValidIntent() throws Exception {
        TestTarget1 target = new TestTarget1();
        
        Intent intent = new Intent(getContext(), SubActivity.class);
        assertFalse(target.isValidIntent(intent));
        
        intent.putExtra(Intent.EXTRA_TEXT, "text");
        assertFalse(target.isValidIntent(intent));

        intent.setData(Uri.parse("http://mixi.jp"));
        assertTrue(target.isValidIntent(intent));
    }

    // Context#startActivity() が、期待通りのコンポーネントに Intent を投げているかテストするための
    // MockContext
    private static class TestTarget1ContextForStartActivity extends MockContext {
        private Context mContext;

        public TestTarget1ContextForStartActivity(Context baseContext) {
            mContext = baseContext;
        }

        @Override
        public String getPackageName() {
            return mContext.getPackageName();
        }

        @Override
        public void startActivity(Intent intent) {
            // Intent から、Intent の送り先のコンポーネント情報を取り出して、期待値と一致するか確認する
            ComponentName component = intent.getComponent();
            assertEquals(SubActivity.class.getCanonicalName(), component.getClassName());
            // Data
            assertNotNull(intent.getData());
            assertEquals("http://mixi.jp", intent.getData().toString());
            // Flags
            assertEquals(Intent.FLAG_ACTIVITY_FORWARD_RESULT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT, intent.getFlags());
            // Extra に期待するものが有るか確認する
            assertTrue(intent.hasExtra(Intent.EXTRA_SUBJECT));
            assertEquals("hogehoge", intent.getStringExtra(Intent.EXTRA_SUBJECT));
        }
    }
}
