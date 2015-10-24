package nl.Fornax;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.Fornax.obj.Seeds;
import nl.Fornax.obj.WalletInfo;

/**
 * @author Fornax
 */
public class SiaWallet extends SiaConnection{
	private static ObjectMapper mapper = new ObjectMapper();

	public SiaWallet(String apiIp, int apiPort) {
		super(apiIp, apiPort);
	}

	public SiaWallet() {
		super();
	}
	
	public WalletInfo getInfo() throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet");
		conn.setRequestMethod("GET");
		
		return mapper.readValue(getHttpResult(conn), WalletInfo.class);
	}
	
	public String getAddress() throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/address");
		conn.setRequestMethod("GET");
		
		return mapper.readTree(getHttpResult(conn)).path("address").textValue();
	}
	
	public List<String> getAddresses() throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/addresses");
		conn.setRequestMethod("GET");
		
		System.out.println(getHttpResult(conn));
		
		return mapper.readValue(getHttpResult(conn), List.class);
	}
	
	public boolean doBackup(String filePath) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/backup");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("filepath", filePath);
		
		addPostParameters(conn, params);
		
		return mapper.readTree(getHttpResult(conn)).path("success").booleanValue();
	}
	
	public String doInit(String encryptionPassword, String dictionary) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/init");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("encryptionpassword", encryptionPassword);
		params.put("dictionary", dictionary);
		
		addPostParameters(conn, params);
		
		return mapper.readTree(getHttpResult(conn)).path("primaryseed").textValue();
	}
	
	public boolean doLoadSeed(String encryptionPassword, String dictionary, String seed) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/load/seed");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("encryptionpassword", encryptionPassword);
		params.put("dictionary", dictionary);
		params.put("seed", seed);
		
		addPostParameters(conn, params);
		
		return mapper.readTree(getHttpResult(conn)).path("success").booleanValue();
	}
	
	public boolean doLoadSiag(String encryptionPassword, String keyFiles) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/load/siag");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("encryptionpassword", encryptionPassword);
		params.put("keyfiles", keyFiles);
		
		addPostParameters(conn, params);
		
		return mapper.readTree(getHttpResult(conn)).path("success").booleanValue();
	}
	
	public boolean doLock() throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/lock");
		conn.setRequestMethod("POST");
		
		return mapper.readTree(getHttpResult(conn)).path("success").booleanValue();
	}
	
	public Seeds getSeeds(String dictionary) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/seeds");
		conn.setRequestMethod("GET");
		
		Map<String, Object> params = new HashMap();
		params.put("dictionary", dictionary);
		
		addPostParameters(conn, params);
		
		return mapper.readValue(getHttpResult(conn), Seeds.class);
	}
	
	// FIXME: Throws HTTP 400
	public List<String> sendSiaCoins(BigDecimal amount, String destination) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/siacoins");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("amount", amount.multiply(new BigDecimal(Constants.divider)));
		params.put("destination", destination);
		
		addPostParameters(conn, params);
		
		return mapper.readValue(getHttpResult(conn), List.class);
	}
	
	public List<String> sendSiaFunds(int amount, String destination) throws IOException{
		HttpURLConnection conn = getHttpConnection("wallet/saifunds");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("amount", amount);
		params.put("destination", destination);
		
		addPostParameters(conn, params);
		
		return mapper.readValue(getHttpResult(conn), List.class);
	}
}
