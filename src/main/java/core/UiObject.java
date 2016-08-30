package core;

//he has api.android.Android and a method scrollTo() but this library does not have it I have to implement it there are some examples online
//scrollTo() is removed
import api.android.some.Android;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
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

    //missing scrollTo method my implementation
    //public MobileElement scrollTo(String text) {
    //    String uiScrollables = AndroidDriver.UiScrollable("new UiSelector().descriptionContains(\"" + text + "\")") +
    //            AndroidDriver.UiScrollable("new UiSelector().textContains(\"" + text + "\")");
    //    return (MobileElement) findElementByAndroidUIAutomator(uiScrollables);
    //}

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
            return element.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element checked
    public boolean isChecked(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("checked").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element checkable
    public boolean isCheckable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("checkable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element clickable
    public boolean isClickable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("clickable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element enabled
    public boolean isEnabled(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("enabled").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element focusable
    public boolean isFocusable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("focusable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element in focus
    public boolean isFocused(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("focused").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }


    //is the element scrollable
    public boolean isScrollable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("scrollable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element longClickable
    public boolean isLongclickable(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("longclickable").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    //is the element selected
    public boolean isSelected(){
        try{
            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("selected").equals("true");
        }
        catch (NoSuchElementException e){
            return false;
        }
    }



    public Point getLocation(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getLocation();


    }

    public String getText(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("name");

    }

    public String getResourceId(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("resourceId");

    }

    public String getClassName(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("className");

    }

    public String getContentDesc(){

            WebElement element;
            if(isXpath()) element = Android.driver.findElementByXPath(locator);
            else element = Android.driver.findElementByAndroidUIAutomator(locator);
            return element.getAttribute("contentDesc");

    }

    public UiObject clearText(){

        if(isXpath()) Android.driver.findElementByXPath(locator).clear();
        else  Android.driver.findElementByAndroidUIAutomator(locator).clear();
        return this;
    }

    public UiObject typeText(String value){

        if(isXpath()) Android.driver.findElementByXPath(locator).sendKeys(value);
        else  Android.driver.findElementByAndroidUIAutomator(locator).sendKeys(value);
        return this;
    }

    public UiObject tap(){

        if(isXpath()) Android.driver.findElementByXPath(locator).click();
        else  Android.driver.findElementByAndroidUIAutomator(locator).click();
        return this;
    }

    //public UiObject scrollTo(){
    //    if(locator.contains("text")) throw new RuntimeException("Scroll to method can only be used with text attributes and current locator: "+locator+" does not contain any text attributes!");
    //    if(isXpath()) Android.driver.scrollTo(locator.substring(locator.indexOf("@text=\""), locator.indexOf("\"]")).replace("@text=\"", ""));
    //    else{
    //        String text;
    //        if(locator.contains("textContains")) text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\")")).replace(".textContains(\"", "");
    //        else text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\")")).replace(".textContains(\"", "");
    //        Android.driver.scrollTo(text);
    //    }
    //    return this;
    //}

    public UiObject waitToAppear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(exists()) break;
        if(timer.expired(seconds) && !exists()) throw new AssertionError("Element "+locator+" failed to appear within "+seconds+" seconds");
        return this;
    }

    public UiObject waitToDisappear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(!exists()) break;
        if(timer.expired(seconds) && exists()) throw new AssertionError("Element "+locator+" failed to disappear within "+seconds+" seconds");
        return this;
    }










}
