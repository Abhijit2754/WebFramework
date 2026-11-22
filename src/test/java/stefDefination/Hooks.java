package stefDefination;

import base.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Before Hook Executed");
    }

    @After
    public void afterScenario(Scenario scenario) {

        System.out.println("After Hook Executed");

        testContext.getDriverManager().quitDriver();
    }
}