package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

    public void testEndpointsAsyncTask(){
        final CountDownLatch latch = new CountDownLatch(1);
        MainFlavorActivity.EndpointsAsyncTask task = new MainFlavorActivity.EndpointsAsyncTask();
        task.setListener(new MainFlavorActivity.EndpointsAsyncTask.MyAsyncTaskListener() {
            @Override
            public void onComplete(String retVal) {
                assertTrue(!TextUtils.isEmpty(retVal));
                latch.countDown();
            }
        });
        task.execute(getContext());
        try {
            latch.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}