package restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author umadduri
 */
public class BpuaaClientRestService {

	private static final String _SEC_PROTOCOL = "TLSv1.2";

	public static void main(String[] args) {
		
		final Logger log = Logger.getLogger(BpuaaClientRestService.class.getName());
		log.setLevel(Level.ALL);
		
		try {
			java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			URL url = new URL(
					"https://10.107.3.10/bpocore/market/api/v1/resources?resourceTypeId=nfv2.resourceTypes.NsInfo&obfuscate=true&offset=0&limit=1");
//			URL url = new URL(
//					"https://10.186.32.11/bpocore/market/api/v1/resource-types?limit=1");
            NfvoData nfvo = new NfvoData();

            nfvo.createSecureSocketConnection(_SEC_PROTOCOL);

//            String user = “admin”;
//            String password = “adminpw”;
//            String authToken = doAuth( user, password, “” );
            
            String basicAuth = "Token e4737ed747c719f93b64";
//            String basicAuth = "token 8bb70034e8aa8ec001ae";
            byte[] encodedAuth= Base64.getEncoder().encode(basicAuth.getBytes());
            
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", basicAuth);
			
			
			
			System.out.println("basicAuth: " + basicAuth);
			System.out.println("encodedAuth: " + encodedAuth.toString());
            System.out.println("Header-Accept: " + con.getRequestProperty("Accept"));
            System.out.println("Header-Authorization: " + con.getRequestProperty("Authorization"));
            System.out.println("Header req method: " + con.getRequestMethod());
            
            System.out.println("================");
            System.out.println(con.getRequestProperties());


//            if( authToken != null )
//                con.setRequestProperty( “X-Auth-Token”, authToken );
//            if (con.getResponseCode() == 200) {
//            System.out.println(“success”);
//            }
            
            System.out.println("ResponseMessage: " + con.getResponseMessage());
            System.out.println("Response: " + con.getErrorStream());

//			if (con.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
//			}

            System.out.println("Test123--");
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println("Response is test123:  " + output);
			}

			con.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}