package nl.Fornax.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Fornax
 */
public class Seeds {
	@JsonProperty("primaryseed")
	private String primarySeed;
	@JsonProperty("addressesremaining")
	private int addressesRemaining;
	@JsonProperty("allseeds")
	private List<String> allSeeds;

	public String getPrimarySeed() {
		return primarySeed;
	}

	public void setPrimarySeed(String primarySeed) {
		this.primarySeed = primarySeed;
	}

	public int getAddressesRemaining() {
		return addressesRemaining;
	}

	public void setAddressesRemaining(int addressesRemaining) {
		this.addressesRemaining = addressesRemaining;
	}

	public List<String> getAllSeeds() {
		return allSeeds;
	}

	public void setAllSeeds(List<String> allSeeds) {
		this.allSeeds = allSeeds;
	}
	
}
