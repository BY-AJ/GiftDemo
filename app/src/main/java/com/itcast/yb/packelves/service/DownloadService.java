package com.itcast.yb.packelves.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
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
    private String downloadUrl = null;
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
            installApk(task.getFile());
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
        public void startDownload(String url) {
            if(task == null) {
                downloadUrl = url;
                task = new DownloadTask(listener);
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
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        if(progress >0) {
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }

    // 开始安装
    public synchronized void installApk(File file) {
        if (file != null) {
            // 跳到系统的安装页面进行安装
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory("adnroid.intent.category.DEFAULT");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            UIUtils.getContext().startActivity(intent);
        }
    }
}
