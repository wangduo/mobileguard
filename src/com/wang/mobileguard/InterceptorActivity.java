package com.wang.mobileguard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class InterceptorActivity extends Activity {

	private TabHost tabHost; 
    private TabWidget mTabWidget;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interceptoractivity);
		TextView  txtView = new TextView(this);
		txtView.setText("�㽶");
		TextView  appleTxt = new TextView(this);
		appleTxt.setText("�㽶");
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		TabWidget tabWidget = tabHost.getTabWidget();  
		
        tabHost.addTab(tabHost.newTabSpec("android").setIndicator(txtView).setContent(R.id.widget59));
        tabHost.addTab(tabHost.newTabSpec("apple").setIndicator(appleTxt).setContent(R.id.widget58));

	}
}
