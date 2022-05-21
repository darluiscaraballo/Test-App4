package com.demo.blaze.glue;

import com.demo.blaze.logic.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlazeProductListGlue {
    @Given("DemoBlaze is displayed")
    public void demo_blaze_is_displayed() {
        Page.demoBlaze().displayPage();
    }
    @When("User Logins into the app with demo {string}, {string}")
    public void userInputCredential(String user, String pass) {
        Page.demoBlaze().userInputCredential(user, pass);
    }
    @And("Request Monitor Categories")
    public void requestMonitorCategories() {
         Page.demoBlaze().selectMonitorCategory();
    }
    @Then("Two monitors items are returned")
    public void verifyReturnedItems() {
        Page.demoBlaze().verifyReturnedItems();
    }
    @And("Request Phones Categories")
    public void requestPhoneCategories(){
        Page.demoBlaze().selectPhoneCategory();
    }
    @And("User extract the result list to a txt file")
    public void extractResultAssTXT(){
        Page.demoBlaze().extractResultAssTXT();
    }
    @Then("Text file generated contains 7 results and not from {string} phone type")
    public void verifyResultsGenerated(String Text){
        Page.demoBlaze().verifyResultGenerated(Text);
    }
}
