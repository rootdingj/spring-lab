package com.dj.fd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dj.util.PropertiesUtil;

public class FdExamProService {
	/** 评分区间范围值 */
	private static int SCORE_SCOPE = 1000000;
	/** 拆分文件个数 */
	private static int SUB_FILE_NUM = 200;
	/** 拆分零时文件相对路径 */
	private static String TEMP_FILE_PATH = "temp";

	/** 
	 * @Title: getPaths 
	 * @Description: 获取拆分文件写出路径
	 * @return
	 * @throws IOException 参数说明
	 * @return String[]    返回类型
	 */
	public String[] getPaths() throws IOException {
		String tempPath = File.separator+TEMP_FILE_PATH;
		File dir = new File(tempPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String path = dir.getAbsolutePath() + tempPath;
		String[] paths = new String[SUB_FILE_NUM];
		String suffix = ".csv";
		File file = null;
		for (int i = 0; i < SUB_FILE_NUM; i++) {
			paths[i] = path + i + suffix;
			file = new File(paths[i]);
			if (!file.exists()) {
				file.createNewFile();
			}
		}
		return paths;
	}

	/** 
	 * @Title: spliFile 
	 * @Description: 文件拆分  
	 * @param inUrl 源文件路径
	 * @param paths 拆分临时文件路径
	 * @throws IOException 参数说明
	 * @return void  返回类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void spliFile(String inUrl, String[] paths) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inUrl));
		ArrayList<BufferedWriter> list = new ArrayList();
		for (int i = 0; i < SUB_FILE_NUM; i++) {
			list.add(new BufferedWriter(new FileWriter(paths[i])));
		}
		String line = null;
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");
			int score = Integer.parseInt(item[1]);
			int num = score / (SCORE_SCOPE / SUB_FILE_NUM);
			list.get(num).write(line);
			list.get(num).newLine();
			list.get(num).flush();
		}
		reader.close();
		for(BufferedWriter bw : list) {
			bw.close();
		}
	}

	/** 
	 * @Title: mergeFile 
	 * @Description: 文件合并输出 
	 * @param outUrl 合并输出文件路径
	 * @param inPaths 临时文件路径
	 * @throws IOException 参数说明
	 * @return void    返回类型
	 */
	public void mergeFile(String outUrl, String[] inPaths) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outUrl, true));
		for (String inPath : inPaths) {
			mergeFile(inPath, writer);
		}
		if (writer != null) {
			writer.close();
		}
	}
	
	/** 
	 * @Title: mergeFile 
	 * @Description: 文件合并输出
	 * @param inPath 临时文件路径
	 * @param writer
	 * @throws IOException 参数说明
	 * @return void    返回类型
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void mergeFile(String inPath, BufferedWriter writer) throws IOException {
		List<User> list = new ArrayList(88);
		BufferedReader reader = new BufferedReader(new FileReader(inPath));
		String line = null;
		while ((line = reader.readLine()) != null) {
			// CSV格式文件为逗号分隔符文件，这里根据逗号切分
			String item[] = line.split(",");
			list.add(new User(item[0], Integer.parseInt(item[1])));
		}
		Collections.sort(list);
		for (User user : list) {
			writer.write(user.getUserId() + "," + user.getScore());
			writer.newLine();
			writer.flush();
		}
		if (reader != null) {
			reader.close();
		}
	}

	/** 
	 * @Title: init 
	 * @Description: 初始化 
	 * @throws IOException 参数说明
	 * @return void    返回类型
	 */
	public void init() throws IOException {
		String[] paths = getPaths();
		spliFile(PropertiesUtil.getValue("readFilePath"), paths);
		mergeFile(PropertiesUtil.getValue("writeFilePath"), paths);
	}

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		System.out.println("start:" + start);
		new FdExamProService().init();
		long end = System.currentTimeMillis();
		System.out.println("end:" + end);
		System.out.println("times:" + (end - start) / 1000);
	}

}
