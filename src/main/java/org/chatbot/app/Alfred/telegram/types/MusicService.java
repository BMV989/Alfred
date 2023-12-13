package org.chatbot.app.Alfred.telegram.types;

import org.chatbot.app.Alfred.youtube.YoutubeDataClass;

public interface MusicService {
    YoutubeDataClass findResult();
    void setQuery(String q);
}
