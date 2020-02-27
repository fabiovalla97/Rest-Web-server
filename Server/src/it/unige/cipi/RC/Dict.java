package it.unige.cipi.RC;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

class Word {
	String english;
	String italian;
	String key;
	

	public void setKey(String key) {
		this.key=key;
	}
	
	public boolean keyControl(String key){  
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost/sp-server","root","password");  
			//here sp-server is database name, root is username and password the password 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select keyy from users");  
			while(rs.next()) {
				if(rs.getString(1).equals(key)) {
					return true;
				}
			}
			con.close();
			return false;
		}catch(Exception e){ return false;}  
	}
}

public class Dict {
	
	public HashMap<String,ArrayList<Word>> dictionary;

	public Dict() {
		this.dictionary = new HashMap<String,ArrayList<Word>>();
	}
	
	
    public void loadDictionary() throws FileNotFoundException, IOException {
    	
        BufferedReader reader = new BufferedReader(new FileReader("/opt/tomcat/apache-tomcat-9.0.30/webapps/RestDict/WEB-INF/Italian.txt"));
        for (int i = 0; i < 10; i++) {
        	reader.readLine();
        }
        
        String line = reader.readLine();
        while (line != null) {
        	
        	String[] fields = line.split("\\s+");
        	Word w = new Word();
        	w.english = fields[0].toLowerCase();
        	w.italian = "";
            for (int i = 1; i < fields.length; i++) {
            	w.italian += fields[i] + " ";
            }
            w.italian = w.italian.trim().toLowerCase();
            System.out.println(w.italian);
            ArrayList<Word> arrayList = this.dictionary.get(w.english);            
            if (arrayList == null) {
            	arrayList = new ArrayList<Word>();
            }
            arrayList.add(w);
            this.dictionary.put(w.english, arrayList);
            
            line = reader.readLine();
            
        }        
        reader.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	Dict d = new Dict();
    	d.loadDictionary();
    }

}
