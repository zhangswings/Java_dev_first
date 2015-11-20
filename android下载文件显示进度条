package com.wyz.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.downloadfile.DownloadService;
import com.pdsu.hello.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DlodActivity extends Activity {

	// private static final String PATH =
	// "http://192.168.0.187:8012/xml/2015110316395194.db";
	private ProgressBar bar = null;
	private TextView info = null;
	private Button but_dlod;
	long time_locked = 0;

	private static String path_db = Environment.getExternalStorageDirectory().toString() + "/mui/zhangswings.db";
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlod_main);
		// instantiate it within the onCreate method
		// new ChildAsyncTask().execute("http://192.168.0.188:8098/db/pd.db");

		// downloadTask.execute("the url to the file you want to download");
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("A message");
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);

		this.info = (TextView) super.findViewById(R.id.info);
		this.bar = (ProgressBar) super.findViewById(R.id.bar);
		// new Thread(new ChileHandler()).start();
		but_dlod = (Button) super.findViewById(R.id.but_dlod);
		but_dlod.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				long current_time = System.currentTimeMillis() - time_locked;
				if (current_time > 10000) {
					DlodActivity.this.but_dlod.setClickable(false);
					DlodActivity.this.but_dlod.setBackgroundColor(Color.RED);
					DlodActivity.this.bar.setVisibility(View.VISIBLE);
					ChildAsyncTask downloadTask = new ChildAsyncTask();
					// child.execute("http://w.x.baidu.com/go/full/1/50002");
					downloadTask.execute("http://192.168.0.188:8098/db/pd.db");
					// downloadTask.execute("http://192.168.0.188:8098/db/pd.db");
					/*
					 * mProgressDialog.show(); Intent intent = new
					 * Intent(DlodActivity.this, DownloadService.class);
					 * intent.putExtra("url",
					 * "http://192.168.0.188:8098/db/pd.db");
					 * intent.putExtra("receiver", new DownloadReceiver(new
					 * Handler())); startService(intent);
					 */} else {
					showToast("稍等" + (10 - (int) current_time / 1000) + "s，即可再次更新");
				}
			}
		});

	}

	private Toast mToast = null;

	private void showToast(String str) {
		if (mToast == null) {
			mToast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(str);
		}
		mToast.show();
	}

	private class DownloadReceiver extends ResultReceiver {
		public DownloadReceiver(Handler handler) {
			super(handler);
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			super.onReceiveResult(resultCode, resultData);
			if (resultCode == DownloadService.UPDATE_PROGRESS) {
				int progress = resultData.getInt("progress");
				mProgressDialog.setProgress(progress);
				if (progress == 100) {
					mProgressDialog.dismiss();
				}
			}
		}
	}

	/**
	 * AsyncTask<String, Integer, String> 1.String excute提交网址字符串
	 * 2.Integer更新进度条数据 3.String 最终返回数据
	 * 
	 * @author swings
	 *
	 */
	public class ChildAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog.show();

		}

		@Override
		protected void onPostExecute(String result) {
			DlodActivity.this.info.setText(result);
			if ("下载完毕".equals(result)) {
				DlodActivity.this.but_dlod.setBackgroundColor(Color.WHITE);
				DlodActivity.this.bar.setVisibility(View.GONE);
				DlodActivity.this.but_dlod.setClickable(true);
			}
			// execute.setEnabled(true);
			// cancel.setEnabled(false);
			mProgressDialog.dismiss();
			time_locked = System.currentTimeMillis();
			onCancelled();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setMax(100);
			mProgressDialog.setProgress(values[0]);
			DlodActivity.this.info.setText("当前进度值为：" + String.valueOf(values[0]));
			bar.setProgress(values[0]);
		}

		@Override
		protected String doInBackground(String... params) {
			InputStream is = null;
			OutputStream os = null;
			HttpURLConnection con = null;
			File file = new File(path_db);
			// 如果目标文件已经存在，则删除。产生覆盖旧文件的效果
			if (!file.exists()) {
				file.mkdirs();
			}
			String path = path_db + "pd.db";
			file = new File(path);

			if (file.exists()) {
				file.delete();
			}
			try {
				// 构造URL
				URL url = new URL(params[0]);
				// 打开连接
				con = (HttpURLConnection) url.openConnection();
				// 获得文件的长度
				if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
					return "Server returned HTTP " + con.getResponseCode() + " " + con.getResponseMessage();
				}
				int contentLength = con.getContentLength();
				System.out.println("数据长度 :" + contentLength);
				// 输入流
				is = con.getInputStream();
				// 输出的文件流
				os = new FileOutputStream(path);
				// 1K的数据缓冲
				byte buffer[] = new byte[1024 * 4];
				// 读取到的数据长度
				int len = 0;
				long total = 0;
				// 开始读取
				while ((len = is.read(buffer)) != -1) {
					if (isCancelled()) {
						is.close();
						return "取消下载";
					}
					total += len;
					if (contentLength > 0) // only if total length is known
						// (int) ((total / (float) contentLength) * 100)
						// publishProgress((int) (total * 100 / contentLength));
						publishProgress((int) ((total / (float) contentLength) * 100));
					os.write(buffer, 0, len);
				}
				os.flush();
				// 完毕，关闭所有链接
				// os.close();
				// is.close();

			} catch (Exception e) {
				e.printStackTrace();
				return "下载失败";
			} finally {
				try {
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (con != null)
					con.disconnect();
			}
			publishProgress(0);
			return "下载完毕";
		}

	}

}
