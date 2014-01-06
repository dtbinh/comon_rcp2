package au.uq.dke.comon_rcp2.application.views.navigator.model;

public interface INavigatorNodeBean {

	public Object[] getElements(Object inputElement) ;

	public Object[] getChildren(Object parentElement) ; 

	public Object getParent(Object element);

	public boolean hasChildren(Object element);

}
