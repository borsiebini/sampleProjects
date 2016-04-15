package com.java.git.maven.sample.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			
			arrayJson = resp.json().readArray();
			listOfJsonObjects = null;
			
			for (int i=0; i<arrayJson.size(); i++) {
				if (  listOfJsonObjects == null)
					listOfJsonObjects = new ArrayList<JsonObject>();
				listOfJsonObjects.add(arrayJson.getJsonObject(i));
			}
			
			for (JsonObject object : listOfJsonObjects ) {
				if (this.listOfAllBranches == null)
					this.listOfAllBranches = new ArrayList<String>();
				this.listOfAllBranches.add(object.getString("name"));
			}
			
			
		}
		
		
		
	}

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
			
			arrayJson = resp.json().readArray();
			listOfJsonObjects = null;
			
			for (int i=0; i<arrayJson.size(); i++) {
				if (  listOfJsonObjects == null)
					listOfJsonObjects = new ArrayList<JsonObject>();
				listOfJsonObjects.add(arrayJson.getJsonObject(i));
			}
			
			for (JsonObject object : listOfJsonObjects ) {
				if (this.listOfAllTags == null)
					this.listOfAllTags = new ArrayList<String>();
				this.listOfAllTags.add(object.getString("name"));
			}
			
			
		}
		
	}
	

}
