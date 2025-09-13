package com.dj.fd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * @ClassName AVLTree  
 * @Description 平衡二叉树工具类 
 * @author dingj 
 * @date 2018年7月12日  
 *   
 */
public class AVLTree {
	
	/** 
	 * @Title: createTree 
	 * @Description: 构建平衡二叉树</br>
	 * 	树节点的score值是按倒叙排列（score的最大值在二叉树最左端的叶子节点） 
	 * @param node
	 * @param from
	 * @param to
	 * @return 参数说明
	 * @return Node    返回类型
	 */
	public Node createTree(Node node, int from, int to) {
		if (from < to) {
			return null;
		}
		node = new Node(from, to);
		if (from == to) {
			return node;
		}
		int mid = to + ((from - to) >> 1);
		node.setLeft(createTree(node.getLeft(), from, mid + 1));
		node.setRight(createTree(node.getRight(), mid, to));
		return node;
	}

	/** 
	 * @Title: addNewScore 
	 * @Description: 给树节点新增评分 
	 * @param node
	 * @param score 参数说明
	 * @return void    返回类型
	 */
	public void addNewScore(Node node, int score) {
		if (node == null)
			return;
		if (score <= node.getFromScore() && score >= node.getToScore()) {
			node.setCount(node.getCount() + 1);
		}
		int mid = node.getToScore() + ((node.getFromScore() - node.getToScore()) >> 1);
		if (score <= mid) {
			addNewScore(node.getRight(), score);
		} else {
			addNewScore(node.getLeft(), score);
		}
	}

	/** 
	 * @Title: getRank 
	 * @Description: 获取给定用户的信用评分名次 
	 * @param node
	 * @param score
	 * @param rank
	 * @return 参数说明
	 * @return int    返回类型
	 */
	public int getRank(Node node, int score, int rank) {
		if (node == null) {
			return 1;
		}
		int mid = node.getToScore() + ((node.getFromScore() - node.getToScore()) >> 1);
		if (score > mid) {
			rank = getRank(node.getLeft(), score, rank);
		} else if (node.getRight() != null) {
			rank += node.getLeft().getCount();
			rank = getRank(node.getRight(), score, rank);
		}
		return rank;
	}
	
	/** 
	 * @Title: saveMaxCountByPostOrder 
	 * @Description: 暂存 信用评分重复次数 
	 * @param node
	 * @param map
	 * @return 参数说明
	 * @return Map<String,Integer>    返回类型
	 */
	public Map<String, Integer> saveMaxCountByPostOrder(Node node, Map<String, Integer> map) {
		int fromScore = node.getFromScore();
		int toScore = node.getToScore();
		if (fromScore == toScore && node.getCount() != 0) {
			map.put(node.getFromScore() + "", node.getCount());
			map = sortMapForCount(map);
		}
		return map;
	}

	/** 
	 * @Title: postOrderTraversal 
	 * @Description: 后序遍历平衡二叉树 
	 * @param node
	 * @param map
	 * @return 参数说明
	 * @return Map<String,Integer>    返回类型
	 */
	public Map<String, Integer> postOrderTraversal(Node node, Map<String, Integer> map) {
		if (node == null) {
			return map;
		}
		if (node.getLeft() != null) {
			map = postOrderTraversal(node.getLeft(), map);
		}
		if (node.getRight() != null) {
			map = postOrderTraversal(node.getRight(), map);
		}
		return saveMaxCountByPostOrder(node, map);
	}
	
	/**
	* 使用 Map按value进行排序
	* @param map
	* @return
	*/
	public Map<String, Integer> sortMapForCount(Map<String, Integer> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
				oriMap.entrySet());
		// 降序排列
		Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return -(o1.getValue() - o2.getValue());
			}
		});
		int temp =0;
		for (Map.Entry<String, Integer> entry : entryList) {
			temp++;
			if(temp <= 10){
				sortedMap.put(entry.getKey(), entry.getValue());
			}
		}
		return sortedMap;
	}

}
