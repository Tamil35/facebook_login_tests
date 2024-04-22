package screenshot;
//importing required classes and interfaces  from respective packages
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ErrorSnap {

	static  File  mobileErrSource,mobileErrSnap,passwdErrSource,passwdErrSnap;


	//Taking mobile error message screenshot
	public static void mobileErrorSnap(WebDriver driver) throws IOException {


		mobileErrSource=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		mobileErrSnap=new File("./src/main/outputs/mobileError.png");
		FileHandler.copy(mobileErrSource, mobileErrSnap);


	}

	//Taking password error message screenshot
	public static void passwordErrorSnap(WebDriver driver) throws IOException {


		passwdErrSource=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		passwdErrSnap=new File("./src/main/outputs/passwordError.png");
		FileHandler.copy(passwdErrSource, passwdErrSnap);


	}
}
