package viettelcyber.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Booking implements Ability {
    private String destination;
    private Date startDate;
    private Date endDate;
    private Integer adult;
    private Integer children;
    private Integer rooms;

    public Booking() {
    }

    public Booking(String destination, Date startDate, Date endDate, Integer adult, Integer children, Integer rooms) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adult = adult;
        this.children = children;
        this.rooms = rooms;
    }

    public static Booking as(Actor actor) {
        if (actor.abilityTo(Booking.class) != null) {
            return actor.abilityTo(Booking.class);
        }
        throw new ExceptionInInitializerError(actor.getName() + "doesn't has an Authenticate ability");
    }

    public static Booking loadTestData(String actorName) {
        try {
            Reader dataReader = new FileReader("src\\test\\resources\\TestData\\user." + actorName + ".properties");
            Properties dataProperties = new Properties();
            dataProperties.load(dataReader);
            String destination = dataProperties.getProperty("destination");
            Integer startDateDistance = Integer.valueOf(dataProperties.getProperty("startDate"));
            Integer endDateDistance = Integer.valueOf(dataProperties.getProperty("endDate"));
            Integer adults = Integer.valueOf(dataProperties.getProperty("adults"));
            Integer children = Integer.valueOf(dataProperties.getProperty("children"));
            Integer rooms = Integer.valueOf(dataProperties.getProperty("rooms"));

            Date startDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE, startDateDistance);
            startDate = c.getTime();

            Date endDate = new Date();
            c.setTime(endDate);
            c.add(Calendar.DATE, endDateDistance);
            endDate = c.getTime();

            return new Booking(destination, startDate, endDate, adults, children, rooms);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Booking();
    }

    public String getDestination() {
        return destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getAdult() {
        return adult;
    }

    public Integer getChildren() {
        return children;
    }

    public Integer getRooms() {
        return rooms;
    }
}