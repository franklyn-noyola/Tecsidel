package AUSA;

import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.util.Loader;
import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.junit.*;
import org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class ausaCrearPartes extends ausaFieldsConfiguration{
	    public static String mPartes;
	    
	    
	    
 @Before
 public  void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		  /*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		  cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);			  
		  cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
				  
		  driver = new ChromeDriver();		  
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		  
 	}
 
 	@Test
 	public void ausaIssuescreate() throws Exception{
		Actions action = new Actions (driver);
			driver.get(baseUrl);
			if (driver.getPageSource().contains("No se puede acceder a este sitio web"))
        {                    
            System.out.println("ITS NO ESTA DISPONIBLE");
            return;
        }			  			
			Thread.sleep(2000);
			takeScreenShot("ausaLoginPage.jpeg");
	        driver.findElement(By.id("BoxLogin")).sendKeys("calidad");
        driver.findElement(By.id("BoxPassword")).sendKeys("calidad");
        driver.findElement(By.id("BtnLogin")).click();
        Thread.sleep(3000);
        takeScreenShot("AusamP.jpeg");
        String currentWindowHandle = driver.getWindowHandle();		                
        String lPartes = driver.findElement(By.xpath("//div[7] / div / ul / li[5] / a")).getText();		                
        Thread.sleep(1000);
        WebElement Partes = driver.findElement(By.linkText(lPartes));		                
        action.clickAndHold(Partes).perform();
        Thread.sleep(2000);
        mPartes = driver.findElement(By.xpath("// div[7] / div / ul / li[5] / ul / li / a")).getText();		                
        driver.findElement(By.linkText(mPartes)).click();
        Thread.sleep(8000);        
        takeScreenShot("AusapP.jpeg");
        ArrayList<String> wHandle = new ArrayList<String>(driver.getWindowHandles());
        //driver.switchTo().window(availableWindows.get(1));		                
        //System.out.println(driver.switchTo().window(availableWindows.get(1)).getTitle());
        
       for (String window : wHandle) {
        	Thread.sleep(600);
        	if (window != currentWindowHandle){
        		driver.switchTo().window(window);		                		
        		 //Partes1 = Partes1.toString();
        		//System.out.println(Partes1);
        		
        	}
       }           
			Object Partes1 = mPartes;
			Assert.assertEquals(mPartes, Partes1);
			WebElement nexTab = driver.findElement(By.tagName("body"));
               nexTab.sendKeys(Keys.CONTROL + "\t");
               driver.switchTo().defaultContent();
        Thread.sleep(3000);
        driver.findElement(By.id(createBtn)).click();
        Thread.sleep(3000);
        //selectDropDownClick(templateSel);
        new Select (driver.findElement(By.id(templateSel))).selectByVisibleText("Accidente");
        driver.findElement(By.id(templateBtn)).click();
        Thread.sleep(4000);               
        createPartes();
 	}
 	
 	
	public void createPartes() throws Exception{
		Thread.sleep(2000);
		ArrayList<String> wHandle = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(wHandle.get(2));		                 			
			Object Partes1 = "#Partes";
       		Assert.assertEquals("#Partes", Partes1);
			WebElement nexTab = driver.findElement(By.tagName("body"));
               nexTab.sendKeys(Keys.CONTROL + "\t");
               driver.switchTo().defaultContent();
               Thread.sleep(1000);
               tipoSel = driver.findElement(By.id("ctl00_ContentZone_txt_type_box_data")).getAttribute("value");
               selectDropDownClick(prioritySel);
               System.out.println(tipoSel);
        //Filling out all data
            selectDropDownClick(gravedadT);//Gravedad
            selectDropDownClick(asignadoT);//Asignado            
            if (driver.findElements(By.id(supervisorT)).size()!=0){
            	selectDropDownClick(supervisorT);//Supervisor            	
            }
            Thread.sleep(3000);
            selectDropDownClick(tValoresT);//Tramo Lista Valores
            Thread.sleep(2000);
            notEmptyDropDown(direcT);
            driver.findElement(By.id("ctl00_ContentZone_ctlPkm_txt_PkmKm_box_data")).sendKeys(ranNumbr(10,900)+"");
            driver.findElement(By.id("ctl00_ContentZone_ctlPkm_txt_PkmM_box_data")).sendKeys(ranNumbr(1,900)+"");
            Thread.sleep(1000);
            notEmptyDropDown(ramalsT);
            driver.findElement(By.id(locationT)).sendKeys("Buenos Aires");
            driver.findElement(By.id(observaT)).sendKeys("QA issue created by Automation Script");
            Thread.sleep(500);
            datosSection();
            Thread.sleep(1000);
            ranclickOption(dOption, 1, dOption.length);
            Thread.sleep(3000);
            if (driver.findElement(By.id(dOption[4])).isSelected()){
            	driver.findElement(By.id(vVolcado)).sendKeys(ranNumbr(1,99)+"");
            }
            Thread.sleep(2000);
            if (tipoSel.equals("Incidente")|| tipoSel.equals("Accidente")){
            	ranclickOption(vOption, 1, vOption.length);
            	for (int  i = 1; i < vOption.length;i++){            		
            		if (driver.findElement(By.id(vOption[i])).isSelected()){
            			Thread.sleep(1000);
            			driver.findElement(By.id(vOptionT[i])).sendKeys(ranNumbr(1,99)+"");;
            		}
            	}
            }	
            Thread.sleep(500);
            communicationSection();
            Thread.sleep(1500);
            driver.findElement(By.id(issueCreateBtn)).click();
            Thread.sleep(2500);

	}
	
	public void datosSection() throws Exception{
        Thread.sleep(1500);
        driver.findElement(By.id(datoBtn)).click();
    	Thread.sleep(1000);
        if (tipoSel.equals("Incidente")|| tipoSel.equals("Accidente")){        	
        	driver.findElement(By.id(typeAccidentes)).click();
        	Thread.sleep(500);
        	ranClick("ctl00_ContentZone_mc_typeOfAccident_ctl","",19,23);
        	Thread.sleep(400);
        	driver.findElement(By.id(typeImpacto)).click();
        	Thread.sleep(500);
        	ranClick("ctl00_ContentZone_mc_causal_ctl","",19,23);
        	Thread.sleep(500);
        }	
        driver.findElement(By.id("ctl00_ContentZone_txt_causes_box_data")).sendKeys("This was written by automation scrript for Test Purpose");
        driver.findElement(By.id("ctl00_ContentZone_txt_information_box_data")).sendKeys("This was written by automation scrript for Test Purpose");
        driver.findElement(By.id("ctl00_ContentZone_txt_observations_box_data")).sendKeys("This was written by automation scrript for Test Purpose");
        driver.findElement(By.id("ctl00_ContentZone_txt_note_box_data")).sendKeys("This was written by automation scrript for Test Purpose");
        Thread.sleep(500);
        driver.findElement(By.id(cameraSel)).click();
        Thread.sleep(500);
        ranClick("ctl00_ContentZone_mcCameras_ctl","",105,119);
 	}
	
	public void communicationSection() throws Exception{
		Thread.sleep(1000);
		driver.findElement(By.id(communicationField)).clear();
		driver.findElement(By.id(communicationField)).sendKeys("Communication"+" - "+ranNumbr(1,99)+" QA Automation" );
		Thread.sleep(500);
		selectDropDownClick(newCommunication);
		Thread.sleep(500);
		selectDropDownClick(medioField);
		Thread.sleep(500);
		selectDropDownClick(motiveField);
		Thread.sleep(500);
		selectDropDownClick(originDestination);
		Thread.sleep(2000);
		notEmptyDropDown(originDest);
		Thread.sleep(500);
		selectDropDownClick(importanceField);
		Thread.sleep(500);
		driver.findElement(By.id(subjectField)).sendKeys("Created by Automation Script");
		driver.findElement(By.id(commentField)).sendKeys("This Communication was created by an automation script for testing purpose");
		Thread.sleep(1000);
		
	}

 	
}