package com.wang.mobileguard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.mobileguard.util.DownloadUtils;
import com.wang.mobileguard.vo.UpdateInfo;

public class SplashActivity extends Activity {
	
	private static final int STARTOTHERACTIVITY = 1;
	private static final int UPDATEPROGRESSDIALOG = 1;
	private TextView versionTxt;
	private ProgressBar versionBar;
	private ProgressDialog progressDialog;
	private Handler handler;
	private UpdateInfo updateInfo;
	private Message msg = new Message();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		//设置全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		versionTxt = (TextView)findViewById(R.id.verionTxt);
		versionBar = (ProgressBar)findViewById(R.id.versionBar);
		
		versionTxt.setText("版本号："+getVersion());
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在下载中。。。");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		new Thread(){
			public void run(){
				updateInfo = getUpdateInfo();
				//是否是最新版本
				if(updateInfo.getVersion().equals(versionTxt.getText())){
					msg.what = STARTOTHERACTIVITY;
					handler.sendMessage(msg);
				}else{
					showUpdateDialog();
				}
			}
		}.start();
		
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				//链接服务器后隐藏掉进度条
				if(msg.what == 1){
					versionBar.setVisibility(View.INVISIBLE);
					Intent intent = new Intent(SplashActivity.this,MainActivity.class);
					startActivity(intent);
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	private String getVersion(){
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo pInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return pInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return "版本号未知";
		}
	}

	private UpdateInfo getUpdateInfo() {
		UpdateInfo updateInfo = new UpdateInfo();
		String path = getResources().getString(R.string.updatePath);
		InputStream is = null;
		try {
			is = DownloadUtils.getInputStream(path);
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "utf-8");
			int type = parser.getEventType();
			while(type != XmlPullParser.END_DOCUMENT){
				switch(type){
					case XmlPullParser.START_TAG :
						if(parser.getName().equals("version")){
							updateInfo.setVersion(parser.nextText());
						} else if(parser.getName().equals("description")){
							updateInfo.setDescription(parser.nextText());
						}else if(parser.getName().equals("url")){
							updateInfo.setUrl(parser.nextText());
						}
						break;
					default :
						break;
				}
			}
		} catch (IOException e) {
			updateInfo.setVersion(String.valueOf(versionTxt.getText()));
			return updateInfo;
		} catch (XmlPullParserException e1){
			updateInfo.setVersion(String.valueOf(versionTxt.getText()));
			return updateInfo;
		}
		return updateInfo;
	}
	
	private void showUpdateDialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("升级提醒");
		dialog.setMessage(updateInfo.getDescription());
		dialog.setCancelable(false);
		dialog.setPositiveButton("更新", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					String path = getResources().getString(R.string.updateApkPath);
					progressDialog.show();
					String localPath = Environment.getExternalStorageState()+"/mobileguard/new.apk";
					try {
						File apkFile = DownloadUtils.downLoadFile(path, localPath, progressDialog);
						install(apkFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Toast.makeText(SplashActivity.this, "SD卡不可用,请插入SD卡", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		dialog.setNegativeButton("暂不更新", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				msg.what = STARTOTHERACTIVITY;
				handler.sendMessage(msg);
			}
		});
	}
	private void install(File apkFile){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
		startActivity(intent);
	}
}
