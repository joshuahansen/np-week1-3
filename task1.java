/*	Task 1 is to use InputStream and OutputStream to take a 
*	user input and replace each whitespace with the underscore
*	'_' charater, then output it to the screen
*/
import java.io.*;
import java.util.Scanner;
import java.lang.StringBuilder;

public class task1 {
	public static void main(String[] args) {
		useInputStream();
	}
	//Use InputStream to get user input. no buffer to read into.
	public static void useInputStream()
	{
		try {
			InputStream input = System.in;
			OutputStream output = System.out;
			byte[] byteArray = new byte[1024];
			//Create a char array to output to screen for user prompt
			char[] prompt = new char [] {'E','n','t','e','r',' ','I','n','p','u','t',':','\n'};
			byte[] bytePrompt = new byte[prompt.length*2];
			//convert char aray to byte array
			for(int x = 0; x < prompt.length; ++x)
			{
				bytePrompt[x] = (byte)prompt[x];
			}
			//output byte array
			output.write(bytePrompt);
		
			//loop until user ends input
			int i = 0;
			while(true)
			{
				try {
					byte c = (byte)input.read();
					if(c == (byte)'\n')
					{
						byteArray[i] = c;
						break;
					}
					else if(c == (byte)' ')
						byteArray[i] = '_';
					else
						byteArray[i] = c;
				//catch exception if input is bigger than byte array and truncate the returned value
				}catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Input exceeded byte array");
					break;
				}
				++i;
			}
			//write formatted string to screen
			output.write(byteArray);
			
			//close input and output streams
			input.close();
			output.close();
		}catch (IOException ex) {
			System.out.println("Exception while reading input " + ex);
		}
	}				
}
