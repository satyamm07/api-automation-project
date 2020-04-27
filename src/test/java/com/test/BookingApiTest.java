package com.test;

import com.core.apiServices.BookingApiService;
import com.core.utils.ApiUtils;
import com.core.utils.IEndPoints;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class BookingApiTest implements IEndPoints {

    private final Logger BASE_LOGGER = Logger.getLogger(ApiUtils.class);
    BookingApiService bookingApiService;
    Response getBookingWithIdResponse;


    @Test(description = "To verify, Get Booking details with Id API response")
    public void getBookingWithIdResponse() throws Exception{
        bookingApiService = new BookingApiService();
        getBookingWithIdResponse = bookingApiService.getBookingDetailsWithIdService(BOOKING_API, "1");
        System.out.println(getBookingWithIdResponse.asString());


    }
}