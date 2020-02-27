package core;

import java.util.ArrayList;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.lang.reflect.Type;
import org.glassfish.jersey.client.ClientConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Word {
	String english;
	String italian;
}

public class RestDictClient {
	
    public static void main(String[] args) {
    	
    	WebTarget webTarget;
    	Invocation.Builder invocationBuilder;
    	Response response;
    	
    	String searchedWord = args[0];
    	if (!searchedWord.matches("^[a-zA-Z]+$")) {
    		System.out.println("Error: you can only use letters (a-z, A-Z)!");
    		System.exit(1);
    	}
    	 
//    	System.exit(0);
    	
    	Client client = ClientBuilder.newClient(new ClientConfig());    	

		Word word = new Word();
		word.english = searchedWord.toLowerCase();
		word.italian = "";
		System.out.println("Searching the word \"" + args[0] + "\" in the database...");
		System.out.println();	
		Gson gson = new Gson();
		String jsonWord = gson.toJson(word);
	     
	//	POST with JSON payload
		webTarget = client.target("http://sp-server.sytes.net:8080/RestDict").path("dict/uploadjson");
		invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.post(Entity.entity(jsonWord, MediaType.APPLICATION_JSON));
		String downloadedJson = response.readEntity(String.class);
	
		Type wordListType = new TypeToken<ArrayList<Word>>(){}.getType();
	    
		ArrayList<Word> wordList = gson.fromJson(downloadedJson, wordListType);
	        	
		if (wordList.size() > 1) {
	        	
		        	System.out.println("-------Search Results-------");
	        		for (int i = 0; i < wordList.size(); i++) {
	        			Word receivedWord = wordList.get(i);
	        			System.out.println(receivedWord.english + ":  " + receivedWord.italian + ".");
	        		}
	        		System.exit(0);
	       	} else {
	        		Word receivedWord = wordList.get(0);
	        		if (receivedWord.english.equals("")) {
	    			System.out.println("Sorry, the searched word is not in the database!");
	        			System.exit(0);
	    		} else {
	    			System.out.println("-------Search Results-------");
	    			System.out.println(receivedWord.english + ":  " + receivedWord.italian);
	    			System.exit(0);
	    		}
	        }
        
		
		
    	}
}
