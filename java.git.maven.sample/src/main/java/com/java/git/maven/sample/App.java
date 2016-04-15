package com.java.git.maven.sample;

import java.io.IOException;
import java.util.List;

import com.java.git.maven.sample.connect.GetGitConnection;
import com.java.git.maven.sample.request.RequestFromRepository;
import com.jcabi.github.Github;

/**
 * Hello world!
 *
 */
public class App
{
    private static Github gitHub;
	private static RequestFromRepository request;

	public static void main( String[] args ) throws IOException
    {
    	
    	String auth = "8040c7ad7b09cfc17e0255d3e7d56095f4eb1739";
    	String userRepo = "ruby";
    	String repoName = "ruby";
    	
    	GetGitConnection.establishConnection(auth);
    	gitHub = GetGitConnection.getGitHub();
    	request = new RequestFromRepository();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
    	
    	request.getAllBranches();
    	List<String> branches = request.getListOfAllBranches();
    	for (String s : branches) {
    		System.out.println("Branch: "+s);
    	}
    	
    	System.out.println();
    	System.out.println();
    	
    	request.getAllTags();
    	List<String> tags = request.getListOfAllTags();
    	for ( String s: tags) {
    		System.out.println("Tag: "+s);
    	}
    	
    	
    }
}
