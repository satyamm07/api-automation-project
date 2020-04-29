package com.core.pojoServices;

import com.core.pojoServices.pojo.BookingDates;
import com.core.pojoServices.pojo.CreateBooking;

public class CreateBookingJsonCreation {

    public CreateBooking mapCreateBooking(String firstName, String lastName, Integer totalPrice,
                                          boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {

        CreateBooking createBooking = new CreateBooking();
        createBooking.setFirstName(firstName);
        createBooking.setLastName(lastName);
        createBooking.setTotalPrice(totalPrice);
        createBooking.setDepositPaid(depositPaid);
        createBooking.setBookingDates(bookingDates);
        createBooking.setAdditionalNeeds(additionalNeeds);

        return createBooking;
    }
}