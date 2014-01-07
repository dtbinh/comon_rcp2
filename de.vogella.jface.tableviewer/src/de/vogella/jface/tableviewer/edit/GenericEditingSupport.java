package de.vogella.jface.tableviewer.edit;

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.PlatformUI;
import org.metawidget.util.ClassUtils;

import de.vogella.jface.tableviewer.model.Person;


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
    	
    	this.editor = new BasicDialogCellEditor(viewer.getTable());
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