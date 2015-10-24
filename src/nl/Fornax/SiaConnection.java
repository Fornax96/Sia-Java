package nl.Fornax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fornax
 */
public class SiaConnection {

	protected final String API_IP;
	protected final int API_PORT;

	protected static final Logger logger = Logger.getLogger(SiaConnection.class.getName());

	/**
	 * Initialize a new siad connection.
	 *
	 * When no parameters are passed the default IP and port are used:
	 *
	 * @param apiIp The IP address of a running sia daemon. Must start with "http://"!!
	 * @param apiPort The port number of a running sia daemon
	 */
	public SiaConnection(String apiIp, int apiPort) {
		this.API_IP = apiIp;
		this.API_PORT = apiPort;
	}

	/**
	 * Initialize a new siad connection.
	 *
	 * When no parameters are passed the default IP and port are used: IP: http://127.0.0.1
	 * PORT: 9980
	 */
	public SiaConnection() {
		this.API_IP = "http://127.0.0.1";
		this.API_PORT = 9980;
	}

	protected URL getUrl(String method) throws MalformedURLException {
		return new URL(API_IP + ":" + API_PORT + "/" + method);
	}

	protected HttpURLConnection getHttpConnection(String method) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) getUrl(method).openConnection();
		conn.setRequestProperty("User-Agent", "Sia-Agent");
		conn.setRequestProperty("Accept", "*/*");
		return conn;
	}

	protected String getHttpResult(HttpURLConnection connection) throws IOException {
		StringBuilder response = new StringBuilder();

		try (BufferedReader br = new BufferedReader(
			new InputStreamReader(connection.getInputStream())
		)) {
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
		} catch (IOException e) {
			System.err.println("Server said this:");

			try (BufferedReader br = new BufferedReader(
				new InputStreamReader(connection.getErrorStream())
			)) {
				String line;
				while ((line = br.readLine()) != null) {
					System.err.println(line);
				}
			}

			throw e;
		}

		//System.out.println(response.toString());
		return response.toString();
	}

	protected void addPostParameters(HttpURLConnection conn, Map<String, Object> params) {
		conn.setDoOutput(true);

		StringBuilder sb = new StringBuilder();

		try (OutputStream os = conn.getOutputStream()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				sb.append("=");
				sb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
				sb.append("&");
			}

			// Remove the trailing ampersand
			sb.setLength(sb.length() - 1);

			System.out.println(sb);

			os.write(sb.toString().getBytes());
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(SiaConnection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SiaConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
