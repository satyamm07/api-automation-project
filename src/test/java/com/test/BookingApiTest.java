package com.test;

import com.core.apiServices.BookingApiService;
import com.core.apiServices.BookingDatesJsonCreation;
import com.core.apiServices.CreateBookingJsonCreation;
import com.core.pojoServices.BookingDates;
import com.core.utils.ApiUtils;
import com.core.utils.IEndPoints;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.google.gson.Gson;

public class BookingApiTest implements IEndPoints {

    private final Logger BASE_LOGGER = Logger.getLogger(ApiUtils.class);
    private Gson gson;
    BookingApiService bookingApiService;
    Response getBookingWithIdResponse;
    CreateBookingJsonCreation createBookingJsonCreation;
    BookingDatesJsonCreation bookingDatesJsonCreation;


    @Test
    public void testCreateBooking() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        createBookingJsonCreation = new CreateBookingJsonCreation();
        bookingDatesJsonCreation = new BookingDatesJsonCreation();

        BookingDates bookingDates = bookingDatesJsonCreation.mapBookingDates("2020-04-28","2020-04-28");


        String createBookingJson = gson.toJson(createBookingJsonCreation
                .mapCreateBooking("Satyam","Mishra",
                        11,true, bookingDates,"Breakfast"));
        System.out.println(createBookingJson);

    }


    @Test(description = "To verify, Get Booking details with Id API response")
    public void getBookingWithIdResponse() throws Exception{
        bookingApiService = new BookingApiService();


        getBookingWithIdResponse = bookingApiService.getBookingDetailsWithIdService(BOOKING_API, "14");
        System.out.println(getBookingWithIdResponse.asString());


    }
}