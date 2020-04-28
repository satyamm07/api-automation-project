package com.core.apiServices;

import com.core.utils.ApiUtils;
import com.core.utils.BaseClass;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.Properties;

public class BookingApiService extends BaseClass {

    Properties configProperties;
    ApiUtils apiUtils = new ApiUtils();
    private final Logger BASE_LOGGER = Logger.getLogger(ApiUtils.class);

    public BookingApiService() throws Exception {
        configProperties = loadProperties();
    }

    public Response createBookingService(String request, String payload) throws Exception {
//        System.out.println(configProperties.getProperty("baseUrl") + request, payload);
        Response createBookingResponse = apiUtils
                .postRequest(configProperties.getProperty("baseUrl") + request, payload);
        if (createBookingResponse != null) {
            return createBookingResponse;
        } else {
            throw new Exception("--- Exception occurred while creating the booking ---");
        }
    }

    public Response getBookingDetailsWithIdService(String request) throws Exception {
        BASE_LOGGER.error("Get booking details API endpoint"
                + configProperties.getProperty("baseUrl") + request);
        Response getBookingDetailsWithIdResponse = apiUtils
                .getRequest(configProperties.getProperty("baseUrl") + request);
        if (getBookingDetailsWithIdResponse != null) {
            return getBookingDetailsWithIdResponse;
        } else {
            throw new Exception("--- Exception occurred while getting the response ---");
        }
    }
}