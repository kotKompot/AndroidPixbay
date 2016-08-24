package com.kirichko.amocrm_kirichko_test_task.network.model;

/**
 * Created by Киричко on 23.08.2016.
 *
 * Класс соответствующй формату json объектов, которые приходят в массиве и представляют собой
 * запрашиваемый изображения и сопутствующие данные
 */

public class PixbayHit {
    String previewHeight;
    String likes;
    String favorites;
    String tags;
    String webformatHeight;
    String views;
    String webformatWidth;
    String previewWidth;
    String comments;
    String downloads;
    String pageURL;
    String previewURL;
    String webformatURL;
    String imageWidth;
    String user_id;
    String user;
    String type;
    String id;
    String userImageURL;
    String imageHeight;

    public PixbayHit() {}

    public PixbayHit(String previewHeight, String likes, String favorites, String tags, String webformatHeight, String views, String webformatWidth, String previewWidth, String comments, String downloads, String pageURL, String previewURL, String webformatURL, String imageWidth, String user_id, String user, String type, String id, String userImageURL, String imageHeight) {
        this.previewHeight = previewHeight;
        this.likes = likes;
        this.favorites = favorites;
        this.tags = tags;
        this.webformatHeight = webformatHeight;
        this.views = views;
        this.webformatWidth = webformatWidth;
        this.previewWidth = previewWidth;
        this.comments = comments;
        this.downloads = downloads;
        this.pageURL = pageURL;
        this.previewURL = previewURL;
        this.webformatURL = webformatURL;
        this.imageWidth = imageWidth;
        this.user_id = user_id;
        this.user = user;
        this.type = type;
        this.id = id;
        this.userImageURL = userImageURL;
        this.imageHeight = imageHeight;
    }

    public String getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(String previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(String webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(String webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public String getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(String previewWidth) {
        this.previewWidth = previewWidth;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }
}
