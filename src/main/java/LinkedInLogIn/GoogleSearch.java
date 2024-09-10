package LinkedInLogIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearch extends BasePage {

	private LinkedInLocators locators;

	public GoogleSearch(WebDriver driver) {
		super(driver);
		locators = PageFactory.initElements(driver, LinkedInLocators.class);
	}

	public void searchAtGoogle(String searchData, String searchType) {
		WebElement searchBox = locators.googleSearchBox;

		try {
			Thread.sleep(1000);  // Adjust sleep time as needed for loading
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sendKeysToElement(searchBox, searchData + " " + searchType + " linkedin.com");
		searchBox.submit();

		try {
			Thread.sleep(1000);  // Adjust sleep time as needed for loading
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> searchResults = locators.googleSearchResults;

		List<String> URLs = new ArrayList<>();

		List<String>  [] URL = new List[1];
		URL[0] = new ArrayList<>();


		for (int i = 0; i < Math.min(5, searchResults.size()); i++) {
			WebElement parent = searchResults.get(i).findElement(By.xpath(".."));
			String url = parent.getAttribute("href");
			URLs.add(url);
			URL[0].add(url);
		}

		CsvHandlers.writeDataLineByLine(System.getProperty("user.dir") + "/src/main/resources/Google_Results.csv",URL);

	}

}
