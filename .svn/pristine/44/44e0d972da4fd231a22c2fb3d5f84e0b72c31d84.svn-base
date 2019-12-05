/**
 * @author 刘志欣
 * ReduceImgUtil.java
 * 2019年1月10日
 */
package com.isolver.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ReduceImgUtil {
	/***
	 * 创建缩略图（压缩图片）
	 * 
	 * @param imgsrc  原图片
	 * @param imgpath 图片路径
	 * @param imgName 图片名
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static String reduceImg(File imgsrc, String imgpath, String imgName) {
		String imgdist = imgpath;
		File file1 = new File(imgdist);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		String imagePath = imgdist + File.separator + "resize" + imgName;
		int widthdist = 400; // 自定义压缩
		int heightdist = 250;
		Float rate = 0.0f; // 按原图片比例压缩（默认）
		try {

			// 检查图片文件是否存在
			if (!imgsrc.exists()) {
				System.out.println("文件不存在");
			}
			// 如果比例不为空则说明是按比例压缩
			if (rate != null && rate > 0) {
				// 获得源图片的宽高存入数组中
				int[] results = getImgWidthHeight(imgsrc);
				if (results == null || results[0] == 0 || results[1] == 0) {
					return null;
				} else {
					// 按比例缩放或扩大图片大小，将浮点型转为整型
					widthdist = (int) (results[0] * rate);
					heightdist = (int) (results[1] * rate);
				}
			}
			// 开始读取文件并进行压缩
			java.awt.Image src = ImageIO.read(imgsrc);
			// 构造一个类型为预定义图像类型之一的 BufferedImage
			BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
			// 绘制图像 getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, java.awt.Image.SCALE_SMOOTH), 0, 0,
					null);
			String formatName = imagePath.substring(imagePath.lastIndexOf(".") + 1);
			ImageIO.write(tag, formatName, new File(imagePath));
			return imagePath;
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取图片宽度和高度
	 * 
	 * @param
	 * @return 返回图片的宽度
	 */
	public static int[] getImgWidthHeight(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = { 0, 0 };
		try {
			// 获得文件输入流
			is = new FileInputStream(file);
			// 从流里将图片写入缓冲图片区
			src = ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图片宽
			result[1] = src.getHeight(null);// 得到源图片高
			is.close(); // 关闭输入流
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return result;
	}
}
