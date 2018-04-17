package com.jacky.rxmastertest.module;

import android.view.View;

import com.jacky.rxmastertest.ExpandableItemAdapter;
import com.jacky.tg.ui.Components.recyclerview.entity.AbstractExpandableItem;
import com.jacky.tg.ui.Components.recyclerview.entity.MultiItemEntity;

/**
 * 2017/12/28.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class LRC {
    public static class Operation implements MultiItemEntity {
        public String title;
        public String desc;
        public View.OnClickListener mOnClickListener;

        public Operation(String title, String desc, View.OnClickListener onClickListener) {
            this.title = title;
            this.desc = desc;
            mOnClickListener = onClickListener;
        }

        @Override
        public int getItemType() {
            return ExpandableItemAdapter.TYPE_OPERATION;
        }
    }

    public static class Level1Item extends AbstractExpandableItem<Operation> implements MultiItemEntity {
        public String title;
        public View.OnClickListener mOnClickListener;

        public Level1Item(final String title, final View.OnClickListener onClickListener) {
            this.title = title;
            this.mOnClickListener = onClickListener;
        }

        @Override
        public int getLevel() {
            return 1;
        }

        @Override
        public int getItemType() {
            return ExpandableItemAdapter.TYPE_LEVEL_1;
        }
    }

    public static class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
        public String title;
        public int cataType;

        public Level0Item(int cataType, String title) {
            this.cataType = cataType;
            this.title = title;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return ExpandableItemAdapter.TYPE_LEVEL_0;
        }
    }
}
