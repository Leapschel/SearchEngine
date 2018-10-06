package Main;
import Crawler.*;
import Index.*;
import Scoring.*;

import java.util.List;
import java.util.Scanner;

public class SearchEngine {

    //choose if you want to use the WebCrawler
    static boolean activateCrawler = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the SearchEngine!");
        if (args.length != 1) {
            System.out.println("Error: Filename is missing");
            return;
        }

        Scanner sc = new Scanner(System.in);
        InvertedIndex indx = new InvertedIndexHashMap();
        Score score = new BM25Score();


        List<Website> sites = FileHelper.parseFile(args[0]);

       if (activateCrawler){
           System.out.println("Please wait...");
//           new Spider(100).landSpider(sites);
           try{
               List<Website>  newSites =  FileHelper.parseFile("myWikiWeb.txt");
               System.out.printf("You have access to %s sites now.", newSites.size());
               indx.build(newSites);
           }
           catch (Exception e){
               e.printStackTrace();
           }
       }
       else {
           indx.build(sites);
       }

        System.out.println("Please provide a query word");

        while (sc.hasNext()) {

            QueryHelper queryHelp = new QueryHelper(indx);

            String line = sc.nextLine();
            List<Website> result = queryHelp.getMatchingWebsites(line);

            long startTime = System.nanoTime();

            for (Website w : result) {
                System.out.println("Query is found on " + w.getUrl() + ".");
            }

            long endTime = System.nanoTime();

            if (result.size() == 0) {
                System.out.println("No websites contain the query word.");
            }

            double elapsedTime = ((endTime - startTime) / 10e6);
            System.out.println("Your search was completed in " + elapsedTime + " miliseconds.");


            System.out.println("Please provide the next query word.");
        }
    }
}
