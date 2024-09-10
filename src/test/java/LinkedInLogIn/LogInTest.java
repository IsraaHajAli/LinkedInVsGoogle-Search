package LinkedInLogIn;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {
	
    PropertyReader propReader = new PropertyReader();

	
	String linkedInURL = propReader.getLoginURL();

//    @Test
//    public void verify_User_Can_LogIn_With_Valid_EmailandPassword() {
//        driver.get(propReader.getLoginURL());
//        
//        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
//        
//        loginPage.login(propReader.getValidUsername(), propReader.getValidPassword());
//        
//        Assert.assertTrue(loginPage.isProfileIconVisible(), "Login failed, profile icon is not displayed.");
//    }
//    
//    @Test
//    public void verify_User_Can_LogIn_With_Valid_Email_InvalidPassword() {
//        driver.get(propReader.getLoginURL());
//        
//        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
//        
//        loginPage.login(propReader.getValidUsername(), propReader.getInValidPassword());
//        
//        Assert.assertTrue(loginPage.isErrorMessageVisible(), "Login looks successful, error message is not displayed.");
//    }
//    
//    @Test
//    public void pageTitle() {
//        
//        driver.get(propReader.getLoginURL());
//
//        String actualTitle = driver.getTitle();
//        
//        Assert.assertEquals(actualTitle, propReader.getPageTitle(), "Page title is not as expected.");
//        
//    }
//    
//    @Test
//    public void verifyForgotPasswordVisible() {
//        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
//        loginPage.isForgotButtonVisible();
//
//    }
    
        
    @Test
    public void verifySearchBoxVisible() {
        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
        loginPage.isSearchBoxVisible();

    }
    
    
}
