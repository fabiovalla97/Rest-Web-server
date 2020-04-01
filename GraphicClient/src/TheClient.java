import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.lang.reflect.Type;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.glassfish.jersey.client.ClientConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


class Word {
	String english;
	String italian;
	String key;
}

public class TheClient {
	
	public String activeClient(String args) {
   	
	WebTarget webTarget;
	Invocation.Builder invocationBuilder;
	Response response;
	
	String searchedWord = args;
	if (!searchedWord.matches("^[a-zA-Z]+$")) {
		System.out.println("Error: you can only use letters (a-z, A-Z)!");
		System.exit(1);
	}
	 
	
	Client client = ClientBuilder.newClient(new ClientConfig());    	

	Word word = new Word();
	word.english = searchedWord.toLowerCase();
	word.italian = "";
	word.key="J4x0tFT3SCwziiprZBtF92cWNJBXwIWFiSs2Gx";
	System.out.println();	
	Gson gson = new Gson();
	String jsonWord = gson.toJson(word);
     
//	POST with JSON payload
	webTarget = client.target("http://sp-server.sytes.net:8080/RestDict-key").path("dict/uploadjson");
	invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
	response = invocationBuilder.post(Entity.entity(jsonWord, MediaType.APPLICATION_JSON));
	String downloadedJson = response.readEntity(String.class);
	return downloadedJson;

	}
}
