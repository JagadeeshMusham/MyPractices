/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author hvarakal
 */
public class NfvoData {

    public void createSecureSocketConnection(String TLSProtocol) {
        try {
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };

            TrustManager[] trustAllCerts = new TrustManager[1];
            TrustManager tm = new TrustAllTrustManager();
            trustAllCerts[ 0] = tm;

            /*
             Create the SSL context
             */
            SSLContext sc = SSLContext.getInstance(TLSProtocol);

            /*
             Create the session context
             */
            SSLSessionContext sslsc = sc.getServerSessionContext();

            /*
             Initialize the contexts; the session context takes the trust manager.
             */
            sslsc.setSessionTimeout(0);
            sc.init(null, trustAllCerts, null);

            /*
             Use the default socket factory to create the socket for the secure connection
             */
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            /*
             Set the default host name verifier to enable the connection.
             */
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            HttpsURLConnection.setFollowRedirects(true);
        } catch (NoSuchAlgorithmException nsae) {
//            log.error( "ERROR: NoSuchAlgorithmException: " + nsae );
            System.out.println("ERROR: NoSuchAlgorithmException: \n");
            nsae.printStackTrace();
        } catch (KeyManagementException kme) {
//            log.error( "ERROR: KeyManagementException: " + kme );
            System.out.println("ERROR: KeyManagementException: \n");
            kme.printStackTrace();
        } catch (Exception kme) {
//            log.error( "ERROR: Exception: " + kme );
            System.out.println("ERROR: Exception: \n");
            kme.printStackTrace();
        }
    }

    class TrustAllTrustManager
            implements TrustManager, X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }

}
