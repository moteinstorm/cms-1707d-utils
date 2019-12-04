package com.zhuzhiguang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class Compare {
	
	public static void main(String[] args) throws IOException {
		System.err.println(args.length);
		if(args.length<2) {
			System.out.println("参数不正确！ 至少需要两个参数");
			return;
		}
		  	
		String src = args[0];
		String dst= args[1];
		trace(src,dst);
		System.out.println("比较完成");
	}
	
	/**
	 * 获取摘要
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	private static String getDigest(File file) throws IOException {
		
		InputStream fis  = new FileInputStream(file);
		byte[] md5 = DigestUtils.md5(fis);
		String digest = new String(md5);
		return  digest;
		
		
		
	}
	
	private static void trace(String src,String dst) throws IOException {
		
		File fileSrc  = new File(src) ;
		if(!fileSrc.exists()) {
			System.out.println(" src 文件夹不存在  "  + src);
			return ;
		}
		
		File fileDst = new File(dst);
		if(!fileDst.exists()) {
			System.out.println(" dst 文件夹不存在  "  + dst);
			return;
		}
		
		
		if(fileSrc.isFile() ){
			
			if( fileDst.isFile() 
					&& (src.endsWith("java") || src.endsWith("xml")) ) {
				// 判断文件大小
				if(fileSrc.length() != fileDst.length()) {
					System.out.println(" 文件大小不匹配   ： " + fileSrc );
				}else if(!getDigest(fileSrc).equals(getDigest(fileDst))){
					System.out.println(" 文件内容不匹配   ： " + fileSrc );
				}
				
			}
			
			return;
		}
		
		String[] srclList = fileSrc.list();
		//遍历子目录
		for (int i = 0; i < srclList.length; i++) {
			trace(src + "\\" + srclList[i], dst+ "\\" + srclList[i]) ;
		}
		
	}
}
