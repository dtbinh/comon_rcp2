package au.uq.dke.comon_rcp2.ontology.ui.model.node;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import au.uq.dke.comon_rcp2.common.CustomRuntimeException;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PPickPath;

public class BasicIconNode extends PImage {


	@Override
	public void setName(String name) {
		if(this.getParent() != null){
			this.getParent().setName(name);
		}
		else{
			throw new CustomRuntimeException("the node has no parent");
		}

	}



	public BasicIconNode() {
		super();
	}



	public BasicIconNode(Image image) {
		super(image);
		// TODO Auto-generated constructor stub
	}



	public BasicIconNode(URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}



	public BasicIconNode(String fileName) {
		super(fileName);
	}



	@Override
	public String getName() {
		if(this.getParent() != null){
			return this.getParent().getName();
		}
		else{
			throw new CustomRuntimeException("the node has no parent");
		}
	}



	public boolean intersects(Rectangle2D aBounds) {
		return this.getBounds().intersects(aBounds);
	}

	
	
	@Override
	public boolean fullIntersects(Rectangle2D parentBounds) {
		return this.intersects(parentBounds);
	}

	/**
	 * Try to pick this node and all of its descendants. Most subclasses should
	 * not need to override this method. Instead they should override
	 * <code>pick</code> or <code>pickAfterChildren</code>.
	 * 
	 * @param pickPath
	 *            the pick path to add the node to if its picked
	 * @return true if this node or one of its descendants was picked.
	 */
	public boolean fullPick(final PPickPath pickPath) {
        if (getVisible() && (getPickable() || getChildrenPickable()) && fullIntersects(pickPath.getPickBounds())) {
        	int a = 1;
        }

        return super.fullPick(pickPath);
    }

	public boolean setBounds(double x, double y, double width, double height) {
		if (super.setBounds(x, y, width, height)) {

		}
		return false;
	}

	public String toString() {
		return this.getName();
	}

}
