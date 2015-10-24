package nl.Fornax.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import nl.Fornax.Constants;

/**
 * @author Fornax
 */
public class WalletInfo {
	@JsonProperty("encrypted")
	private boolean encrypted;
	@JsonProperty("unlocked")
	private boolean unlocked;
	@JsonProperty("confirmedsiacoinbalance")
	private BigDecimal confirmedSiaCoinBalance;
	@JsonProperty("unconfirmedoutgoingsiacoins")
	private BigDecimal unconfirmedOutgoingSiacoins;
	@JsonProperty("unconfirmedincomingsiacoins")
	private BigDecimal unconfirmedIncomingSiacoins;
	@JsonProperty("siafundbalance")
	private int siaFundBalance;
	@JsonProperty("siacoinclaimbalance")
	private BigDecimal siacoinClaimBalance;

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public BigDecimal getConfirmedSiaCoinBalance() {
		return confirmedSiaCoinBalance;
	}

	public void setConfirmedSiaCoinBalance(BigDecimal confirmedSiaCoinBalance) {
		this.confirmedSiaCoinBalance = confirmedSiaCoinBalance.divide(
			new BigDecimal(Constants.divider)
		);
	}

	public BigDecimal getUnconfirmedOutgoingSiacoins() {
		return unconfirmedOutgoingSiacoins;
	}

	public void setUnconfirmedOutgoingSiacoins(BigDecimal unconfirmedOutgoingSiacoins) {
		this.unconfirmedOutgoingSiacoins = unconfirmedOutgoingSiacoins.divide(
			new BigDecimal(Constants.divider)
		);
	}

	public BigDecimal getUnconfirmedIncomingSiacoins() {
		return unconfirmedIncomingSiacoins;
	}

	public void setUnconfirmedIncomingSiacoins(BigDecimal unconfirmedIncomingSiacoins) {
		this.unconfirmedIncomingSiacoins = unconfirmedIncomingSiacoins.divide(
			new BigDecimal(Constants.divider)
		);
	}

	public int getSiaFundBalance() {
		return siaFundBalance;
	}

	public void setSiaFundBalance(int siaFundBalance) {
		this.siaFundBalance = siaFundBalance;
	}

	public BigDecimal getSiacoinClaimBalance() {
		return siacoinClaimBalance;
	}

	public void setSiacoinClaimBalance(BigDecimal siacoinClaimBalance) {
		this.siacoinClaimBalance = siacoinClaimBalance.divide(
			new BigDecimal(Constants.divider)
		);
	}
}
