/**
 * 
 */
package jadeutils.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TreeNodeHelper<K, T> {

	/**
	 * 把结果集组成树
	 * 
	 * @param root
	 *            一个根结点
	 * @param nodes
	 *            所有的结点
	 * @param map
	 *            所有结点的ID->对象映射
	 * @param cper
	 *            子结点排序的comparaotr
	 */
	@SuppressWarnings("unchecked")
	public void genTree(T root, List<T> nodes, Map<K, T> map, Comparator<T> cper) {
		TreeNodeItf<K, T> rootNode = (TreeNodeItf<K, T>) root;
		if (null == root || null == nodes || nodes.size() == 0) {
			return;
		} else {
			for (T st : nodes) {
				TreeNodeItf<K, T> n = (TreeNodeItf<K, T>) st;
				if (null != n.getParentNodeId()
						&& n.getParentNodeId().equals(rootNode.getId())) {
					map.put(n.getId(), st);
					n.setParentNode((T) root);
					if (null == rootNode.getSubNodeList()) {
						rootNode.setSubNodeList(//
						new ArrayList<T>());
					}
					rootNode.getSubNodeList().add(st);
				}
			}
		}
		if (null == rootNode.getSubNodeList()
				|| rootNode.getSubNodeList().size() == 0) {
			return;
		} else {
			if (null != cper) {
				Collections.sort(rootNode.getSubNodeList(), cper);
			}
			for (T r : rootNode.getSubNodeList()) {
				this.genTree(r, nodes, map, cper);
			}
		}
	}

}
