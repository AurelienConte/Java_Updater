package fr.aure.java_updater;
import fr.aure.java_updater.MainUpdater;

public class Main {
	
	private static String URL_SERVER = "http://localhost/ServerUpdater/";
	private static String FOLDER_PATH = "C:\\laragon\\www\\Updater";
	
	/*
	 * Main constructor called first
	 */
	public static void main(String[] args) 
	{
			MainUpdater SUpdate = new MainUpdater(URL_SERVER, FOLDER_PATH);
			SUpdate.start();
	}
}
