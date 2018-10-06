//package Crawler;
//
//import Main.Website;
//
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Set;
//
///**
// * The Spider Class receives the List of all Websites read from the existing textfile.
// * Starting from the first website, each website is scraped with the spiderleg (in Class SpiderLeg),
// * and then added to a new textfile (in Class Filemaker)
// * * @Jana Schmurr
// */
//
//public class Spider {
//
//    private int maxSitesToVisit = 10;
//    private Set<String> sitesVisited = new HashSet<>();
//    private List<String> sitesYetToVisit= new LinkedList<>();
//
//    SpiderLeg leg = new SpiderLeg();
//
//    public Spider(){
//    }
//
//    public Spider(int maxSitesToVisit ){
//        this.maxSitesToVisit = maxSitesToVisit;
//    }
//
//    public void landSpider(List<Website> websiteDatabase) {
//        websiteDatabase.forEach((i) -> sitesYetToVisit.add(i.getUrl()));
//        assert(websiteDatabase.size()==sitesYetToVisit.size());
//        try {
//            crawlWiki();
//        } catch (InterruptedException e) {
//            System.out.println("Error with timer");
//            e.printStackTrace();
//        }
//    }
//
//
//    public void crawlWiki() throws InterruptedException {
//        while ( sitesVisited.size() < maxSitesToVisit && sitesYetToVisit.size()>0) {
//            String currentUrl = sitesYetToVisit.remove(0);
//            if (sitesVisited.contains(currentUrl)) {
//                currentUrl = getNextUrl();
//            }
//            //crawl current website and return links
//            List<String> linksList = leg.crawlWebsite(currentUrl);
//            // we add those links to our To-Do list. This ensures that we crawl first into width, then into depth
//            sitesYetToVisit.addAll(linksList);
//            this.sitesVisited.add(currentUrl);
//
//            //Pause for 2 seconds (not ideal, because of time lag, but ok. For more info, see https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html
//            Thread.sleep(2000);
//                crawlWiki();
//            }
//        //System.out.printf("Finished crawling process at %s MAX. \n Visited %s websites.", maxSitesToVisit, sitesVisited.size());
//    }
//
//
//    private String getNextUrl() {
//        String nextUrl;
//        do {
//            nextUrl = this.sitesYetToVisit.remove(0);
//        } while (this.sitesVisited.contains(nextUrl));
//
//        this.sitesVisited.add(nextUrl);
//        return nextUrl;
//    }
//
//    public Set<String> getSitesVisited() {
//        return sitesVisited;
//    }
//
//    public List<String> getSitesYetToVisit() {
//        return sitesYetToVisit;
//    }
//
//}
//
//
//
//
//
//
//
