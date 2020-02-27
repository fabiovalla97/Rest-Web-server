package it.unige.cipi.RC;


import java.io.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.google.gson.Gson;


@Path("/dict")
public class Main {
	
		@GET
		public Response getMsg() {
			String output = "This is a dictionary! Write an english word in the URL for its translation.";
		    return Response.status(200).entity(output).build();		    
		}
		
		@GET
	    @Path("/{param}")
	    public Response getMsg(@PathParam("param") String msg) throws FileNotFoundException, IOException {
			
			Dict d = new Dict();
			d.loadDictionary();
			ArrayList<Word> searchResults = d.dictionary.get(msg);
			
			String output = "";
			if (searchResults == null) {
				output += "Sorry, the searched word is not in the database!";
			} else {
				output += "<p><b>-------Search Results-------</b></p>";
				for(int i = 0; i < searchResults.size(); i++) {
					Word w = searchResults.get(i);
					output += "<p>" + w.english + ":  " + w.italian + "</p>";
				}
			}			
	        return Response.status(200).entity(output).build();
	        
	    }

//	    @PUT
//	    @Path("/{param}")
//	    public Response putMsg(@PathParam("param") String msg) {
//	        String output = "PUT with Param.: Answer - " + msg;
//	        return Response.status(200).entity(output).build();
//	    }

//	    @DELETE
//	    @Path("/{param}")
//	    public Response deleteMsg(@PathParam("param") String msg) {
//	        String output = "DELETE with Param: Answer - " + msg;
//	        return Response.status(200).entity(output).build();
//	    }
	    
//	    @POST
//	    @Path("/{param}")
//	    public Response postMsg(@PathParam("param") String msg) {
//	        String output = "POST with Param.: Answer - " + msg;
//	        return Response.status(200).entity(output).build();
//	    }

//	    @POST
//	    @Path("/uploadtext")
//	    @Consumes(MediaType.TEXT_PLAIN)
//	    public Response postStrMsg(String msg) {
//	        String output = "POST with Uploaded String Body: Answer - " + msg;
//	        return Response.status(200).entity(output).build();
//	    }
	    
	    @POST
	    @Path("/uploadjson")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response postJsonMsg(String msg) throws FileNotFoundException, IOException {
			Gson gson = new Gson();
			Word w = gson.fromJson(msg, Word.class);
			String key =w.key;
			
			if (w.english.equals("")) {
				String output = "This is a dictionary! Submit an english word for its translation.";
			    return Response.status(200).entity(output).build();
			}
			
			if(!w.keyControl(key)) {
				String output = "The key is not in our db. \nVisit our site sp-server.sytes.net \nto get your key.";
			    return Response.status(200).entity(output).build();
			}
			
			// Searching
			Dict d = new Dict();
			d.loadDictionary();
			ArrayList<Word> searchResults = d.dictionary.get(w.english);
			/*if (searchResults != null) {
				for(int i=0; i<searchResults.size(); i++) {
					searchResults.get(i).setKey(key);	
				}
			}*/
			
			if (searchResults == null) {
				Word word = new Word();
				word.english = "";
				word.italian = "";
				searchResults = new ArrayList<Word>();
				searchResults.add(word);
			}
			String backtojson = gson.toJson(searchResults);
	        return Response.status(200).entity(backtojson).build();
	    }
	}
