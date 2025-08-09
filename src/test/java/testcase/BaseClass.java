package testcase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException 
    {
    	//Loading config.properties file
    	
    	FileReader file=new FileReader("./src//test//resources//config.properties");
    	
    	p=new Properties();
    	p.load(file);
    	
        logger = LogManager.getLogger(this.getClass());

        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name...");
                return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL2"));//READING URL FROM PROPERTIES FILE
        //driver.get("https://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumberic() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return generatedString + "@" + generatedNumber;
    }
}
