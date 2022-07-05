package dev.blackmon;

import dev.blackmon.pages.LoginPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

@Suite
public class loginRunner {
    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setup() {
        File chrome = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }


}
