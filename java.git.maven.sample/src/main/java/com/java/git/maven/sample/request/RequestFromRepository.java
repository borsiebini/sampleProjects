package com.java.git.maven.sample.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.jcabi.github.Github;
import com.jcabi.http.response.JsonResponse;

public class RequestFromRepository {
	
	private String repositoryName;
	private String repositoryUser;
	private Github gitHub;
	private JsonResponse resp;
	private JsonArray arrayJson;
	private List<JsonObject> listOfJsonObjects;
	private Map<String, String> mapKeyToSha;
	private Set<Entry<String, String>> set;
	private Iterator<Entry<String, String>> it;
	private JsonObject jsonObject;
	
	public Github getGitHub() {
		return gitHub;
	}
	public void setGitHub(Github gitHub) {
		this.gitHub = gitHub;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public String getRepositoryUser() {
		return repositoryUser;
	}
	public void setRepositoryUser(String repositoryUser) {
		this.repositoryUser = repositoryUser;
	}
	
	public List<String> getAllBranches() throws IOException {
		
		return getList("/repos/%s/%s/branches");
	}// end getAllBranches method.

	public List<String> getAllTags() throws IOException {
		
		return getList("/repos/%s/%s/tags");
	}// end getAllTags method.
	
	private List<String> getList(String command) throws IOException {

		List<String> result = null;
			
		if ( gitHub == null) {
			System.out.println("MUST ESTABLISH A CONNECTION OBJECT... USER GetGitConnection TO RETRIEVE A CONNECTION OBJECT TO GIT.");
			System.exit(1);
		} else {
			String path = String.format(command, this.repositoryUser, this.repositoryName);
			resp = gitHub.entry()
					.uri().path(path)
					.back()
					.fetch()
					.as(JsonResponse.class);
			
			if ( isItJsonObject(resp)) {
				System.out.println("Failed to retrieve a JsonArray. Please make sure gitHub has the correct credentials");
				System.out.println(resp.toString());
				System.exit(3);
			} else {
				arrayJson = resp.json().readArray();
				listOfJsonObjects = null;
				
				for (int i=0; i<arrayJson.size(); i++) {
					if (  listOfJsonObjects == null)
						listOfJsonObjects = new ArrayList<JsonObject>();
					listOfJsonObjects.add(arrayJson.getJsonObject(i));
				}
				
				String key = null;
				String value = null;
				
				for (JsonObject object : listOfJsonObjects ) {
					if (result == null)
						result = new ArrayList<String>();
					
					key = object.getString("name");
					value = object.getJsonObject("commit").getString("sha");
					result.add(key);
					
					if ( this.mapKeyToSha == null)
						this.mapKeyToSha = new HashMap<String, String>();
					this.mapKeyToSha.put(key, value);
				}// end for loop.
			}
			
		}// end else-statement.
		
		return result;
	}
	
	
	public List<String> findObjectWith(String filename) throws IOException {

		if ( gitHub == null) {
			System.out.println("MUST ESTABLISH A CONNECTION OBJECT... USER GetGitConnection TO RETRIEVE A CONNECTION OBJECT TO GIT.");
			System.exit(1);
			return null;
		} else {
			return filter(this.mapKeyToSha, filename);
		}// end else-statement.
	}
	
	private List<String> filter(Map<String, String> map, String filer) throws IOException {
		set = map.entrySet();
		it = set.iterator();
		Entry<String, String> entry = null;
		List<String> result = null;
		while (it.hasNext()) {
			entry = it.next();
			String path = String.format("/repos/%s/%s/git/trees/%s", 
					this.repositoryUser, this.repositoryName, entry.getValue());
			resp = gitHub.entry()
					.uri().path(path)
					.back()
					.fetch()
					.as(JsonResponse.class);
			
			jsonObject = resp.json().readObject();
			arrayJson = jsonObject.getJsonArray("tree");
			listOfJsonObjects = null;

			for (int i=0; i<arrayJson.size(); i++) {
				if (  listOfJsonObjects == null)
					listOfJsonObjects = new ArrayList<JsonObject>();
				listOfJsonObjects.add(arrayJson.getJsonObject(i));
			}

			String filePath = null;
			boolean hasIt = false;
			for (JsonObject object : listOfJsonObjects ) {
				filePath = object.getString("path");

				if (filePath.equals(filer)) {
					hasIt = true;
					break;
				}
			}// end for loop.

			if ( !hasIt) {
				if ( result == null)
					result= new ArrayList<String>();
				result.add(entry.getKey());
			}
			
		}// end while loop.
		
		return result;
	}
	
	private boolean isItJsonObject(JsonResponse resp) {
		boolean result = false;
		try {
			this.jsonObject = resp.json().readObject();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	

}
