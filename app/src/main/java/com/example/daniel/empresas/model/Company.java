package com.example.daniel.empresas.model;

/**
 * Created by Daniel on 03-01-2018.
 */

public class Company {
    private String name;
    private String category;
    private String image;
    private String description;
    private String phoneNumber;
    private String email;
    private String web;
    private String localization;
    private String facebook;
    private String linkedIn;
    private String twitter;
    private String google;
    private String youtube;

    public Company(String name, String category, String image, String description, String phoneNumber, String email, String web, String localization, String facebook, String linkedIn, String twitter, String google, String youtube) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.web = web;
        this.localization = localization;
        this.facebook = facebook;
        this.linkedIn = linkedIn;
        this.twitter = twitter;
        this.google = google;
        this.youtube = youtube;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
