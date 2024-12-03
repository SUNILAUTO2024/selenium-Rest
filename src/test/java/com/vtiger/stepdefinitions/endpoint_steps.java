package com.vtiger.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;

public class endpoint_steps extends BaseSteps {
    public String jSonDt;
    public static String endpoint;
    public static Response resp;

    @Given("endpoint {string}")
    public void endpoint(String string) {
        endpoint=string;
        logger.pass(string+" ->>Fetch this endpoint successfully");

    }
    @And("user send the get request")
    public void user_send_the_get_request() {
        resp = RestAssured.get(endpoint);
        logger.pass("->>Send Get request to this endpoint");

    }
    @Then("status code should equal to {int}")
    public void status_code_should_equal_to(Integer int1) {
        System.out.println("Validating response code");
        resp.then().statusCode(201);
        if (200==resp.getStatusCode()){
            logger.pass("Status code validated successfully");
        }
        else{
            logger.fail("Status code not matching");
        }
        System.out.println("Status is = "+resp.statusCode());
       // resp.prettyPrint();

    }

    @Then("user validate response")
    public void user_validate_response() {


    }

    @Then("status code should equal to {string}")
    public void status_code_should_equal_to(String string) {
       resp.then().statusCode(Integer.parseInt(string));
       logger.pass(string+" is actual SCode_Validated status code");
    }
    @Then("user validate response with JasonPath {string} and Jason_Value {string}")
    public void user_validate_response_with_jason_path_and_jason_value(String string, String string2) {
        String JPath = string;
        String JValue = string2;
        resp.then().body(JPath, equalTo(JValue));
        logger.pass(JPath+"Validated response"+JValue);
    }

    @Given("request file {string}")
    public void request_file(String file) throws IOException {

        jSonDt = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/test/resources/Request/"+file)));

    }
    @Given("user perform post operation")
    public void user_perform_post_operation() {
        RequestSpecification requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jSonDt);
        //send post req
        resp= requestSpec.post(endpoint);
        resp.prettyPrint();

    }




}
