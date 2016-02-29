/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author Giulio
 */
public class TwitterAuthHandler {

    private static final String CONSUMER_KEY = "zoX1gMukbhnkQ273dzwO0vBpq";
    private static final String CONSUMER_SECRET = "lPFTj3ZESiN6RdIHkqC2witRbB6JWnfO9PRzScDVzAy5g8nmNR";

    public TwitterAuthHandler() {
    }
    
    /**
     * Is used to get permission for get the tweets.
     */
    
    /**
     * Is used to get permission for get the tweets.
     * @return
     */
    public OAuth2Token getOAuth2Token() {
        OAuth2Token token = null;
        ConfigurationBuilder cb;
        cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET);
        try {
            token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
        } catch (Exception e) {
            System.out.println("Could not get OAuth2 token");
            e.printStackTrace();
            System.exit(0);
        }
        return token;
    }

    /**
     * This method give you the auth to work with Twitter API
     * @return istance of Twitter
     */
    public Twitter getTwitter() {
        OAuth2Token token;
        token = getOAuth2Token();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_SECRET);
        cb.setOAuth2TokenType(token.getTokenType());
        cb.setOAuth2AccessToken(token.getAccessToken());
        return new TwitterFactory(cb.build()).getInstance();
    }
}
