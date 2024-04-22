package miniproject;
//importing required classes and interfaces  from respective packages
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import driverutility.BaseUrl;
import driverutility.DriverSetup;
import excel.ExcelInput;
import excel.ExcelOutput;
import output.TextUtility;
import screenshot.ErrorSnap;

public class MiniProject {

	//Declaring a static variable driver of type WebDriver
	static WebDriver driver;

	//declaring static variables of type WebElement 
	static WebElement mobileNumber,day,month,year;

	//declaring static dateOfBirth variable of type Select;
	static Select dateOfBirth;

	/*storing the URL of the web application 
	  in a baseUrl variable of type String
	 */
	String baseUrl=BaseUrl.geturl();

	//launching the browser
	public void setupDriver(){

		//invoking getWebDriver method from DriverSetup file
		driver=DriverSetup.getWebDriver();

		//maximizing the web application window 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	//declaring and defining navigateToBaseUrl method
	public void navigateToBaseUrl() {
		//Navigating to the base URL
		driver.get(baseUrl);

	}

	//navigating  to SignUp page.
	public void navigateToSignup() {

		//Locating the element by using linkText and clicking on the link.
		driver.findElement(By.linkText("Create new account")).click();

	}

	//setting values to the input elements
	public void setElements(String fname,String lname,String mobile){
		/*Locating the web elements with appropriate locators 
		and sending the value*/

		driver.findElement(By.name("firstname")).sendKeys(fname);
		driver.findElement(By.name("lastname")).sendKeys(lname);
		mobileNumber=driver.findElement(By.name("reg_email__"));
		mobileNumber.sendKeys(mobile);
	}

	//selecting DOB from drop down
	public void selectDOB(String date){
		String[] dateValue=date.split("/");
		String dayValue=dateValue[0];
		int monthValue=Integer.parseInt(dateValue[1]);
		String yearValue=dateValue[2];

		day=driver.findElement(By.id("day"));
		dateOfBirth=new Select(day);
		dateOfBirth.selectByVisibleText(dayValue);

		month=driver.findElement(By.id("month"));
		dateOfBirth=new Select(month);
		dateOfBirth.selectByIndex(monthValue-1);

		year=driver.findElement(By.id("year"));
		dateOfBirth=new Select(year);
		dateOfBirth.selectByValue(yearValue);


	}

	//selecting the gender by clicking one of the radio buttons
	public void setGender(String gender){
		if(gender.equalsIgnoreCase("male")) {

			driver.findElement(By.xpath("//label[text()='Male']")).click();

		}else if(gender.equalsIgnoreCase("female")) {

			driver.findElement(By.xpath("//label[text()='Female']")).click();

		}else if(gender.equalsIgnoreCase("custom")) {

			driver.findElement(By.xpath("//label[text()='Custom']")).click();

			WebElement pronounElement=driver.findElement(By.xpath("//select[@name='preferred_pronoun']"));
			Select pronoun=new Select(pronounElement);
			pronoun.selectByValue("2");
		}else {
			System.out.println("Invalid gender input");
		}
	}

	//Clicking on the sign up button
	public void clickSignUp() {
		driver.findElement(By.name("websubmit")).click();
	}

	/*validating the mobile phone error message is displayed and 
	  returning the error message 
	 */
	public String getMobileError() throws InterruptedException, IOException{
		WebElement mobileErrorMsg;
		Thread.sleep(2000);

		mobileNumber.click();
		//exception handling 
		try {
		
		 mobileErrorMsg=driver.findElement(By.xpath("/html/body/div[6]/div/div/div"));
		 if(mobileErrorMsg.isDisplayed()) {
				System.out.println("--Mobile number error message is displayed successfully");
				ErrorSnap.mobileErrorSnap(driver);
				System.out.println("--Screenshot of displayed mobile number error is taken successfully!!!");
				return mobileErrorMsg.getText();
			}else {
				return "no mobile error message displayed";
			}
		}
		catch(Exception e) {
			return "No mobile error.Mobile number is correct";
			
		}
		
		
	}


	/*validating the password error message is displayed and 
	  returning the error message 
	 */

	public String getPasswordError() throws InterruptedException, IOException {
		WebElement passwdErrorMsg;
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"password_step_input\"]")).click();
		//exception handling 
		try {
	    passwdErrorMsg=driver.findElement(By.xpath("//div[@data-ownerid='password_step_input']//div//div/div"));
		if(passwdErrorMsg.isDisplayed()) {
			System.out.println("--Password error message is displayed successfully");
			ErrorSnap.passwordErrorSnap(driver);
			System.out.println("--Screenshot of displayed password error is taken successfully!!!");
			return passwdErrorMsg.getText();

		}
		else {
			return "no password error is displayed";
		}
		}
		catch(Exception e) {
			return "No password error in the website.";
		}
		
	}

	//closing the browser
	public void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException, IOException, InvalidFormatException {
		// Creating the object of the MiniProject class
		MiniProject obj=new MiniProject();
		ArrayList <String> list=ExcelInput.getExcelInput();
		//launching the browser by calling setupDriver method
		obj.setupDriver();

		//navigating to base URL by .calling the navigateToUrl method 
		obj.navigateToBaseUrl();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//invoking navigateToSignup method 
		obj.navigateToSignup();

		//providing  the inputs by invoking the setElements method 
		obj.setElements(list.get(0), list.get(1), list.get(2));

		//invoking the selectDOB method to provide input to DOB drop down
		obj.selectDOB(list.get(3));
		//invoking the setGender method to click one of the radio buttons .
		obj.setGender(list.get(4));

		//invoking the clickSignUp method to click the sign up button
		obj.clickSignUp();

		//storing the mobile number field prompt error in mobileErrorMsg variable
		String mobileErrorMsg=obj.getMobileError();
		//storing the password  field prompt error in passwordErrorMsg variable
		String passwordErrorMsg=obj.getPasswordError();
		//printing errors
		System.out.println("the mobile number field error message displayed:\n\t  "+ mobileErrorMsg);
		System.out.println("the password field error message displayed:\n\t  "+passwordErrorMsg);
		//closing the browser by invoking the closeBrowser method
		obj.closeBrowser();
		//writing the output in the text file 
		
		TextUtility.writeOutput(mobileErrorMsg+"\n"+ passwordErrorMsg);
		System.out.println("--error messages  are stored in text file successfully.");
		//writing the output in the target cell in the excel file
		ExcelOutput.writeOutput(mobileErrorMsg+"\n "+ passwordErrorMsg);
		System.out.println("--error messages  are written  in excel file successfully.");


	}

}
