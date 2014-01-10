package au.uq.dke.comon_rcp2.application.views.filters;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ViewerFilter;

public class FilterManager {
	private static FilterManager instance;
	
	public static FilterManager getInstance(){
		if(instance == null){
			instance = new FilterManager();
		}
		return instance;
	}
	
	private FilterManager(){
		
	}

	private Set<ViewerFilter> filters = new HashSet<ViewerFilter>();
	
	
	public void addFilter(ViewerFilter filter){
		this.filters.add(filter);
	}
	
	public Collection<ViewerFilter> getAllFilters(){
		return filters;
	}
}
