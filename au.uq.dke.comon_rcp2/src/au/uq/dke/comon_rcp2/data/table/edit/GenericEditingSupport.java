package au.uq.dke.comon_rcp2.data.table.edit;

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.metawidget.util.ClassUtils;


public class GenericEditingSupport extends EditingSupport {

  private final TableViewer viewer;
  private final CellEditor editor;
  private final Field field;

  public GenericEditingSupport(TableViewer viewer, Field field) {
    super(viewer);
    this.viewer = viewer;
    this.field = field;
    Class type = field.getType();
    if(String.class.isAssignableFrom(type)){
        this.editor = new TextCellEditor(viewer.getTable());
    }
    //TODO: add other types
    else if(Object.class.isAssignableFrom(type)){
    	
    	this.editor = new BasicDialogCellEditor(viewer.getTable(), this.field);
    }else{
        this.editor = new TextCellEditor(viewer.getTable());
  	
    }
    
    
   }

  @Override
  protected CellEditor getCellEditor(Object element) {
    return editor;
  }

  @Override
  protected boolean canEdit(Object element) {
    return true;
  }

  @Override
  protected Object getValue(Object element) {
    return  ClassUtils.getProperty(element, field.getName());
  }

  @Override
  protected void setValue(Object element, Object userInputValue) {
    ClassUtils.setProperty(element, field.getName(), userInputValue);
    viewer.update(element, null);
  }
} 