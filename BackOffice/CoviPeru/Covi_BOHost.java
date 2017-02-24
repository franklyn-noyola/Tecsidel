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


public class Covi_BOHost {
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
    driver.get(baseUrl + "/HostBO/web/forms/core/login.aspx");

	  Thread.sleep(1000);
    driver.findElement(By.id("BoxLogin")).sendKeys("00001");    
    driver.findElement(By.id("BoxPassword")).sendKeys("00001");
    Thread.sleep(1000);
    driver.findElement(By.id("BtnLogin")).click();
    Thread.sleep(3000);
    takeScreenShot("BackOffice2.png");    
    try{
    		WebElement Transactions = driver.findElement(By.linkText("Transacciones"));
    		action.clickAndHold(Transactions).build().perform();
    		Thread.sleep(3000);    		
    		action.clickAndHold(Transactions).build().perform();
    		Thread.sleep(500);  
    		action.clickAndHold(Transactions).build().perform();
    		Thread.sleep(500);
    		WebElement vTransactions = driver.findElement(By.linkText("Ver transacciones"));
    		Thread.sleep(1000);
    		action.moveToElement(vTransactions).click().perform();
    		int x = 0;
    		List <WebElement> sTran = driver.findElements(By.id("ctl00_SectionZone_LblTitle"));
    		int vTran = sTran.size();    		
    		if (vTran > 0){
    			x = 1;    			
    		}else{
    			x = 0;
    		}
    		if (x == 0){
    			do { 
    				x = 0;
    				action.clickAndHold(Transactions).build().perform();
    				action.clickAndHold(Transactions).build().perform();
    				Thread.sleep(3000);
    				action.moveToElement(vTransactions).click().perform();
    				Thread.sleep(2000);
    				List <WebElement> sTran2 = driver.findElements(By.id("ctl00_SectionZone_LblTitle"));
    	    		vTran = sTran2.size();
    				if (vTran!=0){
    	    			x = 1;
    	    		}
    			Thread.sleep(500);
    			}while (x == 0);	
    		}
    		Thread.sleep(2000);
    		selectDropDown(vViaLabel);
    		Thread.sleep(1000);
    		selectDropDown(vModoVia);
    		Thread.sleep(1500);
    		selectDropDown(vEstadoVia);
    		Thread.sleep(1500);
    		selectDropDown(vCategoria);
    		Thread.sleep(1500);
    		selectDropDown(vTipoAnomalia);
    		Thread.sleep(1200);
    		driver.findElement(By.id("ctl00_ButtonsZone_BtnSearch_IB_Label")).click();
    		Thread.sleep(3000);    		
    		takeScreenShot ("VerTrans.jpg");
    		Thread.sleep(2000);
    		if (isElementPresent(By.id("section"))){
    			System.out.println("Esto se encontro");    			    			
    		}	
    }catch (Exception e){
    	e.printStackTrace();}
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