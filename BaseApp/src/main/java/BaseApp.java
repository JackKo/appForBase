import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.image.ByteArrayImageSource;

import java.util.List;

/**
 * Created by JK on 17.06.15.
 */
public class BaseApp {

    public static void main(String ... args){

        String newLeadName = "Chuck Norris";
        String newStatus = "Knocked-Out";

        WebDriver driver = new FirefoxDriver();
        driver.get("https://core.futuresimple.com/users/login");

        WebElement element = driver.findElement(By.id("user_email"));
        element.sendKeys("jacek.kosno@gmail.com");
        element = driver.findElement(By.id("user_password"));
        element.sendKeys("Plobpomalpa12");

        driver.findElement(By.tagName("button")).click();

        //moving to Leads webPage

        driver.get("https://app.futuresimple.com/leads");


        if (driver instanceof JavascriptExecutor) {
            System.out.println("com.base.app.WebPage load status is set on " + String.valueOf(((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")));
        }

        driver.get("https://app.futuresimple.com/leads/new");

        //adding Lead

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Save']")));

        element = driver.findElement(By.id("lead-first-name"));
        element.sendKeys(newLeadName.split(" ")[0]);
        element = driver.findElement(By.id("lead-last-name"));
        element.sendKeys(newLeadName.split(" ")[1]);
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        // lead is in place, let's check it's status
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='status']" +
                        "/div[@class='dropdown']//span[@class='lead-status']")));

        element = driver.findElement(By.xpath("//div[@class='status']" +
                "/div[@class='dropdown']//span[@class='lead-status']"));
        System.out.println("And status we have " + element.getText());

        //Lets go to Settings / maybee we will play with dropdown if time allows

        driver.get("https://app.futuresimple.com/settings/leads/lead-status");

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-toggle='lead-status']")));

        driver.findElement(By.xpath("//a[@data-toggle='lead-status']")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=' Add Lead Status']")));

        element = driver.findElement(By.xpath("//button[text()=' Add Lead Status']"));
        element.click();

        System.out.println("Sarching for save button");
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#lead-status > " +
                        "div:nth-child(3) > " +
                        "div:nth-child(2) > " +
                        "form:nth-child(1) > " +
                        "fieldset:nth-child(1) > " +
                        "div:nth-child(3) > " +
                        "div:nth-child(1) > " +
                        "button:nth-child(1)")));

        System.out.println("Got it, searching for input");

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

        System.out.println("Got it");

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#lead-status > " +
                        "div:nth-child(3) > " +
                        "div:nth-child(2) > " +
                        "form:nth-child(1) > " +
                        "fieldset:nth-child(1) > " +
                        "div:nth-child(2) > " +
                        "div:nth-child(2) > " +
                        "input:nth-child(1)")));

        element = driver.findElement(By.cssSelector("#lead-status > " +
                "div:nth-child(3) > " +
                "div:nth-child(2) > " +
                "form:nth-child(1) > " +
                "fieldset:nth-child(1) > " +
                "div:nth-child(2) > " +
                "div:nth-child(2) > " +
                "input:nth-child(1)"));

        element.sendKeys(newStatus);
        driver.findElement(By.cssSelector("#lead-status > " +
                "div:nth-child(3) > " +
                "div:nth-child(2) > " +
                "form:nth-child(1) > " +
                "fieldset:nth-child(1) > " +
                "div:nth-child(3) > " +
                "div:nth-child(1) > " +
                "button:nth-child(1)")).click();

        // getting back to leads page

        List<WebElement> leads = loadCreatedLeadsForName(newLeadName, driver);

        int foundLeads = leads.size();
        for(int i = 0; i < foundLeads; i++){
            leads = loadCreatedLeadsForName(newLeadName, driver);
            WebElement lead = leads.get(i);
            lead.click();
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='status']" +
                            "/div[@class='dropdown']//span[@class='lead-status']")));

            element = driver.findElement(By.xpath("//div[@class='status']" +
                    "/div[@class='dropdown']//span[@class='lead-status']"));
//            System.out.println("And status we have " + element.getText());
            if(StringUtils.equals(element.getText(),"New")){
                element.click();
                (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='option' and text() = '"+newStatus+"']")));

                driver.findElement(By.xpath("//a[@class='option' and text() = '"+newStatus+"']")).click();

                (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='status']" +
                                "/div[@class='dropdown']//span[@class='lead-status']")));

                element = driver.findElement(By.xpath("//div[@class='status']" +
                        "/div[@class='dropdown']//span[@class='lead-status']"));

                System.out.println("And status we have " + element.getText());

            }
        }
    }

    private static List<WebElement> loadCreatedLeadsForName(String newLeadName, WebDriver driver) {
        driver.findElement(By.id("nav-leads")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='lead-name' and text()='" + newLeadName + "']")));

        return driver.findElements(By.xpath("//a[@class='lead-name' and text()='"+newLeadName+"']"));
    }
}
