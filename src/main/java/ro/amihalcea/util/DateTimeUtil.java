package ro.amihalcea.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {


    public static String getStringFromDate(Date date) {
        if (date != null)
        return date.toString();
        return "MISSING";
    }

    public static Date getDateFromString(String stringDate) {
        return Date.from(
                LocalDate.parse(stringDate,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        .atStartOfDay()
                        .toInstant((ZoneOffset) ZoneId.systemDefault()));
    }
}
