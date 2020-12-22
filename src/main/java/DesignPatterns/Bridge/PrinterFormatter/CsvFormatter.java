package DesignPatterns.Bridge.PrinterFormatter;

import java.util.List;

public class CsvFormatter implements Formatter{

	@Override
	public String format(String header, List<Detail> details) {
		StringBuilder builder = new StringBuilder();
		builder.append(header);

		for(Detail detail : details) {
			builder.append(", " + detail.getLabel());
			builder.append(" : ");
			builder.append(detail.getValue() );
		}
		return builder.toString();
	}

}
