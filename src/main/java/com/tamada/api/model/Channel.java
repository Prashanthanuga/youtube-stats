package com.tamada.api.model;

public class Channel {

    private String channelName;
    private long subscribers;
    private long totalViews;
    private int videos;
    private double monthlyViews;
    private double revenue;
    private double productivityScore;

    public Channel() {
    }

    public Channel(String channelName,
                   long subscribers,
                   long totalViews,
                   int videos,
                   double monthlyViews,
                   double revenue,
                   double productivityScore) {

        this.channelName = channelName;
        this.subscribers = subscribers;
        this.totalViews = totalViews;
        this.videos = videos;
        this.monthlyViews = monthlyViews;
        this.revenue = revenue;
        this.productivityScore = productivityScore;
    }

    public String getChannelName() {
        return channelName;
    }

    public long getSubscribers() {
        return subscribers;
    }

    public long getTotalViews() {
        return totalViews;
    }

    public int getVideos() {
        return videos;
    }

    public double getMonthlyViews() {
        return monthlyViews;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getProductivityScore() {
        return productivityScore;
    }
}