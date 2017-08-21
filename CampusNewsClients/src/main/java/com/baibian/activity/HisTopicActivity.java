package com.baibian.activity;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.bean.DebateTopicItem;
import com.baibian.listener.OnRecyclerViewItemClickListener;
import com.baibian.tool.SpaceItemDecoration;
import com.baibian.tool.ToastTools;

import java.util.LinkedList;
import java.util.List;


public class HisTopicActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private List<DebateTopicItem> topicItems;
    private LinearLayoutManager llm;
    private TopicAdapter adapter;
    private RecyclerView topicRecyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_topic);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        topicRecyView = (RecyclerView) findViewById(R.id.topic_recycler_view);
        initRecyView();
        initToolbar();
    }

    private void initRecyView() {
        topicItems = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            DebateTopicItem item = new DebateTopicItem();
            item.setDebateTopicTitle("Test" + i+1);
            topicItems.add(item);
        }
        adapter = new TopicAdapter();
        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastTools.ToastShow(position + "   " + view.getNextFocusDownId());
            }
        });
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        topicRecyView.setLayoutManager(llm);
        topicRecyView.setAdapter(adapter);
        topicRecyView.setItemAnimator(new DefaultItemAnimator());
        topicRecyView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder>{

        private OnRecyclerViewItemClickListener mListener;

        public void removeData(int position){
            topicItems.remove(position);
            notifyItemRemoved(position);
        }
        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
            this.mListener = listener;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView debateTopicTitle;
            TextView debateTopicContent;
            TextView topicFocusedAmount;
            TextView topicFocusAction;
            TextView topicFavoredAmount;
            ImageView debateCloseTopic;

            MyViewHolder(View itemView) {
                super(itemView);
                debateTopicTitle = (TextView) itemView.findViewById(R.id.debate_topic_title);
                debateTopicContent = (TextView) itemView.findViewById(R.id.debate_topic_content);
                topicFocusedAmount = (TextView) itemView.findViewById(R.id.topic_focused_amount);
                topicFocusAction = (TextView) itemView.findViewById(R.id.topic_focus_action);
                topicFavoredAmount = (TextView) itemView.findViewById(R.id.topic_favored_amount);
                debateCloseTopic = (ImageView) itemView.findViewById(R.id.debate_close_topic);
            }
        }
        @Override
        public TopicAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_item_layout, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(final TopicAdapter.MyViewHolder myViewHolder, int position) {
            myViewHolder.debateCloseTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = myViewHolder.getAdapterPosition();
                    removeData(position);
                }
            });
            if (mListener != null) {
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = myViewHolder.getLayoutPosition();
                        mListener.onItemClick(v, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return topicItems.size();
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("His Topics");
        }
    }
}
