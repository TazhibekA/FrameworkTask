package page;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnnotatedSearchPage extends AbstractPage
{

	private final Logger logger = LogManager.getRootLogger();




	public AnnotatedSearchPage(WebDriver driver)
	{
		super(driver);
		actions = new Actions(driver);
		PageFactory.initElements(this.driver, this);
	}


	public CalculatorPage clickLink(String linkText)
	{
		new WebDriverWait(driver,30,500).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
		driver.findElement(By.linkText(linkText)).click();
		logger.info("search results opened");
		return new CalculatorPage(driver);
	}

	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}



}
