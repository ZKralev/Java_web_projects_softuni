package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.enums.Service;


import java.time.ZonedDateTime;
import java.util.Date;

public class AddBookingDTO {

    private String name;

    private String email;

    private Date reservationDate;

    private String reservationTime;

    private String service;

    public AddBookingDTO() {
    }

    public String getName() {
        return name;
    }

    public AddBookingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddBookingDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public AddBookingDTO setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public AddBookingDTO setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
        return this;
    }

    public String getService() {
        return service;
    }

    public AddBookingDTO setService(String service) {
        this.service = service;
        return this;
    }
}
