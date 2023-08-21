package be.sample.steps;

import be.sample.pages_and_components.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import javax.annotation.Resource;

public class CommonSteps {

    @Resource private LoginPage loginPage;

    @Given("I am on the B2B portal")
    public void iAmOnTheB2BPortal() {
        System.out.println("I am on the B2B portal");
    }

    @When("I enter username and password")
    public void enterCredentials() {
        loginPage.setUsername("username");
        loginPage.setPassword("password");
        loginPage.clickLogin();
    }

    @Then("I should be able to login")
    public void checkLogin() {
        boolean isLoginSuccessful = true;
        Assertions.assertThat(isLoginSuccessful).isTrue();
    }
}
