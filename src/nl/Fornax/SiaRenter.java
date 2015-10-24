package nl.Fornax;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.Fornax.obj.UploadedFile;

/**
 * @author Fornax
 */
public class SiaRenter extends SiaConnection{

	private static ObjectMapper mapper = new ObjectMapper();

	public SiaRenter(String apiIp, int apiPort) {
		super(apiIp, apiPort);
	}

	public SiaRenter() {
		super();
	}
	
	public List<UploadedFile> listFiles() throws IOException{
		HttpURLConnection conn = getHttpConnection("renter/files/list");
		conn.setRequestMethod("GET");
		
		return mapper.readValue(getHttpResult(conn), new TypeReference<List<UploadedFile>>(){});
	}
	
	public boolean upload(String source, String nickname) throws IOException{
		HttpURLConnection conn = getHttpConnection("renter/files/upload");
		conn.setRequestMethod("POST");
		
		Map<String, Object> params = new HashMap();
		params.put("source", source);
		params.put("nickname", nickname);
		
		addPostParameters(conn, params);
		
		System.out.println("Source: " + params.get("source"));
		System.out.println("Nickname: " + params.get("nickname"));
		
		return mapper.readTree(getHttpResult(conn)).path("success").booleanValue();
	}
}
