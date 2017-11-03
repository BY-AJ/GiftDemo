package com.itcast.yb.packelves.service;

/**
 * Created by yb on 2017/11/3.
 */

public interface DownloadListener {
    void onSuccess();
    void onFailed();
    void onProgress(int progress);
}
