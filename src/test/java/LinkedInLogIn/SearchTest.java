package LinkedInLogIn;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchTest extends BaseTest {

    // below is the file bath to get the search data file
    public static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data.csv";



    @DataProvider(name = "SearchDataProvider")
    public Object[][] DP() throws IOException {
        return CsvHandlers.readDataLineByLine(FILE_PATH);
    }

    @Test(dataProvider = "SearchDataProvider")
    public void testGoogleResultsVsLinkedInResults(String searchData, String searchType) {

        // perform linkedIn search
        HomePageLinkedIn linkedInSearch = new HomePageLinkedIn(driver);
        linkedInSearch.searchAtLinkedIn(searchData, searchType);

        switchToGoogleTab();
        // perform google search
        GoogleSearch googleSearch = new GoogleSearch(driver);
        googleSearch.searchAtGoogle(searchData, searchType);

        Comparison comparison = new Comparison();

        // below I'm fetching the two links lists to compare them
        List<String> google = comparison.readGoogleLinksFromCSV();
        List<String> linkedIn = comparison.readLinkedInLinksFromCSV();

        Assert.assertNotEquals(google, linkedIn);
    }

    public void switchToGoogleTab() {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
        } else {
            System.out.println("Google tab not found!");
        }
    }
}
