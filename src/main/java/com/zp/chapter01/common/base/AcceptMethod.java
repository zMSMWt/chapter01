package com.zp.chapter01.common.base;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 示例：java8 新特性--- 函数参数化(::)
 * 
 * 在 JDK8 中，接口 Iterable 中默认实现了 forEach 方法，
 * 调用了 JDK8 中增加的接口 Consumer 内的 accept 方法，执行传入的方法参数。
 * @author Administrator
 *
 */
public class AcceptMethod {

	public static void printValue(String str) {
		System.out.println("print value: " + str);
	}
	
	public static void main(String[] args) {
		List<String> al = Arrays.asList("a", "b", "c", "d");
		al.forEach(AcceptMethod::printValue);
		System.out.println("*********************\n");
		// 下面的方法和上面的等价
		Consumer<String> methodParam = AcceptMethod::printValue; // 方法参数
		al.forEach(x -> methodParam.accept(x)); // 方法执行 accept【增强 for 循环 + lambda 表达式】
	}
}
