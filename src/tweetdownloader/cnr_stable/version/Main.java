/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

import java.util.ArrayList;
import twitter4j.*;

/**
 *
 * @author Giulio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TwitterAuthHandler giveMeTheAuth = new TwitterAuthHandler();
        Twitter twitterIstance = giveMeTheAuth.getTwitter();
        ArrayList<dataOfTweet> allMyTweets = new ArrayList<>();

        
        TweetDownload downloader = new TweetDownload(twitterIstance);
        downloader.downloadMyTweet(allMyTweets);
        
        
    }
    
}
