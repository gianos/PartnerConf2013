package argos.saleslogix.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.AssertJUnit;

/**
 * Class that defines WebElements and methods for the Header on the Mobile Client.
 * 
 * @author mike.llena@swiftpage.com
 * @version	1.0
 */
public class HeaderButton {

	private WebDriver driver;
	
	public HeaderButton(WebDriver driver) {
		this.driver = driver;		
	}
		
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='toggleLeftDrawer']")
	WebElement globalMenuButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='toggleRightDrawer']")
	WebElement rightCntxtMnuButton;

	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='new']")
	WebElement addButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='edit']")
	WebElement editButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='save']")
	WebElement saveButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='complete']")
	WebElement checkButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='delete']")
	WebElement deleteButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='cancel']")
	WebElement cancelButton;
	
	//@CacheLookup
	@FindBy(xpath = "//*[@id='Mobile_SalesLogix_Views_MainToolbar_0']//descendant::*[@aria-label='back']")
	WebElement backButton;
		
	
	//Methods
	//-------
	/**
	 * This method will click the header Global Menu button to display the Global Menu items.
	 *
	 * @version	1.0
	 * @exception InterruptedException
	 */
	public HeaderButton showGlobalMenu() throws InterruptedException {
		String methodID = "showGlobalMenu";
		
		//click the Page Title (forces closure of any blocking panels)
		driver.findElement(By.id("pageTitle")).click();
		Thread.sleep(1000);
		
		// Click Header Global Menu button...
		clickHeaderButton("global");
		
		// Verify the 'Global Menu' left-screen displays...
		try {
			AssertJUnit.assertTrue(driver.findElement(By.xpath("//*[@id='left_drawer']")).isDisplayed());
			System.out.println("VP: Global Menu was accessed successfully on header button click.");
		} catch (Error e) {     
			System.out.println("Error: Global Menu failed to display on header button click.");
			System.out.println(e.toString());
		}
		Thread.sleep(1000);
		return this;
	}
	
	
	/**
	 * This method will click the Header right-context menu button.
	 * 
	 * @version	1.0
	 * @exception	InterruptedException
	 */
	public boolean showRightContextMenu() throws InterruptedException {
		String methodID = "showRightContextMenu";
		
		// Click Header Right-Context Menu button...
		clickHeaderButton("right context menu");
		
		// Verify the 'Right-Context Menu' left-screen displays...
		try {
			AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='right_drawer']/div")).isDisplayed());
			System.out.println(methodID + ": Right-Context Menu was accessed successfully on header button click.");
			return true;
		} catch (Error e) {     
			System.out.println(methodID + ": Right-Context Menu failed to display on header button click.");
			System.out.println(e.toString());
			return false;
		}
	}
	
	
	/**
	 * This method will perform a click of the Header back button.
	 * 
	 * @version	1.0
	 * @exception	InterruptedException
	 */
	public HeaderButton goBack() throws InterruptedException {
		String methodID = "goBack";
		
		WebElement pgTitleBar = driver.findElement(By.xpath(".//*[@id='pageTitle']"));
		String oldPgTitle = driver.findElement(By.xpath(".//*[@id='pageTitle']")).getText();		

		//conditionally close the Right Context Menu panel (if blocking the Global Menu button)
		closeRightContextMenu();
		
		try {
			//click the Header Back button
			backButton.click();
			Thread.sleep(1000);
		}
		catch (Error e) {
			//revert to Backspace key-press if Back button click fails
			pgTitleBar.click();
			pgTitleBar.sendKeys(Keys.BACK_SPACE);
		}
		
		String newPgTitle = driver.findElement(By.xpath(".//*[@id='pageTitle']")).getText();
		
		// Verify that previous page is displayed (new title not equal to old)
		try {
			AssertJUnit.assertFalse(oldPgTitle == newPgTitle);
		} catch (Error e) {     
			System.out.println(e.toString());
		}
		return this;
	}
	
	
	/**
	 * This method will perform a click of a specific Header button.
	 * 
	 * @param	buttonName		name of header button to click
	 * @version	1.0
	 */
	public HeaderButton clickHeaderButton(String buttonName) throws InterruptedException {
		String methodID = "clickHeaderButton";
		
		Thread.sleep(1000);
		switch (buttonName.toLowerCase()) {
		case "global menu": case "global":
			globalMenuButton.click();
			break;
		case "right context menu": case "right menu": case "right":
			rightCntxtMnuButton.click();
			break;
		case "back": case "go back":
			backButton.click();
			break;
		case "cancel":
			cancelButton.click();
			break;
		case "add": case "add new": 
			addButton.click();
			break;
		case "edit":
			editButton.click();
			break;
		case "check": case "accept":
			checkButton.click();
			break;
		case "delete":
			deleteButton.click();
			break;
		case "save":
			saveButton.click();
			break;
		}
		
		System.out.println(methodID + ": header button - '" + buttonName + "' was clicked.");
		Thread.sleep(1500);
		return this;
	}
	
	
	/**
	 * This method will conditionally close the Right-context menu via the Header right-context button.
	 * 
	 * @version	1.0
	 * @exception	InterruptedException
	 */
	public boolean closeRightContextMenu() throws InterruptedException {
		String methodID = "closeRightContextMenu";
				
		//conditionally close the Right-Context menu...
		if (driver.findElement(By.xpath(".//*[@id='right_drawer']")).isDisplayed()) {
			// Click Header Right-Context Menu button...
			clickHeaderButton("right context menu");
		}
		return true;
	}
}
