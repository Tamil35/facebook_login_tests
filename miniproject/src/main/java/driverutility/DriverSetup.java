package driverutility;
import java.util.InputMismatchException;
//importing required classes and interfaces  from respective packages
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


/*declaring and defining DriverSetup class containing the methods
   to launch the browser.
 */
public class DriverSetup {
	
	/*declaring and defining the static getWebDriver method which
	  returns corresponding driver of Browser chosen by user.
	 */
	
	public static WebDriver getWebDriver() {
		//creating object of Scanner class
		Scanner sc=new Scanner(System.in);
	
		
		//Declaring driver of type WebDriver
		WebDriver driver;
		int choice=0;
		
		//iterating till the user provide a valid input

		while(true) {
			System.out.println("Select one of the Browsers.");
			System.out.println("1.ChromeBrowser");
			System.out.println("2.Edgebrowser");
			System.out.println("Enter 1 for launching chrome browser.");
			System.out.println("Enter 2 for launching Edge browser.");
			
			//handling exception by using try catch.
			try {
				//getting the user input and storing it in a variable choice
			 choice=sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("entered a non-numeric value");
				System.out.println("launching the default (chrome)browser");
				choice=1;
				
			}
			finally {
				sc.close();
			}
			switch(choice) {
			
			case 1:
				/*Creating a object of ChromeDriver class and 
				  storing it in a reference variable driver of
				  type WebDriver.
				 */
				
				driver=new ChromeDriver();
				
				//returning object of ChromeDriver class.
				return driver;
				
				
			case 2:
				
				/*Creating a object of EdgeDriver class and 
				  storing it in a reference variable driver of
				  type WebDriver.
				 */
				driver=new EdgeDriver();
				
				//returning object of EdgeDriver.
				return driver;
				
				
			default:
				//invalid input 
				System.out.println("Invalid input.");
				System.out.println("Enter the browser name ");
				System.out.println("1.ChromeBrowser");
				System.out.println("2.Edgebrowser");
				System.out.println("Enter  1 for launching chrome browser");
				System.out.println("Enter 2 for launching Edge browser");
				choice=sc.nextInt();

			}




		}
	

	}
}
