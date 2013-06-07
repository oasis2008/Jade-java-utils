package jadeutils.extjs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoteTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test2() {
		String ss = "2. 3 三维场景建模 ";
		Pattern repexChapter = Pattern.compile("^\\d.*");
		Matcher matcher = repexChapter.matcher(ss);
		if (matcher.matches()) {
			System.out.println(ss);
		}
	}

	@Test
	public void test() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("src.txt")));
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("note", false)));
			String line = reader.readLine();
			Map<String, String> lm = new HashMap<String, String>();
			boolean canWriteBlank = true;
			StringBuffer sb = new StringBuffer();
			while (line != null) {
				line = line.trim();
				boolean isblank = "".equals(line);
				if (!lm.containsKey(line)) {
					if (isblank) {
						if (canWriteBlank) {
							sb.append("\n");
							String ss = sb.toString();
							if (ss.contains("章章")) {
								writer.write("\n\n\n");
							}
							writer.write(ss);
							if (ss.contains("章章")) {
								writer.write("\n");
							}
							Pattern repexChapter = Pattern.compile(".*1.*");
							Matcher matcher = repexChapter.matcher(ss);
							if (matcher.matches()) {
								System.out.println(ss);
							}
							sb = new StringBuffer();
							canWriteBlank = false;
						}
					} else {
						canWriteBlank = true;
						lm.put(line, line);
						sb.append(line);
					}
				}
				line = reader.readLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
