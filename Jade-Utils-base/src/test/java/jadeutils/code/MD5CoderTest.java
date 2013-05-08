package jadeutils.code;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author morgan
 * 
 */
public class MD5CoderTest {


	@Test
	public void testMD5String() {
		Assert.assertEquals("bf17f4ab50fe9cb9e90ac041351d3946",
				MD5Coder.encodeString("哈哈哈"));
	}

}
