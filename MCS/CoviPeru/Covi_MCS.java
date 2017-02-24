package CoviPeru;

import java.util.regex.Pattern;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.util.Loader;
import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.junit.*;
import org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;


public class Covi_MCS {
  public static WebDriver driver;
  public static String baseUrl;
  public boolean acceptNextAlert = true;
  public StringBuffer verificationErrors = new StringBuffer();
  public static String vViaLabel = "ctl00_ContentZone_companyPlazaLane_cmb_lane_cmb_dropdown";
  public static String vModoVia = "ctl00_ContentZone_cmb_mode_cmb_dropdown";
  public static String vEstadoVia = "ctl00_ContentZone_cmb_state_cmb_dropdown";
  public static String vTipoPago = "ctl00_ContentZone_payment_cmb_type_cmb_dropdown";
  public static String vCategoria = "ctl00_ContentZone_cmb_vehicle_class_cmb_dropdown";
  public String vTipoAnomalia = "ctl00_ContentZone_cmb_abnormaltype_cmb_dropdown";
  

 @Before 
  public void setUp() throws Exception {
	 System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
	 //DesiredCapabilities capabilities=DesiredCapabilities.internetExplorer();	 	      
	  //DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
	  //cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	  //cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
	  driver = new ChromeDriver();
	  baseUrl = "http://172.18.133.148/";
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	  
  }
 

  @Test
  public void test1() throws Exception {
	Actions action = new Actions(driver);
    driver.get(baseUrl + "/MCS");
    //driver.findElement(By.id("BoxLogin")).clear();
	  Thread.sleep(1000);
    driver.findElement(By.id("txt_login")).sendKeys("00001");    
    driver.findElement(By.id("txt_password")).sendKeys("00001");
    Thread.sleep(1000);
    driver.findElement(By.id("btn_login")).click();
    Thread.sleep(5000);
    takeScreenShot("MCS1.png");
    Thread.sleep(2000);
    //driver.getWindowHandle();
    WebElement Alarm = driver.findElement(By.id("img_alarmmonitor_updown"));
    action.click(Alarm).perform();
    action.click(Alarm).perform();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@onclick=\"dropdownmenu(this, event, menu4, '250px')\"]")).click();
    Thread.sleep(500);
    driver.findElement(By.linkText("Incidencias")).click();
    Thread.sleep(1000);
    takeScreenShot("InciScreen.png");
    Thread.sleep(3000);    
  
    driver.getWindowHandle();
    driver.switchTo().frame(0);
    //driver.findElement(By.id("btn_search")).click();    
    driver.findElement(By.id("lnk_new")).click();
    		Thread.sleep(1000);
    			takeScreenShot("NewInci.png");
    	driver.findElement(By.id("txt_title")).sendKeys("New Incidence has been creaated for the Lane");
    	driver.findElement(By.id("chk_error")).click();
    	driver.findElement(By.id("chk_invoice_customer")).click();
    	selectDropDown("cmb_lane");
    	driver.findElement(By.id("txt_description")).sendKeys("This incidence has been created to repair the Lane");
    	selectDropDown("cmb_component_type");
    	driver.findElement(By.id("btn_save")).click();
    	Thread.sleep(2000);
    	takeScreenShot("incidenceCreated.png");
    	Thread.sleep(3000);
    	System.out.println ("New Incidence Created in MCS");
    	
    }
     		
    
    public void takeScreenShot(String fname) throws Exception {
	    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File("C:\\Selenium", fname));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      org.junit.Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
  
  public void selectDropDown(String by) {
	  Select vDropdown = new Select (driver.findElement(By.id(by)));
		List<WebElement> dd = vDropdown.getOptions();		
		Random rand = new Random();
		int vdd = rand.nextInt(dd.size())-1;
			if (vdd<0){vdd = vdd+1;}
			
		new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
		
	  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public static void ZoomIn() throws Exception{
	  WebElement html = driver.findElement(By.tagName("html"));
	  for (int i = 0; i<2; i++){
		  html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
	  }
  }
  
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  
}