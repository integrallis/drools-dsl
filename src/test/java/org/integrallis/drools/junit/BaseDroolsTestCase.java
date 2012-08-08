package org.integrallis.drools.junit;

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
import org.junit.After;
import org.junit.Before;

public abstract class BaseDroolsTestCase {
	private static KnowledgeBase knowledgeBase;
	private KnowledgeRuntimeLogger logger;
	protected StatefulKnowledgeSession knowledgeSession;
	
	public BaseDroolsTestCase(String drlFile) { readRules(drlFile); }
	public BaseDroolsTestCase(String dslrFile, String dslFile) { readRules(dslrFile, dslFile); }
	public BaseDroolsTestCase(String dslrFile, String dslFile, String rfmFile) { readRules(dslrFile, dslFile, rfmFile); }
	
	@Before
	public void setUp() throws Exception {
		knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
		logger = KnowledgeRuntimeLoggerFactory.newConsoleLogger(knowledgeSession);
	}
	
	@After
	public void tearDown() throws Exception {
		logger.close();
		knowledgeSession.dispose();
	}
	
	protected static KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}
	
	private static void readRules(String drlFile) {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(drlFile), ResourceType.DRL);
		
		buildKnowledgeBase(knowledgeBuilder);	
	}
	
	private static void readRules(String dslrFile, String dslFile) {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(dslFile), ResourceType.DSL);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(dslrFile), ResourceType.DSLR);
		
		buildKnowledgeBase(knowledgeBuilder);	
	}
	
	private static void readRules(String dslrFile, String dslFile, String rfmFile) {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(dslFile), ResourceType.DSL);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(dslrFile), ResourceType.DSLR);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(rfmFile), ResourceType.DRF);
		
		buildKnowledgeBase(knowledgeBuilder);
	}
	
	private static void buildKnowledgeBase(KnowledgeBuilder knowledgeBuilder) {
		KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) { System.err.println(error); }
			throw new IllegalArgumentException("Could not parse knowledge");
		}
		
		knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
	}
}
