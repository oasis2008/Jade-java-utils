package jadeutils.misc;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test() {
		Pattern p  = Pattern.compile("[0-9A-Za-z]*");
		assertTrue(p.matcher("").matches());
	}

}
