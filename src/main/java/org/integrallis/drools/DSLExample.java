package org.integrallis.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DSLExample {

	public static final void main(String[] args) {
	    KieSession knowledgeSession = null;
	    try {
	    		// load up the knowledge base
	    		KieServices ks = KieServices.Factory.get();
	        KieContainer kContainer = ks.getKieClasspathContainer();
	        knowledgeSession = kContainer.newKieSession("ksession-rules");
			
			// 4 - create and assert some facts
			Person rocky = new Person("Rocky Balboa", "Philadelphia", 35);
			
			knowledgeSession.insert(rocky);
		
			// 5 - fire the rules
			knowledgeSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			knowledgeSession.dispose();
        }
	}
}