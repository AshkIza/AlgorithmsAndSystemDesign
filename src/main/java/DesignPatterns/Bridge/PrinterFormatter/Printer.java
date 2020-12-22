package DesignPatterns.Bridge.PrinterFormatter;

import java.util.List;
//implementor
public abstract class Printer {
	
	public String print(Formatter formatter){
		return formatter.format(getHeader(), getDetails());
	}
	
	public abstract String getHeader();
	public abstract  List<Detail> getDetails();
}
