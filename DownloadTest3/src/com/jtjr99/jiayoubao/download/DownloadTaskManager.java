package com.jtjr99.jiayoubao.download;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author scott
 * 下载任务管理类
 * 
 * 作用:
 * 1\控制下载任务数
 * 2\监听
 */

public class DownloadTaskManager {
	//下载任务数
	public static final int DEFAULT_DOWNLOAD_TASK_NUMBER = 1;
	private int taskCount = DEFAULT_DOWNLOAD_TASK_NUMBER;
	
	//下载任务
	private ConcurrentLinkedQueue<DownloadTask> tasks = null;
	
	private static DownloadTaskManager instance = null;
	
	private DownloadTaskManager() {}
	
	public DownloadTaskManager getInstance() {
		if (instance == null) {
			instance = new DownloadTaskManager();
		}
		return instance;
	}
	
	public void init() {
		tasks = new ConcurrentLinkedQueue<DownloadTask>();
		
		
	}
	
	//开始下载任务
	public void startDownloadTask(final int threadCount,final String url,final String path,final String fileName,final DownloadListener listener) {
		try {
			new Thread() {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse resp = client.execute(get);
				
				Header[] headers = resp.getHeaders("Content-Length");
				
				if (headers == null || headers.length == 0) {
					listener.downloadFail(0, "");
				} else {
					long size = Long.parseLong(headers[0].getValue());
					long range = size / threadCount;
					
					for (int i=0;i<threadCount;i++) {
						new DownloadTask(listener).execute(url,path,fileName);
					}
				}
				
			}.start();
		} catch (IOException e) {
			listener.downloadFail(0, e.getMessage());
		}
	}
}
