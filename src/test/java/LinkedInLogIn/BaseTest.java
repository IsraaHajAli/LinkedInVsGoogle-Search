package LinkedInLogIn;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

public class BaseTest {
    protected WebDriver driver;



    @BeforeMethod
    public void setUp() {
        PropertyReader propReader = new PropertyReader();

        System.setProperty("webdriver.chrome.driver", propReader.getDriverPath());
        driver = new ChromeDriver();  // Assign the created driver to the class variable

        // below I'm opening the linkedIn first, in the first tab
        driver.get(propReader.getLoginURL());
        driver.manage().window().maximize();

        // below I'm logging in to linked in account
        LinkedInLoginPage login = new LinkedInLoginPage(driver);
        login.login(propReader.getValidUsername(), propReader.getValidPassword());

        // below I'm opening a new tab to open google home page
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // below I'm getting the number of opened tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // below I'm ensuring that there is 2 tabs opened, such that if there is more than one tab then I will swtich to the second one
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            driver.get(propReader.getGoogleURL());
            driver.manage().window().maximize();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Second tab not found ");
        }

        // below I'm switching to linkedIn again to perform the test
        driver.switchTo().window(tabs.get(0));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
