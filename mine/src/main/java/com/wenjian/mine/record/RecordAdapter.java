package com.wenjian.mine.record;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenjian.base.data.db.source.record.Record;
import com.wenjian.mine.R;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: RecordAdapter
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */

public class RecordAdapter extends BaseQuickAdapter<Record, BaseViewHolder> {

    private Set<String> mSelectedIds = new HashSet<>();
    private boolean isEdit;

    public RecordAdapter() {
        super(R.layout.cell_record, new ArrayList<Record>());
    }

    @Override
    protected void convert(BaseViewHolder helper, final Record item) {
        helper.setText(R.id.tv_title, item.getTitle());
        Glide.with(mContext)
                .load(item.getThumb())
                .into((ImageView) helper.getView(R.id.iv_thumb));
        double current = item.getCurrentProgress();
        double ration = current / item.getTotalProgress() * 100;
        helper.setText(R.id.tv_progress, "播放进度: " + String.valueOf(Math.round(ration)) + "%");
        final CheckBox checkBox = helper.getView(R.id.checkBox);
        checkBox.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        checkBox.setChecked(item.isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSelectedIds.add(item.getId());
                } else {
                    mSelectedIds.remove(item.getId());
                }
            }
        });

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                VideoPlayActivity.start(mContext, item.getId());
            }
        });
    }

    void changeUiState(int state) {
        isEdit = state == RecordActivity.STATE_EDIT;
        mSelectedIds.clear();
        notifyDataSetChanged();
    }


    List<String> getSelectedIds() {
        return new ArrayList<>(mSelectedIds);
    }

    /**
     * 全选或取消全选
     *
     * @param chose 全选
     */
    void choseAllOrNot(boolean chose) {
        for (Record record : getData()) {
            record.setChecked(chose);
            mSelectedIds.add(record.getId());
        }
        notifyDataSetChanged();
    }

    void delete() {
        if (mSelectedIds.isEmpty()) {
            return;
        }
        List<Record> oldList = getData();
        List<Record> newList = new ArrayList<>();
        for (Record record : oldList) {
            for (String selectedId : mSelectedIds) {
                if (record.getId().equals(selectedId)) {
                    newList.add(record);
                }
            }
        }

        if (!newList.isEmpty()) {
            oldList.removeAll(newList);
            isEdit = false;
            notifyDataSetChanged();
        }
    }

}
