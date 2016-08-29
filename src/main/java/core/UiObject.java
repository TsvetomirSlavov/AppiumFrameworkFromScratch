package core;

import api.android.some.Android;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by ceko on 08/28/2016.
 */
public class UiObject {

    private String locator;

    UiObject(String locator){
        this.locator = locator;
        System.out.println(this.locator);

    }

    //distinguish between xpath and uiselector from UiSelector class objects
    private boolean isXpath(){
        return !locator.contains("UiSelector");
    }

    //does the element actually exist
    public boolean exists(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
        }
        catch (NoSuchElementException e){
            return false;
        }

    }






}
