package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {


    private final WebDriver driver = new ChromeDriver();

    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("I am on the Google search page")
    public void I_visit_google() {
        driver.get("https://www.google.com");
      pause(2500);
    }


    @And("I Decline Cookies")
    public void I_decline_cookies() {
        WebElement element = driver.findElement(By.xpath(".//*[@id='W0wltc']"));
        element.click();
      pause(2500);
    }

    @When("I search for {string}")
    public void search_for(String query) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(query);
        ((WebElement) element).submit();
      pause(2500);
    }

    @And("View images search")
    public void I_view_images_search() {
        WebElement element = driver.findElement(By.xpath(".//*[@id=\"hdtb-msb\"]/div[1]/div/div[2]/a"));
        element.click();
      pause(2500);
    }

    @And("Open 3rd image in new tab")
    public void openImageInNewTab() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"islrg\"]/div[1]/div[3]/a[1]/div[1]/img"));
        //new Actions(driver)
        //        .contextClick(element)
        //        .perform();
        new Actions(driver)
                .keyDown(element, Keys.ARROW_DOWN)
                .click()
                .perform();
        pause(2500);
    }
    @Then("Result should be visible in new tab")
    public void CheckResultsNewTab() {
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"cc-window\"]/div[5]/a[1]")).isDisplayed());
        Serenity.takeScreenshot();
    }
}