package com.pdsu.wifi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

//检测网络连接状态
public class MainActivity extends Activity {

	private ConnectivityManager manager;
	private NetworkInfo networkInfo;
	AlertDialog.Builder builder;
	private TextView tv_info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_info = (TextView) findViewById(R.id.tv_info);
		// 得到网络连接信息
		manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		builder = new AlertDialog.Builder(this);
	}

	/**
	 * 检测网络是否连接
	 */
	private boolean checkNetworkState() {
		boolean flag = false;

		// 去进行判断网络是否连接
		if (manager.getActiveNetworkInfo() != null) {
			flag = manager.getActiveNetworkInfo().isAvailable();
			networkInfo = manager.getActiveNetworkInfo();
		}
		if (!flag) {
			setNetwork();
		} else {
			isNetworkAvailable();
		}

		return flag;
	}

	/**
	 * 网络未连接时，调用设置方法
	 */
	private void setNetwork() {
		Toast.makeText(this, "网络连接已关闭 !", Toast.LENGTH_SHORT).show();
		tv_info.setText("网络连接已关闭 !");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("网络提示信息");
		builder.setMessage("网络不可用，如果继续，请先设置网络！");
		builder.setPositiveButton("设置", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				/**
				 * 判断手机系统的版本！如果API大于10 就是3.0+ 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
				 */
				if (android.os.Build.VERSION.SDK_INT > 10) {
					intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
				} else {
					intent = new Intent();
					ComponentName component = new ComponentName("com.android.settings",
							"com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				startActivity(intent);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.create();
		builder.show();
	}

	/**
	 * 网络已经连接，然后去判断是wifi连接还是GPRS连接 设置一些自己的逻辑调用
	 */
	private void isNetworkAvailable() {
		State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		if (gprs == State.CONNECTED || gprs == State.CONNECTING) {
			networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			int status = networkInfo.getSubtype();
			tv_info.setText("网络连接开启 !" + "网络状态编码：" + status);
			switch (status) {
			case 0x00000000:
				Toast.makeText(this, "NETWORK_TYPE_UNKNOWN", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000001:
				Toast.makeText(this, "	 * Constant Value: 1 (0x00000001) (2.5G）移动和联通", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000002:
				Toast.makeText(this, "	 * Constant Value: 2 (0x00000002) (2.75G)2.5G到3G的过渡 移动和联通", Toast.LENGTH_SHORT)
						.show();
				break;
			case 0x00000003:
				Toast.makeText(this, "	 * Constant Value: 3 (0x00000003) (3G)联通", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000004:
				Toast.makeText(this, "	 * IS95B Constant Value: 4 (0x00000004) (2G 电信)", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000005:
				Toast.makeText(this, "	 * IS95B Constant Value: 4 (0x00000005) (3G 电信)", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000006:
				Toast.makeText(this, " * revision A Constant Value: 6 (0x00000006) (3.5G) 属于3G过渡", Toast.LENGTH_SHORT)
						.show();
				break;
			case 0x00000007:
				Toast.makeText(this, " * Constant Value: 7 (0x00000007) ( 2G )", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000008:
				Toast.makeText(this, "	 * Value: 8 (0x00000008) (3.5G )", Toast.LENGTH_SHORT).show();
				break;
			case 0x00000009:
				Toast.makeText(this, "	 * Value: 6 (0x00000009) (3.5G )", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000a:
				Toast.makeText(this, " * Constant Value: 10 (0x0000000a) ( 3G )联通", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000b:
				Toast.makeText(this, "	 * Constant Value: 11 (0x0000000b) (2G )", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000c:
				Toast.makeText(this, "* revision B Constant Value: 12 (0x0000000c) 3G-3.5G", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000d:
				Toast.makeText(this, "	 * Value: 13 (0x0000000d) (4G)", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000e:
				Toast.makeText(this, " * Constant Value: 14 (0x0000000e) 3G(3G到4G的升级产物)", Toast.LENGTH_SHORT).show();
				break;
			case 0x0000000f:
				Toast.makeText(this, "Current network is HSPA+ Constant Value: 15 (0x0000000f) ( 3G )",
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
		// wifi状态
		if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
			networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			Toast.makeText(this, "wifi网络! wifi", Toast.LENGTH_SHORT).show();
			tv_info.setText("Wifi网络连接开启 !" + "网络状态编码：Wifi");
		}

	}

	private NetworkReceive receiver;

	// 自定义广播接收者，检测网络状态变化
	public class NetworkReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(getApplicationContext(), "网络状态发生改变", Toast.LENGTH_SHORT).show();
			checkNetworkState();
		}

	}

	@Override
	protected void onResume() {
		// 注册广播
		receiver = new NetworkReceive();
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		this.registerReceiver(receiver, filter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		// 反注册广播
		super.onPause();
		this.unregisterReceiver(receiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}

/*
 * 手机网络状态编码 public static final int NETWORK_TYPE_UNKNOWN Network type is unknown
 * Constant Value: 0 (0x00000000) (不知道网络类型)
 * 
 * public static final int NETWORK_TYPE_GPRS Current network is GPRS Constant
 * Value: 1 (0x00000001) (2.5G）移动和联通
 * 
 * public static final int NETWORK_TYPE_EDGE Current network is EDGE Constant
 * Value: 2 (0x00000002) (2.75G)2.5G到3G的过渡 移动和联通
 * 
 * public static final int NETWORK_TYPE_UMTS Current network is UMTS Constant
 * Value: 3 (0x00000003) (3G)联通
 * 
 * -----------------Added in API level 4--------------------- public static
 * final int NETWORK_TYPE_CDMA Current network is CDMA: Either IS95A or IS95B
 * Constant Value: 4 (0x00000004) (2G 电信)
 * 
 * public static final int NETWORK_TYPE_EVDO_0 Current network is EVDO revision
 * 0 Constant Value: 5 (0x00000005) ( 3G )电信
 * 
 * public static final int NETWORK_TYPE_EVDO_A Current network is EVDO revision
 * A Constant Value: 6 (0x00000006) (3.5G) 属于3G过渡
 * 
 * public static final int NETWORK_TYPE_1xRTT Current network is 1xRTT Constant
 * Value: 7 (0x00000007) ( 2G )
 * 
 * ---------------------Added in API level 5-------------------- public static
 * final int NETWORK_TYPE_HSDPA Current network is HSDPA Constant Value: 8
 * (0x00000008) (3.5G )
 * 
 * public static final int NETWORK_TYPE_HSUPA Current network is HSUPA Constant
 * Value: 9 (0x00000009) ( 3.5G )
 * 
 * public static final int NETWORK_TYPE_HSPA Current network is HSPA Constant
 * Value: 10 (0x0000000a) ( 3G )联通
 * 
 * --------------------------Added in API level 8-------------------------
 * public static final int NETWORK_TYPE_IDEN Current network is iDen Constant
 * Value: 11 (0x0000000b) (2G )
 * 
 * --------------------------Added in API level 9-------------------------
 * public static final int NETWORK_TYPE_EVDO_B Current network is EVDO revision
 * B Constant Value: 12 (0x0000000c) 3G-3.5G
 * 
 * --------------------------Added in API level 11------------------------
 * public static final int NETWORK_TYPE_LTE Current network is LTE Constant
 * Value: 13 (0x0000000d) (4G)
 * 
 * public static final int NETWORK_TYPE_EHRPD Current network is eHRPD Constant
 * Value: 14 (0x0000000e) 3G(3G到4G的升级产物)
 * 
 * 
 * --------------------------Added in API level 13---------------------------
 * public static final int NETWORK_TYPE_HSPAP Current network is HSPA+ Constant
 * Value: 15 (0x0000000f) ( 3G )
 */
