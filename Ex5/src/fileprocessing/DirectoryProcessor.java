package fileprocessing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryProcessor {

	private static final String IOE_EXCEPTION_ERROR = "ERROR: I/O error \n";

	private static final String FILTER_SECTION = "FILTER";

	private static final String ORDER_SECTION = "ORDER";

	public static void main(String[] args) {
		try {
			int line = 0;
			if (args.length != 2) {
				throw new InvalidUsageException();
			}
			List<String> lines = Files.readAllLines(Paths.get(args[1]), StandardCharsets.UTF_8);
			ArrayList<String> result = new ArrayList<>();
			while (line < lines.size()) {
				if (!lines.get(line).equals(FILTER_SECTION) || (line + 2) >= lines.size() ||
					!lines.get(line + 2).equals(ORDER_SECTION)) {
					throw new BadSubSectionException();
				} else {
					if (line + 2 == lines.size() - 1 || lines.get(line + 3).equals(FILTER_SECTION)) {
						result.addAll(SectionProcessor.processSection(line + 2, args[0], lines.get(line + 1),
																	  "abs"));
						line += 3;
					} else {
						result.addAll(SectionProcessor.processSection(line + 2, args[0], lines.get(line + 1),
																	  lines.get(line + 3)));
						line += 4;
					}
				}
			}
			printFilesName(result);
		} catch (InvalidUsageException | BadSubSectionException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(IOE_EXCEPTION_ERROR);
		}
	}

	private static void printFilesName(ArrayList<String> result) {
		for (String str : result) {
			System.out.println(str);
		}
	}
}
