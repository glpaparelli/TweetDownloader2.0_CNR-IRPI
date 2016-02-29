/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetdownloader.cnr_stable.version;

import java.io.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author Giulio
 */
public class SaveAllMyTweets {

    /**
     * This method save the sanitaized tweet text on CSV File
     *
     * @param allMyTweet
     */
    static void saveTweetOnCSVFile(ArrayList<dataOfTweet> allMyTweet) {
        
        PrintWriter printWriterOutput = null;
        try{
            printWriterOutput = new PrintWriter("savedTweet.csv");
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(SaveAllMyTweets.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < allMyTweet.size(); i++ ){
            printWriterOutput.println(allMyTweet.get(i).getTweetText() + ";");
        }
        printWriterOutput.close();
    }

    /**
     * It save all the data in the PostegreSQL DB
     *
     * @param allMyTweet
     */
    static void saveTweetOnPGSQL(ArrayList<dataOfTweet> allMyTweet) {
        try {

            for (int i = 0; i < allMyTweet.size(); i++){
                
                Connection c;
                Statement stmt;
                double lat = 0;
                double longi = 0;
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/cnrtweet", "postgres", "123456");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");
                stmt = c.createStatement();

                String sql = "INSERT INTO provacnr (tweetuser,tweettext,tweettime,tweetlat,tweetlong, wordlat, wordlong)"
                        + "VALUES ('" + allMyTweet.get(i).getUsername() + "', '" + allMyTweet.get(i).getTweetText()
                        + "','{" + allMyTweet.get(i).getCreatedAt() + "}'," + allMyTweet.get(i).getLat() 
                        + "," + allMyTweet.get(i).getLongi() + "', '" + allMyTweet.get(i).getLatOfWord() + "', '" +
                        allMyTweet.get(i).getLongiOfWord() + ")";
                
                
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }
}
