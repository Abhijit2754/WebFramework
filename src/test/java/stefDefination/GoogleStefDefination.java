package stefDefination;

import base.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.GoogleTestObjectForFirstPage;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class GoogleStefDefination {
    TestContext testContext;
    GoogleTestObjectForFirstPage googleTestObjectForFirstPage;

    public GoogleStefDefination(TestContext context){
        this.testContext = context;
        googleTestObjectForFirstPage = testContext.getWebPageObjectManager().getTestStepdefs();
    }

    @Given("User open browser and enter url {string}")
    public void userOpenBrowser(String arg) throws FileNotFoundException {
        googleTestObjectForFirstPage.userOpenBrowser(testContext,arg);
    }

    @Then("user check the Text {string} on google home page")
    public void userCheckTheTextText(String arg0){
        googleTestObjectForFirstPage.userVerifyTheText(testContext, arg0);
    }

    @And("user enter the text {string} on google home page")
    public void userEnterTheTextOnGoogleHomePage(String arg0){
        googleTestObjectForFirstPage.userEnterValue(testContext,arg0);
    }

    @And("user enter text {string} in search box on google home page")
    public void userEnterTextInSearchBoxOnGoogleHomePage(String arg0) {
        googleTestObjectForFirstPage.userEnterValue(testContext,arg0);
    }

    @Then("user click on {string} text from the suggetion on google home page")
    public void userClickOnTextFromTheSuggetionOnGoogleHomePage(String arg0) throws Throwable {
        googleTestObjectForFirstPage.userClickOnSuggetion(testContext, arg0);
    }
}