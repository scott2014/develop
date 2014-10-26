package com.jtjr99.jiayoubao.download;

import android.os.AsyncTask;


public class DownloadTask extends AsyncTask<String,Long,Long> {
	
	private DownloadListener listener;
	
	public DownloadTask(DownloadListener listener) {
		this.listener = listener;
	}
	
	@Override
	protected Long doInBackground(String... params) {
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
