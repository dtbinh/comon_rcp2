package au.uq.dke.comon_rcp2.application.views.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author wangwei
 * it is useless; caller will new an object using default constructor.
 * static class will also not work for the same reason
 */
@Deprecated
public class SingletonViewerFilter extends ViewerFilter{
	 
	private static SingletonViewerFilter instance = null;
	public static SingletonViewerFilter getInstance(){
		if(instance == null){
			instance = new SingletonViewerFilter();
		}
		return instance;
	}
	
	private SingletonViewerFilter(){
		
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return false;
	}

}
