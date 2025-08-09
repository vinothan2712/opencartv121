package testcase;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AccountregistrationPage;
import PageObject.HomePage;

public class TC_001_AccountRegistrationTest extends BaseClass {

	private static final Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TC_001_AccountRegistrationTest.class);

	@Test
    public void verify_account_registration() {
        logger.info("****** Starting TC_001_AccountRegistrationTest ******");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link");

            hp.clickRegister();
            logger.info("Clicked on Register link");

            AccountregistrationPage regpage = new AccountregistrationPage(driver);

            logger.info("Providing customer details");
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String password = randomAlphaNumberic();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Validating expected confirmation message...");
            String confmsg = regpage.getConfirmationMsg();
            if(confmsg.equals("Your Account Has Been Created"))
            {
            	Assert.assertTrue(true);
            }
            
            else 
            {
            	logger.error("test failed");
            	logger.debug("Debug logs");
            	Assert.assertTrue(false);
            }
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
            }
        
         catch (Exception e) 
        {
           // logger.error("Test failed", e);
            Assert.fail("Account registration test failed due to an exception.");
        }

        logger.info("****** Finished TC_001_AccountRegistrationTest ******");
    }
}
