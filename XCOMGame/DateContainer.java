package XCOMGame;

import javalib.colors.*;
import javalib.worldimages.*;

/**
 * Created by User on 11/14/2014.
 */
public class DateContainer {

    private static final int febStart = 32;
    private static final int marchStart = 60;
    private static final int aprilStart = 91;
    private static final int mayStart = 121;
    private static final int juneStart = 152;
    private static final int julyStart = 182;
    private static final int augStart = 213;
    private static final int septStart = 244;
    private static final int octStart = 274;
    private static final int novStart = 304;
    private static final int decStart = 335;

    int year;
    int day;
    int hour;
    int minute;

    public DateContainer(int year, int day, int hour, int minute) {
        this.year = year;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public DateContainer hourAdvance() {
        int newYear = year;
        int newDay = day;
        int newHour = hour + 1;
        if (newHour == 24) {
            newHour = 0;
            newDay = newDay + 1;
            if ((newDay == 366 && year%4 != 0) || newDay == 367) {
                newDay = 1;
                newYear++;
            }
        }
        return new DateContainer(newYear, newDay, newHour, minute);
    }

    //FIX
    public DateContainer hourAdvance(double multiplier) {
        int newMinute = minute + (int) Math.floor(60 * (multiplier - Math.floor(multiplier)));
        boolean overshoot = false;
        if (newMinute >= 60) {
            newMinute = newMinute - 60;
            overshoot = true;
        }
        DateContainer oneStep = new DateContainer(year, day, hour, newMinute);
        for (int i = 0; i < Math.floor(multiplier); i++) {
            oneStep = oneStep.hourAdvance();
        }
        if (overshoot) {
            oneStep = oneStep.hourAdvance();
        }
        return oneStep;
    }

    public WorldImage getImage() {
        return ImageFactory.textImage(MapWorld.datePosn, this.toString(), 30, 1, new White());
    }

    public String toString() {
        String month;
        String dayString;
        String hourString = "" + hour;
        String minuteString = "" + minute;
        if (year % 4 != 0) {
            if (day < febStart) {
                month = "January";
                dayString = ("" + day);
            }
            else if (day < marchStart) {
                month = "February";
                dayString = ("" + (day - febStart + 1));
            }
            else if (day < aprilStart) {
                month = "March";
                dayString = ("" + (day - marchStart + 1));
            }
            else if (day < mayStart) {
                month = "April";
                dayString = ("" + (day - aprilStart + 1));
            }
            else if (day < juneStart) {
                month = "May";
                dayString = ("" + (day - mayStart + 1));
            }
            else if (day < julyStart) {
                month = "June";
                dayString = ("" + (day - juneStart + 1));
            }
            else if (day < augStart) {
                month = "July";
                dayString = ("" + (day - julyStart + 1));
            }
            else if (day < septStart) {
                month = "August";
                dayString = ("" + (day - augStart + 1));
            }
            else if (day < octStart) {
                month = "September";
                dayString = ("" + (day - septStart + 1));
            }
            else if (day < novStart) {
                month = "October";
                dayString = ("" + (day - octStart + 1));
            }
            else if (day < decStart) {
                month = "November";
                dayString = ("" + (day - novStart + 1));
            }
            else {
                month = "December";
                dayString = ("" + (day - decStart + 1));
            }
        } else {
            if (day < febStart) {
                month = "January";
                dayString = ("" + day);
            }
            else if (day < marchStart + 1) {
                month = "February";
                dayString = ("" + (day - febStart + 1));
            }
            else if (day < aprilStart + 1) {
                month = "March";
                dayString = ("" + (day - marchStart));
            }
            else if (day < mayStart + 1) {
                month = "April";
                dayString = ("" + (day - aprilStart));
            }
            else if (day < juneStart + 1) {
                month = "May";
                dayString = ("" + (day - mayStart));
            }
            else if (day < julyStart + 1) {
                month = "June";
                dayString = ("" + (day - juneStart));
            }
            else if (day < augStart + 1) {
                month = "July";
                dayString = ("" + (day - julyStart));
            }
            else if (day < septStart + 1) {
                month = "August";
                dayString = ("" + (day - augStart));
            }
            else if (day < octStart + 1) {
                month = "September";
                dayString = ("" + (day - septStart));
            }
            else if (day < novStart + 1) {
                month = "October";
                dayString = ("" + (day - octStart));
            }
            else if (day < decStart + 1) {
                month = "November";
                dayString = ("" + (day - novStart));
            }
            else {
                month = "December";
                dayString = ("" + (day - decStart));
            }

        }
        if (hour < 10) {
            hourString = "0"+hour;
        }
        if (minute < 10) {
            minuteString = "0"+minute;
        }
        return (month + " " + dayString + " " + year + " " + hourString + ":" + minuteString);
    }

}
