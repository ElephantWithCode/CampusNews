package com.baibian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibian.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ellly on 2017/8/25.
 */

public class MessageHeadView extends RelativeLayout {

    public static enum ResponseType{
        PRESENTATION, REQUEST, DIVERGENCE, QUESTION, PERFECTION
    }

    private CircleImageView mUserPortrait;
    private TextView mUserName;
    private TextView mResponseText;
    private ResponseType mResponsType;
    private Context mContext;
    private RelativeLayout.LayoutParams mLayoutParams;

    public MessageHeadView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MessageHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public MessageHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.message_head_view_layout, this);
        initView();
    }

    private void initView() {
        mResponseText = (TextView) findViewById(R.id.messy_fiction_head_response_type);
        mUserName = (TextView) findViewById(R.id.messy_fiction_head_user_name);
        mUserPortrait = (CircleImageView) findViewById(R.id.messy_fiction_head_user_portrait);

    }

}
