package com.wang.mobileguard;

import com.wang.mobileguard.adapter.MainUIAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener{
	private static final String[] NAMES = new String[]{"手机防盗","通讯卫士","软件管理","任务管理","流量查看",
		"手机杀毒","系统优化"," 常用工具","设置中心"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainactivity);
		GridView gridView = (GridView)findViewById(R.id.gv_main);
		gridView.setAdapter(new MainUIAdapter(this));
		gridView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
		switch(position){
			case 0:
				//手机防盗
				intent = new Intent(this,SecurityActivity.class);
				startActivity(intent);
				break;
			case 1:
				//通讯卫士
				break;
			case 2:
				//软件管理
				break;
			case 3:
				//任务管理
				break;
			case 4:
				//流量查看
				break;
			case 5:
				//手机杀毒
				break;
			case 6:
				//系统优化
				break;
			case 7:
				//常用工具
				intent = new Intent(this,InterceptorActivity.class);
				startActivity(intent);
				break;
			case 8:
				//设置中心
				break;
		}
		
	}
}
