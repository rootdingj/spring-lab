package com.dj.ch08.copy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtil {
	// 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象(绝对好习惯)
	public FileUtil(){
		throw new AssertionError();
	}
	
	/**
     * 统计给定文件中给定字符串的出现次数
     * 
     * @param filename  文件名
     * @param word 字符串
     * @return 字符串在文件中出现的次数
     */
    public static int countWordInFile(String filename, String word) {
    	int count = 0;
    	String line = null;
		int index = -1;
		FileReader reader = null;
		BufferedReader br =  null;
    	try {
    		reader = new FileReader(filename);
    		br = new BufferedReader(reader);
    		while((line=br.readLine())!=null){
				while (line.length() >= word.length()&& (index = line.indexOf(word)) >= 0) {
					count++;
					line = line.substring(index + word.length());
				}
    		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null)reader.close();
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
    }
    
    /**
     * 列出一个目录下所有的文件
     * 
     * @param sourceURL 目录路径
     * @return 文件数目
     * */
    public static void coutFileOfCatalog(String sourceURL){
    	int count = 0;
    	File file = new File(sourceURL);
    	for(File f:file.listFiles()){
    		if(f.isFile()){
    			System.out.println(f.getName());
    			count++;    		}
    	}
    	System.out.println("一共有"+count+"个文件");
    }
    
    /**
     * 对文件夹继续展开
     * 
     * @param file
     * @param level 层级
     * */
    private static void walkDirectory(File file, int level) {
        if(file.isDirectory()) {
            for(File temp : file.listFiles()) {
            	walkDirectory(temp, level + 1);
            }
        }
        else {
            for(int i = 0; i < level - 1; i++) {
                System.out.print("\t");
            }
            System.out.println(file.getName());
        }
    }
    
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
		System.out.println(path.getFileName().toString());
		return FileVisitResult.CONTINUE;
	}
    
    /**
     * 对文件夹继续展开
     * 
     * @param file
     * */
    public static void showDirectory() {
//    	walkDirectory(new File("src/main/java/com/dj/base/ch08/copy/source.txt"), 1);
    	Path path = Paths.get("src/main/java/com/dj/base/ch08/copy/source.txt");
    	try {
			Files.walkFileTree(path, new FileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					return null;
				}
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					System.out.println(file.getFileName().toString());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc)
						throws IOException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(countWordInFile("src/main/java/com/dj/base/ch08/copy/source.txt","输入流"));
//		coutFileOfCatalog("src/main/java/com/dj/base/ch08/copy");
		showDirectory();
		
	}

}
