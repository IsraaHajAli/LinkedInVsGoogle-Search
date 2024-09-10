package LinkedInLogIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePageLinkedIn extends BasePage {

    private LinkedInLocators locators;

    public HomePageLinkedIn(WebDriver driver) {
        super(driver);
        locators = PageFactory.initElements(driver, LinkedInLocators.class);
    }


    /** below is the algorithm of searching in LinkedIn **/
    public void searchAtLinkedIn(String searchData, String searchType) {

        WebElement searchBox = locators.searchBox;
        searchBox.sendKeys(searchData);
        searchBox.sendKeys(Keys.RETURN);

        // wait for search filters to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfAllElements(locators.linkedInFilters));

        // below I'm selecting the search filter according to the search type provided by the user
        List<WebElement> filters = locators.linkedInFilters;
        for (WebElement filter : filters) {
            if (filter.getText().equalsIgnoreCase(searchType)) {
                filter.click();
                break;
            }
        }

        // wait for search results to load
        wait.until(ExpectedConditions.visibilityOfAllElements(locators.linkedInSearchResults));

        /** below is an array of list (each row in the array stores a list, each list stores the attributes for all results from one type)
         * e.g. first row (list) stores the names
         * second row stores the profiles links
         * third row stores the positions
         * last row stores the locations
         * **/
        List<String> [] resultData = new List[4];

        resultData[0] = new ArrayList<>();
        resultData[1] = new ArrayList<>();
        resultData[2] = new ArrayList<>();
        resultData[3] = new ArrayList<>();

        /**
         * the loop below iterates the attributes lists (for the loop condition(Math.min(5, locators.names.size())) I supposed to get the first 5 five results
         * , but it probably that I get less than 5 results so that is why I'm getting the minimum from 5 and the # of results obtained )
         * **/
        for (int i = 0; i < Math.min(5, locators.names.size()); i++) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            resultData[0].add((String) js.executeScript("return arguments[0].childNodes[0].textContent;", locators.names.get(i)));
            resultData[1].add(locators.profilesLinks.get(i).getAttribute("href"));
            resultData[2].add(locators.resultPosition.get(i).getText());
            resultData[3].add(locators.locations.get(i).getText());
        }

        // below I'm calling the method for write the results data on the file
        CsvHandlers.writeDataLineByLine(System.getProperty("user.dir") + "/src/main/resources/LinkedInResults.csv",resultData);

    }


}
