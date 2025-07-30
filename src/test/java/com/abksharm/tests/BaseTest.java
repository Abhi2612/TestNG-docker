package com.abksharm.tests;

import com.abksharm.listener.TestListener;
import com.abksharm.utils.Config;
import com.abksharm.utils.Constants;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getDriverForRemoteExecution() : getDriverForLocalExecution();
        ctx.setAttribute(Constants.DRIVER, this.driver);

    }

    private WebDriver getDriverForLocalExecution() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


    private WebDriver getDriverForRemoteExecution() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        logger.info("grid url format: {} ", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }


    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
