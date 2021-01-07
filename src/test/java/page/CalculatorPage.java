package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

public class CalculatorPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private WebDriverWait wait = new WebDriverWait(driver,30,500);


	private static final String EMAIL_URL = "https://10minutemail.com/";

	@FindBy(id = "input_63")
	private WebElement inputNumberInstances;

	//@FindBy(xpath = "*[id = \"select_value_label_59\"]/span")
	@FindBy(id = "select_88")
	private WebElement series;

	//@FindBy(xpath = "*[id = \"select_value_label_60\"]/span")
	@FindBy(id = "select_90")
	private WebElement machineType;


	//@FindBy(xpath = "*[id = \"select_value_label_61\"]/span")
	@FindBy(id = "select_92")
	private WebElement location;

	//@FindBy(xpath = "*[id = \"select_value_label_62\"]/span")
	@FindBy(id = "select_99")
	private WebElement committedUsage;


	@FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[13]/button")
	private WebElement buttonAddEstimate;

	@FindBy(id = "email_quote")
	private WebElement emailEstimate;

	@FindBy(xpath = "/html/body/div[3]/md-dialog/form/md-content/div[3]/md-input-container/input")
	private WebElement emailInput;


	@FindBy(xpath = "//*[@id=\"dialogContent_390\"]/form/md-dialog-actions/button[2]")
	private WebElement sendEmail;


	@FindBy(id = "mail_address")
	private WebElement emailAddress;



	public CalculatorPage(WebDriver driver)
	{
		super(driver);
		actions = new Actions(driver);
		PageFactory.initElements(this.driver, this);
	}

	public CalculatorPage switchToFrame(){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"cloud-site\"]/devsite-iframe/iframe")));
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.switchTo().frame("myFrame");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input_63")));
		return this;
	}

	public CalculatorPage fillUpAddEstimate( ) throws InterruptedException {
		inputNumberInstances.sendKeys("4");
		//series
		Thread.sleep(2000);
		series.click();
		Thread.sleep(2000);
		actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		//machine type
		Thread.sleep(2000);
		machineType.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		//location
		Thread.sleep(2000);
		location.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ENTER).perform();
		//committed usage
		Thread.sleep(2000);
		committedUsage.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		Thread.sleep(2000);
		buttonAddEstimate.click();

		return this;
	}

	public CalculatorPage sendEmailFromSite() throws InterruptedException {
		Thread.sleep(2000);
		emailEstimate.click();
		Thread.sleep(2000);
		//actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
//		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1)); //switches to new tab


		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); //switches to new tab
		driver.get(EMAIL_URL);

		String mail_address = emailAddress.getText();
		driver.switchTo().window(tabs.get(0)); // switch back to main screen



		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/md-dialog/form/md-content/div[3]/md-input-container/input")));
		driver.switchTo().alert().accept();
		//driver.switchTo().frame(driver.findElement(By.xpath("form[name='emailForm']")));


		emailInput.sendKeys(mail_address);
		sendEmail.click();

		Thread.sleep(5000);
		return this;
	}


	public String getCurrentUrl()
	{
		return  driver.getCurrentUrl();
	}



}
