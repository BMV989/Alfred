package org.chatbot.app.Alfred.youtube;

import java.io.IOException;
import java.text.MessageFormat;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Executor;

public class YoutubeDownloader {
    final Executor session = Executor.newInstance();
    private String id;
    final String downloadEndpoint = "https://apiyoutube.cc/m4a/{0}::{1}";
    final String metaEndpoint = "https://apiyoutube.cc/progress.php?id={0}";
    final String thumbnailEndpoint = "https://i.ytimg.com/vi/{0}/mqdefault.jpg";
    final String dataEndpoint = "https://apiyoutube.cc/check2.php?v={0}&uhash=baceebebc179d3cdb726f5cbfaa81dfe";
    final String baseEndpoint = "https://apiyoutube.cc/?url=https://www.youtube.com/watch?v={0}&t=0&color=3b6c96";


    private final String hash;
    private final String user;
    private final String title;
    private final String tumbnail;
    private final String video;

    public YoutubeDownloader(String id) throws IOException{
        this.id = id;
        String rawDataResponse = session.execute(
                Request.get(MessageFormat.format(dataEndpoint, id))
                        .addHeader("Sec-Fetch-Mode", "corss")
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("Referer", MessageFormat.format(baseEndpoint, this.id))
        ).returnContent().asString();

        hash = rawDataResponse.split(",")[1].split("\"")[3];
        user = rawDataResponse.split("\"")[3];

        String rawMetaResponse = session.execute(
                Request.get(MessageFormat.format(metaEndpoint, hash))
        ).returnContent().asString();

        title = rawMetaResponse.split("\"")[7];
        tumbnail = MessageFormat.format(thumbnailEndpoint, this.id);
        video = MessageFormat.format(downloadEndpoint, hash, user);
    }
    public String[] getDownloadInfo(){
        String[] Info = new String[2];
        Info[0] = tumbnail;
        Info[1] = video;
        return Info;
    }
}
