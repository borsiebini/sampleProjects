package com.java.git.maven.sample.request.test;

import java.io.IOException;
import java.util.List;

import com.java.git.maven.sample.connect.GetGitConnection;
import com.java.git.maven.sample.request.RequestFromRepository;
import com.jcabi.github.Github;

import junit.framework.TestCase;

public class RequestFromRepositoryTest extends TestCase {

	private Github gitHub;
	private RequestFromRepository request;
	private String repoName;
	private String userRepo;

	protected void setUp() throws Exception {
		super.setUp();
		repoName = "";
		userRepo = "";
		gitHub = GetGitConnection.getGitHub();
    	request = new RequestFromRepository();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
	}

	public void testGetGitHub() {
		assertSame(gitHub, request.getGitHub());
	}

	public void testSetGitHub() {
		gitHub = GetGitConnection.getGitHub();
		request.setGitHub(gitHub);
		assertSame(gitHub, request.getGitHub());
	}

	public void testGetRepositoryName() {
		assertSame(repoName, request.getRepositoryName());
	}

	public void testSetRepositoryName() {
		repoName = "ruby";
		request.setRepositoryName(repoName);
		assertSame(repoName, request.getRepositoryName());
	}

	public void testGetRepositoryUser() {
		assertSame(userRepo, request.getRepositoryUser());
	}

	public void testSetRepositoryUser() {
		userRepo = "ruby";
		request.setRepositoryUser(userRepo);
		assertSame(userRepo, request.getRepositoryUser());
	}

	public void testGetAllBranches() {
		System.out.println("*** Start testGetAllBranches ***");
		userRepo = "ruby";
    	repoName = "ruby";
    	GetGitConnection.establishConnection();
    	gitHub = GetGitConnection.getGitHub();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
    	
    	try {
    		System.out.println("Acces get ALl branches");
			List<String> list = request.getAllBranches();
			System.out.println("*** End testGetAllBranches ***");
			assertNotNull(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("Error: "+e.getMessage());
			e.printStackTrace();
		}
    	
	}

	public void testGetAllTags() {
		userRepo = "ruby";
    	repoName = "ruby";
    	GetGitConnection.establishConnection();
    	gitHub = GetGitConnection.getGitHub();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
    	
    	try {
			List<String> list = request.getAllTags();
			System.out.println("*** End testGetAllTags *** ");
			assertNotNull(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}

	public void testFindObjectWith() {
		System.out.println("*** Start testFindObjectWith ***");
		userRepo = "ruby";
    	repoName = "ruby";
    	GetGitConnection.establishConnection();
    	gitHub = GetGitConnection.getGitHub();
    	request.setGitHub(gitHub);
    	request.setRepositoryName(repoName);
    	request.setRepositoryUser(userRepo);
    	
		try {
			request.getAllBranches();
			List<String> list = request.findObjectWith(".travis.yml");
			System.out.println("*** End testFindObjectWith ***");
			assertNotNull(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("Error: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
