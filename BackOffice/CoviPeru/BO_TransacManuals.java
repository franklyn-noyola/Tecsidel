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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

//import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;


public class BO_TransacManuals {
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
  public static int numbering;
  public static String platlet;
  public static char[] let1;  
  public static char[] chr={'A','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Y','Z'};
 
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
    		Thread.sleep(2000);
    		WebElement tManuals = driver.findElement(By.linkText("Transacciones manuales"));
    		action.clickAndHold(Transactions).build().perform();

    		action.click(tManuals).click().build().perform();
    		Thread.sleep(1000);
    		String section = driver.findElement(By.id("section")).getText();
    		if (section.isEmpty()){
    		do {    			    		    	
        		action.clickAndHold(Transactions).build().perform();
        		Thread.sleep(3000);
        		action.clickAndHold(Transactions).build().perform();
        		Thread.sleep(1000);
        		action.click(tManuals).click().build().perform();
        		Thread.sleep(2000);
        		section = driver.findElement(By.id("section")).getText();
        		Thread.sleep(1000);
    			}while (section.isEmpty());
    		}
    		String Error = "En esta via, hay un ciclo de transacciones abierto por otro cobrador";
    		Thread.sleep(2000);
    		takeScreenShot("tManuals.png");
    		Thread.sleep(2000);
    		selectDropDown("ctl00_ContentZone_companyPlazaLane_cmb_plaza_cmb_dropdown");
    		Thread.sleep(1000);
    		selectDropDown("ctl00_ContentZone_companyPlazaLane_cmb_lane_cmb_dropdown");
    		Thread.sleep(3500);
    		driver.findElement(By.id("ctl00_ContentZone_txt_operator_box_data")).sendKeys(String.valueOf(ranNumbr(1,9)));;
    		Thread.sleep(3000);
    		driver.findElement(By.id("ctl00_ButtonsZone_BtnSearch_IB_Label")).click();
    		Thread.sleep(3500);
    		String errorLbl = driver.findElement(By.id("ctl00_LblError")).getText();
    		if (errorLbl.contains(Error)){
    			driver.findElement(By.id("ctl00_ContentZone_BtnCloseTrset")).click();
    			Thread.sleep(3500);
    			driver.findElement(By.id("ctl00_ContentZone_BtnAddTransaction")).click();
    			Thread.sleep(3500);
    			addTrans();     			
    		}else{
    			driver.findElement(By.id("ctl00_ContentZone_BtnAddTransaction")).click();
    			Thread.sleep(3500);
    			addTrans();
    			
    		}
    		
    		takeScreenShot("fManuals.png");
    		Thread.sleep(1000);
    		String TranId= driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr[2]/td[1]")).getText();
    		System.out.println("Transacción: "+TranId+" ha sido creada");
    		Thread.sleep(4000);
    			
    }catch (Exception e){
    	e.printStackTrace();}
  }
     
   public void addTrans() throws Exception{
	   Thread.sleep(2000);
	   takeScreenShot("aManuals.png");
	   Thread.sleep(1000);
	   selectDropDown("ctl00_ContentZone_cmbPnlVehicleClass_cmb_dropdown");	   
	   char[] plateletter=new char[24];
		char[] let1 = new char[3];
		for(int i=0;i<=2;i++){
			plateletter[i]=chr[(int)(Math.random()*24)];
			let1[i] = plateletter[i];			
		}
		platlet = String.valueOf(let1);						
		driver.findElement(By.id("ctl00_ContentZone_txtPnlPlate_box_data")).sendKeys(ranNumbr(10001,99999)+platlet);
		Thread.sleep(5000);
		selectDropDown("ctl00_ContentZone_cmbPnlTypeTicket_cmb_dropdown");
		Thread.sleep(3000);
		String selected = new Select(driver.findElement(By.id("ctl00_ContentZone_cmbPnlTypeTicket_cmb_dropdown"))).getFirstSelectedOption().getText();				
		if (selected.equals("Boleta")){
				
			driver.findElement(By.id("ctl00_ContentZone_txtPnlNumberTicket_box_data")).sendKeys(ranNumbr(100,999)+"-"+ranNumbr(10,99)+"-"+ranNumbr(10000000,99999999));
			Thread.sleep(3000);			
		}
		if (selected.equals("Factura")){								
			driver.findElement(By.id("ctl00_ContentZone_txtPnlNumberTicket_box_data")).sendKeys(ranNumbr(100,999)+"-"+ranNumbr(10,99)+"-"+ranNumbr(10000000,99999999));
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_ContentZone_txtPnlRUC_box_data")).sendKeys(String.valueOf(ranNumbr(100000000,888888888))+String.valueOf(ranNumbr(10,99)));
			Thread.sleep(2500);
			driver.findElement(By.id("ctl00_ContentZone_txtPnlDesignation_box_data")).sendKeys("CONCESIONARIA COVIERU");
			Thread.sleep(2500);			
			
		}
			driver.findElement(By.id("ctl00_ContentZone_BtnPnlAddTransaction")).click();
			Thread.sleep(4000);
	   
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
		int vdd = rand.nextInt(dd.size());
			if (vdd<0){vdd = vdd+1;}	
			if (vdd>=dd.size()){vdd=vdd-1;}
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
  public static int ranNumbr(int min, int max) {
	  Random rand = new Random();
	  numbering = min+rand.nextInt((max-min)+1);
	  return numbering;
	  
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