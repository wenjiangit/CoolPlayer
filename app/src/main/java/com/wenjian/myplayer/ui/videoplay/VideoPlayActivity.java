package com.wenjian.myplayer.ui.videoplay;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.data.network.model.Comment;
import com.wenjian.myplayer.data.network.model.VideoDetail;
import com.wenjian.myplayer.data.network.model.VideoInfo;
import com.wenjian.myplayer.ui.base.AppBaseActivity;
import com.wenjian.myplayer.ui.home.HomeRecyclerAdapter;
import com.wenjian.myplayer.ui.web.WebActivity;
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

    private static final String EXTRA_VIDEO_SRC = "video_src";
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

    @Override
    protected boolean initArgs(Bundle bundle) {
        mVideoDetail = bundle.getParcelable(EXTRA_VIDEO_SRC);
        return mVideoDetail != null;
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
                getPresenter().getCommentList(mVideoDetail.getDataId(), isLoadMore = true);
            }
        }, mCommentRecycler);

    }

    @Override
    protected void initData() {
        super.initData();
        loadData();
    }

    private void loadData() {
        getPresenter().loadVideoInfo(mVideoDetail.getLoadURL());
        getPresenter().getCommentList(mVideoDetail.getDataId(), isLoadMore = false);
    }

    private void setupVideoPlayer() {
        mController = new TxVideoPlayerController(this);
        mVideoPlayer.setController(mController);
        updateController(mVideoDetail.getTitle(), mVideoDetail.getDuration(), mVideoDetail.getPic());
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
        WebActivity.start(this, mVideoDetail.getShareURL());
    }

    @Override
    public VideoPlayContract.Presenter createPresenter() {
        return new VideoPlayPresenter();
    }

    @Override
    public VideoPlayContract.View createView() {
        return this;
    }


    @Override
    public void onBackPressed() {
        if (!NiceVideoPlayerManager.instance().onBackPressd()) {
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
        if (mVideoPlayer.isPlaying()) {
            mController.reset2Start();
        }
        mVideoPlayer.setUp(info.getVideoSrc(), null);
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
            mVideoDetail = intent.getExtras().getParcelable(EXTRA_VIDEO_SRC);
            loadData();
        }
    }
}
