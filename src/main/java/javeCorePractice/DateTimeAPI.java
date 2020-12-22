package javeCorePractice;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DateTimeAPI {
	
	

	public static void main(String[] args) {
		System.out.println(" date(year/month/day) + time(hour/min/sec) + zone = timestamp");
		System.out.println("      ZoneDateTime : timestamp in a specific time-zone - java.time.ZonedDateTime");
		System.out.println("      Instant : timestamp in a standard time-zone (greenwich) - java.time.Instant");
		System.out.println("      Date (legacy api) : timestamp in a standard time-zone - java.util.Date");

		LocalDate localDate01 = LocalDate.parse("2020-09-02");// date (year/month/day)
		LocalTime localTime01 = LocalTime.parse("17:28:31");// time of day (hour/min)
		LocalDateTime localDateTime = localDate01.atTime(localTime01);
		ZoneId easterenZone = ZoneId.of("America/St_Johns");//zone info 
		ZonedDateTime zonedDateTime01 = localDateTime.atZone(easterenZone);
		Instant instant01 = zonedDateTime01.toInstant();
		System.out.println("\n localDate     : " + localDate01 + "\n localDateTime : " + localDateTime + 
				"\n zonedDateTime : " + zonedDateTime01 + "\n instant       : " + instant01);
		
		ZonedDateTime zonedDateTime02 = LocalDate.of(2020, 8, 3)
				.atTime(13, 20, 56)
				.atZone(ZoneId.of("America/Anchorage"));
		System.out.println("\n zonedDateTime : " + zonedDateTime02 + "\n instant       : " + zonedDateTime02.toInstant());

		Duration duration = Duration.between(zonedDateTime01, zonedDateTime02);
		Period period = Period.between(zonedDateTime01.toLocalDate(), zonedDateTime02.toLocalDate());
		System.out.println("\n duration.toDays() : " + duration.toDays()+ "\n period.getDays()       : " + period.getDays());

		System.out.println("------------------------------");
		GregorianCalendar calendar = new GregorianCalendar(1984, 02, 11, 14, 22, 59);
		Date date = calendar.getTime();//date is the milsec offset from epoch time
		long msec = date.getTime();//Returns the number of milliseconds since unix epoc time
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println(" legacy api (Calendar Date SimpleDateFormat) -> " + sdf.format(date));
		
		Date zonedDateTime01ToDate = Date.from(zonedDateTime01.toInstant());// Instant -> Date
		long elapsedTime = msec - zonedDateTime01ToDate.getTime();
		System.out.println(" elapsedTime (milliseconds) :  " + elapsedTime);
		
		
		System.out.println("\nCaelndar/Date -> instant     Instant -> Date  ");
		Instant instantFromDate = new Date().toInstant();
		Instant instantFromCalendar = new GregorianCalendar().toInstant();
		Date dateFromInstant = Date.from(Instant.now());
		Calendar calendarfromZoneDateTime = GregorianCalendar.from(ZonedDateTime.now());
		System.out.println("	Instant instantFromDate = new Date().toInstant();");
		System.out.println("	Instant instantFromCalendar = new GregorianCalendar().toInstant();");
		System.out.println("	Date dateFromInstant = Date.from(Instant.now());");
		System.out.println("	Calendar calendarfromZoneDateTime = GregorianCalendar.from(ZonedDateTime.now());");

		System.out.println("\nCompare LocalDate instances  ");
		List<LocalDate> family = Arrays.asList(
				LocalDate.of(2010, 12, 15),
				LocalDate.of(1979, 2, 15),
				LocalDate.of(2000, 9, 28),
				LocalDate.of(1985, 5, 22)
		);
		System.out.println("before sort:  " + family);
		//family.sort((a,b) -> a.compareTo(b));
		family.sort(LocalDate::compareTo);
		System.out.println("after sort:  " + family);
		
		LocalDate lastDayOfCentry = LocalDate.parse("1999-12-29");
		List<LocalDate> generationZ = family.stream()
				.filter(lastDayOfCentry::isBefore)
				//.filter(localDate -> lastDayOfCentry.isBefore(localDate))
				.collect(Collectors.toList());
		System.out.println("generationZ:  " + generationZ);
	}
}
