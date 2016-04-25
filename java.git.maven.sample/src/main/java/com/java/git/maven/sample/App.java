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
		
		String userRepo = "ruby";
    	String repoName = "ruby";
    	
    	GetGitConnection.establishConnection();
    	
    	gitHub = GetGitConnection.getGitHub();
    	request = new RequestFromRepository();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
    	
    	List<String> branches = request.getAllBranches();
    	for (String s : branches) {
    		System.out.println("Branch: "+s);
    	}// end loop.
    	
    	System.out.println();
    	
    	List<String> list = request.findObjectWith(".travis.yml");
    	
    	for ( String s : list) {
    		System.out.println("Branch: "+s+" does not have .travis.yml");
    	}// end loop
    	

    	System.out.println();
    	System.out.println();
    	
    	List<String> tags = request.getAllTags();
    	for ( String s: tags) {
    		System.out.println("Tag: "+s);
    	}// end loop.
    	
    	System.out.println();
    	
    	list = request.findObjectWith(".travis.yml");
    	
    	for ( String s : list) {
    		System.out.println("Tag: "+s+" does not have .travis.yml");
    	}// end loop.
    	
    }// end main.
	
	
	
	
}// end App.
