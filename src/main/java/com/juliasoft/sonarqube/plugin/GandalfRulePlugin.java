package com.juliasoft.sonarqube.plugin;


import com.google.common.collect.ImmutableList;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
//import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
//import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import java.util.List;

@Rule(
		  key = "GandalfRulePlugin",
		  name = "More than 2 nested loops",
		  description = "Raises a warning if it finds more than 2 nested loops",
		  priority = Priority.INFO,
		  tags = {"style"})

public class GandalfRulePlugin extends IssuableSubscriptionVisitor {
	@Override
	public List<Kind> nodesToVisit() {
		final Kind[] LOOP_STATEMENTS = {
				Kind.FOR_STATEMENT, 
				Kind.FOR_EACH_STATEMENT,
				Kind.WHILE_STATEMENT,
				Kind.DO_STATEMENT
		};
		return ImmutableList.copyOf(LOOP_STATEMENTS);
	}
  
	private static boolean isLoopStatement(StatementTree stat){
		return stat.is(Kind.FOR_STATEMENT) || stat.is(Kind.FOR_EACH_STATEMENT) || stat.is(Kind.WHILE_STATEMENT) || stat.is(Kind.DO_STATEMENT);
	}
	
	@Override
	public void visitNode(Tree tree) {
		int nLoops = 0;
		StatementTree myParent = (StatementTree) tree; //inizializzo il parent uguale a me stesso
		boolean methodsFound = false;
		
		while(methodsFound == false){
			if(isLoopStatement(myParent)){
				nLoops++;
			}
		    if(myParent.parent().is(Kind.BLOCK)){ //se mio "padre" è di tipo BLOCK controllo il "nonno"
				if(myParent.parent().parent().is(Kind.METHOD)){ //se mio "nonno" è di tipo METHOD mi devo fermare, ho analizzato tutto il metodo
					methodsFound = true;
				}
				else{ //se mio "nonno" non è un METHOD devo continuare ad adanizzare
					myParent = (StatementTree) myParent.parent().parent();
				}
			}
			else{ //se mio "padre" non è di tipo block potrebbe essere o "LOOP_STATEMENT" o "IF_STATEMENT"
				myParent = (StatementTree) myParent.parent();
			}
		}
		if(nLoops>2){
			reportIssue(tree, "More than 2 nested loop");
		}
	}
}
