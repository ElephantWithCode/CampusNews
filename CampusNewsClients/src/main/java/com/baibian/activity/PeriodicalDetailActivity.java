package com.baibian.activity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.tool.MeasureTools;
import com.baibian.tool.ToastTools;
import com.baibian.view.AddSubtractView;
import com.baibian.view.CommentCardView;
import com.baibian.view.CustomBottomUpDialog;
import com.baibian.view.CustomDialog;
import com.baibian.view.ListenerScrollView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "periodical_detail_activity";
    private ListenerScrollView mScrollView;
    private RelativeLayout mBottomNavBar;
    private LinearLayout mCommentFatherLayout;
    private TextView mWriteComment;
    private RelativeLayout mLoadingView;
    private ImageView mArrowCategory;
    private Toolbar mToolbar;
    private Button mBuyButton;
    private CustomBottomUpDialog mCustomBottomUpDialog;
    private CustomBottomUpDialog mBuyRuleDetailDialog;
    private Handler mHandler;
    private int mBottomHeight = 0;
    private int mBottomOriginHeight = 0;
    private List<CommentCardView.CardContent> mCardDatas = new ArrayList<>();
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_detail);

        initVariousViews();

        initDialogs();

        initToolbar();

        initListeners();

        initCommentContent();

    }

    private void initDialogs() {
        initDetailDialog();
        initBuyDialog();
    }

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
        }
    }

    private void initListeners() {
        mBuyButton.setOnClickListener(this);
        mArrowCategory.setOnClickListener(this);
        mWriteComment.setOnClickListener(this);
        mScrollView.setOnScrollDownListener(new ListenerScrollView.OnScrollDownListener() {
            @Override
            public void onScrollDownComplete(float scrollY) {
                if (scrollY > 0){
                    Log.d("www111", mBottomHeight + "");
                    if (mBottomHeight == 0) {
                        startNavAnimation(0, mBottomOriginHeight);
                    }
                }else if (scrollY < 0){
                    Log.d("hhh111", mBottomHeight + "");
                    if (mBottomHeight == mBottomOriginHeight) {
                        startNavAnimation(mBottomHeight, 0);
                    }
                }
            }

            @Override
            public Rect onDropReleased(int offsetY, final View inner) {
                final Rect mNewLayout = new Rect();
//                int measuredHeightBefore = MeasureTools.measureWidthAndHeight(inner)[1];
//                mNewLayout.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getTop() + measuredHeightBefore);
//                mNewLayout.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
//                mCommentFatherLayout.addView(new Button(PeriodicalDetailActivity.this));

//                mCommentFatherLayout.addView(new CommentCardView(PeriodicalDetailActivity.this));

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

    private void initVariousViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mBuyButton = (Button) findViewById(R.id.buy_btn);
        mArrowCategory = (ImageView) findViewById(R.id.arrow_category);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mLoadingView = (RelativeLayout) findViewById(R.id.loading_view_content);
        mWriteComment = (TextView) findViewById(R.id.write_comment_text);
        mScrollView = (ListenerScrollView) findViewById(R.id.scroll_view);
        mBottomNavBar = (RelativeLayout) findViewById(R.id.bottom_nav_bar);
        mCommentFatherLayout = (LinearLayout) findViewById(R.id.comment_father_view_layout);
        mHandler = new Handler();
        mBottomOriginHeight = mBottomHeight = MeasureTools.measureWidthAndHeight(mBottomNavBar)[1];
    }

    private void initCommentContent() {
        for (int i = 0; i < 10; i++){
            mCommentFatherLayout.addView(new CommentCardView(this));
        }
    }

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
                                    mCommentFatherLayout.addView(cardView, 0);
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
                ToastTools.ToastShow("UNDO NOW");
                break;
            case R.id.buy_btn:
                mCustomBottomUpDialog.show();
        }
    }
}
