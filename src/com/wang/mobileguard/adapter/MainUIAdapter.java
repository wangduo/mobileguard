package com.wang.mobileguard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.mobileguard.R;

public class MainUIAdapter extends BaseAdapter {
	private static final String[] NAMES = new String[]{"手机防盗","通讯卫士","软件管理","任务管理","流量查看","手机杀毒","系统优化"," 常用工具","设置中心"};
		
	private static final int[] ICONS = new int[]{R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,
		R.drawable.taskmanager,R.drawable.netmanager,R.drawable.trojan,R.drawable.sysoptimize,R.drawable.atools,R.drawable.settings};
	
	private LayoutInflater inflater;
	private Context context;
	
	public MainUIAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NAMES.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.main_gv_item, null);
		ImageView imageView = (ImageView)view.findViewById(R.id.gv_img_id);
		imageView.setImageResource(ICONS[position]);
//		imageView.setBackground(context.getResources().getDrawable(ICONS[position]));
//		imageView.setAlpha(0.5f);
		TextView txtView = (TextView)view.findViewById(R.id.gv_txt_id);
		txtView.setText(NAMES[position]);
		return view;
	}

}
