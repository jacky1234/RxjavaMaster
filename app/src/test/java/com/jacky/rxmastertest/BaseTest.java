package com.jacky.rxmastertest;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 2017/12/28.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class BaseTest {
    private Subscription mSubscription;

    private void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void print(String info) {
        System.out.println("Thread:" + Thread.currentThread().getName() + "," + info);
    }

    @Test
    public void rxBuffer_test() {
        unSubscribe();

        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .buffer(5)
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        print(longs.toString());
                    }
                });
    }

    @Test
    public void subject() {
        Subject subject = PublishSubject.create();

        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(aLong);
                    }
                });


    }
}
