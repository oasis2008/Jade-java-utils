package jadeutils.image;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImageUtilsTest {

	private String imgSrc;
	private String imgTag;

	@Before
	public void setUp() throws Exception {
		String base = System.getProperty("user.dir");// user.dir指定了当前的路径
		this.imgSrc = base + "/src/test/resources/src/";
		this.imgTag = base + "/src/test/resources/tag/";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCaculateRatio() {
		double ratio = 1.0;

		ratio = ImageUtils.caculateScaleRatio(100, 100, 200, 250);
		assertTrue(1.99 < ratio && ratio < 2.01);

		ratio = ImageUtils.caculateScaleRatio(100, 100, 250, 200);
		assertTrue(1.99 < ratio && ratio < 2.01);

		ratio = ImageUtils.caculateScaleRatio(100, 100, 80, 30);
		assertTrue(0.29 < ratio && ratio < 0.31);

		ratio = ImageUtils.caculateScaleRatio(100, 100, 30, 80);
		assertTrue(0.29 < ratio && ratio < 0.31);
	}

	@Test
	public void testScale() throws IOException {
		// 1-缩放图像：
		// 方法一：按比例缩放
		ImageUtils.scale(this.imgSrc + "abc.jpg", this.imgTag
				+ "abc_scale1.jpg", 2);// 测试OK
		ImageUtils.scale(this.imgSrc + "abc.jpg", this.imgTag
				+ "abc_scale2.jpg", 0.5);// 测试OK
		// 方法二：按高度和宽度缩放
		ImageUtils.scale(this.imgSrc + "abc.jpg",//
				this.imgTag + "abc_scale3.jpg", 300, 3000, true);// 测试OK
		ImageUtils.scale(this.imgSrc + "abc.jpg",//
				this.imgTag + "abc_scale4.jpg", 3000, 300, true);// 测试OK
	}

	@Test
	public void testCute() {
		// 2-切割图像：
		// 方法一：按指定起点坐标和宽高切割
		ImageUtils.cut(this.imgSrc + "abc.jpg",//
				this.imgTag + "abc_cut.jpg", 0, 0, 400, 400);// 测试OK
		// 方法二：指定切片的行数和列数
		ImageUtils.cut2(this.imgSrc + "abc.jpg",//
				this.imgTag + "", 2, 2);// 测试OK
		// 方法三：指定切片的宽度和高度
		ImageUtils.cut3(this.imgSrc + "abc.jpg", //
				this.imgTag + "", 300, 300);// 测试OK
	}

	@Test
	public void testConv() {
		// 3-图像类型转换：
		ImageUtils.convert(this.imgSrc + "abc.jpg", "GIF",//
				this.imgTag + "abc_convert.gif");// 测试OK
	}

	@Test
	public void testGray() {
		// 4-彩色转黑白：
		ImageUtils.gray(this.imgSrc + "abc.jpg", //
				this.imgTag + "abc_gray.jpg");// 测试OK
	}

	@Test
	public void testPressText() {
		// 5-给图片添加文字水印：
		// 方法一：
		ImageUtils.pressText(this.imgSrc + "abc.jpg",//
				this.imgTag + "abc_pressText.jpg", //
				"我是水印文字", 0, 0, //
				"宋体", Font.BOLD, 80, Color.white, 0.5f);// 测试OK
		// 方法二：
		ImageUtils.pressText2(this.imgSrc + "abc.jpg",//
				this.imgTag + "abc_pressText2.jpg", //
				"我也是水印文字", 0, 0, //
				"黑体", 36, 80, Color.white, 0.5f);// 测试OK
	}

	@Test
	public void testPressImage() {
		// 6-给图片添加图片水印：
		ImageUtils.pressImage(this.imgSrc + "abc.jpg",//
				this.imgSrc + "logo.jpg", //
				this.imgTag + "abc_pressImage.jpg", 0, 0, 0.5f);// 测试OK

	}

}
