package stefDefination;

import base.TestContext;
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

    @Then("user check the Text {string} text")
    public void userCheckTheTextText(String arg0) {
        testObject1.userVerifyTheText(testObject1, arg0);
    }
}
