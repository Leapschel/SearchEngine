package Crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *The filemaker receives the information about the current website and writes it to a textfile.
 * * @Jana Schmurr
 */

public class FileMaker {

    PrintWriter pw;
    String nl = System.getProperty("line.separator");

    public FileMaker() {

        try {
            //BufferedWriter - so it doesn't open and close every time
            BufferedWriter bw = new BufferedWriter(new FileWriter("myWikiWeb.txt", true));
            //pw without automatic line flushing
            pw = new PrintWriter(bw);
        } catch (IOException e) {
            System.out.println("Error with the FileMaker");
            e.printStackTrace();
        }
    }

    public void writeToFile(String url, String title, String paragraph){
        //build intro
        String words = paragraph.split("\\.")[0];
        if (words.length()<15){ words += paragraph.split("\\.")[1]; }
        // clean up text
        String intro =  words.replaceAll( "\\(.*\\)|,|\"|","").replaceAll("\\s+", nl).toLowerCase();
        pw.println("*PAGE:"+ url+ nl +title + nl + intro);
        pw.close();
    }

}
