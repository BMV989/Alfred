package org.chatbot.app.Alfred.telegram.types;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.hc.client5.http.fluent.Request;
import org.chatbot.app.Alfred.youtube.Youtube;
import org.chatbot.app.Alfred.youtube.YoutubeDataClass;

public interface MusicService {
    YoutubeDataClass findResult();
    void setQuery(String q);
}
