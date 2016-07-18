package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import com.example.douglas.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by douglas on 24/05/2016.
 */
public class AsyncTest extends InstrumentationTestCase{

    public void testAsyncTest() throws Throwable {


        final CountDownLatch signal = new CountDownLatch(1);


        final AsyncTask<Void, Void, String> EndpointsAsyncTask = new AsyncTask<Void, Void, String>() {
            private MyApi myApiService = null;

            @Override
            protected String doInBackground(Void... params) {
                if (myApiService == null) {
                    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                            .setRootUrl("https://black-backup-131917.appspot.com/_ah/api/");

                    myApiService = builder.build();
                }

                try {
                    return myApiService.joke().execute().getData();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                assertNotNull(result);
                signal.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                EndpointsAsyncTask.execute();
            }
        });

        signal.await(30, TimeUnit.SECONDS);
    }
}
