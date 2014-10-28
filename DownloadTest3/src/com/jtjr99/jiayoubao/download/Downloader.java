package com.jtjr99.jiayoubao.download;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scott
 * 文件下载类
 */

public class Downloader {
	
	private static List<DownloadTaskInfo> infos = null;
	
	private static Downloader instance = null;
	
	public static Downloader getInstance() {
		if (instance == null) {
			instance = new Downloader();
		}
		if (infos == null) {
			infos = new ArrayList<DownloadTaskInfo>();
		}
		return instance;
	}
	
	public void addTask(DownloadTaskInfo info) {
		infos.add(info);
	}
	
	//开始下载
	public void start() {
		for (DownloadTaskInfo info : infos) {
			new DownloadTask(null).execute(info.url,info.size + "",info.startPos + "",info.endPos + "",info.path,info.fileName);
		}
	}
}
