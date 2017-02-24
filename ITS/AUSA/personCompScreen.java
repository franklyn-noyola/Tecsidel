package AUSA;



import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

public class personCompScreen extends ausaFieldsConfiguration{
	  
	  public static void ibPerson() throws Exception {
		  Thread.sleep(1000);
		  driver.findElement(By.id(perLabel)).click();
		  Thread.sleep(1000);
		  driver.switchTo().frame(0);
          Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_Title_box_data")).clear();
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_Title_box_data")).sendKeys("Damnificado"+" - "+ranNumbr(1,99)+" QA" );
			Thread.sleep(500);
			int nameGender = ranYearNumbr(0,personsT.length-1);
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_name_box_data")).sendKeys(personsT[nameGender]);
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_address_box_data")).sendKeys("ESPAÑA");
			selectDropDownClick("ctl00_ContentZone_ctrlPerson_cmb_city_cmb_dropdown");
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_phone_box_data")).sendKeys(ranYearNumbr(910000000,980000000)+"");
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_dni_box_data")).sendKeys(dniLetra(ranYearNumbr(10000000,40000000))+"");
			new Select (driver.findElement(By.id("ctl00_ContentZone_ctrlVehicle_cmb_driver_gender_cmb_dropdown"))).selectByIndex(genderT[nameGender]);
			selectDropDownClick("ctl00_ContentZone_ctrlPerson_cmb_status_cmb_dropdown");
			driver.findElement(By.id("ctl00_ContentZone_ctrlPerson_txt_comment_box_data")).sendKeys("Component Created by QA Automation Script");
			Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ButtonsZone_BtnSave_IB_Label")).click();
			Thread.sleep(2000);
	  }
     	
}
