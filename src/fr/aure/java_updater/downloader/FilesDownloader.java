package fr.aure.java_updater.downloader;

import java.util.ArrayList;

import fr.aure.java_updater.xml.ListFile;

public class FilesDownloader {
	
	private Long BytesDownloaded = 0L;
	private Integer FilesToDownload = 0;
	private Integer FilesDownloaded = 0;
	private Float PourcentTask = 0.0f;
	
	public FilesDownloader(ServerConfiguration Server, ArrayList<ListFile> DL) {
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
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
				e.printStackTrace();
			}
			
		
		System.out.println("Total bytes download : " + this.getBytesDownloaded() + ", Total Files : " + this.getFilesDownloaded());
	}
	
	public void updatePourcent()
	{
		this.PourcentTask = (float) ((FilesDownloaded * 100) / this.FilesToDownload);
	}
	
	public Float getPourcent()
	{
		return this.PourcentTask;
	}
	
	public void IncreaseFilesDownloaded(Integer nbr)
	{
		this.FilesDownloaded += nbr;
	}
	
	public Integer getFilesDownloaded()
	{
		return this.FilesDownloaded;
	}
	
	public Integer getFilesToDownload()
	{
		return this.FilesToDownload;
	}
	
	public void IncreaseBytesDownloaded(Long bytes)
	{
		this.BytesDownloaded += bytes;
	}
	
	public Long getBytesDownloaded()
	{
		return this.BytesDownloaded;
	}
}
