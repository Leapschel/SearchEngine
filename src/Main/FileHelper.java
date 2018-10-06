package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileHelper
{
    /**
     * @param filename
     * @return List<Website>
     * @description This method takes a file as a parameter, scans the file, creates website objects for each website listed in the
     * file, and files a list of those websites.
     */

    public static List<Website> parseFile(String filename) {
        List<Website> sites = new ArrayList<Website>();
        Set<Website> sitesWODuplicates = new HashSet<>();
        String url = null;
        String title = null;
        List<String> listOfWords = null;

        try {
            Scanner sc = new Scanner(new File(filename), "UTF-8");
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.startsWith("*PAGE:")) {
                    // create previous website from data gathered
                    if (url != null && title != null && listOfWords != null)
                        {sitesWODuplicates.add(new Website(url, title, listOfWords));}

                    // new website start
                    {url = line.substring(6);
                    title = null;
                    listOfWords = null;}
                }
                else if (title == null) {
                    title = line;
                }
                else {
                    // and that's a word!
                    if (listOfWords == null)
                        {listOfWords = new ArrayList<String>();}

                        listOfWords.add(line.toLowerCase().trim());}
            }
            //this line adds the last scanned website b/c the loop will end before creating it
            if (url != null && title != null && listOfWords != null) {
                sitesWODuplicates.add(new Website(url, title, listOfWords));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Couldn't load the given file");
           e.printStackTrace();
        }
        for(Website site : sitesWODuplicates) sites.add(site);
        return sites;
    }


}