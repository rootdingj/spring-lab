package com.dj.fd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dj.util.PropertiesUtil;

public class FdExamService {
	private static int MIN_SCORE = 0;
	private static int MAX_SCORE = 999999;
	
	/** 
	 * @Title: getIdByReadCVS 
	 * @Description: 获取当前用户ID的序号和分数
	 * @param userId
	 * @return 参数说明
	 * @return Map<Integer,Integer>    返回类型
	 */
	public Map<Integer,Integer> getIdByReadCVS(String userId){
		// 用户的序号
		int seqNo = 0;
		// 当前用户的评分
		int rankScore = 0;
		BufferedReader reader = null;
		String line = null;  
		try {
			reader = new BufferedReader(new FileReader(PropertiesUtil.getValue("readFilePath")));
            while((line=reader.readLine())!=null){  
            	seqNo++;
            	//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                String item[] = line.split(",");
                int score = Integer.parseInt(item[1]);
                // 如果读取到的是当前指定用户ID，则暂存用户评分score
                if(userId.equals(item[0])) {
                	rankScore = score; 
                	break;
                }
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(seqNo,rankScore);
		return map;
	}
	
	/**
	* 实时排序，保存Top 100信用评分的用户信息
	* @param map
	* @return
	*/
	public Map<String, Integer> sortMapTop100(Map<String, Integer> oriMap) {
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
		/*for (Map.Entry<String, Integer> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		*/
		int temp =0;
		for (Map.Entry<String, Integer> entry : entryList) {
			temp++;
			if(temp <= 100){
				sortedMap.put(entry.getKey(), entry.getValue());
			}
		}
		return sortedMap;
	}
	
	/** 
	 * @Title: getUserIdByScoreTop100 
	 * @Description: Top 100 的信用评分的用户   参数说明
	 * @return void    返回类型
	 */
	public void getUserIdByScoreTop100(Map<String, Integer> map){
		int sum = 1;
		System.out.println("--------- Top 100 的信用评分的用户  start ----------");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("userId=" + entry.getKey() + ", score=" + entry.getValue());
			sum++;
			if(sum>100){
				break;
			}
		}
		System.out.println("--------- Top 100 的信用评分的用户  end ----------");
		
	}
	
	/** 
	 * @Title: getScoreByCount 
	 * @Description: 信用评分重复次数最高的10个信用评分（第三题）
	 * @param tree
	 * @param node 参数说明
	 * @return void    返回类型
	 */
	public void getScoreByCount(AVLTree tree,Node node){
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(16);
		map = tree.postOrderTraversal(node, map);
		int sum = 1;
		System.out.println("--------- 信用评分重复次数最高的10个信用评分 start ----------");
		System.out.println("重复次数最高的10个信用评分为：");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("分数："+entry.getKey()+", 重复次数："+entry.getValue());
			sum++;
			if(sum>10){
				break;
			}
		}
		System.out.println("--------- 信用评分重复次数最高的10个信用评分 end ----------");
	}
	
	/** 
	 * @Title: init 初始化
	 * @Description:  
	 * @param userId
	 * @return 参数说明
	 * @return int    返回类型
	 */
	public void init(String userId) {
		// 当前指定用户ID的序号
		int seqNo = 0;
		// 当前指定用户ID的评分
		int rankScore = 0;
		// 文件读取时用户ID对应的序号
		int countNo = 0;
		// 序号小于当前用户ID,并且评分和当前用户ID相同的用户个数
		int countScore = 0;
		//构建平衡二叉树
		AVLTree tree = new AVLTree();
		Node node = new Node();
		node = tree.createTree(node, MAX_SCORE, MIN_SCORE);
		//获取当前用户ID的序号和分数
		Map<Integer, Integer> userMap = getIdByReadCVS(userId);
		for (Map.Entry<Integer, Integer> entry : userMap.entrySet()) {
			seqNo = entry.getKey();
			rankScore = entry.getValue();
		}
		Map<String, Integer> oriMap = new LinkedHashMap<String, Integer>();
		Map<String, Integer> sortMap = new HashMap<String, Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(PropertiesUtil.getValue("readFilePath")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				countNo++;
				String item[] = line.split(",");
				int score = Integer.parseInt(item[1]);
				tree.addNewScore(node, score);
				// 实时保存Top 100信用评分的用户信息 
				oriMap.put(item[0], score);
				sortMap = sortMapTop100(oriMap);
				// 统计 序号小于当前用户ID,并且评分和当前用户ID相同的用户个数
				if (countNo < seqNo && score == rankScore) {
					countScore++;
				}
			}
			// 第一题
			int rank = tree.getRank(node, rankScore, 1);
			rank += countScore;
			System.out.println(userId + " 的排名为： " + rank);
			// 第二题
			 getUserIdByScoreTop100(sortMap);
			// 第三题
			 getScoreByCount(tree, node);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FdExamService().init("b9fc9c17-2c28-45a1-8f4c-1968d954c0f5");
	}

}
