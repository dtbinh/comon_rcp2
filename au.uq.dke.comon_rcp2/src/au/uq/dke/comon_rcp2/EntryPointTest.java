package au.uq.dke.comon_rcp2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.model.data.businessProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class EntryPointTest {
	EntryPoint entryPoint = new EntryPoint();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEntryPointInit() {
		entryPoint.start();
	}

	public void testEntryPointWithData() {
		//initData();
		entryPoint.start();
	}

	public static void initData() {

		ProcessActivity activity1 = new ProcessActivity("activity 1");
		activity1.persist();

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity1.getProcessObjectives().add(objective1);
		activity1.getProcessObjectives().add(objective2);
		
		objective1.persist();
		objective2.persist();
		
		ProcessRule rule1 = new ProcessRule("rule 1");
		rule1.getProcessActivities().add(activity1);
		rule1.persist();

	}

	public static void main(String[] args) {
		new EntryPointTest().testEntryPointWithData();
	}

}
