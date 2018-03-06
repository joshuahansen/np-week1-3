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
		System.out.println(dataChecksum.getValue());
	
		data = readFile("checksum");
		display(data);
	}

	public static byte[] readFile(String filename)
	{
		byte[] fileInput = new byte[100];
		try {
			FileInputStream file = new FileInputStream(filename);
/*			Checksum checksum = new CRC32();
			CheckedInputStream cs = new CheckedInputStream(file, checksum);
			System.out.println(cs.getChecksum().getValue());
*/			try {
				file.read(fileInput);
				file.close();
			}catch(IOException ex)
			{
				System.out.println("unable to read from file" + ex);
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("File not found " + e);
		}
		
		return fileInput;
	}
	
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
