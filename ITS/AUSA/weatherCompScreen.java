package AUSA;



import org.openqa.selenium.By;



public class weatherCompScreen extends ausaFieldsConfiguration{
	  
	  public static void ibWeather() throws Exception {
		  Thread.sleep(1000);
		  driver.findElement(By.id(weaLabel)).click();
		  Thread.sleep(1000);
		  driver.switchTo().frame(0);
        Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ContentZone_ctrlWeather_txt_Title_box_data")).clear();
			driver.findElement(By.id("ctl00_ContentZone_ctrlWeather_txt_Title_box_data")).sendKeys("Tiempo"+" - "+ranNumbr(1,99)+" QA" );
			Thread.sleep(500);
			selectDropDownClick("ctl00_ContentZone_ctrlWeather_cmb_weather_cmb_dropdown");
			selectDropDownClick("ctl00_ContentZone_ctrlWeather_cmb_lighting_cmb_dropdown");
			selectDropDownClick("ctl00_ContentZone_ctrlWeather_cmb_visibility_cmb_dropdown");
			driver.findElement(By.id("ctl00_ContentZone_ctrlWeather_txt_comment_box_data")).sendKeys("This was created by QA Automation Script");			
			Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ButtonsZone_BtnSave_IB_Label")).click();
			Thread.sleep(2000);
	  }
   	
}

