package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseClass.BaseClass;

public class HomePage extends BaseClass{

	
	
	
	public HomePage() {
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy (xpath="//div[@class='text']") WebElement txtuser;
	@FindBy (xpath="//input[contains(@type, 'text')]") WebElement txtpass;
	@FindBy (xpath="//div//span[starts-with(@class,'text')]") WebElement dropdown;
	@FindBy (xpath="//span[normalize-space()='text']") WebElement dd;
	@FindBy (xpath="//div[@type='name' and @close='name']") List<WebElement> naam;
	@FindBy (xpath="//input[@type='text' or @class='nma']") WebElement pan;
	
	
	
	public void setsignup(String name, String pass) {

		txtuser.sendKeys(name);
		txtpass.sendKeys(pass);
		pan.click();
		
		Select slt=new Select(dropdown);
		slt.selectByVisibleText("indina");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(dd)).click();

	}
	
	public void verifyTitle() {
		Assert.assertEquals(driver.getTitle(), "chinmaya");
		Assert.assertTrue(dd.getText().contains("chinmaya"));
	}
}
