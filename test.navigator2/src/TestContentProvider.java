import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import test.navigator2.NavigatorRoot;


public class TestContentProvider implements ITreeContentProvider {
	private NavigatorRoot navigatorRoot = new NavigatorRoot();

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		int a = 1;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return navigatorRoot.getParentBeans().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return navigatorRoot.getParentBeans().toArray();
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

}
