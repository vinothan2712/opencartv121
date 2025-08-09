package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTest extends BaseClass {

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void verify_login() {
        logger.info("**** Starting TC_002_LoginTest ****");
        logger.debug("Capturing application debug logs...");

        try {
            // Home Page actions
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link on the home page.");
            hp.clickLogin();
            logger.info("Clicked on Login link under My Account.");

            // Login Page actions
            LoginPage lp = new LoginPage(driver);
            logger.info("Entering valid email and password...");
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();
            logger.info("Clicked on login button.");

            // My Account Page verification
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            Assert.assertTrue(targetPage, "Login failed - My Account page not displayed.");
            logger.info("Login successful and My Account page verified.");

        } catch (Exception e) {
            logger.error("Login test encountered an exception: " + e.getMessage());
            Assert.fail("Test execution failed due to an exception.");
        }

        logger.info("**** Finished TC_002_LoginTest ****");
    }
}
