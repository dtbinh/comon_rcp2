package table.model;

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.metawidget.util.ClassUtils;

import table.GenericTableUnit;

public class GenericFilter extends ViewerFilter {

  private String searchString;
  
  private GenericTableUnit viewer;

  public void setSearchText(String s) {
    // ensure that the value can be used for matching 
    this.searchString = ".*" + s + ".*";
  }
  
  public GenericFilter(GenericTableUnit viewer){
	  this.viewer = viewer;
  }

  @Override
  public boolean select(Viewer viewer, 
      Object parentElement, 
      Object element) {
    if (searchString == null || searchString.length() == 0) {
      return true;
    }
    
    for(Field field : element.getClass().getDeclaredFields()){
    	if(ClassUtils.getProperty(element, field.getName()).toString().matches(searchString)){
    		
    		return true;
    	}
    }
    

    return false;
  }
} 