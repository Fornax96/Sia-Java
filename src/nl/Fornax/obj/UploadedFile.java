package nl.Fornax.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * @author Fornax
 */
public class UploadedFile {
	@JsonProperty("Available")
	private boolean available;
	@JsonProperty("UploadProgress")
	private double uploadProgress;
	@JsonProperty("Nickname")
	private String nickname;
	@JsonProperty("Filesize")
	private long fileSize;
	@JsonProperty("TimeRemaining")
	private long timeRemaining;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getUploadProgress() {
		return uploadProgress;
	}

	public void setUploadProgress(double uploadProgress) {
		this.uploadProgress = uploadProgress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public long getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().equals(getClass())){
			return false;
		}else{
			obj = (UploadedFile) obj;
		}
		
		return hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + (this.available ? 1 : 0);
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.uploadProgress) ^ (Double.doubleToLongBits(this.uploadProgress) >>> 32));
		hash = 37 * hash + Objects.hashCode(this.nickname);
		hash = 37 * hash + (int) (this.fileSize ^ (this.fileSize >>> 32));
		hash = 37 * hash + (int) (this.timeRemaining ^ (this.timeRemaining >>> 32));
		return hash;
	}
	
	
}
