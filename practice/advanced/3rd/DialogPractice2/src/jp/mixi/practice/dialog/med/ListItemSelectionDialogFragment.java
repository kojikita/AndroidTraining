package jp.mixi.practice.dialog.med;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * TODO: ダイアログで、はい・いいえ の選択肢を表示する
 * TODO: はい、を選択したら、リストの項目を削除する
 * @author keishin.yokomaku
 *
 */
public class ListItemSelectionDialogFragment extends DialogFragment {

    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // AlertDialogはBuilderパターンで生成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("押したやつを消しますか？")
                // OKボタン
                .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Positive", Toast.LENGTH_SHORT).show();
                    }
                })
                // Cancelボタン
                .setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Negative", Toast.LENGTH_SHORT).show();
                    }
                });
        // Dialogを作成して返却
        return builder.create();
    }
}