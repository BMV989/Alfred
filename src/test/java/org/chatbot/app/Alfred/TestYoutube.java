package org.chatbot.app.Alfred;

import com.google.gson.Gson;
import org.chatbot.app.Alfred.youtube.YotubeDataClass;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestYoutube {
    private final String content;
    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    public TestYoutube(String q) throws IOException {
        String filePath = "./src/test/java/org/chatbot/app/Alfred/rawResp.txt";
        this.content = readFile(filePath, StandardCharsets.UTF_8);
    }
    public YotubeDataClass findResult(){ return new Gson().fromJson(this.content, YotubeDataClass.class);
    }
}
