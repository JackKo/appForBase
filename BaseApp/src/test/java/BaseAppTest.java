import com.base.app.Elements;
import com.base.app.Page;
import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.util.List;

import static com.base.app.Elements.*;


/**
 * Created by JK on 17.06.15.
 */
abstract class BaseAppTest {

    protected String appEndpoint;
    protected WebDriver driver;
    protected WebElement element;
    private Page currentPage;


    protected BaseAppTest(){
        appEndpoint = "https://core.futuresimple.com/users/login";
        driver = new FirefoxDriver();
    }

    protected BaseAppTest(WebDriver driver, String appEndpoint) {
        this.driver = driver;
        this.appEndpoint = appEndpoint;
    }

    protected void login(String username, String password){

        driver.get(appEndpoint);

        element = driver.findElement(By.id("user_email"));
        element.sendKeys(username);
        element = driver.findElement(By.id("user_password"));
        element.sendKeys(password);

        driver.findElement(By.tagName("button")).click();
    }

    protected void goToPage(Page page){
        driver.get(page.getUri());
    }

    protected WebElement getElementSafetly(Elements element){
        return getElementSafetly(element.getLocator());
    }
    private WebElement getElementSafetly(By locator){
        (new WebDriverWait(driver, Constants.DEFAULT_WAIT_TO_LOAD_TIME))
        .until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void addNewLead(String lead_name) {
        element = getElementSafetly(NEW_LEAD_FN);
        element.sendKeys(lead_name.split(" ")[0]);
        element = getElementSafetly(NEW_LEAD_SN);
        element.sendKeys(lead_name.split(" ")[1]);
        getElementSafetly(SAVE_NEW_LEAD_ELEMENT).click();
    }

    public void clickOn(Elements settingsLeadStatus) {
        getElementSafetly(settingsLeadStatus).click();
    }

    protected void addNewAvailableStatus(String new_status) {
        getElementSafetly(SETTINGS_ADD_LEAD_STATUS_INPUT).sendKeys(new_status);
        getElementSafetly(SETTINGS_ADD_LEAD_STATUS_SAVE_BUTTON).click();
    }

    protected List<WebElement> loadCreatedLeadsForName(String newLeadName, WebDriver driver) {
        clickOn(LEAD_LIST);
        getElementsSafetly(By.xpath("//a[@class='lead-name' and text()='" + newLeadName + "']"));

        return getElementsSafetly(By.xpath("//a[@class='lead-name' and text()='"+newLeadName+"']"));
    }

    private List<WebElement> getElementsSafetly(By xpath) {
        getElementSafetly(xpath);
        return driver.findElements(xpath);
    }

    protected void updateAllLeadsByNameWithStatus(String lead_name, String new_status) {
        List<WebElement> leads = loadCreatedLeadsForName(lead_name, driver);

        int foundLeads = leads.size();
        for(int i = 0; i < foundLeads; i++){

            leads = loadCreatedLeadsForName(lead_name, driver);
            WebElement lead = leads.get(i);
            lead.click();
            element = getElementSafetly(LEAD_STATUS);
            if(StringUtils.equals(element.getText(), "New")){
                element.click();
                getElementSafetly(By.xpath("//a[@class='option' and text() = '"+new_status+"']")).click();
                //way to getting back on previous page
                element = getElementSafetly(LEAD_STATUS);
                if(!StringUtils.equals(element.getText(),new_status)){
                    throw new AssertionError("New status for lead is not updated properly");
                }
            }
        }
    }
}
