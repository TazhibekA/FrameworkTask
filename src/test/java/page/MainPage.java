package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://cloud.google.com/";

	@FindBy(xpath = "/html/body/section/devsite-header/div/div[1]/div/div/div[2]/devsite-search/form/div[1]/div/input")
	private WebElement searchInput;


	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public AnnotatedSearchPage search(String searchText)
	{
		searchInput.sendKeys(searchText);
		return new AnnotatedSearchPage(driver);
	}

 	public MainPage openPage()
	{
		driver.navigate().to(BASE_URL);
		return this;
	}


}
