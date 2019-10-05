import java.io.File;
import edu.duke.*;

public class BatchGrayscale {
	
	//Start with image you want at inImage
	public ImageResource grayscale(ImageResource inImage) {
		//Make blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's sum of (R, G, B)
			//divide by 3 (call it avg)
			int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
			//set red pixel to avg
			pixel.setRed(avg);
			//set green pixel to avg
			pixel.setGreen(avg);
			//set blue pixel to avg
			pixel.setBlue(avg);
		}
		return outImage;
	}
	
	public ImageResource invertImage(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for (Pixel pixel: outImage.pixels()) {
			//look at corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's sum of (R, G, B)
			//divide by 3 (call it avg)
			int newRed = 255 - inPixel.getRed();
			int newGreen = 255 - inPixel.getGreen();
			int newBlue = 255 - inPixel.getBlue();
			pixel.setRed(newRed);
			pixel.setGreen(newGreen);
			pixel.setBlue(newBlue);
		}
		return outImage;
	}
	
	public void convertManyGray() {
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource grayImage = grayscale(inImage);
			grayImage.draw();
			saveImage("gray", inImage, grayImage);
		}
	}
	
	public void invertMany() {
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource invImage = invertImage(inImage);
			invImage.draw();
			saveImage("invert", inImage, invImage);
		}
	}
	
	public void saveImage(String name, ImageResource inImage, ImageResource outImage) {
		String filename = inImage.getFileName();
		String newFilename = name + "-" + filename;
		outImage.setFileName(newFilename);
		outImage.save();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatchGrayscale gray = new BatchGrayscale();

		gray.convertManyGray();
		gray.invertMany();
	}

}
