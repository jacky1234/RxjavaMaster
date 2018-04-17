package com.jacky.rxmastertest;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;

import com.jacky.rxmastertest.module.LRC.Level0Item;
import com.jacky.rxmastertest.module.LRC.Level1Item;
import com.jacky.tg.ui.Components.recyclerview.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/4/17.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class DataHelper {
    private final static DataHelper instance = new DataHelper();

    public static DataHelper get() {
        return instance;
    }

    private DataHelper() {
    }

    public static SparseArray<List<Model>> sparseArray;

    public static final String[] first = {"创建操作", "变换操作", "过滤操作", "结合操作", "错误操作", "其他"};


    static {
        try {
            sparseArray = new SparseArray<>();

            //0 创建操作
            List<Model> models0 = new ArrayList<>();
            sparseArray.put(0, models0);

            //1 change
            List<Model> models1 = new ArrayList<>();
            sparseArray.put(1, models1);
            models1.add(new Model("buffer", Class.forName("com.jacky.rxmastertest.test.transform.BufferTest")));

            //2
            List<Model> models2 = new ArrayList<>();
            sparseArray.put(2, models2);

            //3
            List<Model> models3 = new ArrayList<>();
            sparseArray.put(3, models3);

            //4
            List<Model> models4 = new ArrayList<>();
            sparseArray.put(4, models4);

            //5
            List<Model> models5 = new ArrayList<>();
            sparseArray.put(5, models5);
            models5.add(new Model("获取验证码", Class.forName("com.jacky.rxmastertest.test.other.VerifyCodeTest")));
            models5.add(new Model("表单验证", Class.forName("com.jacky.rxmastertest.test.other.FormTest")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Model {
        String title;
        Class<?> aClass;

        public Model(String title, Class<?> aClass) {
            this.title = title;
            this.aClass = aClass;
        }
    }

    public List<MultiItemEntity> getData(final Activity activity) {
        List<MultiItemEntity> res = new ArrayList<>();
        int i, j, k;
        String[] titles, descs;
        Level1Item second;
        View.OnClickListener[] listeners;
        for (i = 0; i < first.length; i++) {
            Level0Item item0 = new Level0Item(i, first[i]);

            final List<Model> models = sparseArray.get(i);
            if (models != null && !models.isEmpty()) {
                for (final Model model : models) {
                    second = new Level1Item(model.title, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.startActivity(new Intent(activity, model.aClass));
                        }
                    });
                    item0.addSubItem(second);
                }
            }

//            if (i == 1) {
//                //buffer
//                second = new Level1Item("buffer");
//                item0.addSubItem(second);
//
//                titles = new String[]{"buffer(int)", "buffer(long,TimeUnit)"};
//                descs = new String[]{null, "防重复点击"};
//                listeners = new View.OnClickListener[]{buffer1OnclickListener, buffer2OnclickListener};
//                addSubItem(titles, descs, listeners, second);
//            }
//
//            if (i == 5) {
//                second = new Level1Item("practice");
//                item0.addSubItem(second);
//
//                titles = new String[]{"获取验证码", "表单验证"};
//                descs = new String[]{null, "结合RxBinding更高效"};
//                listeners = new View.OnClickListener[]{practice1OnClickListener, practice2OnClickListener};
//                addSubItem(titles, descs, listeners, second);
//            }
            res.add(item0);
        }
        return res;
    }

    public void launch() {

    }
}
