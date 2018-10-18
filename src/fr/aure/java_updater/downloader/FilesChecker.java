package fr.aure.java_updater.downloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import fr.aure.java_updater.xml.ListFile;
import fr.aure.updater.md5.md5Encrypt;

public class FilesChecker {
	
	private ServerConfiguration Servers = null;
	private ArrayList<ListFile> DL = null;
	
	public FilesChecker(ServerConfiguration Servers, ArrayList<ListFile> DL) {
		this.Servers = Servers;
		this.DL = DL;
		CreateRootFolder();
		FilesDelete();
		FilesCheckMd5();
	}
	
	/*
	 * Create if folder not exist
	 */
	public void CreateRootFolder()
	{
		File dest = new File(this.Servers.getClientFolder());
		dest.getParentFile().mkdirs();
	}
	
	/*
	 * DELETING FILES NOT PRESENT IN XML FILE
	 */
	public void FilesDelete()
	{
		File file = new File(this.Servers.getClientFolder());
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(file.list()));
		
		for (String content : names) {
			boolean Found = false;
			for (ListFile item : this.DL) {
				if(item.getName().equals(content)) {
					Found = true;
					break;
				}		
			}
			if(Found == false) {
				File file_to_delete = new File(this.Servers.getClientFolder() + "/" + content);	
				if(file_to_delete.exists()) {
					System.out.println("Deleting file no present in download XML : " + file_to_delete.getName());
					file_to_delete.delete();
				}
			}
		}
	}
	
	/*
	 * Remove BAD MD5 FILES
	 */
	public void FilesCheckMd5()
	{
		ArrayList<ListFile> temp_del = new ArrayList<ListFile>(); 
		
		for (ListFile item : this.DL) {
			System.out.println("Checking MD5 : " + item.getName());
			File file = new File(this.Servers.getClientFolder() + "/" + item.getName());
			if(file.exists()) {
				String file_md5 = new md5Encrypt().md5(this.Servers.getClientFolder() + "/" + item.getName()).toUpperCase();
				
				if(!file_md5.equals(item.getMd5().toUpperCase())) {
					
					System.out.println("GEN : " + file_md5 + ", " + item.getMd5().toUpperCase());
					
					System.out.println("Deleting file bad MD5 : " + file.getName());
					file.delete();
				} else {
					temp_del.add(item);
				}
			}
		}
	
		for (ListFile item: temp_del) {
			if(this.DL.contains(item))
				this.DL.remove(item);
		}
	}
	
	public ArrayList<ListFile> getNewList()
	{
		return this.DL;
	}
}
