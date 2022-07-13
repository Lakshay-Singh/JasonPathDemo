package com.bestbuy.response;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class JsonPathJsonSlurpurExamples {
	static ValidatableResponse validatableResponse; 
	
	static void print(String val) {
		System.out.println(val);
	}
	
	 @BeforeAll 
	    public static void init(){

	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 3030;
	        
	        validatableResponse=given().when().get("/stores").then();
	    }
	   
	   @BeforeEach
	   void printToConsole() {
		   System.out.println("---------");
		   System.out.println("   ");
	   }
	   
	   @AfterEach
	   void printToConsoleAgain() {
		   System.out.println("---------");
		   System.out.println("   ");
	   }
	   
	   @Test
	   public void getTotal() {
		   int total=validatableResponse.extract().path("total");
		   print(total+"");
	   }
	   
	   @Test
	   public void getFirstStoreName() {
		   String name=validatableResponse.extract().path("data[0].name");
		   print(name);
	   }
	   
	   @Test
	   public void firstServiceFirstData() {
		   String name=validatableResponse.extract().path("data[0].services[0].name");
		   print(name);
	   }
	   
	   @Test
	   public void findStoreWithZip() {
		   Map<String,?> result=validatableResponse.extract().path("data.find{it.zip=='55901'}");
		   print(result.toString());
	   }
	   
	   @Test
	   public void findAddressOfStoreWithZip() {
		   String result=validatableResponse.extract().path("data.find{it.zip=='55901'}.address");
		   print(result.toString());
	   }
	   
	   @Test
	   public void findMinMaxid() {
		   Map<String,?> minid=validatableResponse.extract().path("data.min{it.id}");
		   print(minid.toString());
		   Map<String,?> maxid=validatableResponse.extract().path("data.max{it.id}");
		   print(maxid.toString());
	   }
	   
	   @Test
		public void getAllStoreswithIdLessThan10() {
			    List<String> result = validatableResponse.extract().path("data.findAll{it.id<10}.zip");	       
			    print(result.toString());
			    
		}

		@Test
		public void getServiceNamesInAllStores() {	
			  List< List<String> > serviceName = validatableResponse.extract().path("data.services.findAll{it.name}.name");
		      print(serviceName.toString());
		}
}
