package com.juliasoft.sonarqube.plugin;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class SimpleTest {

	/*@Test
	public void testRecursive() {
	  JavaCheckVerifier.verify("src/test/files/TestRecursive.java", new AvoidRecursiveCallsPlugin());
	}*/

	@Test
	public void testGandalf() {
	  JavaCheckVerifier.verify("src/test/files/TestGandalf.java", new GandalfRulePlugin());
	  
	}
}
