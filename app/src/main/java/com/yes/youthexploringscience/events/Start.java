package com.yes.youthexploringscience.events;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Dan on 8/1/2016.
 */
public class Start implements Serializable {
    private String dateTime;
    private String date;

    public Start(String dateTime) {
        this.dateTime = dateTime;
        date = "";
    }

    public Start(String date, boolean bool) {
        this.date = date;
        dateTime = "";
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeInMilliseconds() {
        String[] components = date.equals("") ? dateTime.split("-|T|:") : date.split("-|T|:");
        if (components.length == 3) {

        } else if (components.length == 8){

        }
        return "";
    }

    public String getFormattedDate() {

        Calendar today = Calendar.getInstance();
        Calendar event = Calendar.getInstance();

        String[] eventComponents = date.equals("") ? dateTime.split("-|T|:") : date.split("-|T|:");

        // Only include time if time is provided.
        if (eventComponents.length == 3) {
            event.set(Integer.parseInt(eventComponents[0]),
                    Integer.parseInt(eventComponents[1]) - 1,
                    Integer.parseInt(eventComponents[2]));
        } else if (eventComponents.length == 8) {
            event.set(Integer.parseInt(eventComponents[0]),
                    Integer.parseInt(eventComponents[1]) - 1,
                    Integer.parseInt(eventComponents[2]),
                    Integer.parseInt(eventComponents[3]),
                    Integer.parseInt(eventComponents[4]));
        }

        String formattedDate = "";
        if (today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR)) {
            formattedDate += "Today, ";
        } else if (today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) - 1) {
            formattedDate += "Tomorrow, ";
        }

        // If no time is provided, Must be an all day event otherwise include time.
        if (eventComponents.length == 3) {
            formattedDate += getMonthName(event) + " "
                    + event.get(Calendar.DAY_OF_MONTH) + ", 12:00 AM";
        } else {
            formattedDate += getMonthName(event) + " "
                    + event.get(Calendar.DAY_OF_MONTH) + ", "
                    + event.get(Calendar.HOUR) % 12 + ":"
                    + String.format("%02d", event.get(Calendar.MINUTE))
//                    + event.get(Calendar.MINUTE)
                    + (event.get(Calendar.AM_PM) == 0 ? " AM" : " PM");
        }

        return formattedDate;
    }

    private String getMonthName(Calendar event) {
        switch (event.get(Calendar.MONTH)) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "No Month";
        }
    }

    public String getCompare() {
        if (date.equals("")) {
            return dateTime;
        } else {
            return date;
        }
    }

    @Override
    public String toString() {
        return dateTime.equals("") ? "\t\t\t\"date\": \"" + date + "\"" : "\t\t\t\"dateTime\": \"" + dateTime + "\"";
    }
}
