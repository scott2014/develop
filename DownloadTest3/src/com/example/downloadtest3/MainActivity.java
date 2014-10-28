package com.example.downloadtest3;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jtjr99.jiayoubao.download.DownloadListener;
import com.jtjr99.jiayoubao.download.DownloadTaskManager;

public class MainActivity extends ActionBarActivity implements DownloadListener {
	
	String url = "https://www.jtjr99.com/downloads/Jyb.latest.apk";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		
		DownloadTaskManager.getInstance().startDownloadTask(3, url, path, "jyb444.apk",this );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void downloadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadFail(int code, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloading(long progress, float percent) {
		// TODO Auto-generated method stub
		
	}
}
