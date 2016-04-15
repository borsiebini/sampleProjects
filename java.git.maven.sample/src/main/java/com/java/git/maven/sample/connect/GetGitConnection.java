package com.java.git.maven.sample.connect;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;

public class GetGitConnection {

	private static Github gitHub;

	public static void establishConnection() {
		gitHub = new RtGithub();
	}
	
	public static Github getGitHub() {
		return gitHub;
	}
	
	public static void establishConnection(String auth) {
		gitHub = new RtGithub(auth);
	}
}
