package jp.mixi.training.inapppurchase;

import java.util.ArrayList;

import com.android.vending.billing.IInAppBillingService;

import jp.mixi.training.inapppurchase.helper.DummySku;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;

public class MainActivity extends FragmentActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

    IInAppBillingService mService;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ServiceConnection mServiceConn = new ServiceConnection() {
		   @Override
		   public void onServiceDisconnected(ComponentName name) {
		       mService = null;
		   }

		   @Override
		   public void onServiceConnected(ComponentName name, IBinder service) {
		       mService = IInAppBillingService.Stub.asInterface(service);
		   }
		};
		
	    bindService(new
	        Intent("com.android.vending.billing.InAppBillingService.BIND"),
	            mServiceConn, Context.BIND_AUTO_CREATE);
	    
	    

	}

	private void disableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(false);
		findViewById(R.id.action_start_canceled_purchase).setEnabled(false);
	}
	private void enableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(true);
		findViewById(R.id.action_start_canceled_purchase).setEnabled(true);
	}
	private void showResult (final boolean resultOk, final String text) {
		final TextView resultView = (TextView)findViewById(R.id.print_purchase_result);
		if (resultView != null) {
			resultView.setBackgroundColor(getResources().getColor(resultOk ? R.color.result_ok_bg : R.color.result_ko_bg));
			resultView.setText(text != null ? text : "");
		} else {
			Log.w(TAG, "Can't find result view ; nothing done");
		}
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	}

	private void startPurchase (final DummySku skuToPurchase) {
		//IMP here
	    Bundle buyIntentBundle = null;
	    try {
            buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
                    skuToPurchase.getId(), "inapp", "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
	    try {
            startIntentSenderForResult(pendingIntent.getIntentSender(),
                    1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
                    Integer.valueOf(0));
        } catch (SendIntentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
		Log.w(TAG, "purchase not implemented");
	}

	public void onStartAcceptedPurchaseClick (final View view) {
		startPurchase(DummySku.PURCHASED);
	}

	public void onStartCanceledPurchaseClick (final View view) {
		startPurchase(DummySku.CANCELED);
	}
}
