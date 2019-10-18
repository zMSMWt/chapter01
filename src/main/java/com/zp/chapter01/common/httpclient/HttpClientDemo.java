package com.zp.chapter01.common.httpclient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpClientDemo {

	public static void main(String[] args) throws IOException {
		
		myPost();
		//myGet();
	}
	
	/**
	 * 以 post 方式请求网页
	 * @throws IOException 
	 */
	public static void myPost() {
		// 目标 URL
		String url = "http://127.0.0.1:8080/***";
		// 创建一个默认的 HttpClient
		//HttpClient httpClient = new DefaultHttpClient();
		// DefaultHttpClient 被弃用后的新的请求方法
		//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			//第一步：创建 httpClient 对象
			httpClient = HttpClients.createDefault();
			//第二步：创建 HttpPost 对象， 以 post 方式请求网页
			HttpPost httpPost = new HttpPost(url);
			// 添加 HTTP POST 参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			//nvps.add(new BasicNameValuePair("username", "admin"));
			//nvps.add(new BasicNameValuePair("password", "123456"));
			nvps.add(new BasicNameValuePair("id", "1"));
			//以表单的形式将参数传递过去
			//httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
			// 将 POST 参数以 UTF-8  编码并包装成表单实体对象
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			// 打印请求地址
			System.out.println("executing request " + httpPost.getRequestLine().getUri());
			// 创建响应处理器服务器相应内容
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// 执行请求并获取结果
			String responseBody = httpClient.execute(httpPost, responseHandler);
			System.out.println(responseBody);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 当不需要 HTTPClient 实例时，关闭连接管理器以确保释放所有占用的系统资源
			//httpClient.getConnectionManager().shutdown();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 以 get 方式请求网页
	 * @throws IOException 
	 */
	public static void myGet() {
		// 目标 URL
		String url = "http://127.0.0.1:8080/***";
		// 创建一个默认的 HttpClient
		//HttpClient httpClient = new DefaultHttpClient();
		// DefaultHttpClient 被弃用后的新的请求方法
		//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			// 以 get 方式请求网页
			HttpGet httpGet = new HttpGet(url);
			// 打印请求地址
			System.out.println("executing request " + httpGet.getURI());
			// 创建响应处理器服务器相应内容
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// 执行请求并获取结果
			String responseBody = httpClient.execute(httpGet, responseHandler);
			System.out.println(responseBody);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 当不需要 HTTPClient 实例时，关闭连接管理器以确保释放所有占用的系统资源
			//httpClient.getConnectionManager().shutdown();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 调用其他服务接口的方法一（POST 方式）
	 * @param params
	 * @return
	 */
	public static JSONObject doPost(JSONObject params) {
		HttpClient client = HttpClients.createDefault();
		// 要调用的接口方法
		String url = "http://localhost:8080/***";
		HttpPost post = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			StringEntity stringEntity = new StringEntity(params.toString());
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			post.setEntity(stringEntity);
			post.addHeader("content-type", "te");
			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String res = EntityUtils.toString(response.getEntity());// 返回 json 格式
				jsonObject = JSONObject.parseObject(res); 
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return jsonObject;
	}
	
//	/**
//	 * Controller 层 调用例子（doPost）
//	 * @return
//	 * @throws ParseException
//	 */
//	@RequestMapping("/doPostGetJson")
//	public String doPostGetJson() throws ParseException {
//		// 此处将要发送的数据转换为 json 格式字符串
//		String jsonText = "{id:1}";
//		JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonText);
//		JSONObject res = this.doPost(jsonObject);
//		System.out.println("返回参数：" + res);
//		return sr.toString();
//	}
}
