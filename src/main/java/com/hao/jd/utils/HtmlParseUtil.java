package com.hao.jd.utils;

import com.hao.jd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
//    public static void main(String[] args) {
//    new HtmlParseUtil().parseJD("java").forEach(System.out::println);
//
//    }

    public List<Content> parseJD(String keywords) {
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        List<Content> goodLists = new ArrayList<>();
        try {
            Document document = Jsoup.parse(new URL(url), 3000);
            Element element = document.getElementById("J_goodsList");
            Elements elements = element.getElementsByTag("li");

            for (Element el : elements) {
                String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();

                Content content = new Content();
                content.setImg(img);
                content.setPrice(price);
                content.setTitle(title);
                goodLists.add(content);
//                System.out.println("++++++++++++++++++++");
//                System.out.println(img);
//                System.out.println(price);
//                System.out.println(title);
//                System.out.println("+++++++++++++++++++++");
            }
//            System.out.println(element.html());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodLists;
    }
}
