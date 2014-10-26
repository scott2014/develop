package com.jtjr99.jiayoubao.download;

public interface DownloadListener {
	public void downloadComplete();
	/**
	 * @param code  错误码
	 * @param msg   错误信息
	 */
	public void downloadFail(int code,String msg);
	/**
	 * @param progress  下载进度,用下载大小表示
	 * @param percent   下载进度,用下载百分比表示
	 */
	public void downloading(long progress,float percent);
}
