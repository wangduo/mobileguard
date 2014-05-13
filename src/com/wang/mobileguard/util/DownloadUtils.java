package com.wang.mobileguard.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;

public class DownloadUtils {
	public static URL url = null;
	public static HttpURLConnection con = null;
	public static InputStream is;
	public static InputStream getInputStream(String path) throws IOException{
		url = new URL(path);
		con = (HttpURLConnection)url.openConnection();
//		con.setConnectTimeout(5000);
		con.setRequestMethod("GET");
		if(con.getResponseCode() == 200){
			return con.getInputStream();
		}
		return null;
	}
	
	public static File downLoadFile(String remotePath,String localPath, ProgressDialog progressDialog) throws IOException{
		InputStream is = getInputStream(remotePath);
		if(is == null){
			return null;
		}
		File file = new File(localPath);
		if(!file.isFile()){
			file.mkdirs();
		}
		OutputStream os = new FileOutputStream(file);
		int max = con.getContentLength();
		progressDialog.setMax(max);
		byte[] buffer = new byte[1024];
		int len =0;
		int progress = 0;
		while((len=is.read(buffer))!=-1){
			os.write(buffer,0,len);
			progress += len;
			progressDialog.setProgress(progress);
		}
		progressDialog.dismiss();
		os.flush();
		os.close();
		is.close();
		return file;
	}
}
