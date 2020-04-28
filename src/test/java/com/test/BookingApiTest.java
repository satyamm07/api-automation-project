package com.test;

import com.core.apiServices.BookingApiService;
import com.core.apiServices.BookingDatesJsonCreation;
import com.core.apiServices.CreateBookingJsonCreation;
import com.core.pojoServices.BookingDates;
import com.core.utils.BaseClass;
import com.core.utils.IEndPoints;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BookingApiTest extends BaseClass implements IEndPoints {

    private final Logger BASE_LOGGER = Logger.getLogger(BookingApiTest.class);
    private Gson gson;
    private BookingApiService bookingApiService;
    private Response getBookingDetailsResponse;
    private CreateBookingJsonCreation createBookingJsonCreation;
    private BookingDatesJsonCreation bookingDatesJsonCreation;
    private Response createBookingResponse;

    private final String firstName = "Satyam";
    private final String lastName = "Mishra";
    private final int totalPrice = 1100;
    private final boolean depositPaidTrue = true;
    private final boolean depositPaidFalse = false;
    private final String checkInDate = "2020-04-28";
    private final String checkOutDate = "2020-04-28";
    private final String additionalNeeds = "Breakfast";
    private final String unregisteredFirstName = "TestUSer";
    private final String invalidBookingId = "77abc$%!";
    private final String notFoundMessage = "Not Found";

    private int jsonBookingId;

    public BookingApiTest() {
        BasicConfigurator.configure();
    }

    @Test(description = "To verify, Create Booking API response", priority = 0)
    public void testCreateBooking() throws Exception{
        gson = new GsonBuilder().setPrettyPrinting().create();
        createBookingJsonCreation = new CreateBookingJsonCreation();
        bookingDatesJsonCreation = new BookingDatesJsonCreation();
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();

        BookingDates bookingDates = bookingDatesJsonCreation
                .mapBookingDates(checkInDate,checkOutDate);

        String createBookingJson = gson.toJson(createBookingJsonCreation
                .mapCreateBooking(firstName,lastName,
                        totalPrice,depositPaidTrue, bookingDates,additionalNeeds));
        BASE_LOGGER.info("The generated JSON is = " + createBookingJson);
        createBookingResponse = bookingApiService.createBookingService(BOOKING_API, createBookingJson);
        jsonBookingId = createBookingResponse.getBody().jsonPath().getInt("bookingid");
        softAssert.assertEquals(createBookingResponse.statusCode(), 200,
                "Status code is not matching");
        BASE_LOGGER.info("Verified the status code from the response and it is: "
                + createBookingResponse.statusCode());
        BASE_LOGGER.info("The generated response is: " + createBookingResponse.asString());

        softAssert.assertAll();
    }

    @Test(description = "To verify, the booking details after creating the ", priority = 1)
    public void testVerifyCreateBookingResponse() {
        softAssert = new SoftAssert();

        String createdFirstName = createBookingResponse.getBody().jsonPath().getString("booking.firstname");
        String createdLastName = createBookingResponse.getBody().jsonPath().getString("booking.lastname");
        int createdTotalPrice = createBookingResponse.getBody().jsonPath().getInt("booking.totalprice");
        boolean createdDepositPaidValue = createBookingResponse.getBody().jsonPath().getBoolean("booking.depositpaid");
        String createdCheckInDate = createBookingResponse.getBody().jsonPath().getString("booking.bookingdates.checkin");
        String createdCheckOutDate = createBookingResponse.getBody().jsonPath().getString("booking.bookingdates.checkout");
        String createdAdditionalNeeds = createBookingResponse.getBody().jsonPath().getString("booking.additionalneeds");

        softAssert.assertEquals(createdFirstName, firstName,
                "Created First Name is not matching");
        softAssert.assertEquals(createdLastName, lastName,
                "Created last Name is not matching");
        softAssert.assertEquals(createdTotalPrice, totalPrice,
                "Created Total price is not matching");
        softAssert.assertEquals(createdDepositPaidValue, depositPaidTrue,
                "Created deposit value is not matching");
        softAssert.assertEquals(createdCheckInDate, checkInDate,
                "Created check in date is not matching");
        softAssert.assertEquals(createdCheckOutDate, checkOutDate,
                "Created check out date is not matching");
        softAssert.assertEquals(createdAdditionalNeeds, additionalNeeds,
                "Created additional needs is not matching");

        softAssert.assertAll();
    }

    @Test(description = "To verify, Get Booking details with Id API response", priority = 2)
    public void getBookingWithIdResponse() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();
        String bookingId = "/" + jsonBookingId;
        getBookingDetailsResponse = bookingApiService
                .getBookingDetailsWithIdService(BOOKING_API + bookingId);
        BASE_LOGGER.info("The generated response is: " + getBookingDetailsResponse.asString());

        softAssert.assertEquals(getBookingDetailsResponse.statusCode(), 200,
                "Status code is not matching");
        BASE_LOGGER.info("Verified the status code from the response and it is: "
                + getBookingDetailsResponse.statusCode());

        softAssert.assertAll();
    }

    @Test(description = "To verify, the booking details after creating the ", priority = 3)
    public void testJsonBookingApiResponse() {
        softAssert = new SoftAssert();

        String jsonFirstName = getBookingDetailsResponse.getBody().jsonPath().getString("firstname");
        String jsonLastName = getBookingDetailsResponse.getBody().jsonPath().getString("lastname");
        int jsonTotalPrice = getBookingDetailsResponse.getBody().jsonPath().getInt("totalprice");
        boolean jsonDepositPaidValue = getBookingDetailsResponse.getBody().jsonPath().getBoolean("depositpaid");
        String jsonCheckInDate = getBookingDetailsResponse.getBody().jsonPath().getString("bookingdates.checkin");
        String jsonCheckOutDate = getBookingDetailsResponse.getBody().jsonPath().getString("bookingdates.checkout");
        String jsonAdditionalNeeds = getBookingDetailsResponse.getBody().jsonPath().getString("additionalneeds");

        softAssert.assertEquals(jsonFirstName, firstName,
                "json First Name is not matching");
        softAssert.assertEquals(jsonLastName, lastName,
                "json last Name is not matching");
        softAssert.assertEquals(jsonTotalPrice, totalPrice,
                "json Total price is not matching");
        softAssert.assertEquals(jsonDepositPaidValue, depositPaidTrue,
                "json deposit value is not matching");
        softAssert.assertEquals(jsonCheckInDate, checkInDate,
                "json check in date is not matching");
        softAssert.assertEquals(jsonCheckOutDate, checkOutDate,
                "json check out date is not matching");
        softAssert.assertEquals(jsonAdditionalNeeds, additionalNeeds,
                "json additional needs is not matching");

        softAssert.assertAll();

    }

    @Test(description = "To verify, Get Booking details after passing the first name & last name" +
            " query parameters", priority = 4)
    public void testGetBookingApiResponseWithNameQueryParam() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();

        String apiParameters = "?firstname=" + firstName + "&lastname=" + lastName;
        getBookingDetailsResponse = bookingApiService
                .getBookingDetailsWithIdService(BOOKING_API + apiParameters);
        BASE_LOGGER.info("The generated response is: " + getBookingDetailsResponse.asString());

        softAssert.assertEquals(getBookingDetailsResponse.statusCode(), 200,
                "Status code is not matching");
        BASE_LOGGER.info("Verified the status code from the response and it is: "
                + getBookingDetailsResponse.statusCode());

        softAssert.assertAll();
    }

    @Test(description = "To verify, Booking API json after passing the query parameters", priority = 5)
    public void testBookingApiJsonResponseWithQueryParam() {
        softAssert = new SoftAssert();

        List<Integer> bookingId = getBookingDetailsResponse.getBody().jsonPath().getList("bookingid");

        softAssert.assertEquals(bookingId.contains(jsonBookingId), true,
                "Booking id not found for the searched user");
        softAssert.assertAll();
    }


    @Test(description = "To verify, Get Booking details after passing the checking & checkout date" +
            " query parameters", priority = 6)
    public void testGetBookingApiResponseWithDatesQueryParam() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();

        String apiParameters = "?checkin=" + checkInDate + "&checkout=" + checkOutDate;
        getBookingDetailsResponse = bookingApiService
                .getBookingDetailsWithIdService(BOOKING_API + apiParameters);
        System.out.println(getBookingDetailsResponse.asString());

        softAssert.assertEquals(getBookingDetailsResponse.statusCode(), 200,
                "Status code is not matching");
        BASE_LOGGER.info("Verified the status code from the response and it is: "
                + getBookingDetailsResponse.statusCode());

        testBookingApiJsonResponseWithQueryParam();
        softAssert.assertAll();
    }

    @Test(description = "To verify the status response of the create booking api after not passing " +
            "the request body", priority = 7)
    public void testStatusResponseOfCreateBookingApi() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();
        createBookingResponse = bookingApiService.createBookingService(BOOKING_API, "");
        softAssert.assertEquals(createBookingResponse.statusCode(), 500,
                "Status code is not matching");
        softAssert.assertAll();
    }

    @Test(description = "To verify the response of the get bookings api after passing " +
            "the unregistered booking details", priority = 8)
    public void testStatusResponseOfGetBookingsApi() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();

        String apiParameters = "?firstname=" + unregisteredFirstName + "&lastname=" + lastName;
        getBookingDetailsResponse = bookingApiService.getBookingDetailsWithIdService(BOOKING_API + apiParameters);
        softAssert.assertEquals(getBookingDetailsResponse.statusCode(), 200,
                "Status code is not matching");
        softAssert.assertEquals("[]","[]", "Response is not matching");

        softAssert.assertAll();
    }

    @Test(description = "To verify the response of the get bookings api after passing the booking ID"
            , priority = 9)
    public void testStatusResponseOfGetBookingsWithInvalidIdApi() throws Exception{
        bookingApiService = new BookingApiService();
        softAssert = new SoftAssert();

        String bookingId = "/" + invalidBookingId;
        getBookingDetailsResponse = bookingApiService.getBookingDetailsWithIdService(BOOKING_API + bookingId);
        softAssert.assertEquals(getBookingDetailsResponse.statusCode(), 404,
                "Status code is not matching");
        softAssert.assertEquals(getBookingDetailsResponse.asString(), notFoundMessage,
                "Response is not matching");

        softAssert.assertAll();
    }
}