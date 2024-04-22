package driverutility;
//importing required classes and interfaces  from respective packages
import java.io.FileInputStream;
import java.util.Properties;

public class BaseUrl {
	//creating a static object of the properties class
	static Properties urlFile= new Properties();
	public static String geturl() {
		//Handling exception using Try catch
		try {
			//Reading the properties file by using FileInputStream class
			FileInputStream link = new FileInputStream("./src/main/browser/applicationurl.properties");
			//loading the properties file
			urlFile.load(link);
			/*reading the properties file and storing the baseURL value 
			in a variable of type String  */
			String url=urlFile.getProperty("baseURL");
			//returning the baseURL 
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
