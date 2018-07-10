package com.hellosatish.generator.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 
 * @author satish-s
 * <pre>
 * 
 * </pre>
 */
public class FileHelper {

	private static final String NEEDLE= "injector do not remove this line needle";
	private static final String FILE= "E:/temp/testfile.java";
	
	/*public static void main(String[] args) throws IOException {
		FileHelper helper = new FileHelper();
		int offset = helper.getOffset(FILE);
			helper.insert(FILE, offset, "\nTHis is newly added content\n".getBytes());

	}*/
	
	public int getOffset(String fileName) throws IOException{
		int offset = 0;
		List<String>  data = Files.readAllLines(Paths.get(fileName));
			for(String line : data){
				//System.out.println("Line : "+line);
				if (line.contains(NEEDLE)){
					//offset = offset+line.length();
					break;
				}else{
					offset = (offset+line.length());
					System.out.println("Adding to offset : "+line +"\t current "+offset);
				}	
			}
			System.out.println("Offset : "+offset);
		return (offset+1);
	}

	public void insert(String filename, long offset, byte[] content) throws IOException {
		  RandomAccessFile r = new RandomAccessFile(new File(filename), "rw");
		  RandomAccessFile rtemp = new RandomAccessFile(new File(filename + "~"), "rw");
		  long fileSize = r.length();
		  FileChannel sourceChannel = r.getChannel();
		  FileChannel targetChannel = rtemp.getChannel();
		  sourceChannel.transferTo(offset, (fileSize - offset), targetChannel);
		  sourceChannel.truncate(offset);
		  r.seek(offset);
		  r.write(content);
		  long newOffset = r.getFilePointer();
		  targetChannel.position(0L);
		  sourceChannel.transferFrom(targetChannel, newOffset, (fileSize - offset));
		  sourceChannel.close();
		  targetChannel.close();
		}
}
