//package Crawler;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import javax.swing.text.Document;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * The SpiderLeg receives a url from the Spider, and then retrieves the tile, the text, and all Links on the website.
// * The url, title and text is passed on to the Filemaker,
// * the links are added to the List of Websites the Spider will visit.
// * * @Jana Schmurr
// */
//
//public class SpiderLeg {
//
//    // User-Agent-String (Browsertyp-Indefitizierungstext)
//    private static final String USER_AGENT =
//            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1; jansc@itu.dk";
//    // This is our web page, or in other words, our HTML document
//    private Document site;
//    private String baseUrl = "https://en.wikipedia.org";
//
//
//    public List<String> crawlWebsite(String url) {
//        //the List of Links on that Website
//        List<String> linksList = new LinkedList<String>();
//
//        Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
//        try {
//            this.site = connection.get();
//        } catch (IOException e) {
//            System.out.println(url + " doesn't exist.");
//            e.printStackTrace();
//        }
//        String title = site.select("h1").text();
//        String paragraph = site.select(".mw-body-content p:not(:has(#coordinates))").text();
//
//        //write website to File
//        FileMaker fileMaker = new FileMaker();
//        fileMaker.writeToFile(url, title, paragraph);
//
//        Elements links = site.select("a[href]");
//       if (!links.isEmpty()) {
//           for (Element link : links) {
//               String linkUrl = link.attr("href");
//               if (isValidLink(linkUrl) && !linksList.contains(linkUrl)) {
//                   linksList.add(baseUrl + linkUrl);
//               }
//           }
//       }
//        return linksList;
//    }
//
//    public boolean isValidLink(String url) {
//        if (url.startsWith("/wiki/")) {
//            if (url.contains(":") || url.contains("php?title") || url.contains("Main_Page") || url.endsWith("_(disambiguation)")
//                    || url.contains("International_Standard_Book_Number")  || url.endsWith("JSTOR"))  {
//                return false;
//            } else return true;
//        } else return false;
//    }
//}
