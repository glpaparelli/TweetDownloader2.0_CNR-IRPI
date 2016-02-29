/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

import java.util.ArrayList;

/**
 *
 * @author Giulio
 */
public class TweetTextOperation {
    /**
     * This method remvoe all the possible special chars
     * that can be present in a tweet's text.
     * @param allMyTweet
     * @return allMyTweet
     */
    public static ArrayList<dataOfTweet> TextSanitaize(ArrayList<dataOfTweet> allMyTweet){
        
        for(int i=0; i<allMyTweet.size(); i++){
            allMyTweet.get(i).setTweetText(allMyTweet.get(i).getTweetText().replaceAll("\"", "").replaceAll("\'", "")); 
            allMyTweet.get(i).setTweetText(allMyTweet.get(i).getTweetText().replaceAll("HTTP", "")); 

        }
        return allMyTweet;
    }
    
    /**
     * This method create an array, set every word of tweetText in 
     * a singol position of array.
     * @param tweetText
     * @return wordsOfTweetText
     */
    public static String[] getEveryWordOfText(String tweetText){
        String[] wordsOfTweetText = tweetText.split(" ");
        return wordsOfTweetText;
    }
    
}
