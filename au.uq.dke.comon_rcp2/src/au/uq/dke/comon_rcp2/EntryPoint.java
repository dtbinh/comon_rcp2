package au.uq.dke.comon_rcp2;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import au.uq.dke.comon_rcp2.ontology.ui.model.ComonGraphModel;
import au.uq.dke.comon_rcp2.ontology.ui.view.TopView;
import au.uq.dke.comonviz.actions.LayoutAction;
import au.uq.dke.comonviz.filter.FilterManager;
import au.uq.dke.comonviz.handler.NodeExpandCollapseListener;
import au.uq.dke.comonviz.misc.OwlApi;
import au.uq.dke.comonviz.model.AnnotationManager;
import au.uq.dke.comonviz.treeUtils.TreeInfoManager;
import au.uq.dke.comonviz.ui.ontology.StyleManager;
import ca.uvic.cs.chisel.cajun.constants.LayoutConstants;
import ca.uvic.cs.chisel.cajun.graph.AbstractGraph;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import database.model.ontology.OntologyRelationship;
import database.service.OntologyAxiomService;
import database.service.OntologyClassService;
import database.service.OntologyRelationshipService;

public class EntryPoint {
	private final String dataBaseFileName = "database.h2.db";
	private final String outerDatabaseDirectory = "C:/comonviz/";
	private final File outerDatabaseFile = new File(outerDatabaseDirectory
			+ dataBaseFileName);
	
	
	public  static LayoutAction radicalLayoutStyle;

	public  static LayoutAction treeLayoutStyle;
	
	private static LayoutAction currentLayoutStyle ;

	private static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static OntologyAxiomService ontologyAxiomService = (OntologyAxiomService) ctx
			.getBean("ontologyAxiomService");
	private static OntologyClassService ontologyClassService = (OntologyClassService) ctx
			.getBean("ontologyClassService");
	private static OntologyRelationshipService ontologyRelationshipService = (OntologyRelationshipService) ctx
			.getBean("ontologyRelationshipService");


	public static OntologyAxiomService getOntologyAxiomService() {
		return ontologyAxiomService;
	}

	public static OntologyClassService getOntologyClassService() {
		return ontologyClassService;
	}

	public static OntologyRelationshipService getOntologyRelationshipService() {
		return ontologyRelationshipService;
	}

	public static OWLOntology ontology = null;
	private final String internalOWLFilePath = "/COMON_v8_HenryNewRel.owl";

	/** the graph object, performs layouts and renders the model */
	private static FlatGraph flatGraph;

	/** the model representation of the graph, nodes and edges */
	private static ComonGraphModel graphModel;

	private static DefaultMutableTreeNode ontologyTreeRoot;

	private static TopView topView;

	private static JFrame jFrame;
	private static FilterManager filterManager;

	private static StyleManager styleManager;

	public static FilterManager getFilterManager() {
		return filterManager;
	}

	public static JFrame getjFrame() {
		return jFrame;
	}

	private static MutableTree ontologyTree;

	public static Dimension frameSize;

	private static AnnotationManager annotationManager;

	public static AnnotationManager getAnnotationManager() {
		return annotationManager;
	}

	/**
     * 
     */
	@SuppressWarnings({ "unused", "static-access", "static-access" })
	public void start() {

		// set look and feel
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// Okay to fail
		}

		try {
			initDataBase();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List objects = EntryPoint.getOntologyClassService().findAll();

		filterManager = new FilterManager();
		jFrame = new JFrame("CoMOnViz");
		graphModel = new ComonGraphModel();
		this.getFilterManager().getArcTypeFilter().updateArcTypes();
		flatGraph = new FlatGraph();
		
		
		radicalLayoutStyle = new LayoutAction(LayoutConstants.LAYOUT_RADIAL,
				null, new RadialLayoutAlgorithm(1), EntryPoint.getFlatGraph());

		treeLayoutStyle = new LayoutAction("tree layout",
				null, new TreeLayoutAlgorithm(), EntryPoint.getFlatGraph());
		
		currentLayoutStyle = radicalLayoutStyle;
		
		
		
		topView = new TopView();
		annotationManager = new AnnotationManager();
		// graphController = new GraphController();
		currentLayoutStyle = new LayoutAction(LayoutConstants.LAYOUT_RADIAL,
				null, new RadialLayoutAlgorithm(1), EntryPoint.getFlatGraph());

		graphModel.init();
		ontologyTreeRoot = graphModel.generateMutableTree();

		topView.initialize(ontologyTreeRoot);

		styleManager = StyleManager.getStyleManager();

		flatGraph.addInputEventListener(new NodeExpandCollapseListener());
		filterManager.addListeners();
		graphModel.addListeners();
		topView.addListeners();
		flatGraph.addListeners();

		this.filterManager.getNodeLevelFilter().updateNodeLevels(3);
		this.filterManager.getNodeBranchFilter().init();
		
		this.topView.getArcTypeFilterPanel().reload();
		this.topView.getNodeLevelFilterPanel().reload();
		this.topView.getNodeBranchFilterPanel().reload();

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Display the window.
		jFrame.setMinimumSize(new Dimension(800, 600));
		jFrame.pack();
		jFrame.setExtendedState(jFrame.getExtendedState()
				| JFrame.MAXIMIZED_BOTH);
		jFrame.setVisible(true);

		EntryPoint.getTopView().setSplitPaneDividers();
		EntryPoint.getTopView().hideSubclassArcType();

	}

	public static DefaultMutableTreeNode getOntologyTreeRoot() {
		return ontologyTreeRoot;
	}

	public static LayoutAction getCurrentLayoutStyle() {
		return currentLayoutStyle;
	}

	public static void setCurrentLayoutStyle(LayoutAction currentLayoutStyle) {
		EntryPoint.currentLayoutStyle = currentLayoutStyle;
	}

	public static OWLOntology getOntology() {
		return ontology;
	}

	public static FlatGraph getFlatGraph() {
		return flatGraph;
	}

	public static ComonGraphModel getGraphModel() {
		return graphModel;
	}

	public static TopView getTopView() {
		return topView;
	}

	public static JFrame getFrame() {
		return jFrame;
	}

	public static MutableTree getOntologyTree() {
		return ontologyTree;
	}

	public static void main(String[] args) throws URISyntaxException,
			IOException {
		EntryPoint entryPoint = new EntryPoint();

		entryPoint.start();

	}

	public void addAxiomId() {
		List<OntologyRelationship> relationshipList = EntryPoint
				.getOntologyRelationshipService().findAll();
		for (OntologyRelationship relationship : relationshipList) {
			String name = relationship.getName();
			Long id = EntryPoint.getOntologyAxiomService().findByName(name)
					.getId();
			relationship.setAxiomId(id);
			EntryPoint.getOntologyRelationshipService().save(relationship);
		}
	}

	private void initDataBase() throws URISyntaxException, IOException {
		// File innerDatabaseResourceFile = new File(this.getClass()
		// .getResource("/database/" + dataBaseFileName).toURI());

		InputStream innerSourceFileStream = this.getClass()
				.getResourceAsStream("/database/" + dataBaseFileName);
		FileUtils.forceMkdir(new File(outerDatabaseDirectory));
		if (outerDatabaseFile.exists()) {
//			FileUtils.copyInputStreamToFile(innerSourceFileStream,
//					outerDatabaseFile);
		} else {
			FileUtils.copyInputStreamToFile(innerSourceFileStream,
					outerDatabaseFile);
			// FileUtils.copyFile(innerDatabaseResourceFile, outerDatabaseFile);
		}
	}
}
