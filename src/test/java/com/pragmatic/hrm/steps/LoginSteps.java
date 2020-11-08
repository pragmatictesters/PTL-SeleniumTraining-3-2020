package com.pragmatic.hrm.steps;

import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginSteps extends HRMTestBase {
    LoginPage loginPage;
    LandingPage landingPage;


    @Before
    public void beforeAllSteps(){
        beforeSuite();
    }

    @Given("^I have access the login page$")
    public void iHaveAccessTheLoginPage() {
        beforeMethod();
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @When("I type a valid user name {string}")
    public void iTypeAValidUserName(String username) {
        loginPage.typeUsername(username);
    }

    @And("I type a valid password {string}")
    public void iTypeAValidPassword(String password) {
        loginPage.typePassword(password);
    }


    @And("^I click on the login button$")
    public void iClickOnTheLoginButton() {
        loginPage.clickLoginWithSuccess();
    }


    @Then("I should be logged into the system with welcome message {string}")
    public void iShouldBeLoggedIntoTheSystemWithWelcomeMessage(String welcomeMessage) {
        landingPage = PageFactory.initElements(webDriver,LandingPage.class);
        Assert.assertEquals(landingPage.getWelcomeMessage(), welcomeMessage);

    }

    @After
    public void afterAllSteps(){
        afterMethod();
    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String error) {
        Assert.assertEquals(loginPage.getErrorMessage(),error);
    }
}
