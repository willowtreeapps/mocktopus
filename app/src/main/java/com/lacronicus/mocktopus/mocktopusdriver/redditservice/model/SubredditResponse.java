package com.lacronicus.mocktopus.mocktopusdriver.redditservice.model;

import com.lacronicus.mocktopus.mocktopusdriver.mocktopus.annotation.StringImageUrl;
import com.lacronicus.mocktopus.mocktopusdriver.mocktopus.annotation.StringWebpageUrl;

import java.util.List;

/**
 * Created by fdoyle on 7/23/14.
 */
public class SubredditResponse {
    public String kind;
    public Data data;

    public static class Data {
        public String modHash;
        public List<Child> children;
        //public Child children;
        public String after;
        public String before;
    }

    public static class Child {
        public String kind;
        public ChildData data;
    }

    public static class ChildData {
        @StringWebpageUrl
        public String domain;
        public String subreddit;
        public String selftext_html;
        public String selftext;
        public String author;
        @StringImageUrl
        public String thumbnail;
        public String title;
        public String permalink;
        @StringWebpageUrl
        public String url;
        public Integer ups;
        public Integer num_comments;
    }
}
