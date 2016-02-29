/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

/**
 *
 * @author Giulio
 */
public class dataOfTweet {
    
    private String tweetText;
    private String username;
    private String createdAt;
    private double lat; 
    private double longi;
    private double latOfWord;
    private double longiOfWord;

    public dataOfTweet() {
       
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLatOfWord() {
        return latOfWord;
    }

    public void setLatOfWord(double latOfWord) {
        this.latOfWord = latOfWord;
    }

    public double getLongiOfWord() {
        return longiOfWord;
    }

    public void setLongiOfWord(double longiOfWord) {
        this.longiOfWord = longiOfWord;
    }
    
    
}
