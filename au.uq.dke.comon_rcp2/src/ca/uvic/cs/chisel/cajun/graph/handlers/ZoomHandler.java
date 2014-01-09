package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.awt.event.KeyEvent;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventFilter;

/**
 * Handles zooming.
 * 
 * @author Chris
 * @since 8-Nov-07
 */
public class ZoomHandler implements MouseWheelListener {

	private PNormalZoomHandler zoom;

	// private PCamera camera;

	public ZoomHandler(PCamera camera) {
		super();
		// this.camera = camera;
		zoom = new PNormalZoomHandler(camera);

		PInputEventFilter filter = new PInputEventFilter();
		filter.rejectAllEventTypes();
		filter.setAcceptsKeyPressed(true);
		filter.setAcceptsKeyReleased(true);
		filter.setAcceptsMouseWheelRotated(true);
		// setEventFilter(filter);
	}

	// OVERRIDES

	// @Override
	// public void mouseWheelRotated(PInputEvent event) {
	// if (event.getWheelRotation() < 0) {
	// // zoom in
	// zoomIn();
	// stopZoom();
	// } else if (event.getWheelRotation() > 0) {
	// // zoom out
	// zoomOut();
	// stopZoom();
	// }
	// super.mouseWheelRotated(event);
	// }

	public void zoomOut() {
		// zoom out on the center of the canvas
		zoom.startZoomingOut();
	}

	public void zoomIn() {
		// zoom in on the center of the canvas
		zoom.startZoomingIn();
	}

	public void stopZoom() {
		zoom.stopZooming();
	}

	@Override
	public void mouseScrolled(MouseEvent e) {
		// TODO Auto-generated method stub
		int count = e.count;
		if (count < 0) {
			// zoom in
			zoomIn();
			stopZoom();
		} else if (count > 0) {
			// zoom out
			zoomOut();
			stopZoom();
		}

	}
}
