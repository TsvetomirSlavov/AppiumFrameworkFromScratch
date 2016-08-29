import io.appium.java_client.android.AndroidDriver;
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

    public static void main(String[] args) {


        new UiSelector().resourceId("hello").text("item1").makeUiObject();
        new UiSelector().resourceId("hello").text("item2").makeUiObject();
        new UiSelector().resourceId("hello").text("item3").makeUiObject();
        new UiSelector().resourceId("hello").text("item4").makeUiObject();
        new UiSelector().resourceId("hello").text("item5").makeUiObject();
        new UiSelector().resourceId("hello").text("item6").makeUiObject();
        new UiSelector().resourceId("hello").text("item7").makeUiObject();





    }

}
