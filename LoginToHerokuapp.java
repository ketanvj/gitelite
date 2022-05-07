package com.nt.hk;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginToHerokuapp {
	private WebDriver driver;
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		  openBrowserAndGotoUrl();
	}

	private void openBrowserAndGotoUrl() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/");       
	}

	@When("^User Navigates to Authentication Page$")
	public void user_Navigates_to_Authentication_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
		driver.findElement(By.linkText("Form Authentication")).click();
	}
	
	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_and(String userName, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	    login(userName,password);
	}

	@When("^User enters valid \"([^\"]*)\" and invalid \"([^\"]*)\"$")
	public void user_enters_valid_and_invalid(String userName, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	    login(userName,password);
	}

	@When("^User enters invalid \"([^\"]*)\" and valid \"([^\"]*)\"$")
	public void user_enters_invalid_and_valid(String userName, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		login(userName,password);
	}

	@Then("^Message displayed \"([^\"]*)\"$")
	public void message_displayed(String messageDisplayed) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		verifyMessage(messageDisplayed);
		driver.quit();
	}

	private void verifyMessage(String messageDisplayed) {
		    assertTrue(driver.findElement(By.id("flash")).getText().contains(messageDisplayed));
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}		
	}

	@Given("^User is logged in the System$")
	public void user_is_logged_in_the_System() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		openBrowserAndGotoUrl();
		driver.findElement(By.linkText("Form Authentication")).click();
		login("tomsmith","SuperSecretPassword!");
	}

	@When("^User LogOut from the System$")
	public void user_LogOut_from_the_System() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	    driver.findElement(By.linkText("Logout")).click();
	}
	
	@When("^User enters credentials and verify message$")
	public void user_enters_credentials_and_verify_message(DataTable credentials) throws Throwable {
	List<List<String>> credentialsData = credentials.asLists();
		for (List<String> oneRowAtATime:credentialsData) {
		  login(oneRowAtATime.get(0),oneRowAtATime.get(1));
		  verifyMessage(oneRowAtATime.get(2));
		  try {
		    driver.findElement(By.linkText("Logout")).click();
		  }catch (NoSuchElementException e) {
			  //No Action Required as seems there is previous login was unsuccessful
		  }
		  
		}
		driver.quit();
	}

	public void login(String username, String password) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("radius")).click();		
	}	
}
