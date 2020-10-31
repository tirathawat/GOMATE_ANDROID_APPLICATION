package com.example.gomate.Model;

public class HomeChat {
    private String name;
    private String lastMessage;
    private String imageUrl;
    private String timeStamp;

    public HomeChat(String name, String lastMessage, String imageUrl, String timeStamp) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
    }
    public HomeChat(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
