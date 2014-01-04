package au.uq.dke.comon_rcp2.common.utils.treeUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.semanticweb.owlapi.model.OWLEntity;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import uk.ac.manchester.cs.bhig.util.Tree;
import au.uq.dke.comon_rcp2.ontology.ui.model.node.BasicGraphNode_back;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class TreeInfoManager {
        
	
		private static MutableTree treeRoot;
        private static TreeInfoManager instance;
        
        
        private static Map<Object, EntityTreeInfo> entityTreeInfoMap;
        
        public static Map<Object, EntityTreeInfo> getEntityTreeInfoMap() {
			return entityTreeInfoMap;
		}

		private static List<MutableTree> branchNodes;
        
        public static Collection<MutableTree> getBranchNodes() {
			return branchNodes;
		}

		private static boolean initiated = false;

        
        private TreeInfoManager(){
        };
        
        public int getChildrenCount(Object key){
        	return getTree(key).getChildCount();
        }
        
        public static TreeInfoManager getTreeManager(){
        		if(instance == null){
                        instance = new TreeInfoManager();
                        entityTreeInfoMap = new HashMap();
        		}
                return instance;
        }
        
        public int getLevel(Object key){
        	return getEntityTreeInfo(key).getLevel();
        }
        
        public EntityTreeInfo getEntityTreeInfo(Object key){
        	return entityTreeInfoMap.get(key);
        }
        
        public Object getBranchEntity(Object key){
        	EntityTreeInfo entityTreeInfo  = entityTreeInfoMap.get(key);
        	
        	return entityTreeInfo.getBranchObject();
        }
        
        public Tree getTree(Object key){
        	return entityTreeInfoMap.get(key).getTreeNode();
        }
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void generateTreeInfo(Collection nodes){
                
                List<IGraphNode> localNodeList = new ArrayList();
                localNodeList.addAll(nodes);
                
                //莽锟�鸥忙藛锟矫ぢ糕偓盲赂陋treeList
                List<MutableTree> treeNodeList = new ArrayList();
                
                
                for(IGraphNode node: localNodeList){
                        treeNodeList.add(new MutableTree(node));
                }
                
                for(int i = 0; i< treeNodeList.size(); i++){
                        for(int j = 1; j< treeNodeList.size(); j++){
                                if(i == j){
                                        continue;
                                }
                                BasicGraphNode_back iNode = (BasicGraphNode_back) treeNodeList.get(i).getUserObject();
                                BasicGraphNode_back jNode = (BasicGraphNode_back) treeNodeList.get(j).getUserObject();
                                
                                for(IGraphArc arc: jNode.getArcs()){
                                	if(!arc.getType().equals("has subclass")){
                                		continue;
                                	}
                                        if(arc.getSource() == iNode){// source is parent
                                                if(!treeNodeList.get(i).getChildren().contains(treeNodeList.get(j)))
                                                        treeNodeList.get(i).addChild(treeNodeList.get(j));
                                                continue;
                                        }else if(arc.getDestination() == iNode){
                                                treeNodeList.get(i).setParent(treeNodeList.get(j));
                                                continue;
                                        }
                                }
                                
                        }
                }
                
                //now the treeList is formed
                
                //now we generate tree info for each entity
                
                //first, we find roots
                
                List<MutableTree> rootList = new ArrayList();
                
                for(MutableTree tree: treeNodeList){
                        if(tree.getParent() == null){
                                rootList.add(tree);
                        }
                }
                
                branchNodes = null;
                
                List<MutableTree> level1Nodes;

                if(rootList.size() == 1){
                		this.treeRoot = rootList.get(0);
                        
                        branchNodes = rootList.get(0).getChildren();
                        //branchNodes.add(rootList.get(0));//only one root, the root have to have color
                        OWLEntity entity = (OWLEntity)(((IGraphNode) (rootList.get(0).getUserObject())).getUserObject());
                        EntityTreeInfo entityTreeInfo = new EntityTreeInfo(entity, rootList.get(0), (OWLEntity)((IGraphNode) rootList.get(0).getUserObject()).getUserObject(), 1, -1);
                        entityTreeInfoMap.put(entity, entityTreeInfo);

                }
                else{
                		this.treeRoot = new MutableTree("Thing");
                        branchNodes = rootList;
                        for(MutableTree t: branchNodes){
                        	treeRoot.addChild(t);
                        }
                }
                
                //then, we generate level and branch info for each node, map them into map
                
                //test
                Collection path = rootList.get(0).getPathToRoot();
               
                for(MutableTree t: treeNodeList){
                        
                        OWLEntity entity = (OWLEntity)(((IGraphNode) (t.getUserObject())).getUserObject());
                        int level = -1;
                        
                        level = Math.max(t.getPathToRoot().size(), level);
                        
                        for(MutableTree branchTreeNode: branchNodes){
                                if(t.getPathToRoot().contains(branchTreeNode)){//that means they belong to the same cluster
                                        
                                        EntityTreeInfo entityTreeInfo = new EntityTreeInfo(entity, t, (OWLEntity)((IGraphNode) branchTreeNode.getUserObject()).getUserObject(), level, -1);
                                        entityTreeInfoMap.put(entity, entityTreeInfo);
                                }
                        }
                        
                }
                
                this.initiated = true;
                
                return;
                
        }

		public static MutableTree getTreeRoot() {
			return treeRoot;
		}
        
		public static DefaultMutableTreeNode convertFromManchesterToUITreeNode(MutableTree manchesterMutableTree){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(manchesterMutableTree.getUserObject());
			DefaultMutableTreeNode child;
			
			java.util.List<MutableTree> childrenList  = manchesterMutableTree.getChildren();
			for (int i = 0; i < childrenList.size(); i++) {
				MutableTree nodeSpecifier = childrenList.get(i);
				if (nodeSpecifier.getChildCount() != 0) // Ie node with children
					child = convertFromManchesterToUITreeNode(nodeSpecifier);
				else
					child = new DefaultMutableTreeNode(nodeSpecifier.getUserObject()); // Ie Leaf
				node.add(child);
			}
			return (node);
		}

        

}