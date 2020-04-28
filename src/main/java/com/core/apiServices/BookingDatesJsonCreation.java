package com.core.apiServices;

import com.core.pojoServices.BookingDates;

public class BookingDatesJsonCreation {

    public BookingDates mapBookingDates(String checkIn, String checkOut) {

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn(checkIn);
        bookingDates.setCheckOut(checkOut);

        return bookingDates;
    }
}
