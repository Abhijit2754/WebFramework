package stefDefination;

import base.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.TestObject1;

public class TestStepdefs {
    TestContext testContext;
    TestObject1 testObject1;

    public TestStepdefs(TestContext context){
        this.testContext = context;
        testObject1 = testContext.getWebPageObjectManager().getTestStepdefs();
    }

    @Given("User open browser and enter url {string}")
    public void userOpenBrowser(String arg) {
        testObject1.userOpenBrowser(testContext,arg);
    }

    @Then("user check the Text {string} on google home page")
    public void userCheckTheTextText(String arg0) {
        testObject1.userVerifyTheText(testContext, arg0);
    }

    @And("user enter the text {string} on google home page")
    public void userEnterTheTextOnGoogleHomePage(String arg0) {
        testObject1.userEnterValue(testContext,arg0);
    }
}
