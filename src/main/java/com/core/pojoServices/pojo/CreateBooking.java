package com.core.pojoServices.pojo;

public class CreateBooking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalPrice() {
        return totalprice;
    }

    public void setTotalPrice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositPaid() {
        return depositpaid;
    }

    public void setDepositPaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingDates() {
        return bookingdates;
    }

    public void setBookingDates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalNeeds() {
        return additionalneeds;
    }

    public void setAdditionalNeeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
