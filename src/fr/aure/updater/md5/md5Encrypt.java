package fr.aure.updater.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5Encrypt {
	
	
	private static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
	    FileInputStream fis = new FileInputStream(file);
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0;
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	    fis.close();
	    byte[] bytes = digest.digest();
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	   return sb.toString();
	}
	
	public String md5(String path)
	{
		File file = new File(path);
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		String checksum = null;
		try {
			checksum = getFileChecksum(md5Digest, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checksum;
	}
}
