package com.bw.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zhuzhiguang.StringUtils;

public class TestStringUtils {
	
	@Test
	public void testIsBlank() {
		
		
		// 判断是否为空
		assertTrue( StringUtils.isBlank("   "));
		
	}
	
	
	@Test
	public void testIsBlank2() {
		//判断是否为空
		assertFalse( StringUtils.isBlank("aaaa"));
	}
	
	@Test
	public void testRandomn() {
		String s = StringUtils.getRandomStr(10);
		System.out.println(" s is :: " + s);
	}
	
	@Test
	public void testStrRland() {
		String s = StringUtils.getRandomStr2(25);
		System.out.println(" s is " + s + "  and 长度是  " + s.length());
	}
	
	
	@Test
	public void testCnStrn() {
		String s = StringUtils.getRandomCn(25);
		System.out.println(" s " + s + "  长度是 : " + s.length());
		
	}
	
	
	
	
	
}
