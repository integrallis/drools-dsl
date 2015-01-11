package org.integrallis.drools;

import static org.junit.Assert.assertEquals;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.junit.Test;



public class DSLTest extends BaseDroolsTestCase {
	
    public DSLTest() {
    	  super("ksession-rules");
	}

	@Test
    public void testPersonMeantTucsonRule() {
	    	Person bob = new Person("Bob", "Tooson", 35);
	    	Person jim = new Person("Jim", "Tuzzon", 25);
	    	Person charlie = new Person("Charlie", "Tucson", 44);
	    	Person fred = new Person("Fred", "too zone", 23);
	    	Person julia = new Person("Julia", "two so on", 45);
	    	Person colin = new Person("Colin", "Pasadena", 19);
	    	
	    	knowledgeSession.insert(bob);
	    	knowledgeSession.insert(jim);
	    	knowledgeSession.insert(charlie);
	    	knowledgeSession.insert(fred);
	    	knowledgeSession.insert(julia);
	    	knowledgeSession.insert(colin);
	    	
	    	knowledgeSession.fireAllRules();
    	
		QueryResults results = knowledgeSession.getQueryResults( "Get all Messages" );
		assertEquals(4, results.size());
		for ( QueryResultsRow row : results ) {
			Message message = (Message) row.get( "message" );
			System.out.println(message.getMessage() + " instead of " + message.getOriginalWord());			
			assertEquals("You probably meant Tucson", message.getMessage());
		}
    }
}
