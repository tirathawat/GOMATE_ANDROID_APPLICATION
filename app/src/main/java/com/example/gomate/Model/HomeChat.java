package com.example.gomate.Model;

public class HomeChat {
    private String name;
    private String timeStamp;
    private String imageUrl;

    public HomeChat() {}

    public HomeChat(String name, String timeStamp, String imageUrl) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.timeStamp = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getimageUrl(){
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
