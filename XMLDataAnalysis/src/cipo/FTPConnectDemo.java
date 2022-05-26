package cipo;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class FTPConnectDemo {

	
	
	
	    public void connect() {
	        FTPClient client = new FTPClient();
	        System.out.println("invoking FTP client...");
	        try {
	            client.connect("38.117.69.16");

	            // When login success the login method returns true.
	            boolean login = client.login("cipo.customer65", "Iexaeb0wei^shi");
	            if (login) {
	                System.out.println("Login success...");

	                // When logout success the logout method returns true.
	                boolean logout = client.logout();
	                if (logout) {
	                    System.out.println("Logout from FTP server...");
	                }
	            } else {
	                System.out.println("Login fail...");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Closes the connection to the FTP server
	                client.disconnect();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	
	
	
	
}
