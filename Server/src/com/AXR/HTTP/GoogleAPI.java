package com.AXR.HTTP;

import com.AXR.DomainLayer.GoogleBookArray;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GoogleAPI {

    private final String server = "https://www.googleapis.com/";
    private String api;


    public GoogleAPI(String api){

        this.api = api;
    }

    public String executeQuery(String query){
        String result = "";

        try {
            URL bookInfo = new URL( server + api + query);
            InputStream input = bookInfo.openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");
            GoogleBookArray objectResult  = new Gson().fromJson(reader, GoogleBookArray.class);

            result += "Title: " + objectResult.getBookDetail().getTitle() + ", " + "Subtitle: " + objectResult.getBookDetail().getSubTitle();
            List<String> authors = objectResult.getBookDetail().getAuthors();

            for (String author: authors) {
                result += " Author: " + author  + ", ";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
