import com.base.app.Elements;
import org.apache.bcel.generic.NEW;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.base.app.Page.*;
import static com.base.app.Elements.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by JK on 17.06.15.
 */
public class LeadStausTest extends BaseAppTest {

    private String LEAD_NAME = "Chuck Norris";
    private String NEW_STATUS = "Knocked-Out";

    @BeforeTest
    public void loginToApp(){
        login("jacek.kosno@gmail.com","Abc12345");
    }

    @Test
    public void checkLeadStatusAssignation(){

        goToPage(ADD_NEW_LEAD);

        addNewLead(LEAD_NAME);

        assertThat(getElementSafetly(LEAD_STATUS).getText(), equalTo("New"));

        goToPage(STATUS_SETTINGS);

        clickOn(SETTINGS_LEAD_STATUS);

        clickOn(SETTINGS_ADD_LEAD_STATUS);

        addNewAvailableStatus(NEW_STATUS);

        updateAllLeadsByNameWithStatus(LEAD_NAME, NEW_STATUS);
    }
}
