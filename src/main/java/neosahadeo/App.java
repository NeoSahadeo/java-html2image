package neosahadeo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.cli.*;
import gui.ava.html.image.HtmlImageGenerator;
import java.awt.Dimension;

public class App {
	protected static Map<String, Object> dict = new HashMap<>();
	protected static String html;

	private static void generate() {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(html);
		imageGenerator.setSize(new Dimension((int) dict.get("width"), (int) dict.get("height")));
		imageGenerator.saveAsImage((String) dict.get("name"));
		imageGenerator.show();
	}

	private static void replace_default(String name, String value) {
		if (value == null)
			return;
		try {
			dict.put(name, Integer.parseInt(value));
		} catch (NumberFormatException e) {
			dict.put(name, value);
		}
	}

	public static void main(String[] args) throws IOException {
		dict.put("name", "snappy-snapped.png");
		dict.put("width", 1920);
		dict.put("height", 1080);

		CommandLineParser parser = new DefaultParser();

		Options options = new Options();
		options.addOption("w", "width", true, "Width of image");
		options.addOption("h", "height", true, "Height of image");
		options.addOption("n", "name", true, "Name of the image");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder allLines = new StringBuilder();
		String __line;
		while ((__line = reader.readLine()) != null) {
			allLines.append(__line);
			allLines.append("\n"); // explicitly add newline after each line
		}
		html = allLines.toString();

		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);
			replace_default("name", line.getOptionValue("n"));
			replace_default("width", line.getOptionValue("w"));
			replace_default("height", line.getOptionValue("h"));
			generate();
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}
}
