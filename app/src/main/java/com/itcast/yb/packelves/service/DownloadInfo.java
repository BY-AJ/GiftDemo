package com.itcast.yb.packelves.service;

import android.os.Environment;

import com.itcast.yb.packelves.bean.DownloadInfoBean;

import java.io.File;

/**
 * Created by yb on 2017/11/4.
 */

public class DownloadInfo {

    public String download_addr;//下载地址
    public String name;//下载apk的名字
    public int id;//下载的id

    public long currentPos;// 当前下载位置
    public int currentState;// 当前下载状态
    public String path;// 下载到本地文件的路径
    public long size;//下载的总大小
    public static final String PACKELVES = "PACK_ELVES";// sdcard根目录文件夹名称
    public static final String DONWLOAD = "download";// 子文件夹名称, 存放下载的文件

    // 获取下载进度(0-1)
    public float getProgress() {
        if (size == 0) {
            return 0;
        }
        float progress = currentPos / (float) size;
        return progress;
    }

    /**
     * 设置下载文件的大小
     */
    public void setSize(long size) {
        this.size = size;
    }


    // 拷贝对象, 从AppInfo中拷贝出一个DownloadInfo
    public static DownloadInfo copy(DownloadInfoBean.AppInfo info) {
        DownloadInfo downloadInfo = new DownloadInfo();

        downloadInfo.id = info.id;
        downloadInfo.name = info.name;
        downloadInfo.download_addr = info.download_addr;

        downloadInfo.currentPos = 0;
        downloadInfo.currentState = DownloadManager.STATE_UNDO;// 默认状态是未下载
        downloadInfo.path = downloadInfo.getFilePath();

        return downloadInfo;
    }

    // 获取文件下载路径
    public String getFilePath() {
        StringBuffer sb = new StringBuffer();
        String sdcard = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        sb.append(sdcard);
        // sb.append("/");
        sb.append(File.separator);
        sb.append(PACKELVES);
        sb.append(File.separator);
        sb.append(DONWLOAD);
        if (createDir(sb.toString())) {
            // 文件夹存在或者已经创建完成
            return sb.toString() + File.separator + id + ".apk";// 返回文件路径
        }

        return null;
    }

    private boolean createDir(String dir) {
        File dirFile = new File(dir);
        // 文件夹不存在或者不是一个文件夹
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return dirFile.mkdirs();
        }
        return true;// 文件夹存在
    }
}
