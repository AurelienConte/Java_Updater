package fr.aure.java_updater;
import fr.aure.java_updater.MainUpdater;

public class Main {
	
	//Remote Server
	private static String URL_SERVER = "http://localhost/Java_UpdaterServer/public/JAVA_UPDATER";
	
	//Local PATH for install
	private static String FOLDER_PATH = "C:\\laragon\\www\\Updater";
	
	/*
	 * Main constructor called first
	 */
	public static void main(String[] args) 
	{
			//First you need to init MainUpdater with provided LOCAL FOLDER PATH, URL OF UPDATER SERVER and the XML filename :)
			MainUpdater SUpdate = new MainUpdater(FOLDER_PATH, URL_SERVER, "my_project");
			
			//next you just need to start the update :) enjoy !
			SUpdate.start();
	}
}
