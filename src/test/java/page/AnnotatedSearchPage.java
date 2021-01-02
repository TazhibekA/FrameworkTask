package page;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnnotatedSearchPage extends AbstractPage
{

	private final Logger logger = LogManager.getRootLogger();


	public AnnotatedSearchPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}


	public AnnotatedSearchPage clickLink(String linkText)
	{
		driver.findElement(By.linkText("Google Cloud Platform Pricing Calculator")).click();
		logger.info("search results opened");
		return this;
	}

	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}



}
