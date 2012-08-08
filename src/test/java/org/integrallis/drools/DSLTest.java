package org.integrallis.drools;

import static org.junit.Assert.assertEquals;

import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.junit.Test;

public class DSLTest extends BaseDroolsTestCase {
	
    public DSLTest() {
		super("rockys_rules.dslr", "say_something.dsl");
	}

	@Test
    public void testPersonMeantTucsonRule() {
    	Person bob = new Person("Bob", "Tooson", 35);
    	Person jim = new Person("Jim", "Tuzzon", 25);
    	Person charlie = new Person("Charlie", "Tucson", 44);
    	knowledgeSession.insert(bob);
    	knowledgeSession.insert(jim);
    	knowledgeSession.insert(charlie);
    	knowledgeSession.fireAllRules();
    	
		QueryResults results = knowledgeSession.getQueryResults( "Gel all Messages" );
		assertEquals(2, results.size());
		for ( QueryResultsRow row : results ) {
			Message message = (Message) row.get( "message" );
			assertEquals("You probably meant Tucson", message.getMessage());
		}


    }
}
