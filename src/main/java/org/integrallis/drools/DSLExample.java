package org.integrallis.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DSLExample {

	public static final void main(String[] args) {
		try {
			// 1 - load the rules in to a knowledge builder
			KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			
			knowledgeBuilder.add(ResourceFactory.newClassPathResource("say_something.dsl"), ResourceType.DSL);
			knowledgeBuilder.add(ResourceFactory.newClassPathResource("rockys_rules.dslr"), ResourceType.DSLR);			
			
			KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error: errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}
			// 2 - create a knowledge base using a knowledge builder
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
			
			// 3 - create a stateful knowledge session
			StatefulKnowledgeSession knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
			
			// create a logger for the knowledge session
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(knowledgeSession, "test");
			
			// 4 - create and assert some facts
			Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
			
			knowledgeSession.insert(rocky);
		
			// 5 - fire the rules
			knowledgeSession.fireAllRules();
			
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}