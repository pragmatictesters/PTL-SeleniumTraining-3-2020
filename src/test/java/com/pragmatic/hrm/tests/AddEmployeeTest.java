package com.pragmatic.hrm.tests;


import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.Constants;
import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.AddNewEmployeePage;
import com.pragmatic.hrm.pages.EmployeeProfilePage;
import com.pragmatic.hrm.pages.LoginPage;
import com.pragmatic.hrm.pages.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddEmployeeTest extends HRMTestBase {

    private WebDriver webDriver;
    private AddNewEmployeePage addNewEmployeePage;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void beforeMethod() {
        webDriver = new BrowserManager().getBrowserInstance(Constants.BROWSER_TYPE);
        webDriver.get(Constants.BASE_URL);
        addNewEmployeePage = loginAndNavigateToAddEmployeePage();
    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


    @Test
    public void addNewEmployeeWithMandatoryInformation() {
        addNewEmployeePage.typeFirstname(faker.name().firstName())
                .typeLastname(faker.name().lastName())
                .typeEmployeeID(faker.idNumber().toString())
                .clickSave();
    }


    @Test
    public void addNewEmployeeWithUserCredentialsEnabled() {
        addNewEmployeePage.typeFirstname("Janesh")
                .typeLastname("Kodikara")
                .checkCreateLoginDetails()
                .selectStatus("Enabled");
        addNewEmployeePage.clickSave();

    }


    @Test
    public void addNewEmployeeWithUserCredentialsDisabled() {
        addNewEmployeePage.typeFirstname("Janesh")
                .typeLastname("Kodikara")
                .checkCreateLoginDetails()
                .selectStatus("Disabled");
        addNewEmployeePage.clickSave();

    }


    @Test
    public void addNewEmployeeWithoutFirstName() {
        addNewEmployeePage.typeFirstname("")
                .typeLastname("Kodikara")
                .clickSave();
        Assert.assertEquals(addNewEmployeePage.getFirstNameError(), "Required");
    }


    @Test
    public void addNewEmployeeWithoutLastName() {
        addNewEmployeePage.typeFirstname("").typeLastname("")
                .clickSaveWithError();
        Assert.assertEquals(addNewEmployeePage.getFirstNameError(), "Required");
        Assert.assertEquals(addNewEmployeePage.getLastNameError(), "Required");
    }


    @Test
    public void addNewEmployeeWithProfilePicture() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        addNewEmployeePage.typeFirstname(firstName)
                .typeLastname(lastName)
                .uplaodProfile("/Users/hansi/Documents/learning/Pragmatic-Learning-Selenium-WebDriver/test_data/good-pic.png")
                .clickSave();

        EmployeeProfilePage employeeProfilePage = PageFactory.initElements(webDriver, EmployeeProfilePage.class);
        Assert.assertEquals(employeeProfilePage.getFirstname(), firstName);
        Assert.assertEquals(employeeProfilePage.getLastname(), lastName);

    }


    private AddNewEmployeePage loginAndNavigateToAddEmployeePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(Constants.ADMIN_USERNAME, Constants.ADMIN_PASSWORD);

        MenuPage menuPage = new MenuPage(webDriver);
        menuPage.navigateToAddEmployeePage();
        return PageFactory.initElements(webDriver, AddNewEmployeePage.class);
    }


}
