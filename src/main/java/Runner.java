import api.android.some.Android;
import core.ADB;
import core.MyLogger;
import core.UiObject;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import core.UiSelector;

/**
 * Created by ceko on 08/27/2016.
 */
public class Runner {

    //start appium manually from cmd appium
    public static void main(String[] args) throws MalformedURLException {
        MyLogger.log.setLevel(Level.DEBUG);
        AndroidDriver driver = null;
        try{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "0715f7c98061163a");
            capabilities.setCapability("platformName", "Android");
            //FIX TO WORK CHANGE PERMISSIONS SECURITY THE FILE AND FOLDER FULL ACCESS TO USER ALSO IN WINDOWS FILE CONTEXT PROPERTIES
            //app capability with unlock is very importatnt to bring the application under test to the same state, without it the application under test restarts and goes to home screen and sometimes we do not want that
            capabilities.setCapability("app", "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\build\\unlock_apk\\unlock_apk-debug.apk");
            //capabilities.setCapability("appPackage", "org.zwanoo.android.speedtest");
            //capabilities.setCapability("appActivity", "com.ookla.speedtest.softfacade.MainActivity");

            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            //just for now
            Android.driver = driver;

            ADB adb = new ADB("0715f7c98061163a");
            adb.openAppsActivity("org.zwanoo.android.speedtest", "com.ookla.speedtest.softfacade.MainActivity");

            UiObject testAgainButton = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/o2_button_text").makeUiObject();
            UiObject ping = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/pingSpeed").makeUiObject();
            UiObject download = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/downloadSpeed").makeUiObject();
            UiObject upload = new UiSelector().resourceId("org.zwanoo.android.speedtest:id/uploadSpeed").makeUiObject();

            //VERY IMPORTANT WAITS BECAUSE OTHERWISE IT CAN NOT FIND THE ELEMENTS
            testAgainButton.waitToAppear(15).tap().waitToDisappear(5).waitToAppear(120);

            MyLogger.log.info("Ping: " +ping.getText());
            MyLogger.log.info("Download: " +download.getText());
            MyLogger.log.info("Upload: " +upload.getText());
        }
        finally{
            if(driver != null) driver.quit();
        }





    }






















//MyLogger.log.setLevel(Level.DEBUG);
    //MyLogger.log.debug("Test Debug");//Use DEBUG for low level classes my classes
    //MyLogger.log.error("Test Error");
    //MyLogger.log.info("Test Info");
    //MyLogger.log.warn("Test Warning");
}
