package com.jacky.rxmastertest;

import android.util.Log;
import android.view.View;

import com.jacky.rxmastertest.module.LRC.Level0Item;
import com.jacky.rxmastertest.module.LRC.Level1Item;
import com.jacky.rxmastertest.module.LRC.Operation;
import com.jacky.tg.ui.Components.recyclerview.BaseMultiItemQuickAdapter;
import com.jacky.tg.ui.Components.recyclerview.BaseViewHolder;
import com.jacky.tg.ui.Components.recyclerview.entity.MultiItemEntity;

import java.util.List;


/**
 * 2017/12/29.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_OPERATION = 2;

    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);

        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_OPERATION, R.layout.item_expandable_lv2);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (holder.getLayoutPosition() % 3) {
                    case 0:
                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img0);
                        break;
                    case 1:
                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img1);
                        break;
                    case 2:
                        holder.setImageResource(R.id.iv_head, R.mipmap.head_img2);
                        break;
                }
                final Level0Item lv0 = (Level0Item) item;
                holder.setText(R.id.title, lv0.title)
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item) item;
                holder.setText(R.id.title, lv1.title)
                        .setImageResource(R.id.iv, lv1.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 1 item pos: " + pos);
                        if (lv1.isExpanded()) {
                            collapse(pos, true);
                        } else {
                            expand(pos, true);
                        }
                        lv1.mOnClickListener.onClick(v);
                    }
                });
                break;
            case TYPE_OPERATION:
                final Operation operation = (Operation) item;
                holder.setText(R.id.tv, operation.title)
                        .setText(R.id.tv_desc, operation.desc);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        int cp = getParentPosition(person);
//                        if (cp != -1) {
//                            ((Level1Item) getData().get(cp)).removeSubItem(person);
//                            getData().remove(holder.getLayoutPosition());
//                            notifyItemRemoved(holder.getLayoutPosition());
//                        }
                        operation.mOnClickListener.onClick(view);
                    }
                });
                break;
        }
    }
}
