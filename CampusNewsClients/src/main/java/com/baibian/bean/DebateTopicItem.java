package com.baibian.bean;

/**
 * Created by Ellly on 2017/8/3.
 */

public class DebateTopicItem {
    private String debateTopicTitle;
    private String debateTopicContent;
    private String topicFocusedAmount;
    private String topicFavoredAmount;

    public String getDebateTopicTitle() {
        return debateTopicTitle;
    }

    public void setDebateTopicTitle(String debateTopicTitle) {
        this.debateTopicTitle = debateTopicTitle;
    }

    public String getDebateTopicContent() {
        return debateTopicContent;
    }

    public void setDebateTopicContent(String debateTopicContent) {
        this.debateTopicContent = debateTopicContent;
    }

    public String getTopicFocusedAmount() {
        return topicFocusedAmount;
    }

    public void setTopicFocusedAmount(String topicFocusedAmount) {
        this.topicFocusedAmount = topicFocusedAmount;
    }

    public String getTopicFavoredAmount() {
        return topicFavoredAmount;
    }

    public void setTopicFavoredAmount(String topicFavoredAmount) {
        this.topicFavoredAmount = topicFavoredAmount;
    }
}
