package com.jacky.rxmastertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jacky.tg.ui.Components.recyclerview.entity.MultiItemEntity;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 2017/12/28.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class MainActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    ExpandableItemAdapter adapter;
    List<MultiItemEntity> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        list = DataHelper.get().getData(this);

        mRecyclerView = findViewById(R.id.recyclerview);
        adapter = new ExpandableItemAdapter(list);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_OPERATION ? 1 : manager.getSpanCount();
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
    }

    //表单验证，结合 RxBinding更 搞笑
    final View.OnClickListener practice2OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            //rxBinding
            /*Observable<CharSequence> ObservableName = RxTextView.textChanges(mEtPhone);
            Observable<CharSequence> ObservablePassword = RxTextView.textChanges(mEtPassword);

            Observable.combineLatest(ObservableName, ObservablePassword, new Func2<CharSequence, CharSequence, Boolean>() {
                @Override
                public Boolean call(CharSequence phone, CharSequence password) {
                    return isPhoneValid(phone.toString()) && isPasswordValid(password.toString());
                }
            }).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {
                    RxView.enabled(mBtLogin).call(aBoolean);
                }
            });

            RxView.clicks(mBtLogin)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            Toast.makeText(LoginActivity.this, "登录成功！" ,Toast.LENGTH_SHORT).show();
                        }
                    });*/

        }
    };

    final int SECOND = 20;
    final View.OnClickListener practice1OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            unSubscribe();
            v.setEnabled(false);
            final TextView mTextView = v.findViewById(R.id.tv);
            mSubscription = Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .take(SECOND)
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onCompleted() {
                            v.setEnabled(true);
                            mTextView.setText("获取验证码");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("practice", e.toString());
                        }

                        @Override
                        public void onNext(Long aLong) {
                            Logger.t("practice").d("剩余" + (SECOND - aLong) + "秒");
                            mTextView.setText("剩余" + (SECOND - aLong) + "秒");
                        }
                    });

        }
    };

    private int i = 0; //记录点击次数

    final View.OnClickListener buffer2OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            unSubscribe();
            i++;
            mSubscription = Observable.create(new Observable.OnSubscribe<View>() {
                @Override
                public void call(Subscriber<? super View> subscriber) {
                    if (subscriber.isUnsubscribed()) return;
                    Log.i(TAG, "按钮点击" + i + "次");
                    subscriber.onNext(v);
                }
            })
                    .buffer(500, TimeUnit.MILLISECONDS)
                    .subscribe(new Action1<List<View>>() {
                        @Override
                        public void call(List<View> views) {
                            if (views.isEmpty()) return;
                            final View view = views.get(views.size() - 1);
                            i = 0;
                            Logger.t("buffer").d("clicked...");
                            toast("clicked...");
                        }
                    });
        }
    };

    //buffer(int count)
    final View.OnClickListener buffer1OnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            unSubscribe();

            mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                    .buffer(5)
                    .subscribe(new Action1<List<Long>>() {
                        @Override
                        public void call(List<Long> longs) {
                            Logger.t("buffer").d(longs.toString());
                        }
                    });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}
