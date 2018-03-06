/*	A program that takes lines of characters from the console
*	and writes them to a file. Input stops when a line is a 
*	single 'x' character.
*	The Checksum is also calculated and written to a seperate
*	file.
*/

import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.*;

public class task2a {
	public static void main(String[] args) {
		byte[] input = getInput();
		boolean write = writeToFile(input);
		if(write)
			System.out.println("Files written");
		else
			System.out.println("could not write to files");
	}
	//write input to file
	public static boolean writeToFile(byte[] input)
	{
		try {
			//open file and checksum file
			FileOutputStream file = new FileOutputStream("test");
			FileOutputStream csFile = new FileOutputStream("checksum");	
			
			//create a checksum from input
			Checksum checksum = new CRC32();
			checksum.update(input, 0, input.length);
		
			//print checksum to screen
			System.out.println("Checksum: " + checksum.getValue());
			byte[] cs = longToBytes(checksum.getValue());	
			//write checksum to file
			csFile.write(cs);
		
			//write input to file
			file.write(input);
			return true;
			}catch(FileNotFoundException ex)
			{
				System.out.println("File not found: " + ex);
				return false;
			}catch(IOException e)
			{
				System.out.println("Can't write to file: " + e);
				return false;
			}
	}
	
	//get user input
	public static byte[] getInput()
	{
		try
		{
			InputStream input = System.in;
			OutputStream output = System.out;
			//input buffer byte array
			byte[] byteArray = new byte[100];
			//create char array for output prompt
			char[] prompt = new char [] {'E','n','t','e','r',' ','I','n','p','u','t',':','\n'};
			byte[] bytePrompt = new byte[prompt.length*2];
			for(int x = 0; x < prompt.length; ++x)
			{
				bytePrompt[x] = (byte)prompt[x];
			}
			output.write(bytePrompt);
			int i = 0;
			while(true)
			{
				try {
					byte c = (byte)input.read();
					if(c == (byte)'\n')
					{
						//check if 'x' is on a line by itself stop input if it is.
						if(i > 2)
						{
							if(byteArray[i-1] == (byte)'x' && byteArray[i-2] == (byte)'\n')
							{
								byte[] tmp = new byte[i-1];
								System.arraycopy(byteArray, 0, tmp, 0, i-1);
								byteArray = tmp;
								break;
							}
						}
						//checks if 'x' first
						else if(i == 1)
						{
							if(byteArray[i-1] == (byte)'x')
							{
								byte[] tmp = new byte[i-1];
								System.arraycopy(byteArray, 0, tmp, 0, i-1);
								byteArray = tmp;
								break;
							}
						}
						byteArray[i] = c;
						input = System.in;
					}
					else
						byteArray[i] = c;
				}catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Input was larger than byte array result will be truncated");
					break;
				}
				++i;
			}	
			input.close();
			//output.close();
			return byteArray;
		}catch (IOException ex) {
			System.out.println("Exception while reading input " + ex);
			return null;
		}
	}
	public static byte[] longToBytes(long checksum)
	{
		String cs = String.valueOf(checksum);
		char[] csChar = cs.toCharArray();
		byte[] csByte = new byte[csChar.length*2];
		for(int x = 0; x < csChar.length; ++x)
		{
			csByte[x] = (byte)csChar[x];
		}
		return csByte;
	}
}
