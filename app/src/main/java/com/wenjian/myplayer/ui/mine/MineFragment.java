package com.wenjian.myplayer.ui.mine;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wenjian.core.ui.base.BaseFragment;
import com.wenjian.myplayer.AppExecutors;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.AppDataManager;
import com.wenjian.myplayer.data.db.AppDbHelper;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.db.source.collection.CollectionDataSource;
import com.wenjian.myplayer.data.db.source.collection.LocalCollectionDataSource;
import com.wenjian.myplayer.ui.classify.VideoListActivity;
import com.wenjian.myplayer.ui.record.RecordActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author wenjian
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.btn_record)
    Button mBtnRecord;
    @BindView(R.id.btn_collect)
    Button mBtnCollect;

    @BindView(R.id.tv_data)
    TextView mTextView;

    public MineFragment() {

        // Required empty public constructor
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

    }

    @OnClick(R.id.btn_record)
    void record() {
        RecordActivity.start(getActivity());
    }

    @OnClick(R.id.btn_collect)
    void collect() {
        VideoListActivity.start(getActivity(), null, VideoListActivity.TITLE_COLLECTION);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @OnClick({R.id.btn_create, R.id.btn_insert_one, R.id.btn_delete_id, R.id.btn_delete_all, R.id.btn_query_id, R.id.btn_inset_list})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                DbManager.getInstance().createTable();
                break;

            case R.id.btn_delete_id:
                List<User> users = DbManager.getInstance().getUsers();
                Log.d(TAG, "query users: " + users);
                bindText(users);
                break;

            case R.id.btn_insert_one:
                DbManager.getInstance().insert(new User("wenjian", 25, 1, 0));
                break;

            case R.id.btn_delete_all:
                DbManager.getInstance().deleteAll();
                break;

            case R.id.btn_query_id:
                DbManager.getInstance().getUserById("fafafa");
                break;

            case R.id.btn_inset_list:
                AppExecutors.getInstance().forDiskIO(new Runnable() {
                    @Override
                    public void run() {

                        List<User> userList = new ArrayList<>();
                        for (int i = 0; i < 1000; i++) {
                            User user = new User("wenjian" + i, (int) (Math.random() * 100), i % 3 == 0 ? 0 : 1, 0);
                            userList.add(user);
                        }

                        long start = System.currentTimeMillis();
                        DbManager.getInstance().saveAll(userList);
                        Log.d(TAG, "run: " + (System.currentTimeMillis() - start));
                    }
                });

                break;

            default:
        }
    }


    private void bindText(List<User> userList) {
        mTextView.setText("");
        if (userList != null) {
            StringBuilder dbData = new StringBuilder();
            dbData.append("ID").append("\t\t\t")
                    .append("NAME").append("\t\t\t")
                    .append("AGE").append("\t\t\t")
                    .append("SEX").append("\n");

            for (User user : userList) {
                dbData.append(user.getId()).append("\t\t")
                        .append(user.getName()).append("\t\t")
                        .append(user.getAge()).append("\t\t")
                        .append(user.getSex()).append("\n");
            }
            mTextView.setText(dbData.toString());
        }
    }
}
