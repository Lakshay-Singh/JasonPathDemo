package com.bestbuy.response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPathJaywayExamples {
	 static String jsonResponse;

	    static void print(String val){
	        System.out.println("------------------");
	        System.out.println(val);
	        System.out.println("------------------");
	    }

	   @BeforeAll 
	    public static void init(){

	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 3030;
	        
	        jsonResponse=given().when().get("/products").asString();

	    }
	   
	   @BeforeEach
	   void printToConsole() {
		   System.out.println("-----------");
		   System.out.println("   ");
	   }
	   
	   @AfterEach
	   void printToConsoleAgain() {
		   System.out.println("-----------");
		   System.out.println("   ");
	   }
	   
	   @DisplayName("Get Root Element")
	   @Test
	   public void getRoot() {
		   Map<String,?> rootElement=JsonPath.read(jsonResponse, "$");
		   print(rootElement.toString());
	   }
	   
	   @DisplayName("Get the total value from the response")
	   @Test
	   public void getTotalFromResponse() {
		   int total=JsonPath.read(jsonResponse, "$.total");
		   print(total +"");
	   }
	   
//	   @Test
//	    public void getAllDataElements(){
//
//	    	List<HashMap<String,Object>>  dataElements= JsonPath.read(jsonResponse, "$.data");
//	    	dataElements.stream().forEach(System.out::println);
//	    	
//			   forEach(<HashMap<String,Object> dataElement:dataElements){
//			   System.out.println(dataElement);
//		   }
//	    }
	   
	   @Test
	   public void getFirstElement() {
		   Map<String,?> firstElement= JsonPath.read(jsonResponse, "$.data[0]");
		   print(firstElement.toString());
	   }
	   
	   @Test
	   public void getLastElement() {
		   Map<String,?> lastElement= JsonPath.read(jsonResponse, "$.data[-1]");
		   print(lastElement.toString());
	   }
	   
	   @Test
	   public void getIdInData(){
		   List<String> ids=JsonPath.read(jsonResponse, "$.data[*].id");
		   print(ids.toString());
	   }
	   
	   @Test
	   public void getAllId() {
		   List<String> allids=JsonPath.read(jsonResponse, "$..[*].id");
		   print(allids.toString());
	   }
	   
	   @Test
	    public void filterExpression(){

	    	List<String> names = JsonPath.read(jsonResponse, "$.data[?(@.price>5)].name");
	    	print(names.toString());
//	    	names.stream().forEach(System.out::println);
	    }
	   
		   
}
