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
	private static final String[] NAMES = new String[]{"�ֻ�����","ͨѶ��ʿ","�������","�������","�����鿴",
		"�ֻ�ɱ��","ϵͳ�Ż�"," ���ù���","��������"};
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
				//�ֻ�����
				intent = new Intent(this,SecurityActivity.class);
				startActivity(intent);
				break;
			case 1:
				//ͨѶ��ʿ
				break;
			case 2:
				//�������
				break;
			case 3:
				//�������
				break;
			case 4:
				//�����鿴
				break;
			case 5:
				//�ֻ�ɱ��
				break;
			case 6:
				//ϵͳ�Ż�
				break;
			case 7:
				//���ù���
				intent = new Intent(this,InterceptorActivity.class);
				startActivity(intent);
				break;
			case 8:
				//��������
				break;
		}
		
	}
}
