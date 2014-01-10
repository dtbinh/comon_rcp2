package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.util.Collection;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import au.uq.dke.comon_rcp2.data.service.MockDataServiceImpl;
import au.uq.dke.comon_rcp2.data.table.GenericTableUnit;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;
import ca.uvic.cs.chisel.cajun.graph.util.RecordConnectionUtils;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class ShowRecordsTableHandler extends PBasicInputEventHandler {

	@Override
	public void mousePressed(PInputEvent event) {
		if (event.isRightMouseButton()) {

			BasicGraphNode clickedNode = (BasicGraphNode) event.getPickedNode();
			Class recordType = RecordConnectionUtils.getRecordType(clickedNode);
			if (recordType != null) {
				Shell shell = new Shell(Display.getCurrent());
				Collection dataInput = MockDataServiceImpl.getInstance().getDataSet(recordType);
				new GenericTableUnit(shell, recordType, dataInput, true);

			}
		}
		super.mousePressed(event);
	}

}
