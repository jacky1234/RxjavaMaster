package com.jacky.rxmastertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rx.Subscription;

/**
 * 2017/12/28.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class BaseActivity extends AppCompatActivity {
    protected String TAG;

    protected Subscription mSubscription;

    protected void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }

    protected void toast(String message, Object... objects) {
        Toast.makeText(this, String.format(message, objects), Toast.LENGTH_SHORT).show();
    }
}
