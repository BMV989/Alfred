package org.chatbot.app.Alfred;

import com.google.gson.Gson;
import org.chatbot.app.Alfred.telegram.types.MusicService;
import org.chatbot.app.Alfred.youtube.YoutubeDataClass;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestYoutube implements MusicService {
    private static String filePath = "./src/test/java/org/chatbot/app/Alfred/rawResp.txt";
    private String content;
    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    public TestYoutube() {
        try {
            this.content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public YoutubeDataClass findResult(){ return new Gson().fromJson(this.content, YoutubeDataClass.class);
    }
}
