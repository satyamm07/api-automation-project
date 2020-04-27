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

    public Response getBookingDetailsWithIdService(String request, String id) throws Exception {
        String bookingId = "/" + id;
        BASE_LOGGER.error("Get booking details API endpoint"
                + configProperties.getProperty("baseUrl") + request + bookingId);
        Response getBookingDetailsWithIdResponse = apiUtils
                .getRequest(configProperties.getProperty("baseUrl") + request + bookingId);
        if (getBookingDetailsWithIdResponse != null) {
            return getBookingDetailsWithIdResponse;
        } else {
            throw new Exception("--- Exception occurred while getting the response ---");
        }
    }


}
