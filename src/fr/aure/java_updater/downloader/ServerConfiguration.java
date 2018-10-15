package fr.aure.java_updater.downloader;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerConfiguration {
	
	public String protocol = null;
	public String hostname = null;
	public Integer PortNumber = null;
	public String Path = null;
	public String ClientFolder = null;
	
	public ServerConfiguration(String URL_PATH, String FOLDER_PATH) throws MalformedURLException {
		URL aURL = new URL(URL_PATH);
		
		this.protocol = aURL.getProtocol();		
		this.hostname = aURL.getHost();
		this.PortNumber = aURL.getPort();
		this.Path = aURL.getPath();
		this.ClientFolder = FOLDER_PATH;
	}
	
	public String getProtocol()
	{
		return this.protocol;
	}
	
	public String getHostName()
	{
		return this.hostname;
	}
	
	public Integer getPortNumber()
	{
		return this.PortNumber;
	}
	
	public String getPath()
	{
		return this.Path;
	}
	
	public String FolderPather()
	{
		return this.ClientFolder;
	}
}
