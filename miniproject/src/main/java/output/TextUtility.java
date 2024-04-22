package output;
//importing required classes and interfaces  from respective packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextUtility {
	/*declaring and defining the writeOutput()
	method to write the output in the text file*/
	public static void writeOutput(String output) throws IOException
	{
		
		//creating the object of File class and define the file path. 
		File file = new File("./src/main/outputs/textOutput.txt");
		
		//iterating to check the file existence
		while(true)
		{
			//using exists() method to check the existence of the file 
			if(file.exists())
			{
				file.delete();
			}
			else
			{
				//creating new file 
				file.createNewFile();
				break;
			}
		}
		
		if(file.exists())
		{
			//creating the instance of the FileWriter class
			FileWriter writer = new FileWriter(file);
			//using write method to write the output text in the text file
			writer.write(output);
			//closing the resource
			writer.close();
		}
	}
}
