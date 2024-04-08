package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Constant.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Utilities {
    public static String getProjectPath(){
        return "D:/Selenium";
    }

    public static void scrollToFindElement(WebElement element){
        Actions actions = new Actions(Constants.WEBDRIVER);
        actions.moveToElement(element);
        actions.perform();
    }

    public static String getDate(){
        LocalDate currentDate = LocalDate.now().plusDays(5);
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("M/d/yyyy");
        return currentDate.format(formatter);
    }





}