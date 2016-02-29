/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

import twitter4j.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Giulio
 */
public class TweetDownload {

    private final int TWEETS_PER_QUERY = 10;
    private final int MAX_QUERIES = 5;
    private String SEARCH_TERM;
    int totalTweets = 0;
    long maxID = -1;
    Twitter twitter;
    
    /**
     * 
     * @param twitterIstance 
     */
    public TweetDownload(Twitter twitterIstance) {
        twitter = twitterIstance;
    }
    
    /**
     * this method return an ArrayList of dataOfTweets
     * @param allMyTweets
     * @return allMyTweet
     */
    public ArrayList<dataOfTweet> downloadMyTweet(ArrayList<dataOfTweet> allMyTweets) { 
        {
            try {
                Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");
                RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
                for (int queryNumber = 0; queryNumber < MAX_QUERIES; queryNumber++) {
                    dataOfTweet tweet = new dataOfTweet();

                    //System.out.printf("\n\n!!! Starting loop %d\n\n", queryNumber);
                    if (searchTweetsRateLimit.getRemaining() == 0) {
                        try {
                            //System.out.printf("!!! Sleeping for %d seconds due to rate limits\n", searchTweetsRateLimit.getSecondsUntilReset());
                            Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset() + 2) * 1000l);
                        } catch (InterruptedException ex) {
                            java.util.logging.Logger.getLogger(TweetDownload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Query q = new Query(SEARCH_TERM);
                    q.setCount(TWEETS_PER_QUERY);
                    q.setLang("it");

                    if (maxID != -1) {
                        q.setMaxId(maxID - 1);
                    }

                    QueryResult r = twitter.search(q);

                    if (r.getTweets().isEmpty()) {
                        break;
                    }
                    for (Status s : r.getTweets()) {

                        totalTweets++;
                        if (maxID == -1 || s.getId() < maxID) {
                            maxID = s.getId();
                        }
                        if (s.isRetweeted() == false) {

                            tweet.setUsername(s.getUser().getScreenName());
                            tweet.setCreatedAt(s.getCreatedAt().toString());
                            tweet.setTweetText(s.getText());

                            if (s.getGeoLocation() != null) {
                                tweet.setLat(s.getGeoLocation().getLatitude());
                                tweet.setLongi(s.getGeoLocation().getLongitude());
                            }
                        }
                    }
                    allMyTweets.add(tweet);
                }
            } catch (TwitterException ex) {
                java.util.logging.Logger.getLogger(TweetDownload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return allMyTweets;
    }
}
