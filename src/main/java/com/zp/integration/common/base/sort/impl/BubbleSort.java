package com.zp.integration.common.base.sort.impl;

import com.zp.integration.common.base.sort.IArraySort;

import java.util.Arrays;

public class BubbleSort implements IArraySort {

	@Override
	public int[] sort(int[] sourceArray) {
		// 对 arr 进行拷贝，不改变参数内容
		int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
		
		for(int i = 1; i < arr.length; i++) {
			// 设定一个标记，若为 true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
			boolean flag = true;
			
			for(int j = 0; j < arr.length - i; j++) {
				if(arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					
					flag = false;
				}
			}
			if(flag) {
				break;
			}
		}
		return arr;
	}

	public static final int[] ARRAY = {2, 5, 6, 8, 4, 3, 7, 0, 1, 9};

	public static void main(String[] args) {
		//int[] sourceArray = {2, 5, 6, 8, 4, 3, 7, 0, 1, 9};

		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.sort(ARRAY);

		String arrString = Arrays.toString(bubbleSort.sort(ARRAY));
		System.out.println(arrString);
	}
}
