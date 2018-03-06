/*	Read a character file print content to screen and
*	calculate the checksum of the file. Read checksum from
*	seperate file and print to screen
*/

import java.io.*;
import java.util.zip.*;

public class task2b 
{
	public static void main(String[] args)
	{	
		byte[] data = readFile("test");
		display(data);
		Checksum dataChecksum = new CRC32();
		dataChecksum.update(data, 0, data.length);
		System.out.println("DEBUG: " + data);
		System.out.println("DEBUG: " + data.length);
		System.out.println(dataChecksum.getValue());
	
		data = readFile("checksum");
		display(data);
	}

//read file in one byte at a time and return byte array from file
	public static byte[] readFile(String filename)
	{
		byte[] fileInput = new byte[100];
		try {
			FileInputStream file = new FileInputStream(filename);
		try {
				int x = 0;
				int count = 0;
				while(x != -1)
				{
					x = file.read();
					if(x != -1)
					{
						fileInput[count] = (byte)x;
						++count;
					}
				}
				byte[] tmp = new byte[count];
				System.arraycopy(fileInput, 0, tmp, 0, count);
				file.close();
				return tmp;
			}catch(IOException ex)
			{
				System.out.println("unable to read from file" + ex);
			}catch(ArrayIndexOutOfBoundsException exc)
			{
				System.out.println("Buffer not large enough for input." + exc);
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found " + e);
		}
		
		return null;
	}
//Display byte array to screan	
	public static void display(byte[] data)
	{
		try {
			OutputStream output = System.out;
			output.write(data);
		}catch(IOException ex)
		{
			System.out.println("Error when displaying data " + ex);
		}
	}
}
