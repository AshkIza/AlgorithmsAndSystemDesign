package DesignPatterns.Bridge.PrinterFormatter;

import java.util.List;
//abstraction
public interface Formatter {
	public String format(String header, List<Detail> details);
}
