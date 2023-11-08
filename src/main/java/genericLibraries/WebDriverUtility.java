package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * this class contains all reusuable methods to perform web driver actions
 * @author abhi
 */
public class WebDriverUtility {
	WebDriver driver;
	
	/**
	 * this method is used to launch user desired browser
	 * @param browser
	 * @return
	 */
	
	public WebDriver launchBrowser(String browser) {
		switch(browser) {
		case "chrome":
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
				driver = new ChromeDriver();
				break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("invalid browser info");
		}
		driver.manage().window().maximize();
		return driver;
		}
	
	/**
	 * this method is used to navigate to the application
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}
	
	/**
	 * this method is an implicitly wait statement
	 * @param time
	 */
	
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	/**
	 * this method is used to wait until element is visible
	 * @param time
	 * @param element
	 * @return
	 */
	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * this method is used to wait until element is enabled
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitwait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * this method is used until title of the web page appears
	 * @param title
	 * @param time
	 * @return
	 */
	public Boolean explicitwait(String title, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * this method is used to mouse hover on an element
	 * @param element
	 */
	public void mouseHoverToelement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	/**
	 * this method is used to double click on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	/**
	 * this method is used to drag and drop an element
	 * @param element
	 * @param target
	 */
	
	public void dragAndDropElement(WebElement element, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(element, target).perform();	
	}
	/**
	 * this method is used to select an element from drop down based on index
	 * @param element
	 * @param index
	 */
	public void handleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * this method is used to select an element from dropdown based on value
	 * @param element
	 * @param value
	 */
	public void handleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * this method is used to select an element from dropdown based on text
	 * @param text
	 * @param element
	 */
	public void handleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * this method is used to switch to frame based on index
	 * @param index
	 */
	public void switchToFrame(int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * this method is used to switch to frame based on id or name attribute
	 * @param id or name 
	 */
	public void switchToFrame(String idorname)
	{
		driver.switchTo().frame(idorname);
	}
	
	/**
	 * this method is used to switch to frame based on frame element reference
	 * @param frame Element
	 */
	public void switchToFrame(WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	
	/**
	 * this method is used to switch back from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * this method is used to capture window screenshot
	 * @param driver
	 * @param classname
	 * @param jutil
	 */
	public void takeScreenshot(WebDriver driver, String classname, JavaUtility jutil) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/" + classname + "-" +
				jutil.getCurrentTime() + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used to scroll till element
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].srollIntoView(true)", element);
	}
	
	/**
	 * this method is used to handle alert
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert alert = driver.switchTo().alert();
		if(status.equalsIgnoreCase("ok"))
			alert.accept();
		else
			alert.dismiss();
	}
	
	/**
	 * this methos is used to switch to child browser
	 */
	public void switchTochildBrowser() {
		Set<String> set = driver.getWindowHandles();
		for (String windowID : set) {
			driver.switchTo().window(windowID);
		}
	}
	
	/**
	 * this method returns parent browser address
	 */
	
	public String getParentWindowID() {
		return driver.getWindowHandle();
	}
	
	/**
	 * this method is used to switch to specific window
	 * @param windowID
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}
	
	/**
	 * this method is used to close current window
	 */
	public void closeCurrentWindow() {
		driver.close();
	}
	
	/**
	 * this method is used to close all the windows
	 */
	public void closeAllWindows() {
		driver.quit();
	}
}
