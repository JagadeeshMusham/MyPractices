package restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

public class testClient1 {
	public static void main(String args[]) {
		try {

			final String _SEC_PROTOCOL = "TLSv1.2";
			NfvoData nfvo = new NfvoData();
			nfvo.createSecureSocketConnection(_SEC_PROTOCOL);

			URL myURL = new URL(
					"https://10.107.3.10/bpocore/market/api/v1/resources?resourceTypeId=nfv2.resourceTypes.NsInfo&obfuscate=true&offset=0&limit=1000");
			HttpURLConnection myURLConnection;
			myURLConnection = (HttpURLConnection) myURL.openConnection();

			String userCredentials = "admin:adminpw";
			String basicAuth = "Bearer " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

			myURLConnection.setRequestProperty("Authorization", basicAuth);
			myURLConnection.setRequestMethod("GET");
			myURLConnection.setRequestProperty("Content-Type", "application/json");
//			myURLConnection.setRequestProperty("Content-Length", "" + postData.getBytes().length);
			myURLConnection.setRequestProperty("Content-Language", "en-US");
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);

			System.out.println("Header-Accept: " + myURLConnection.getRequestProperty("Accept"));
			System.out.println("Header-Authorization: " + myURLConnection.getRequestProperty("Authorization"));
			System.out.println("Header req method: " + myURLConnection.getRequestMethod());

			System.out.println("================");
			System.out.println(myURLConnection.getRequestProperties());

			if (myURLConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + myURLConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((myURLConnection.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			myURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
