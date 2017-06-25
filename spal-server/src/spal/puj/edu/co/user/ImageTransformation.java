/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.user;

import com.mortennobel.imagescaling.ResampleOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * ImageTransformation is an utilitarian class that is in charge of resizing
 * images.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class ImageTransformation {

	/**
	 * Resize an image given some parameters.
	 * 
	 * @param fileItem
	 *            The Image that will be resized.
	 * @param width
	 *            The new Width of the image.
	 * @param height
	 *            The new height of the image.
	 * @param name
	 *            The name of the image.
	 * @return A byte array with the image imformation.
	 */
	public static byte[] resize(BufferedImage fileItem, int width, int height, String name) {
		try {
			ResampleOp resampleOp = new ResampleOp(width, height);
			BufferedImage scaledImage = resampleOp.filter(fileItem, null);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledImage, "png", new File("c:\\image\\" + name + "_new.png"));

			return baos.toByteArray();
		} catch (Exception ex) {
			System.out.println("Falle 2");
		}
		return null;
	}

	/**
	 * Perform a scaling to the image, that is, seeks for the largest dimension
	 * to perform the resize while maintaining the original scale.
	 * 
	 * @param maxWidth
	 *            The maximum new width.
	 * @param maxHeight
	 *            The maximum new height.
	 * @param img
	 *            The image that will be resized.
	 * @param name
	 *            The name of the image.
	 * @return A byte array with the image imformation.
	 */
	public static byte[] resizeAdjustMax(int maxWidth, int maxHeight, BufferedImage img, String name) {
		try {
			BufferedImage bufimg = img;
			int img_width = bufimg.getWidth();
			int img_height = bufimg.getHeight();
			if (img_width > maxWidth || img_height > maxHeight) {
				float factx = (float) img_width / maxWidth;
				float facty = (float) img_height / maxHeight;
				float fact = (factx > facty) ? factx : facty;
				img_width = (int) ((int) img_width / fact);
				img_height = (int) ((int) img_height / fact);
			}
			return resize(bufimg, img_width, img_height, name);
		} catch (Exception ex) {
			System.out.println("Falle 1");
		}
		return null;
	}
}