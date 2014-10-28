package com.jtjr99.jiayoubao.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;


public class DownloadTask extends AsyncTask<String,Long,Long> {
	
	private DownloadListener listener;
	
	//下载链接
	private String url;
	//开始位置
	private long startPos;
	//结束位置
	private long endPos;
	
	public DownloadTask(DownloadListener listener) {
		this.listener = listener;
	}
	
	@Override
	protected Long doInBackground(String... params) {
		String url = params[0];
		String size = params[1];
		String startPos = params[2];
		String endPos = params[3];
		String path = params[4];
		String fileName = params[5];
		
		RandomAccessFile acf = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		
		try {
			acf = new RandomAccessFile(new File(path + "/" + fileName),"rwd");
			acf.setLength(Long.parseLong(size));
			Log.e("size",size + "");
			//acf.close();
			get.setHeader("Range", "bytes=" + startPos + "-" + endPos);
			HttpResponse response = client.execute(get);
			InputStream is = response.getEntity().getContent();
			
			acf.seek(Long.parseLong(startPos));
			
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = is.read(buffer)) > 0) {
				Log.e("download",i + "");
				acf.write(buffer,0,i);
			}
			
			acf.close();
			is.close();
		
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}
		
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Long result) {
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Long... values) {
		super.onProgressUpdate(values);
	}
}
