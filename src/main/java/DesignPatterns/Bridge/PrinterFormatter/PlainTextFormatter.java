package DesignPatterns.Bridge.PrinterFormatter;

import java.util.List;

public class PlainTextFormatter implements Formatter{

	@Override
	public String format(String header, List<Detail> details) {
		StringBuilder builder = new StringBuilder();
		builder.append(header + "\n");
		builder.append("details : \n");

		for(Detail detail : details) {
			builder.append(detail.getLabel());
			builder.append(" : ");
			builder.append(detail.getValue() + "\n");
		}
		return builder.toString();
	}

}
