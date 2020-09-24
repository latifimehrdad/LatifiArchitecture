package ir.mlcode.latifiarchitecturelibrary.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.subjects.PublishSubject;

public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private String path;
    private PublishSubject<Byte> publishSubject;
    public static int progressDownload;


    public DownloadTask(Context context, String path, PublishSubject<Byte> publishSubject) {
        this.context = context;
        this.path = Environment.getExternalStorageDirectory() + "/WMS/" + path;
        this.publishSubject = publishSubject;
        progressDownload = 0;
    }



    @Override
    protected String doInBackground(String... sUrl) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {

            File file = new File(Environment.getExternalStorageDirectory() + "/WMS/");
            if (!file.exists()) {
                file.mkdir();
            }

            URL url = new URL(sUrl[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(path);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        progressDownload = progress[0];

    }


    @Override
    protected void onPostExecute(String result) {
        publishSubject.onNext(StaticValues.ML_FileDownloaded);
    }

}