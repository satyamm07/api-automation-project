package com.core.utils;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import static io.restassured.RestAssured.given;

/**
 * @author Satyamm
 */
public class ApiUtils {

    private final Logger BASE_LOGGER = Logger.getLogger(ApiUtils.class);

    public Response getRequest(String request) {
        Response response = given().when().get(request);
        if (null != response) {
            BASE_LOGGER.info("---Successfully Received the response ---");
        } else {
            BASE_LOGGER.error("--- Response retrieved as NULL ---");
        }
        return response;
    }

    public Response getRequest(String request, String contentType) {
        Response response = given().contentType(contentType).when().get(request);
        if(null != response) {
            BASE_LOGGER.info("---Successfully Received the response ---");
        }
        else {
            BASE_LOGGER.error("--- Response retrieved as NULL ---");
        }
        return response;
    }

    public Response postRequest(String request, String payLoad) {
        Response response = given().contentType("application/json").body(payLoad).post(request);
        if(null != response) {
            BASE_LOGGER.info("---Successfully Received the response ---");
        }
        else {
            BASE_LOGGER.error("--- Response retrieved as NULL ---");
        }
        return response;
    }

    public Response putRequest(String request, String payLoad, String cookie) {
        Response response = given().contentType("application/json").body(payLoad).cookie(cookie).put(request);
        if(request != null) {
            BASE_LOGGER.info("---Successfully Received the response ---");
        }
        else {
            BASE_LOGGER.error("--- Response retrieved as NULL ---");
        }
        return response;
    }

    public Response patchRequest(String request, String payload, String cookie) {
        Response response = given().contentType("application/json").cookie(cookie).body(request).patch(payload);
        if(request != null) {
            BASE_LOGGER.info("---Successfully Received the response ---");
        }
        else {
            BASE_LOGGER.error("--- Response retrieved as NULL ---");
        }
        return response;
    }

}
