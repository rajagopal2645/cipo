package cipo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translator {
	
	
	public static void Translate(String translatestring)
	{
		//String translatestring = "Tourbillons, mouvements rotatifs, tornade -- Note: Non compris les orbites astronomiques (1.13.1), les orbites atomiques (1.13.1), les cercles concentriques (26.1.4 ou 26.1.5) et les spirales (26.1.5).";
	    translatestring = encodeValue(translatestring);
		String httprequeststring = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200424T214035Z.326aa634301fe0f9.0a28585faa04ea5e86aa45105f345ccb804fdbcd&lang=fr-en&text="+translatestring;
	
	    MakeHttpRequest req = new MakeHttpRequest();
	    System.out.println(httprequeststring);
	    try 
	    {
			System.out.println(req.getHTML(httprequeststring));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}
	
	
	private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
	
    
}
