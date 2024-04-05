package restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TestClient {
	private static final String _SEC_PROTOCOL = "TLSv1.2";

	public static void main(String[] args) {

		try {
			java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			URL url = new URL("https://10.107.3.10/bpocore/market/api/v1/resources?resourceTypeId=nfv2.resourceTypes.NsInfo&obfuscate=true&offset=0&limit=1000");
//            URL url = new URL("https://10.107.3.10/bpocore/market/api/v1/resources?resourceTypeId=nfv2.resourceTypes.NsInfo&obfuscate=true&offset=0&limit=1000");
//            NfvoData nfvo = new NfvoData();
//            nfvo.createSecureSocketConnection(_SEC_PROTOCOL);

//            String user = "admin";
//            String password = "adminpw";
//            String authToken = doAuth( user, password, "" );
			System.out.println("start ....");
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			System.out.println("start connection ....");

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer e4737ed747c719f93b64");
            
            System.out.println("Header-Accept: " + con.getRequestProperty("Accept"));
            System.out.println("Header-Authorization: " + con.getRequestProperty("Authorization"));
            System.out.println("Header req method: " + con.getRequestMethod());
//			con.setRequestProperty("authorization",
//					"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJOZXRPbW5pYSBBcHAgc2VydmVyIiwiYXVkIjoiTmV0T21uaWEgQW5ndWxhciBKUyBDbGllbnQiLCJleHAiOjE1OTg1MTg0NzMsImp0aSI6ImFWcFNFTUp4TWJKYm4tdFBrTnVQeGciLCJpYXQiOjE1OTU5MjY0NzMsIm5iZiI6MTU5NTkyNjQxMywiYWNsVGFnIjoxLCJzdWIiOiJOZXRPbW5pYSB1c2VyIiwibmFtZSI6ImNlbnRpbmEiLCJ1aWQiOiJjZW50aW5hQDo6ZmZmZjoxMC44MS4yMzQuMjM1OlJTQDgzN0UwNjgzQzNFOTY0REYwMjYxMUQ4NzRDRkJCMEE1QENocm9tZSA4NC4wLjQxNDciLCJyb2xlcyI6WyJPcGVyYXRvciIsIkFkbWluIiwiTmV0d29yayBhZG1pbiJdLCJwcmVmZXJyZWRMYW5ndWFnZSI6IkVuZ2xpc2giLCJhY2xJZHMiOm51bGwsInVzZXJHcm91cHMiOltdLCJkb21haW5TcGVjaWZpYyI6ZmFsc2UsImRvbWFpbklkIjpudWxsLCJkb21haW5OYW1lIjpudWxsLCJsaW1pdGVkQWNjZXNzIjpmYWxzZSwiZWZmZWN0aXZlQWNsVGFnIjoxLCJsaW1pdGVkU3VibmV0cyI6bnVsbCwibGltaXRlZE5vZGVzIjpudWxsLCJ0aW1lWm9uZSI6IkFzaWEvS29sa2F0YSIsInRpbWVGb3JtYXQiOiJFRUUgTU1NIGRkIEhIOm1tOnNzIHogeXl5eSIsImN1c3RvbWVySWQiOm51bGx9.7DIJJrg-63ma9Eoiy4h4CsyH6GDR5galZ8dppJiTuKY");

            System.out.println("Response: " + con.getResponseMessage());
            System.out.println("URL: " + con.getURL());
			System.out.println(con.getResponseCode() + "end connection ....");

//            if( authToken != null )
//                con.setRequestProperty( "X-Auth-Token", authToken );
//            if (con.getResponseCode() == 200) {
//            System.out.println("success");
//            }

			if (con.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			con.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
