package fr.aure.java_updater;

import java.net.*;

import fr.aure.java_updater.downloader.ServerConfiguration;
import fr.aure.java_updater.xml.Reader;

public class MainUpdater {
	
	public ServerConfiguration ServerInformations = null;
	
	/*
	 * URL_PATH --> Target web server
	 * FOLDER_PATH --> Where you want install files download
	 */
	public MainUpdater(String URL_PATH, String FOLDER_PATH) {
		try { this.ServerInformations = new ServerConfiguration(URL_PATH, FOLDER_PATH); } catch (MalformedURLException e) { e.printStackTrace(); }
		if(this.ServerInformations == null)
			System.exit(0);
	}
	
	public boolean start()
	{
		new Reader(this.ServerInformations);
		return true;
	}
}
