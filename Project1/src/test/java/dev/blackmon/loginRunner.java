package dev.blackmon;

import dev.blackmon.pages.LoginPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "dev.blackmon.steps")
public class loginRunner {
    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeClass
    public static void setup() {
        File chrome = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }


}
