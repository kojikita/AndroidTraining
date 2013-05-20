package jp.mixi.practice.test.target;

/**
 * TODO: TestPractice2 の各テストケースをパスするメソッドを書く
 */
public class TestTarget2 {
    public boolean isValidLength(String string) {
        if (string.equals("")) {
            return false;
        }
        if (string.length() > 10) {
            return false;
        }
        return true;
    }

    public String formatTextCount(int count, int max) {
        return count + " / " + max;
    }
}