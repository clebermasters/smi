package com.scriptMachineInstaller.bean;

public class AppBean {

	private String fileName;
	private String extension;
	private String version;
	private String link;
	private int bits;
	private String key;
	private long size;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		String[] parts = link.split("/");
		this.fileName = parts[parts.length - 1];
		this.link = link;
	}
	public int getBits() {
		return bits;
	}
	public void setBits(int bits) {
		this.bits = bits;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size/1000;
	}
	
	
	
	
}
