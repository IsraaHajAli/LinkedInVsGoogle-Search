package LinkedInLogIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LinkedInLoginPage extends BasePage {

    private LinkedInLocators locators;

    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        locators = PageFactory.initElements(driver, LinkedInLocators.class);
    }

    public void enterUsername(String username) {
        locators.userNameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        locators.passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement signInBtn = locators.signInButton;
        click(signInBtn);
    }

    public boolean isProfileIconVisible() {
        WebElement profileIcon = locators.profileIcon;
        return profileIcon.isDisplayed();
    }

    public boolean isErrorMessageVisible() {
        WebElement errorMessage = locators.errorMessage;
        return errorMessage.isDisplayed();
    }

    public boolean isForgotButtonVisible() {
        WebElement forgotButton = locators.forgotButton;
        waitForVisibility(forgotButton);
        return forgotButton.isDisplayed();
    }

    public boolean isSearchBoxVisible() {
        WebElement searchBox = locators.searchBox;
        waitForVisibility(searchBox);
        return searchBox.isDisplayed();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }
}
