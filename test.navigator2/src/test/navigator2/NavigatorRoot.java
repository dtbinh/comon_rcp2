package test.navigator2;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.PlatformObject;

public class NavigatorRoot extends PlatformObject {
	
	public Collection getParentBeans(){
		Collection parentCollection = new ArrayList();
		int n = 3;
		while(n-- > 0){
			ParentBean pb = new ParentBean("parent " + n);
			parentCollection.add(pb);
		}
		
		return parentCollection;
	}

}
