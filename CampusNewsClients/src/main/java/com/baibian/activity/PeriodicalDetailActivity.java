package com.baibian.activity;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.MeasureTools;
import com.baibian.tool.ToastTools;
import com.baibian.view.AddSubtractView;
import com.baibian.view.CommentCardView;
import com.baibian.view.CustomBottomUpDialog;
import com.baibian.view.CustomDialog;
import com.baibian.view.ListenerScrollView;
import com.baibian.view.RevealFollowButton;
import com.baibian.view.SharePeriodicalDialog;
import com.baibian.view.pullable_view.PullToRefreshLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "periodical_detail_activity";
    private ListenerScrollView mScrollView;
    private RelativeLayout mBottomNavBar;
    private LinearLayout mCommentFatherLayout;
    private TextView mWriteComment;
    private TextView mCategoryCount;
    private TextView mRecommendCount;
    private TextView mReadCount;
    private TextView mFansCount;
    private LikeButton mLikeButton;
    private boolean mIsRead = false;
    private long mRecommendNumber = 0;
    private long mReadNumber = 0;
    private long mFansNumber = 0;
    private RelativeLayout mLoadingView;
    private ImageView mArrowCategory;
    private Toolbar mToolbar;
    private Button mBuyButton;
    private CustomBottomUpDialog mCustomBottomUpDialog;
    private CustomBottomUpDialog mBuyRuleDetailDialog;
    private PullToRefreshLayout mRefreshLayout;
    private RevealFollowButton mCollectButton;
    private Handler mHandler;
    private int mBottomHeight = 0;
    private int mBottomOriginHeight = 0;
    private long mCategoryNumber = 0;
    private SharePeriodicalDialog mShareDialog;

    /**
     * 并没有用上的东西
     */
    private List<CommentCardView.CardContent> mCardDatas = new ArrayList<>();
    private ProgressBar mProgressBar;
    private boolean isFree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_detail);

        /**
         * 初步设想是从点击时获得数据并传进来
         * 或者只传id在本activity内请求数据
         */

        Intent intentionIntent = getIntent();
        if (intentionIntent != null){
            //TODO 初始化各种数据
        }

        initVariousViews();

        initNumbers();

        initDialogs();

        initToolbar();

        initListeners();

        initCommentContent();

    }

    /**
     * 初始化数据（模拟数据源）
     */
    private void initNumbers() {
        mFansNumber = getNumbers();
        mReadNumber = getNumbers();
        mRecommendNumber = getNumbers();
        onRecommendNumberChanged();
        onFansNumberChanged();
        onReadNumberChanged();
        mCategoryNumber = getNumbers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_periodical, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_share:
                mShareDialog.show();
                break;
            case R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        return true;
    }

    /**
     * 模拟后端发送的数据
     * @return
     */
    private long getNumbers() {
        return 10;
    }

    private void initDialogs() {
        initShareDialog();
        initDetailDialog();
        initBuyDialog();
    }

    /**
     * 分享的dialog
     */
    private void initShareDialog() {
        mShareDialog = new SharePeriodicalDialog(this, R.style.CustomDialogTheme);
    }

    /**
     * 购买的底部dialog
     */
    private void initBuyDialog() {
        mCustomBottomUpDialog = new CustomBottomUpDialog(this, R.layout.buy_popup_layout) {

            public static final int ITEM_PRICE = 5;
            private ImageView mCloseButton;
            private TextView mDeclaration;
            private Button mBuy12Button;
            private Button mBuyAllButton;
            private Button mPayButton;
            private Button mBuy6Button;
            private TextView mPresentBis;
            private TextView mGoodsPrice;
            private TextView mSufficientAttention;
            private AddSubtractView mBuyAmount;

            /**
             *判断是否应该显示提示余额不足的信息
             * @param count 应传入的当前的选择的总值
             */
            private void showAttentionText(int count) {
                if (Integer.valueOf(mPresentBis.getText().toString()) > count * ITEM_PRICE){
                    mSufficientAttention.setVisibility(View.INVISIBLE);
                }else {
                    mSufficientAttention.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void initView(CustomBottomUpDialog dialog) {

                mCloseButton = (ImageView) dialog.findViewById(R.id.close_button);
                mPayButton = (Button) dialog.findViewById(R.id.pay_button);
                mBuy6Button = (Button) dialog.findViewById(R.id.buy_choice_6);
                mBuy12Button = (Button) dialog.findViewById(R.id.buy_choice_12);
                mBuyAllButton = (Button) dialog.findViewById(R.id.buy_choice_all);
                mDeclaration = (TextView) dialog.findViewById(R.id.buy_detail_declaration);
                mBuyAmount = (AddSubtractView) dialog.findViewById(R.id.add_subtract_periodical);
                mPresentBis = (TextView) dialog.findViewById(R.id.present_money_hold);
                mGoodsPrice = (TextView) dialog.findViewById(R.id.lun_dao_coin_count);
                mSufficientAttention = (TextView) dialog.findViewById(R.id.attention_money_insufficient);

                mBuyAmount.setOnCountChangeListener(new AddSubtractView.OnCountChangeListener() {
                    @Override
                    public void onCountChange(int count) {
                        mGoodsPrice.setText(String.valueOf(count * ITEM_PRICE));
                        showAttentionText(count);
                    }
                });
                mBuyAllButton.setOnClickListener(this);
                mBuy12Button.setOnClickListener(this);
                mBuy6Button.setOnClickListener(this);
                mDeclaration.setOnClickListener(this);
                mPayButton.setOnClickListener(this);
                mCloseButton.setOnClickListener(this);

                /**
                 * 随意设置了当前余额为88
                 * 以及当前只选择了一章
                 */
                mPresentBis.setText(String.valueOf(88));
                mGoodsPrice.setText(String.valueOf(ITEM_PRICE));
                showAttentionText(1);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buy_detail_declaration:
                        dismiss();
                        mBuyRuleDetailDialog.show();
                        break;
                    case R.id.close_button:
                        dismiss();
                        break;
                    /**
                     * 这里只改变了选择的数目
                     * 并没有实际的购买逻辑
                     */
                    case R.id.buy_choice_6:
                        mBuyAmount.changeCount(6);
                        break;
                    case R.id.buy_choice_12:
                        mBuyAmount.changeCount(12);
                        break;
                    case R.id.buy_choice_all:
                        mBuyAmount.changeCount(mBuyAmount.getMaxCount());
                        break;
                }
            }
        };
    }

    /**
     * 购买详情dialog
     * 随便加了点字
     */
    private void initDetailDialog() {
        mBuyRuleDetailDialog = new CustomBottomUpDialog(this, R.layout.periodical_buy_rule_detail) {
            private ImageView mCloseButton;
            @Override
            public void initView(CustomBottomUpDialog dialog) {
                mCloseButton = (ImageView) dialog.findViewById(R.id.close_button);
                mCloseButton.setOnClickListener(this);
            }
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.close_button:
                        dismiss();
                        mCustomBottomUpDialog.show();
                        break;
                }
            }

        };
        mBuyRuleDetailDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mCustomBottomUpDialog.show();
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("测试标题栏");
        }
    }

    private void initListeners() {
        mRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

            }

            /**
             * 延迟1s发送加载成功信息
             * @param pullToRefreshLayout
             */
            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        mCommentFatherLayout.addView(new CommentCardView(PeriodicalDetailActivity.this));
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
        /**
         * 后期应该向后端发送对应的数字
         */
        mCollectButton.setOnFollowedClickListener(new RevealFollowButton.OnFollowedClickListener() {
            @Override
            public void onFollowedClick(boolean isFollowed) {
                if (isFollowed){
                    if (!mIsRead) {
                        ++mReadNumber;
                        mIsRead = true;
                        onReadNumberChanged();
                    }
                    ++mFansNumber;
                    onFansNumberChanged();
                }else {
                    --mFansNumber;
                    onFansNumberChanged();
                }
            }
        });

        mLikeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                ++mRecommendNumber;
                onRecommendNumberChanged();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                --mRecommendNumber;
                onRecommendNumberChanged();
            }
        });
        mBuyButton.setOnClickListener(this);
        mArrowCategory.setOnClickListener(this);
        mWriteComment.setOnClickListener(this);
        mScrollView.setOnScrollDownListener(new ListenerScrollView.OnScrollDownListener() {
            @Override
            public void onScrollDownComplete(float scrollY) {
                if (scrollY > 0){
                    Log.d("www111", mBottomHeight + "");
                    if (mBottomHeight == 0 && !isFree) {
                        startNavAnimation(0, mBottomOriginHeight);
                    }
                }else if (scrollY < 0 && !isFree){
                    Log.d("hhh111", mBottomHeight + "");
                    if (mBottomHeight == mBottomOriginHeight) {
                        startNavAnimation(mBottomHeight, 0);
                    }
                }
            }

            @Override
            public Rect onDropReleased(int offsetY, final View inner) {
                final Rect mNewLayout = new Rect();
                mLoadingView.setVisibility(View.VISIBLE);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCommentFatherLayout.addView(new CommentCardView(PeriodicalDetailActivity.this));
                        mLoadingView.setVisibility(View.INVISIBLE);
                        ToastTools.ToastShow("Add Successfully");
                    }
                } ,1000);
                int measuredHeight = MeasureTools.measureWidthAndHeight(inner)[1];
                mNewLayout.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getTop() + measuredHeight);
                ToastTools.ToastShow("HHHHHHHHH");
                Log.d(TAG + 123, mNewLayout.top +" "+ mNewLayout.left);
                return mNewLayout;
            }
        });
    }

    /**
     * 可以改变数字表示的形式（比如数字过大可以用W代替万）
     */
    private void onRecommendNumberChanged() {
        mRecommendCount.setText(String.valueOf(mRecommendNumber));
    }

    private void onFansNumberChanged() {
        mFansCount.setText(String.valueOf(mFansNumber));
    }

    private void onReadNumberChanged() {
        mReadCount.setText(String.valueOf(mReadNumber));
    }

    /**
     * find各种id
     */
    private void initVariousViews() {
        mCollectButton = (RevealFollowButton) findViewById(R.id.collect_btn);
        mCollectButton.setPadding(40,15,40,17);
        mCollectButton.setFollowBackground(ContextCompat.getDrawable(this, R.drawable.collect_btn_background_clicked));
        mCollectButton.setFollowTextContent(R.string.collected);
        mCollectButton.setUnFollowTextColor(Color.WHITE);
        mCollectButton.setUnFollowBackground(ContextCompat.getDrawable(this, R.drawable.collect_btn_background_unclick));
        mCollectButton.setUnFollowTvTextContent(R.string.collect);
        mLikeButton = (LikeButton) findViewById(R.id.recommend_button);
        mCategoryCount = (TextView) findViewById(R.id.category_count);
        mRecommendCount = (TextView) findViewById(R.id.recommend_count);
        mReadCount = (TextView) findViewById(R.id.read_count);
        mFansCount = (TextView) findViewById(R.id.fans_count);
        mRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mBuyButton = (Button) findViewById(R.id.buy_btn);
        mArrowCategory = (ImageView) findViewById(R.id.arrow_category);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mLoadingView = (RelativeLayout) findViewById(R.id.loading_view_content);
        mWriteComment = (TextView) findViewById(R.id.write_comment_text);
        mScrollView = (ListenerScrollView) findViewById(R.id.scroll_view);
        mBottomNavBar = (RelativeLayout) findViewById(R.id.bottom_nav_bar);
        if (isFree){
            mBottomNavBar.setVisibility(View.GONE);
        }
        mCommentFatherLayout = (LinearLayout) findViewById(R.id.comment_father_view_layout);
        mHandler = new Handler();
        mBottomOriginHeight = mBottomHeight = MeasureTools.measureWidthAndHeight(mBottomNavBar)[1];
    }

    /**
     * 模拟评论数据源
     */
    private void initCommentContent() {
        for (int i = 0; i < 10; i++){
            mCommentFatherLayout.addView(new CommentCardView(this));
        }
    }

    /**
     * 导航栏动画
     * @param startY
     * @param endY
     */
    private void startNavAnimation(int startY, int endY) {
        ValueAnimator animator = ValueAnimator.ofInt(startY, endY);
        animator.setDuration(200);
//        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBottomHeight = mBottomNavBar.getLayoutParams().height = (int) animation.getAnimatedValue();
                Log.d("fff111", mBottomHeight + "");

                mBottomNavBar.requestLayout();
            }
        });
        animator.start();
    }

    /**
     * 当免费时不再显示下端导航栏
     * @param isFree
     */
    public void setIsFree(boolean isFree){
        this.isFree = isFree;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.write_comment_text:
                CustomDialog dialog = new CustomDialog(this, R.style.CustomDialogTheme);
                dialog.setOnDialogDismissListener(new CustomDialog.OnDialogDismissListener() {
                    @Override
                    public void OnDismiss(final String editTextContent, boolean isPositive) {
                        if (isPositive){
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    CommentCardView cardView = new CommentCardView(PeriodicalDetailActivity.this);
                                    cardView.setCommentText(editTextContent);
                                    mCommentFatherLayout.addView(cardView, 0);//在顶端添加评论
                                }
                            });
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.arrow_category:
                Intent newIntent = new Intent(PeriodicalDetailActivity.this, PeriodicalCategoryActivity.class);
                startActivity(newIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.buy_btn:
                mCustomBottomUpDialog.show();
        }
    }
}
