package Main;

import java.util.ArrayList;
import java.util.List;

/**
 * The website is the basic entity of the search engine.
 */

public class Website {
    /**
     * The website's title
     */
    private String title;
    /**
     * The website's url
     */
    private String url;
    /**
     * The website's words
     */
    private List<String> words;


    /**
     * @param url
     * @param title
     * @param words
     * @description Creates a website object from a url, a title, and a list of words that are contain on the website.
     */

    public Website(String url, String title, List<String> words)
        {this.url = url;
        this.title = title;
        this.words = words;
            }

    public List<String> getWords()
        {return words;}

    public String getTitle()
        {return title;}

    public String getUrl()
        {return url;}

    public Boolean containsWord(String word)
        {return words.contains(word);}

    @Override
    public String toString() {
        return "Main.Website{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", words=" + words +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Website website = (Website) o;

        return url != null ? url.equals(website.url) : website.url == null;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
