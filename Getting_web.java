package httpclient_learn;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;


/**
 * java实现爬虫并读取图片
 */
public class Getting_web {

    /**
     * 启动类
     */
    public static void main(String[] args) throws IOException {
        System.out.println("\n" +
                "  ______               __                      __    __                        __                __                      ______  \n" +
                " /      \\             /  |                    /  |  /  |                      /  |              /  |                    /      \\ \n" +
                "/$$$$$$  | __    __  _$$ |_     ______        $$ |  $$ |  ______   _______   _$$ |_     ______  $$/          __     __ /$$$$$$  |\n" +
                "$$ |__$$ |/  |  /  |/ $$   |   /      \\       $$ |__$$ | /      \\ /       \\ / $$   |   /      \\ /  | ______ /  \\   /  |$$ ___$$ |\n" +
                "$$    $$ |$$ |  $$ |$$$$$$/   /$$$$$$  |      $$    $$ |/$$$$$$  |$$$$$$$  |$$$$$$/    $$$$$$  |$$ |/      |$$  \\ /$$/   /   $$< \n" +
                "$$$$$$$$ |$$ |  $$ |  $$ | __ $$ |  $$ |      $$$$$$$$ |$$    $$ |$$ |  $$ |  $$ | __  /    $$ |$$ |$$$$$$/  $$  /$$/   _$$$$$  |\n" +
                "$$ |  $$ |$$ \\__$$ |  $$ |/  |$$ \\__$$ |      $$ |  $$ |$$$$$$$$/ $$ |  $$ |  $$ |/  |/$$$$$$$ |$$ |          $$ $$/   /  \\__$$ |\n" +
                "$$ |  $$ |$$    $$/   $$  $$/ $$    $$/______ $$ |  $$ |$$       |$$ |  $$ |  $$  $$/ $$    $$ |$$ |           $$$/    $$    $$/ \n" +
                "$$/   $$/  $$$$$$/     $$$$/   $$$$$$//      |$$/   $$/  $$$$$$$/ $$/   $$/    $$$$/   $$$$$$$/ $$/             $/      $$$$$$/  \n" +
                "                                      $$$$$$/                                                                                    \n");
        Scanner sc = new Scanner(System.in);
        System.out.println("输入标签，若无的话可输入回车");
        String get;
        if (sc.hasNextLine()) {
            get = sc.nextLine();
        } else {
            return;
        }
        /**
         * @theURL 所爬取网页链接
         */
        String theURL = "https://api.lolicon.app/setu/v2?r18=1&tag=" + get;

        System.out.println("网站爬取完成");
        String last0 = spiderURL(theURL);
        JsonObject jsonObject = new JsonParser().parse(Objects.requireNonNull(last0)).getAsJsonObject();
        System.out.println(jsonObject);
        JsonArray data = jsonObject.getAsJsonArray("data");
        String last1 = data.get(0).getAsJsonObject().get("urls").getAsJsonObject().get("original").getAsString();
        String last2 = data.get(0).getAsJsonObject().get("title").getAsString();
        System.out.println(last2 + "\n" + last1);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(last1));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * 爬取该网页源码和网页内连接
     *
     * @param url 该网页网址
     */
    public static String spiderURL(String url) throws IOException {
        Connection con = Jsoup.connect(url);
        con.ignoreContentType(true);
        return con.get().body().text();
    }
}