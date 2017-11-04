package com.itcast.yb.packelves.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.activity.DownloadActivity;
import com.itcast.yb.packelves.utils.UIUtils;

import java.io.File;

/**
 * Created by yb on 2017/11/3.
 */

public class DownloadService extends Service{
    private DownloadTask task ;
    private String downloadUrl;
    private int mAppId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onSuccess() {
            stopForeground(true);
            getManager().notify(1,getNotification("下载成功...",-1));
            installApk(getApkFile());
            task = null;
        }
        @Override
        public void onFailed() {
            task = null;
            stopForeground(true);
            getManager().notify(1,getNotification("下载失败...",-1));
        }
        @Override
        public void onProgress(int progress) {
            getManager().notify(1,getNotification("下载中...",progress));
        }
    };

    public class DownloadBinder extends Binder {
        public void startDownload(String url,int appId) {
            if(task == null) {
                downloadUrl = url;
                mAppId = appId;
                task = new DownloadTask(listener,mAppId);
                task.execute(downloadUrl);
                startForeground(1,getNotification("开始下载",0));
            }
        }
    }

    private NotificationManager getManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this,DownloadActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.drawable.logo1);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        if(progress >0) {
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }

    /**
     * 获取下载的文件
     */
    private File getApkFile() {
        String fileName = File.separator+mAppId +".apk";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        return new File(path+fileName);
    }

    /**
     * 安装Apk
     */
    protected void installApk(File file) {
        // 跳到系统的安装页面进行安装
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        UIUtils.getContext().startActivity(intent);
    }
}
