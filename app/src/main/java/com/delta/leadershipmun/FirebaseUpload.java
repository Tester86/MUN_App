package com.delta.leadershipmun;

public class FirebaseUpload {

    private String name;
    private String imageUrl;

    public FirebaseUpload(){}

    public FirebaseUpload(String name, String imageUrl) {

        if (name.trim().equals("")) {

            name = "No Name";
        }

        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
