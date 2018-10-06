import Index.*;
import Main.FileHelper;
import Main.Website;

import java.util.ArrayList;
import java.util.List;

/*
A class for testing the efficiency of the SimpleIndex, InvertedIndexHashMap, and InvertedIndexTreeMap.
@author Sarah Amick
 */
public class BenchmarkTest {
    public static void main(String[] args) {

        List<Website> sites = FileHelper.parseFile("TestFiles/enwiki-tiny-copy.txt");

        //Change index here:
        Index index = new InvertedIndexHashMap();

        index.build(sites);

        ArrayList<String> queryWords = new ArrayList<>();
        queryWords.add("denmark");
        queryWords.add("it");
        queryWords.add("ocean");
        queryWords.add("america");
        queryWords.add("of");
        queryWords.add("scandinavian");
        queryWords.add("square");
        queryWords.add("russia");
        queryWords.add("north");
        queryWords.add("university");
        queryWords.add("officially");
        queryWords.add("danish");
        queryWords.add("major");
        queryWords.add("contiguous");

       //Warm up:
        int counter = 0;
        int numberOfWarmupSites = 0;

        while(counter<1000){

            for(String query : queryWords){
            List<Website> foundWebsites = index.lookup(query);
            numberOfWarmupSites += foundWebsites.size();
            }
            counter++;
        }
        System.out.println("Number of websites found in warmup: " + numberOfWarmupSites);

        //Measure time:
        List<Double> listofElapsedTimes = new ArrayList<>();
        int numberOfFoundWebsites = 0;

        for(int i=1; i<10; i++) {


            long startTime = System.nanoTime();

            for (String query : queryWords) {
                List<Website> foundWebsites = index.lookup(query);
                numberOfFoundWebsites += foundWebsites.size();
            }

            long endTime = System.nanoTime();

            double elapsedTime = (endTime - startTime) / 10e6;
            listofElapsedTimes.add(elapsedTime);
        }

        double sum = 0;
        for(Double time : listofElapsedTimes){
            sum += time;}
        double averageTime = sum/listofElapsedTimes.size();



        System.out.println("Average index running time for " + queryWords.size() + " query words took: " + averageTime + " miliseconds.");
        System.out.println("Found websites: " + numberOfFoundWebsites);

    }
}

