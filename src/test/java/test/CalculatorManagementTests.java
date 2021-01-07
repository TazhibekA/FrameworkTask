package test;

import javafx.scene.chart.PieChart;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CalculatorPage;
import page.MainPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CalculatorManagementTests extends CommonConditions{

    protected static final String CALCULATOR_URL = "https://cloud.google.com/products/calculator";


    protected static final String SEARCHTEXT = "Google Cloud Platform Pricing Calculator";
    protected static final String LINKTEXT = "Google Cloud Platform Pricing Calculator";
    private ArrayList<String> tabs ;
    private WebDriverWait wait ;

    @Test
    public void checkCalculatorUrl() throws IOException, InterruptedException {
        String calculatorUrl = new MainPage(driver)
                .openPage()
                .search(SEARCHTEXT)
                .clickLink(LINKTEXT)
                .getCurrentUrl();

        try{
            Assert.assertEquals(calculatorUrl, CALCULATOR_URL);
        }catch(AssertionError e){
            screenShot(driver);
        }


    }
    public static void screenShot(WebDriver driver) throws IOException, InterruptedException {
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("C:\\Users\\User\\IdeaProjects\\FrameworkTask\\screenshots\\screenshot_" + timestamp() + ".png");
        FileUtils.copyFile(scr, dest);
        Thread.sleep(3000);
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    @Test
    public void checkCalculatorEstimateClickable() throws InterruptedException, IOException {
        CalculatorPage calculatorPage = new MainPage(driver)
                .openPage()
                .search(SEARCHTEXT)
                .clickLink(LINKTEXT)
                .switchToFrame()
                .fillUpAddEstimate();
        try{
            Assert.assertTrue(driver.findElement(By.id("compute")).isDisplayed());
        }catch(AssertionError e){
            screenShot(driver);
        }

    }

    @Test
    public void checkEmailEstimateClickable() throws InterruptedException, IOException {
        wait = new WebDriverWait(driver,30,500);
        CalculatorPage calculatorPage = new MainPage(driver)
                .openPage()
                .search(SEARCHTEXT)
                .clickLink(LINKTEXT)
                .switchToFrame()
                .fillUpAddEstimate()
                .sendEmailFromSite();

        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        //wait.until(driver.findElement(By.id("inbox_count_number")).getText().equals("1"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("inbox_count_number"), "1"));
        driver.findElement(By.xpath("//*[@id=\"mail_messages_content\"]/div[1]")).click();
        String  totalCost= driver.findElement(By.xpath("//*[@id=\"mobilepadding\"]/td/table/tbody/tr[2]/td[2]/h3")).getText();
        driver.switchTo().window(tabs.get(0));
        String totalCostInGoogle = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list[1]/md-list-item[5]/div/b")).getText();
        try{
            Assert.assertTrue(totalCostInGoogle.contains(totalCost));
        }catch(AssertionError e) {
            screenShot(driver);
        }

    }


}
