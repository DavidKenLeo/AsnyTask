package com.dkl.auser.asnytask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        imageView = (ImageView)findViewById(R.id.imageView);
        MyTask task = new MyTask();
        task.execute();
//        task.execute("https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif");
//        task.execute("https://upload.wikimedia.org/wikipedia/commons/e/e0/JPEG_example_JPG_RIP_050.jpg");
    }

    class MyTask extends AsyncTask<String,Integer,Bitmap>{

        private ProgressDialog progressBar;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = new ProgressDialog(MainActivity.this);
            progressBar.setMessage("Loading...");
            progressBar.setCancelable(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.show();
        }



        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
//tv.setText(String.valueOf(values[0]));

        }

        @Override
        protected Bitmap doInBackground(String... params) {
//            int i=params[0];
//            for(int n=i;n>=0;n--){
//                publishProgress(n);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.d("Task",String.valueOf(n));
//
//            }
//            return "ok";
//            String urlStr = params[0];
            try {
                URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif");
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {

//            tv.setText(s);
            super.onPostExecute(bitmap);

            progressBar.dismiss();
            //當完成的時候，把進度條消失
            imageView.setImageBitmap(bitmap);
        }
    }
}
