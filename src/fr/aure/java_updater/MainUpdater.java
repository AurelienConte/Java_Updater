package fr.aure.java_updater;

import java.net.*;
import java.util.ArrayList;

import fr.aure.java_updater.downloader.FilesDownloader;
import fr.aure.java_updater.downloader.ServerConfiguration;
import fr.aure.java_updater.xml.ListFile;
import fr.aure.java_updater.xml.Reader;

public class MainUpdater {
	
	public ServerConfiguration ServerInformations = null;
	
	/*
	 * URL_PATH --> Target web server
	 * FOLDER_PATH --> Where you want install files download
	 */
	public MainUpdater(String FOLDER_PATH, String URL_PATH, String XML_FILENAME) {
		try { this.ServerInformations = new ServerConfiguration(URL_PATH, FOLDER_PATH, XML_FILENAME); } catch (MalformedURLException e) { new ErrorHandler(e); }
		if(this.ServerInformations == null)
			System.exit(0);
	}
	
	public boolean start()
	{
		Reader instance = new Reader(this.ServerInformations);
		ArrayList<ListFile> DL = instance.getDL();
		new FilesDownloader(this.ServerInformations, DL);
		return true;
	}
}
