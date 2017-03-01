package com.aesean.gankio.api.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Result
 * http://gank.io/api/data/Android/10/1
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class Result {
    private static final SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private String _id;
    private Date createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    private static String toYmd(Date date) {
        return sFormat.format(date);
    }

    public String getCreatedAtYmd() {
        return toYmd(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static int compare(Result o1, Result o2) {
        if (o1 == null) {
            return o2 == null ? 0 : 1;
        } else if (o2 == null) {
            return 1;
        }
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
    }

    public static boolean areContentsTheSame(Result oldItem, Result newItem) {
        return oldItem == newItem || oldItem.get_id().equals(newItem.get_id());
    }

    public static boolean areItemsTheSame(Result item1, Result item2) {
        return item1 == item2 || item1.get_id().equals(item2.get_id());
    }
}
