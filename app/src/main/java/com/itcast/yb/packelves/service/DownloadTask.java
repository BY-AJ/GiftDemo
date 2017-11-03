package com.itcast.yb.packelves.service;

import android.os.AsyncTask;
import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yb on 2017/11/3.
 */

public class DownloadTask extends AsyncTask<String,Integer,Integer>{
    private static final int TYPE_SUCCESS =1;//下载成功
    private static final int TYPE_FAILED =2;//下载失败

    private int lastProgress;
    private DownloadListener listener;
    private File file = null;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        String downloadUrl = params[0];
        InputStream in = null;
        RandomAccessFile saveFile = null;
        try {
            long downloadLength = 0;//下载文件的长度
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            Logger.d("apk="+fileName);
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            Logger.d("path="+path);
            file = new File(path+fileName);
            if(file.exists()) {
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if(contentLength == 0) {
                return TYPE_FAILED;
            }else if(contentLength == downloadLength) {
                return TYPE_SUCCESS;
            }
            //实现断点续传
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(downloadUrl)
                    .addHeader("RANGE","bytes="+downloadLength+"-")
                    .build();
            Response response = client.newCall(request).execute();
            if(response != null) {
                in = response.body().byteStream();
                saveFile = new RandomAccessFile(file,"rw");
                saveFile.seek(downloadLength);
                byte[] buff = new byte[1024];
                int len = 0;
                int total = 0;
                while((len = in.read(buff))!= -1) {
                    total+=len;
                    saveFile.write(buff,0,len);
                    int progress = (int) ((total+downloadLength)*100/contentLength);
                    publishProgress(progress);
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e) {
            Logger.d(e.getMessage());
        }finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(saveFile != null) {
                    saveFile.close();
                }
                if(file != null) {
                    file.delete();
                }
            }catch (Exception e) {
                Logger.d(e.getMessage());
            }
        }
        return TYPE_FAILED;
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        Integer progress = values[0];
        if(progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS :
                listener.onSuccess();
                break;
            case TYPE_FAILED :
                listener.onFailed();
                break;
            default:
                break;
        }
    }

    public File getFile() {
        return file;
    }
}
