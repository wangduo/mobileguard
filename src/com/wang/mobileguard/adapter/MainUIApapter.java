package com.wang.mobileguard.adapter;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainUIApapter extends BaseAdapter {
	private static final String[] NAMES = new String[]{"手机防盗", "通讯卫士", "软件管理", "流量管理", "任务管理", "手机杀毒", 
            "系统优化", "高级工具", "设置中心"};
		
	private static final int[] ICONS = new int[]{R.drawable.widget01, R.drawable.widget02, R.drawable.widget03, 
                R.drawable.widget04, R.drawable.widget05, R.drawable.widget06, R.drawable.widget07, 
                R.drawable.widget08, R.drawable.widget09};
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
