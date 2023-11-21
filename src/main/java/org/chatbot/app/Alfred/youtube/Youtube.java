package org.chatbot.app.Alfred.youtube;

import java.io.IOException;
import java.net.URLEncoder;

import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

public class Youtube {
     final String youtubeApiKey = System.getenv("YT_TOKEN");
     String header = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=";
     Integer maxResults = 5;
     String q = "";
     String query = "";
     Content rawResponse;
     public Youtube(String q) throws IOException {
         this.q = URLEncoder.encode(q);
         this.query = header + maxResults + "&q=" +
                  this.q + "&type=video&videoCategoryId=10&key=" +
                  youtubeApiKey;
         System.out.println(query);
         this.rawResponse = Request.get(this.query).execute().returnContent();
     }
     public String getResponse(){
          System.out.println(rawResponse.asString());
          return rawResponse.asString();
     }
     public YotubeDataClass findResult(){
         return new Gson().fromJson(rawResponse.asString(), YotubeDataClass.class);
     }

}
