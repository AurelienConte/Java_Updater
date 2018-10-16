package fr.aure.java_updater.downloader;

import java.util.ArrayList;

import fr.aure.java_updater.ErrorHandler;
import fr.aure.java_updater.xml.ListFile;

public class FilesDownloader {
	
	private Long BytesDownloaded = 0L;
	private Integer FilesToDownload = 0;
	private Integer FilesDownloaded = 0;
	private Float PourcentTask = 0.0f;
	
	public FilesDownloader(ServerConfiguration Server, ArrayList<ListFile> DL) {
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		FilesChecker checkInstance = new FilesChecker(Server, DL);
		
		
		DL = checkInstance.getNewList();
		this.FilesToDownload = DL.size();
		
		System.out.println("Files to download : " + this.FilesToDownload);
		
		for (ListFile item : DL) {
			System.out.println("Launching Download : " + item.getName());
			threads.add(new Thread(new ProcessDownload(this, Server, item)));
			threads.get(threads.size() - 1).start();
		}
		
		for(Thread item: threads)
			try {
				item.join();
			} catch (InterruptedException e) {
				new ErrorHandler(e);
			}
			
		System.out.println("Total bytes download : " + this.getBytesDownloaded() + ", Total Files : " + this.getFilesDownloaded());
	}
	
	public synchronized void updatePourcent()
	{
		this.PourcentTask = (float) ((FilesDownloaded * 100) / this.FilesToDownload);
	}
	
	public synchronized Float getPourcent()
	{
		return this.PourcentTask;
	}
	
	public synchronized void IncreaseFilesDownloaded(Integer nbr)
	{
		this.FilesDownloaded += nbr;
	}
	
	public synchronized Integer getFilesDownloaded()
	{
		return this.FilesDownloaded;
	}
	
	public synchronized Integer getFilesToDownload()
	{
		return this.FilesToDownload;
	}
	
	public synchronized void IncreaseBytesDownloaded(Long bytes)
	{
		this.BytesDownloaded += bytes;
	}
	
	public synchronized Long getBytesDownloaded()
	{
		return this.BytesDownloaded;
	}
}
