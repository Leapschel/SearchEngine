import Main.FileHelper;
import Main.Website;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHelperTest {

    List<Website> sites = new ArrayList<>();

    @BeforeEach
    void setUp() {
        sites = FileHelper.parseFile("TestFiles/FileHelperTestFile");
    }

    @AfterEach
    void tearDown() {
        sites.clear();
    }

    @Test
    void parseFile() {
        assertTrue(sites.size() == 3);
        assertFalse(sites.size() == 5);
        assertTrue(sites.get(0).getWords().size() == 1);
        assertTrue(sites.get(1).getWords().size() == 1);
        assertTrue(sites.get(2).getWords().size() == 3);
        assertEquals(sites.get(0).getTitle(), "Haha");
        assertEquals(sites.get(1).getTitle(), "HAHAAA");
        assertEquals(sites.get(2).getTitle(), "Titleforregularsite");
        assertEquals(sites.get(0).getUrl(), "https://haha.com");
        assertEquals(sites.get(1).getUrl(), "https://hahahaha.com");
        assertEquals(sites.get(2).getUrl(), "https://regularsite.com");
    }
}
