package au.uq.dke.comon_rcp2.ontology.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import au.uq.dke.comon_rcp2.EntryPoint;
import au.uq.dke.comonviz.ui.login.UserLoginDialog;
import au.uq.dke.comonviz.ui.ontology.FilterPanel;
import au.uq.dke.comonviz.ui.ontology.OpenEditConceptsDialogAction;
import au.uq.dke.comonviz.ui.ontology.OpenEditRelTypesDialogAction;
import au.uq.dke.comonviz.ui.ontology.OpenEditRelationshipsDialogAction;
import au.uq.dke.comonviz.ui.ontology.OpenOntologyFileAction;
import au.uq.dke.comonviz.ui.ontology.StatusProgressBar;
import au.uq.dke.comonviz.ui.ontology.filterPanel.ArcTypeFilterPanel;
import au.uq.dke.comonviz.ui.ontology.filterPanel.HierarchalArcFilterPanel;
import au.uq.dke.comonviz.ui.ontology.filterPanel.InterBranchArcFilterPanel;
import au.uq.dke.comonviz.ui.ontology.filterPanel.IntraBranchNonTreeStyleArcFilterPanel;
import au.uq.dke.comonviz.ui.ontology.filterPanel.NodeBranchFilterPanel;
import au.uq.dke.comonviz.ui.ontology.filterPanel.NodeLevelFilterPanel;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.graph.Graph;
import ca.uvic.cs.chisel.cajun.graph.GraphModelAdapter;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.NodeCollection;
import database.model.ontology.OntologyClass;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolox.swing.PScrollPane;

public class TopView extends JPanel {

	private static final int FilterPanelWidth = 300;

	private static final long serialVersionUID = -7720543969598323711L;

	private FlatGraph graph;

	private JToolBar toolbar;
	private JPanel centerGraphPanel;

	public JPanel getCenterGraphPanel() {
		return centerGraphPanel;
	}

	private StatusProgressBar status;

	private JSplitPane rightSplitPane;
	private FilterPanel nodeFilterPanel;
	private FilterPanel arcTypeFilterPanel;
	private FilterPanel nodeLevelFilterPanel;
	private FilterPanel nodeBranchFilterPanel;
	private FilterPanel interBranchArcFilterPanel;
	private FilterPanel intraBranchNonTreeStyleArcFilterPanel;
	private FilterPanel hierarchalArcFilter;
	

	private JSplitPane centerAndRightHorizontalSplitPane;

	private JSplitPane leftVerticalSplitPane;
	private JSplitPane topHorizontalSplitPane;

	public JSplitPane getTopHorizontalSplitPane() {
		return topHorizontalSplitPane;
	}

	private DefaultTreeModel treeModel;

	private NodeCollection selectedNodes;

	private JTree jTree;
	private JTextPane jTextArea;

