package com.wenjian.myplayer.ui.videoplay;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.db.source.collection.Collection;
import com.wenjian.myplayer.data.db.source.record.Record;
import com.wenjian.myplayer.di.Injector;
import com.wenjian.myplayer.entity.Comment;
import com.wenjian.myplayer.entity.VideoDetail;
import com.wenjian.myplayer.entity.VideoInfo;
import com.wenjian.myplayer.ui.base.AppBaseActivity;
import com.wenjian.myplayer.ui.main.home.HomeRecyclerAdapter;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 视频播放页
 *
 * @author wenjian
 */
public class VideoPlayActivity extends AppBaseActivity<VideoPlayContract.View, VideoPlayContract.Presenter>
        implements VideoPlayContract.View {

    @BindView(R.id.video_player)
    NiceVideoPlayer mVideoPlayer;
    @BindView(R.id.tv_video_name)
    TextView mTvVideoName;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.iv_star)
    ImageView mIvStar;
    @BindView(R.id.comment_recycler)
    RecyclerView mCommentRecycler;

    private boolean isLoadMore = false;

    RecyclerView mGuessRecycler;

    private VideoDetail mVideoDetail;
    private String mDataId;
    private VideoInfo mVideoInfo;

    private static final String EXTRA_VIDEO_SRC = "video_src";
    private static final String EXTRA_DATA_ID = "data_id";
    private HomeRecyclerAdapter mAdapter;
    private CommentRecyclerAdapter mCommentAdapter;
    private TxVideoPlayerController mController;
    private TextView mTvVideoDesc;
    private TextView mTvCommentCount;

    public static void start(Context context, VideoDetail detail) {
        Intent starter = new Intent(context, VideoPlayActivity.class);
        starter.putExtra(EXTRA_VIDEO_SRC, detail);
        context.startActivity(starter);
    }

    public static void start(Context context, String dataId) {
        Intent starter = new Intent(context, VideoPlayActivity.class);
        starter.putExtra(EXTRA_DATA_ID, dataId);
        context.startActivity(starter);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        mDataId = bundle.getString(EXTRA_DATA_ID);
        mVideoDetail = bundle.getParcelable(EXTRA_VIDEO_SRC);
        if (TextUtils.isEmpty(mDataId)) {
            if (mVideoDetail != null) {
                mDataId = mVideoDetail.getDataId();
            }
        }
        return !TextUtils.isEmpty(mDataId);
    }

    @Override
    protected void initWindows() {
        super.initWindows();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_alpha_50));
        }
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setupVideoPlayer();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mCommentRecycler.setLayoutManager(manager);
        mCommentAdapter = new CommentRecyclerAdapter();
        mCommentRecycler.setAdapter(mCommentAdapter);

        View header = LayoutInflater.from(this).inflate(R.layout.video_comment_header,
                mCommentRecycler, false);
        mTvVideoDesc = header.findViewById(R.id.tv_video_desc);
        mGuessRecycler = header.findViewById(R.id.guess_recycler);
        mTvCommentCount = header.findViewById(R.id.tv_comment_count);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mGuessRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new HomeRecyclerAdapter();
        mGuessRecycler.setAdapter(mAdapter);

        mCommentAdapter.addHeaderView(header);
        mCommentAdapter.setEnableLoadMore(true);
        mCommentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isLoadMore = true;
                getPresenter().loadMore();
            }
        }, mCommentRecycler);

    }

    @Override
    protected void initData() {
        super.initData();
        loadData();
    }

    private void loadData() {
        isLoadMore = false;
        getPresenter().refresh(mDataId);
    }

    private void setupVideoPlayer() {
        mController = new TxVideoPlayerController(this);
        mVideoPlayer.setController(mController);
        if (mVideoDetail != null) {
            updateController(mVideoDetail.getTitle(), mVideoDetail.getDuration(), mVideoDetail.getPic());
        }
    }

    /**
     * 更新Controller的内容
     *
     * @param title    标题
     * @param duration 时长
     * @param pic      图片
     */
    private void updateController(String title, String duration, String pic) {
        mController.setTitle(title);
        mController.setLength(duration);
        Glide.with(this)
                .load(pic)
                .centerCrop()
                .into(mController.imageView());
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_video_play;
    }

    @OnClick(R.id.iv_share)
    void share() {
//        WebActivity.start(this, mVideoInfo.getKuaiKan());
    }

    @OnClick(R.id.iv_star)
    void star() {
        getPresenter().addCollection(buildCollection());
    }

    private Collection buildCollection() {
        Collection collection = new Collection();
        collection.setId(mDataId);
        collection.setUpdateTime(System.currentTimeMillis());
        if (mVideoInfo != null) {
            collection.setThumb(mVideoInfo.getPic());
            collection.setTitle(mVideoInfo.getTitle());
        } else if (mVideoDetail != null) {
            collection.setThumb(mVideoDetail.getPic());
            collection.setTitle(mVideoDetail.getTitle());
        }
        return collection;
    }

    private Record buildRecord() {
        Record record = new Record();
        record.setId(mDataId);
        record.setCurrentProgress(mVideoPlayer.getCurrentPosition());
        record.setTotalProgress(mVideoPlayer.getDuration());
        record.setUpdateTime(System.currentTimeMillis());
        if (mVideoInfo != null) {
            record.setThumb(mVideoInfo.getPic());
            record.setTitle(mVideoInfo.getTitle());
        }
        return record;
    }

    @Override
    public VideoPlayContract.Presenter createPresenter() {
        return new VideoPlayPresenter(Injector.provideRecordDataSource(this),
                Injector.provideCollectionDataSource(this));
    }

    @Override
    public VideoPlayContract.View createView() {
        return this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().saveRecord(buildRecord());
    }

    @Override
    public void onBackPressed() {
        if (!NiceVideoPlayerManager.instance().onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onLoadSuccess(VideoInfo info) {
        this.mVideoInfo = info;
        if (mVideoPlayer.isPlaying()) {
            mVideoPlayer.releasePlayer();
        }
        mController.setClarity(info.getClarity(), 0);
        mController.autoplay();
        mTvVideoName.setText(info.getTitle());
        mAdapter.setNewData(info.getList());
        updateController(info.getTitle(), info.getDuration(), info.getPic());
    }

    @Override
    public void onVideoDescBuild(String videoDesc) {
        mTvVideoDesc.setText(videoDesc);
    }

    @Override
    public void onCommentLoaded(List<Comment> comments) {
        if (isLoadMore) {
            mCommentAdapter.addData(comments);
        } else {
            mCommentAdapter.setNewData(comments);
        }
    }

    @Override
    public void setLoadMoreEnd() {
        mCommentAdapter.loadMoreEnd();
    }

    @Override
    public void setCommentCount(int count) {
        mTvCommentCount.setText(String.format(Locale.getDefault(),"%d 条", count));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras() != null) {
            mDataId = intent.getExtras().getString(EXTRA_DATA_ID);
            loadData();
        }
    }
}
