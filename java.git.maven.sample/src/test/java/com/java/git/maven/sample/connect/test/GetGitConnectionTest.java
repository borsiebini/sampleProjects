package com.java.git.maven.sample.connect.test;

import com.java.git.maven.sample.connect.GetGitConnection;
import com.jcabi.github.Github;

import junit.framework.TestCase;

public class GetGitConnectionTest extends TestCase {

	
	private Github gitConnect;

	public void testEstablishConnection() {
		GetGitConnection.establishConnection();
		gitConnect = GetGitConnection.getGitHub();
		assertNotNull(gitConnect);
		
	}

	public void testEstablishConnectionString() {
		String auth="";
		GetGitConnection.establishConnection(auth);
		gitConnect = GetGitConnection.getGitHub();
		assertNotNull(gitConnect);
		
	}

}
