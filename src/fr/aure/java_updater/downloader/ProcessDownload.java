package fr.aure.java_updater.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import fr.aure.java_updater.xml.ListFile;

public class ProcessDownload extends Thread {

	private ServerConfiguration Servers = null;
	private ListFile FileInfo = null;
	private FilesDownloader Instance = null;
	
	public ProcessDownload(FilesDownloader instance, ServerConfiguration Servers, ListFile FileInfo) {
		this.Instance = instance;
		this.Servers = Servers;
		this.FileInfo = FileInfo;
	}
	
	public void run()
	{
		URL url = null;
		
		try {
			url = new URL(this.Servers.getProtocol() + "://" + this.Servers.getHostName() + this.Servers.getPath() + "files/" + this.Servers.FILES_FOLDER + "/" + this.FileInfo.getName());
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(this.Servers.getClientFolder() + "/" + this.FileInfo.getName());
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			
			this.Instance.IncreaseBytesDownloaded(fos.getChannel().size());
			this.Instance.IncreaseFilesDownloaded(1);
			this.Instance.updatePourcent();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Download finished : " + this.FileInfo.getName() + ", to : " + this.Servers.getClientFolder() + ", Pourcent : " + this.Instance.getPourcent());
	}
}
