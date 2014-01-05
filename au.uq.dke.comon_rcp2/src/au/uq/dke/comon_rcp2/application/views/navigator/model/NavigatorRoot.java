package au.uq.dke.comon_rcp2.application.views.navigator.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.PlatformObject;

public class NavigatorRoot extends PlatformObject {
	
	
	
	public Collection getOntologyRoots(){
		Collection parentCollection = new ArrayList();
		int n = 3;
		while(n-- > 0){
			MockNodeBean pb = new MockNodeBean("parent " + n);
			parentCollection.add(pb);
		}
		
		return parentCollection;
	}

}
