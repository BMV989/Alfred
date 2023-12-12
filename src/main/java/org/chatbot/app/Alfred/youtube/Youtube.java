package org.chatbot.app.Alfred.youtube;

import java.io.IOException;
import java.net.URLEncoder;

import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.chatbot.app.Alfred.telegram.types.MusicService;

public class Youtube implements MusicService {
     private final String youtubeApiKey = System.getenv("YT_TOKEN");
     private String header = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=";
     private Integer maxResults = 5;
     private String q = "";
     private String query = "";
     private Content rawResponse;
     public Youtube(String q) throws IOException {
         this.q = URLEncoder.encode(q);
         this.query = header + maxResults + "&q=" +
                  this.q + "&type=video&videoCategoryId=10&key=" +
                  youtubeApiKey;
         System.out.println(query);
         this.rawResponse = Request.get(this.query).execute().returnContent();
     }
     public Youtube() { }
    @Override
    public void setQuery(String q) {
        this.q = URLEncoder.encode(q);
        this.query = header + maxResults + "&q=" +
            this.q + "&type=video&videoCategoryId=10&key=" +
            youtubeApiKey;
        System.out.println(query);
        try {
            this.rawResponse = Request.get(this.query).execute().returnContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public String getResponse(){
          System.out.println(rawResponse.asString());
          return rawResponse.asString();
     }
     public YoutubeDataClass findResult(){
         return new Gson().fromJson(rawResponse.asString(), YoutubeDataClass.class);
     }

}
