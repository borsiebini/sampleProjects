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
	private List<String> listOfAllBranches;
	private List<String> listOfAllTags;
	private List<String> listOfSetOfBranches;
	private List<String> listOfSetOfTags;
	private Github gitHub;
	private JsonResponse resp;
	private JsonArray arrayJson;
	private List<JsonObject> listOfJsonObjects;
	private Map<String, String> mapBranchToSha;
	private Map<String, String> mapTagToSha;
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
	public List<String> getListOfAllBranches() {
		return listOfAllBranches;
	}
	public void setListOfAllBranches(List<String> listOfAllBranches) {
		this.listOfAllBranches = listOfAllBranches;
	}
	public List<String> getListOfAllTags() {
		return listOfAllTags;
	}
	public void setListOfAllTags(List<String> listOfAllTags) {
		this.listOfAllTags = listOfAllTags;
	}
	public List<String> getListOfSetOfBranches() {
		return listOfSetOfBranches;
	}
	public void setListOfSetOfBranches(List<String> listOfSetOfBranches) {
		this.listOfSetOfBranches = listOfSetOfBranches;
	}
	public List<String> getListOfSetOfTags() {
		return listOfSetOfTags;
	}
	public void setListOfSetOfTags(List<String> listOfSetOfTags) {
		this.listOfSetOfTags = listOfSetOfTags;
	}
	
	public void getAllBranches() throws IOException {
		if ( gitHub == null) {
			System.out.println("MUST ESTABLISH A CONNECTION OBJECT... USER GetGitConnection TO RETRIEVE A CONNECTION OBJECT TO GIT.");
			System.exit(1);
		} else {
			String path = String.format("/repos/%s/%s/branches", this.repositoryUser, this.repositoryName);
			resp = gitHub.entry()
					.uri().path(path)
					.back()
					.fetch()
					.as(JsonResponse.class);
			
			if (isItJsonObject(resp) ) {
				System.out.println("Failed to retrieve a JsonArray. Please make sure gitHub has the correct credentials");
				System.out.println(resp.toString());
				System.exit(2);
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
					if (this.listOfAllBranches == null)
						this.listOfAllBranches = new ArrayList<String>();
					
					key = object.getString("name");
					value = object.getJsonObject("commit").getString("sha");
					this.listOfAllBranches.add(key);
					
					if (this.mapBranchToSha == null)
						this.mapBranchToSha = new HashMap<String, String>();
					this.mapBranchToSha.put(key, value);
					
				}// end for loop.
			}
			

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
				if (this.listOfAllBranches == null)
					this.listOfAllBranches = new ArrayList<String>();
				
				key = object.getString("name");
				value = object.getJsonObject("commit").getString("sha");
				this.listOfAllBranches.add(key);
				
				if (this.mapBranchToSha == null)
					this.mapBranchToSha = new HashMap<String, String>();
				this.mapBranchToSha.put(key, value);
				
			}// end for loop.
			
		
		}// end else-statement.
		
	}// end getAllBranches method.

	public void getAllTags() throws IOException {

		if ( gitHub == null) {
			System.out.println("MUST ESTABLISH A CONNECTION OBJECT... USER GetGitConnection TO RETRIEVE A CONNECTION OBJECT TO GIT.");
			System.exit(1);
		} else {
			String path = String.format("/repos/%s/%s/tags", this.repositoryUser, this.repositoryName);
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
					if (this.listOfAllTags == null)
						this.listOfAllTags = new ArrayList<String>();
					
					key = object.getString("name");
					value = object.getJsonObject("commit").getString("sha");
					this.listOfAllTags.add(key);
					
					if ( this.mapTagToSha == null)
						this.mapTagToSha = new HashMap<String, String>();
					this.mapTagToSha.put(key, value);
				}// end for loop.
			}
			
			
		}// end else-statement.
		
	}// end getAllTags method.
	
	public void findObjectWith(String filename, String selection) throws IOException {

		if ( gitHub == null) {
			System.out.println("MUST ESTABLISH A CONNECTION OBJECT... USER GetGitConnection TO RETRIEVE A CONNECTION OBJECT TO GIT.");
			System.exit(1);
		} else {
			if ( selection.equals("BRANCH")) {
				filter(this.mapBranchToSha, filename);
			} else {
				filter(this.mapTagToSha, filename);
			}
			
		}// end else-statement.
	}
	
	private void filter(Map<String, String> map, String filer) throws IOException {
		set = map.entrySet();
		it = set.iterator();
		Entry<String, String> entry = null;
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
				if ( this.listOfSetOfBranches == null)
					this.listOfSetOfBranches = new ArrayList<String>();
				this.listOfSetOfBranches.add(entry.getKey());
			}
			
			
//			if ( isItJsonObject(resp) ) {
//			
//				jsonObject = resp.json().readObject();
//				arrayJson = jsonObject.getJsonArray("tree");
//				listOfJsonObjects = null;
//
//				for (int i=0; i<arrayJson.size(); i++) {
//					if (  listOfJsonObjects == null)
//						listOfJsonObjects = new ArrayList<JsonObject>();
//					listOfJsonObjects.add(arrayJson.getJsonObject(i));
//				}
//
//				String filePath = null;
//				boolean hasIt = false;
//				for (JsonObject object : listOfJsonObjects ) {
//					filePath = object.getString("path");
//
//					if (filePath.equals(filer)) {
//						hasIt = true;
//						break;
//					}
//				}// end for loop.
//
//				if ( !hasIt) {
//					if ( this.listOfSetOfBranches == null)
//						this.listOfSetOfBranches = new ArrayList<String>();
//					this.listOfSetOfBranches.add(entry.getKey());
//				}
//			
//			} else {
//				System.out.println("Failed to retrieve JsonObject ");
//				System.out.println(resp);
//				System.exit(4);
//			}
			
		}// end while loop.
	}
	
	
	private void printMap(Map<String, String> map) {
		set = map.entrySet();
		it = set.iterator();
		Entry<String, String> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			System.out.printf("Key: %s  Value: %s \n", entry.getKey(), entry.getValue());
		}
	}
	
	private boolean isItJsonObject(JsonResponse resp) {
		boolean result = false;
		try {
			resp.json().readObject();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	

}
