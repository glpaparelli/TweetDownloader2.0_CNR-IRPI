/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.geonames.*;
/**
 *
 * @author Giulio
 */
public class FindPositionOfNominatedCity {

    public FindPositionOfNominatedCity() {
    }

    /**
     * this method read the file with all the cities of Italy, then convert it in
     * a classic java array.
     * @param allMyTweet
     * @return citiesList
     */
    static ArrayList<String> getArrayOfCities(ArrayList<dataOfTweet> allMyTweet) {
        JSONParser jsonFileParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonFileParser.parse(new FileReader("C:\\Users\\Giulio\\Desktop\\CountriesToCitiesJSON"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindPositionOfNominatedCity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(FindPositionOfNominatedCity.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray jsonCitiesArray = (JSONArray) obj;
        ArrayList<String> citiesList = new ArrayList<String>();
        
        if (jsonCitiesArray != null) {
            int len = jsonCitiesArray.size();
            for (int i = 0; i < len; i++) {
                citiesList.add(jsonCitiesArray.get(i).toString());
            }
        }
        return citiesList;
    }
    
    static ArrayList<dataOfTweet> getPositionOfNominatedCity(ArrayList<dataOfTweet> allMyTweets, ArrayList<String> citiesList){
        for(int i = 0; i < allMyTweets.size(); i++){
            for(int j = 0; j < citiesList.size(); i++){
                String[] wordsOfTweet;
                wordsOfTweet = TweetTextOperation.getEveryWordOfText(allMyTweets.get(i).getTweetText());
                for(int k = 0; k < wordsOfTweet.length; k++ ){
                    boolean isTheSame = citiesList.get(j).compareTo(wordsOfTweet[k]) == 0;
                    if(isTheSame == true){
                        try{
                            WebService.setUserName("provacnr"); // add your username here
                            ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
                            searchCriteria.setQ(wordsOfTweet[k]);
                            ToponymSearchResult searchResult = WebService.search(searchCriteria);
                            for (Toponym toponym : searchResult.getToponyms()) {
                                allMyTweets.get(i).setLatOfWord(toponym.getLatitude());
                                allMyTweets.get(i).setLongiOfWord(toponym.getLongitude());
                            }
                        }catch (Exception ex) {
                            Logger.getLogger(FindPositionOfNominatedCity.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return allMyTweets;
    } 
}