	TreeSelectionListener textFieldtreeSelectionListener = new TreeSelectionListener() {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			// TODO Auto-generated method stub
			DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode) ((JTree) e
					.getSource()).getLastSelectedPathComponent();
			GraphNode selectedGraphNode = (GraphNode) selectedTreeNode
					.getUserObject();
			OntologyClass ontologyClass = (OntologyClass) selectedGraphNode
					.getUserObject();
			String annotation = ontologyClass.getDiscription();
			// annotation = annotation.substring(1, annotation.length() - 1);
			annotation = EntryPoint.getAnnotationManager()
					.getStylizedAnnotation(annotation);
			TopView.this.getjTextArea().setText(annotation);
			TopView.this.getjTextArea().setCaretPosition(0);

		}

	};

	GraphModelListener graphModelListener = new GraphModelListener() {

		@Override
		public void graphCleared() {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphNodeAdded(GraphNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphNodeRemoved(GraphNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphArcAdded(GraphArc arc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphArcRemoved(GraphArc arc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphNodeTypeAdded(Object nodeType) {
			// TODO Auto-generated method stub

		}

		@Override
		public void graphArcTypeAdded(Object arcType) {
			// TODO Auto-generated method stub

		}

	};

	public JTextPane getjTextArea() {
		return jTextArea;
	}

	public void setjTextArea(JTextPane jTextArea) {
		this.jTextArea = jTextArea;
	}

	public JTree getjTree() {
		return jTree;
	}

	public TopView() {
		super(new BorderLayout());
		this.graph = EntryPoint.getFlatGraph();
		// this.selectedNodes = graph.getNodeSelection();
		// initialize();
	}

	public void initialize(DefaultMutableTreeNode root) {
		// this.ontologyTree = ontologyTree;

		EntryPoint.getjFrame().add(this);

		// left
		treeModel = new DefaultTreeModel(root);
		jTextArea = new JTextPane();
		jTextArea.setContentType("text/html");
		jTextArea.setMinimumSize(new Dimension(200, 100));
		jTextArea.setEditable(true);
		jTextArea.setMargin(new Insets(10, 10, 10, 10));
		jTree = new JTree(treeModel);
		jTree.setSelectionRow(0);
		jTree.setCellRenderer(treeCellRender);
		jTree.addTreeSelectionListener(textFieldtreeSelectionListener);

		JScrollPane leftTopJScrollPane = new JScrollPane(jTree);
		leftTopJScrollPane.setMinimumSize(new Dimension(200, 200));

		JScrollPane leftBottomJScrollPane = new JScrollPane(jTextArea);
		leftBottomJScrollPane.setMinimumSize(new Dimension(100, 200));

		leftVerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		// leftVerticalSplitPane.setMinimumSize(new Dimension(200, 200));
		leftVerticalSplitPane.add(leftTopJScrollPane);
		leftVerticalSplitPane.add(leftBottomJScrollPane);
		leftVerticalSplitPane.setOneTouchExpandable(true);

		// center & right
		centerGraphPanel = new JPanel(new BorderLayout());
		centerGraphPanel.add(new PScrollPane(graph.getCanvas()),
				BorderLayout.CENTER);

		arcTypeFilterPanel = new ArcTypeFilterPanel("Arc Types", null,
				graph.getGraphArcStyle());
		nodeLevelFilterPanel = new NodeLevelFilterPanel("Node Levels", null,
				graph.getGraphArcStyle());

		nodeBranchFilterPanel = new NodeBranchFilterPanel("Node Branches", null,
				graph.getGraphArcStyle());

		interBranchArcFilterPanel = new InterBranchArcFilterPanel("", null,
				graph.getGraphArcStyle());
		intraBranchNonTreeStyleArcFilterPanel = new IntraBranchNonTreeStyleArcFilterPanel("", null,
				graph.getGraphArcStyle());
		hierarchalArcFilter = new HierarchalArcFilterPanel("", null,
				graph.getGraphArcStyle());

		rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // new

		JPanel filtersPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridy = 0;
		filtersPanel.add(arcTypeFilterPanel, c);
		c.gridy = 1;
		filtersPanel.add(nodeLevelFilterPanel, c);
		c.gridy = 2;
		filtersPanel.add(nodeBranchFilterPanel, c);
		c.gridy = 3;
		filtersPanel.add(interBranchArcFilterPanel, c);
		c.gridy = 4;
		filtersPanel.add(intraBranchNonTreeStyleArcFilterPanel, c);
		c.gridy = 5;
		filtersPanel.add(hierarchalArcFilter, c);

		rightSplitPane.setVisible(true);

		rightSplitPane.setDividerLocation(0.5f);
		rightSplitPane.add(filtersPanel);
		rightSplitPane.setMinimumSize(new Dimension(FilterPanelWidth, FilterPanelWidth));

		centerAndRightHorizontalSplitPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT);

		centerAndRightHorizontalSplitPane.add(centerGraphPanel);
		centerAndRightHorizontalSplitPane.setVisible(true);
		centerAndRightHorizontalSplitPane.setOneTouchExpandable(true);

		topHorizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		topHorizontalSplitPane.add(leftVerticalSplitPane);
		topHorizontalSplitPane.add(centerAndRightHorizontalSplitPane);
		topHorizontalSplitPane.setOneTouchExpandable(true);

		this.add(getToolBar(), BorderLayout.NORTH);
		this.add(topHorizontalSplitPane, BorderLayout.CENTER);
		initializeToolBar();
	}

	public FilterPanel getNodeBranchFilterPanel() {
		return nodeBranchFilterPanel;
	}

	public void setNodeBranchFilterPanel(FilterPanel nodeBranchPanel) {
		this.nodeBranchFilterPanel = nodeBranchPanel;
	}

	public void setSplitPaneDividers() {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				leftVerticalSplitPane.setDividerLocation(0.7f);
			}
		});
	}

	public FilterPanel getArcTypeFilterPanel() {
		return arcTypeFilterPanel;
	}

	public FilterPanel getNodeLevelFilterPanel() {
		return nodeLevelFilterPanel;
	}

	public void hideSubclassArcType() {
		String subClass = "has subclass";
		String subClassType = null;
		for (Object arcType : EntryPoint.getFilterManager().getArcTypeFilter()
				.getArcTypes()) {
			if (((String) arcType).equals(subClass)) {
				subClassType = (String) arcType;
			}
		}
		this.arcTypeFilterPanel.setTypeVisibility(subClassType, false);
	}

	public void addListeners() {
		PBasicInputEventHandler graphListener = new PBasicInputEventHandler() {
			@Override
			public void mousePressed(PInputEvent e) {
				PNode node = e.getPickedNode();
				if (node instanceof GraphNode) {
					node.moveToFront();
					// don't reflect to the tree, it will calls a cycle in
					// reaction!
					// nodePressed(e, (GraphNode) node);
				}
				if (e.isLeftMouseButton()) {
					if (e.getClickCount() == 2) {
						if (e.getPickedNode() instanceof GraphNode) {
							// expand this node in tree
						}
					}
				}
				super.mousePressed(e);
			}

			private void nodePressed(PInputEvent e, GraphNode displayNode) {
				// select node in the tree explorer
				JTree jTree = EntryPoint.getTopView().getjTree();
				DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jTree
						.getModel().getRoot();
				Enumeration<?> enumeration = rootNode.breadthFirstEnumeration();
				while (enumeration.hasMoreElements()) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration
							.nextElement();
					GraphNode graphNode = (GraphNode) node.getUserObject();
					if (graphNode.getUserObject() == displayNode
							.getUserObject()) {
						TreePath treePath = new TreePath(
								((DefaultTreeModel) jTree.getModel())
										.getPathToRoot(node));
						jTree.scrollPathToVisible(treePath);
						jTree.setSelectionPath(treePath);
					}
				}

			}

		};

		EntryPoint.getFlatGraph().getCamera()
				.addInputEventListener(graphListener);
		graph.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (Graph.GRAPH_ARC_STYLE_PROPERTY.equals(evt.getPropertyName())) {
					arcTypeFilterPanel.setStyle(graph.getGraphArcStyle());
				}
			}
		});
		EntryPoint.getFilterManager().addFilterChangedListener(
				arcTypeFilterPanel);
		graph.getModel().addGraphModelListener(new GraphModelAdapter() {
			@Override
			public void graphArcTypeAdded(Object arcType) {
				arcTypeFilterPanel.reload();
			}
		});

	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	private void initializeToolBar() {
		JToggleButton showFilter = new JToggleButton("show filter");
		showFilter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (centerAndRightHorizontalSplitPane.getComponentCount() == 2) {

					TopView.this.centerAndRightHorizontalSplitPane
							.setDividerLocation(centerAndRightHorizontalSplitPane
									.getWidth() - FilterPanelWidth);
					TopView.this.centerAndRightHorizontalSplitPane
							.add(rightSplitPane);
				} else {
					centerAndRightHorizontalSplitPane.remove(rightSplitPane);
				}
			}

		});
		this.getToolBar().add(showFilter);
		
		JToggleButton showLogin = new JToggleButton("login");
		showLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserLoginDialog();
			}

		});
		this.getToolBar().add(showLogin);
		
		

		addToolBarAction(new OpenOntologyFileAction());
		addToolBarAction(new OpenEditConceptsDialogAction());
		addToolBarAction(new OpenEditRelTypesDialogAction());
		addToolBarAction(new OpenEditRelationshipsDialogAction());

	}

	public JToolBar getToolBar() {
		if (toolbar == null) {
			toolbar = new JToolBar(JToolBar.HORIZONTAL);
			toolbar.setFloatable(false);
			toolbar.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		return toolbar;
	}

	public StatusProgressBar getStatusBar() {
		if (status == null) {
			status = new StatusProgressBar();
		}
		return status;
	}

	/**
	 * Repaints the this panel so that the right panel will properly resize.
	 */

	/**
	 * add a btn to a toolbar, and the btn is linked with an action
	 * 
	 * @param action
	 * @return
	 */
	public JButton addToolBarAction(Action action) {
		JButton btn = getToolBar().add(action);
		btn.setText((String) action.getValue(Action.NAME));
		btn.setToolTipText((String) action.getValue(Action.NAME));
		return btn;
	}

	public JToggleButton addToolBarToggleAction(Action action) {
		JToggleButton btn = new JToggleButton(action);
		btn.setText(null);
		btn.setToolTipText((String) action.getValue(Action.NAME));
		getToolBar().add(btn);
		return btn;
	}

	public void removeToolBarAction(Action action) {
		if (action != null) {
			Component found = null;
			for (Component c : getToolBar().getComponents()) {
				if (c instanceof AbstractButton) {
					AbstractButton btn = (AbstractButton) c;
					if (action.equals(btn.getAction())) {
						found = c;
						break;
					}
				}
			}
			if (found != null) {
				getToolBar().remove(found);
				getToolBar().revalidate();
				getToolBar().repaint();
			}
		}
	}

	public void addToolBarSeparator() {
		addToolBarComponent(null);
	}

	public void addToolBarComponent(Component component) {
		if (component == null) {
			getToolBar().addSeparator();
		} else {
			getToolBar().add(component);
		}
	}

	public void removeToolBarComponent(Component c) {
		if (c != null) {
			getToolBar().remove(c);
			getToolBar().revalidate();
			getToolBar().repaint();
		}
	}

	DefaultTreeCellRenderer treeCellRender = new DefaultTreeCellRenderer() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			this.setLeafIcon(null);
			this.setOpenIcon(null);
			this.setClosedIcon(null);

			// StringBuffer html = new
			// StringBuffer("<html><b style= \"color: #000000; background-color: #fff3ff\">T</b>  ");
			// StringBuffer html.append(value.toString());
			// html.append("</html>");
			return super.getTreeCellRendererComponent(tree, value.toString(),
					sel, expanded, leaf, row, hasFocus);
		}
	};

}
