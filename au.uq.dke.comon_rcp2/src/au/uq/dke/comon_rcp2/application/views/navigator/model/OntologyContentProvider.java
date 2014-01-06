package au.uq.dke.comon_rcp2.application.views.navigator.model;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;



public class OntologyContentProvider implements ITreeContentProvider {
	private NavigatorRoot navigatorRoot = new NavigatorRoot();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		//TODO: make it the root element
		return navigatorRoot.getOntologyRoots().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof INavigatorNodeBean){
			INavigatorNodeBean parent = (INavigatorNodeBean)parentElement;
			return parent.getChildren(parent);
		}
		else{
			//TODO: throw custom exception
			return null;
		}
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof INavigatorNodeBean){
			INavigatorNodeBean nodeBean = (INavigatorNodeBean)element;
			return nodeBean.getParent(nodeBean);
		}
		else{
			//TODO: throw custom exception
			return null;
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof INavigatorNodeBean){
			INavigatorNodeBean nodeBean = (INavigatorNodeBean)element;
			return nodeBean.hasChildren(nodeBean);
		}
		else{
			//TODO: throw custom exception
			return false;
		}
	}

}
