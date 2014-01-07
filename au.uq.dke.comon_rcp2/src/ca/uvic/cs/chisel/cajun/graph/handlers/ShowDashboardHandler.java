package ca.uvic.cs.chisel.cajun.graph.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.uq.dke.comonviz.graph.node.DashboardIconNode;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.ui.data.dialog.BasicDashboardDialog;
import au.uq.dke.comonviz.ui.data.panel.BasicDashboardUnitPanel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class ShowDashboardHandler extends PBasicInputEventHandler{

	@Override
	public void mouseClicked(PInputEvent event) {
		PNode clickedNode = event.getPickedNode();
		if(clickedNode instanceof DashboardIconNode){
			DefaultGraphNode parent = (DefaultGraphNode)clickedNode.getParent();
			Class recordType = parent.getRecordType();
			Map<Class, Integer> actualRecordTypeMap = new HashMap<Class, Integer>();
			List records = DatabaseUtils.findAll(recordType);
			for(Object record : records){
				if(actualRecordTypeMap.containsKey(record.getClass())){
					Integer count = actualRecordTypeMap.get(record.getClass()) + 1;
					actualRecordTypeMap.put(record.getClass(), count);
				}else{
					actualRecordTypeMap.put(record.getClass(), 1);
					
				}
			}
			
			//show dashboard
			BasicDashboardDialog dashboardDialog = new BasicDashboardDialog(recordType.getSimpleName());
			for(Map.Entry<Class, Integer> entry : actualRecordTypeMap.entrySet()){
				BasicDashboardUnitPanel unitPanel = new BasicDashboardUnitPanel(entry.getKey().getSimpleName(), entry.getValue());
				dashboardDialog.addUnitPanel(unitPanel);
			}
			return;
		}
	}

}
