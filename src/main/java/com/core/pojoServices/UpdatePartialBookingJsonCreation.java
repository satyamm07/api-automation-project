package com.core.pojoServices;

import com.core.pojoServices.pojo.UpdatePartialBooking;

public class UpdatePartialBookingJsonCreation {

    public UpdatePartialBooking mapUpdatePartialBooking(String firstName, String lastname) {

        UpdatePartialBooking updatePartialBooking = new UpdatePartialBooking();
        updatePartialBooking.setFirstname(firstName);
        updatePartialBooking.setLastname(lastname);
        return updatePartialBooking;
    }
}
