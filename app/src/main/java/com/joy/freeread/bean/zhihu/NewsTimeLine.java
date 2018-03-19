package com.joy.freeread.bean.zhihu;

import java.io.Serializable;
import java.util.List;

public class NewsTimeLine implements Serializable {

    private String date;
    private List<Story> stories;
    private List<TopStories> top_stories;

    public String getDate() {
        return date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

}
