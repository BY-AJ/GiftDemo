package com.itcast.yb.packelves.service;

import android.content.Intent;
import android.net.Uri;

import com.itcast.yb.packelves.bean.DownloadInfoBean;
import com.itcast.yb.packelves.bean.WeekDetailsBean;
import com.itcast.yb.packelves.utils.UIUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yb on 2017/11/4.
 */

public class DownloadManager {
    public static final int STATE_UNDO = 1;
    public static final int STATE_WAITING = 2;
    public static final int STATE_DOWNLOADING = 3;
    public static final int STATE_PAUSE = 4;
    public static final int STATE_ERROR = 5;
    public static final int STATE_SUCCESS = 6;

    private static DownloadManager mDM = new DownloadManager();

    // 4. 观察者集合
    private ArrayList<DownloadObserver> mObservers = new ArrayList<DownloadManager.DownloadObserver>();

    // 下载对象的集合, 使用线程安全的HashMap
    // private HashMap<String, DownloadInfo> mDownloadInfoMap = new
    // HashMap<String, DownloadInfo>();
    private ConcurrentHashMap<Integer, DownloadInfo> mDownloadInfoMap = new ConcurrentHashMap<Integer, DownloadInfo>();

    // 下载任务的集合
    private ConcurrentHashMap<Integer, DownloadTask> mDownloadTaskMap = new ConcurrentHashMap<Integer, DownloadTask>();

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        return mDM;
    }

    // 2. 注册观察者
    public synchronized void registerObserver(DownloadObserver observer) {
        if (observer != null && !mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    // 3. 注销观察者
    public synchronized void unregisterObserver(DownloadObserver observer) {
        if (observer != null && mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    // 5.通知下载状态发生变化
    public synchronized void notifyDownloadStateChanged(DownloadInfo info) {
        for (DownloadObserver observer : mObservers) {
            observer.onDownloadStateChanged(info);
        }
    }

    // 6.通知下载进度发生变化
    public synchronized void notifyDownloadProgressChanged(DownloadInfo info) {
        for (DownloadObserver observer : mObservers) {
            observer.onDownloadProgressChanged(info);
        }
    }

    // 开始下载
    public synchronized void download(DownloadInfoBean.AppInfo info) {
        // 如果对象是第一次下载, 需要创建一个新的DownloadInfo对象,从头下载
        // 如果之前下载过, 要接着下载,实现断点续传
        DownloadInfo downloadInfo = mDownloadInfoMap.get(info.id);
        if (downloadInfo == null) {
            downloadInfo = DownloadInfo.copy(info);// 生成一个下载的对象
        }
        // 状态切换为等待下载
        downloadInfo.currentState = STATE_WAITING;
        // 通知所有的观察者, 状态发生变化了
        notifyDownloadStateChanged(downloadInfo);
        Logger.d(downloadInfo.name + "等待下载啦");
        // 将下载对象放入集合中
        mDownloadInfoMap.put(downloadInfo.id, downloadInfo);
        // 初始化下载任务, 并放入线程池中运行
        DownloadTask task = new DownloadTask(downloadInfo);
        ThreadManager.getThreadPool().execute(task);
        // 将下载任务放入集合中
        mDownloadTaskMap.put(downloadInfo.id, task);
    }

    // 下载任务对象
    class DownloadTask implements Runnable {
        private DownloadInfo downloadInfo;
        public DownloadTask(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void run() {
            Logger.d(downloadInfo.name + "开始下载啦");
            // 状态切换为正在下载
            downloadInfo.currentState = STATE_DOWNLOADING;
            notifyDownloadStateChanged(downloadInfo);
            File file = new File(downloadInfo.path);
            //apk的大小
            downloadInfo.setSize(getContentLength(downloadInfo.download_addr));
            //实现断点续传
            OkHttpClient client = new OkHttpClient();
            Request request ;
            if (!file.exists() || file.length() != downloadInfo.currentPos
                    || downloadInfo.currentPos == 0) {
                // 从头开始下载
                // 删除无效文件
                file.delete();// 文件如果不存在也是可以删除的, 只不过没有效果而已
                downloadInfo.currentPos = 0;// 当前下载位置置为0
                request = new Request.Builder()
                        .url(downloadInfo.download_addr)
                        .build();
            } else {
                // 断点续传
                // range 表示请求服务器从文件的哪个位置开始返回数据
                request = new Request.Builder()
                        .url(downloadInfo.download_addr)
                        .addHeader("RANGE","bytes="+file.length()+"-")
                        .build();
            }
            try {
                Response response = client.newCall(request).execute();
                if(response != null) {
                    InputStream in = response.body().byteStream();
                    // 要在原有文件基础上追加数据;
                    FileOutputStream out = new FileOutputStream(file, true);
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    // 只有状态是正在下载, 才继续轮询. 解决下载过程中中途暂停的问题
                    while ((len = in.read(buffer)) != -1
                            && downloadInfo.currentState == STATE_DOWNLOADING) {
                        out.write(buffer, 0, len);
                        out.flush();// 把剩余数据刷入本地
                        // 更新下载进度
                        downloadInfo.currentPos += len;
                        notifyDownloadProgressChanged(downloadInfo);
                    }
                    in.close();
                    out.close();
                    // 文件下载结束
                    if (file.length() == getContentLength(downloadInfo.download_addr)) {
                        // 文件完整, 表示下载成功
                        downloadInfo.currentState = STATE_SUCCESS;
                        notifyDownloadStateChanged(downloadInfo);
                    } else if (downloadInfo.currentState == STATE_PAUSE) {
                        // 中途暂停
                        notifyDownloadStateChanged(downloadInfo);
                    } else {
                        // 下载失败
                        file.delete();// 删除无效文件
                        downloadInfo.currentState = STATE_ERROR;
                        downloadInfo.currentPos = 0;
                        notifyDownloadStateChanged(downloadInfo);
                    }
                }else {
                    // 网络异常
                    file.delete();// 删除无效文件
                    downloadInfo.currentState = STATE_ERROR;
                    downloadInfo.currentPos = 0;
                    notifyDownloadStateChanged(downloadInfo);
                }
            } catch (Exception e) {
                Logger.d(e.getMessage());
            }
            // 从集合中移除下载任务
            mDownloadTaskMap.remove(downloadInfo.id);
        }
    }

    private long getContentLength(String downloadUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        try{
            Response response = client.newCall(request).execute();
            if(response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.body().close();
                return contentLength;
            }
        }catch (Exception e) {
            Logger.d(e.getMessage());
        }
        return 0;
    }

    // 下载暂停
    public synchronized void pause(DownloadInfoBean.AppInfo info) {
        // 取出下载对象
        DownloadInfo downloadInfo = mDownloadInfoMap.get(info.id);

        if (downloadInfo != null) {
            // 只有在正在下载和等待下载时才需要暂停
            if (downloadInfo.currentState == STATE_DOWNLOADING || downloadInfo.currentState == STATE_WAITING) {
                DownloadTask task = mDownloadTaskMap.get(downloadInfo.id);
                if (task != null) {
                    // 移除下载任务, 如果任务还没开始,正在等待, 可以通过此方法移除
                    // 如果任务已经开始运行, 需要在run方法里面进行中断
                    ThreadManager.getThreadPool().cancel(task);
                }
                // 将下载状态切换为暂停
                downloadInfo.currentState = STATE_PAUSE;
                notifyDownloadStateChanged(downloadInfo);
            }
        }
    }

    // 开始安装
    public synchronized void install(DownloadInfoBean.AppInfo info) {
        DownloadInfo downloadInfo = mDownloadInfoMap.get(info.id);
        if (downloadInfo != null) {
            // 跳到系统的安装页面进行安装
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + downloadInfo.path),
                    "application/vnd.android.package-archive");
            UIUtils.getContext().startActivity(intent);
        }
    }

    /**
     * 1. 声明观察者的接口
     */
    public interface DownloadObserver {

        // 下载状态发生变化
        public void onDownloadStateChanged(DownloadInfo info);

        // 下载进度发生变化
        public void onDownloadProgressChanged(DownloadInfo info);
    }

    // 根据应用信息返回下载对象
    public DownloadInfo getDownloadInfo(DownloadInfoBean.AppInfo info) {
        return mDownloadInfoMap.get(info.id);
    }

    public DownloadInfo getDownloadInfo(WeekDetailsBean.DetailsInfo info) {
        return mDownloadInfoMap.get(info.id);
    }
}
