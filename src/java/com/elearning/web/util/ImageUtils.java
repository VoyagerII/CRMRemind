package com.elearning.web.util;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;


public final class ImageUtils {

    public static String[] suportFormatName = {"png", "gif", "jpg", "jpeg"};
    
    /**
     * Whether or not extention name is suported
     *
     * @param extentionName - The file extention name
     * @return Whether or not suport
     */
    private static boolean isSuportImage(String extentionName) {
        for (String format : suportFormatName) {
            if (StringUtils.equalsIgnoreCase(extentionName, format)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resizes an image
     *
     * @param imgName   The image name to resize. Must be the complet path to the file
     * @param maxWidth  The image's max width
     * @param maxHeight The image's max height
     * @return A resized <code>BufferedImage</code>
     */
    public static BufferedImage resizeImage(String imgName, int maxWidth, int maxHeight) throws IOException, FileNameException {
        String extName = FilenameUtils.getExtension(imgName);
        return resizeImage(ImageIO.read(new File(imgName)), StringUtils.lowerCase(extName), maxWidth, maxHeight);

    }

   
    /**
     *  将图片的部分截取到另外一个文件中。 
     * 
     * @param sourcePath ： 源文件
     * @param targetPath ： 目标文件
     * @param x1 ： 左上 x
     * @param y1 ： 左上 y
     * @param x2 ： 右下 x
     * @param y2 ： 右下 y
     * 
     * @return
     */
    public static boolean CutImage(String sourcePath, String targetPath,  
            int x1, int y1, int x2, int y2) 
    {
    	boolean bret =false;
        try 
        {
        	
        	String extName = FilenameUtils.getExtension(sourcePath);
        	String suffix = StringUtils.lowerCase(extName);
        	 
            Image img;  
            ImageFilter cropFilter;  
            File sourceImgFile = new File(sourcePath);  
            BufferedImage bi = ImageIO.read(sourceImgFile);  
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            int destWidth = x2 - x1;
            int destHeight = y2 - y1;
            if (srcWidth >= destWidth && srcHeight >= destHeight) 
            { 
                Image image = bi.getScaledInstance(srcWidth, srcHeight,  
                        Image.SCALE_DEFAULT);  
                cropFilter = new CropImageFilter(x1, y1, destWidth, destHeight);  
                img = Toolkit.getDefaultToolkit().createImage(  
                        new FilteredImageSource(image.getSource(), cropFilter));  
                BufferedImage tag = new BufferedImage(destWidth, destHeight,  
                        BufferedImage.TYPE_INT_RGB);  
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);  
                g.dispose();  
                ImageIO.write(tag, suffix, new File(targetPath));
                bret =true;
            }  
        } catch (Exception e) 
        {  
            e.printStackTrace();  
        }  
        
        return bret;
    }  
    
    /**
     * 得到某一图片文件的宽 ， 高 。 
     * @param sfile
     * @return
     */
    
    public static HashMap <String , Integer>getImageWithAndHeight(String sfile)
    {
    	return getImageWithAndHeight(new File(sfile));
    }
    public static HashMap <String , Integer>getImageWithAndHeight(File file)
    {
    	HashMap<String , Integer> hs=new HashMap<String , Integer>();
    	try
    	{
    		BufferedImage img = ImageIO.read(file);
    		hs.put("width", img.getWidth());
    		hs.put("height", img.getHeight());
    		
    	}catch(Exception se)
    	{
    		se.printStackTrace();
    		
    	}
    	
    	return hs;
    }
    
    
    /**
     * Resizes an image.
     *
     * @param image      The image to resize
     * @param maxWidth   The image's max width
     * @param maxHeight  The image's max height
     * @param formatName The format name
     * @return A resized <code>BufferedImage</code>
     */
    public static BufferedImage resizeImage(BufferedImage image, String formatName, int maxWidth, int maxHeight) throws FileNameException {
        if (isSuportImage(formatName)) {
        } else {
            throw new FileNameException(String.format("Bad FileName Extention.[%s]", formatName));
        }

        Dimension largestDimension = new Dimension(maxWidth, maxHeight);

        // Original size
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        float aspectRation = (float) imageWidth / imageHeight;

        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            if ((float) largestDimension.width / largestDimension.height > aspectRation) {
                largestDimension.width = (int) Math.ceil(largestDimension.height * aspectRation);
            } else {
                largestDimension.height = (int) Math.ceil(largestDimension.width / aspectRation);
            }

            imageWidth = largestDimension.width;
            imageHeight = largestDimension.height;
        }

        return createHeadlessSmoothBufferedImage(image, formatName, imageWidth, imageHeight);
    }

    /**
     * Saves an image to the disk.
     *
     * @param image      The image to save
     * @param toFileName The filename to use.
     * @return <code>false</code> if no appropriate writer is found
     */
    public static boolean saveImage(BufferedImage image, String toFileName) throws IOException, FileNameException {
        String formatName = FilenameUtils.getExtension(toFileName);
        if (isSuportImage(formatName)) {
        } else {
            throw new FileNameException(String.format("Bad FileName Extention.[%s]", toFileName));
        }
        return ImageIO.write(image, formatName, new File(toFileName));

    }


    /**
     * Creates a <code>BufferedImage</code> from an <code>Image</code>. This method can
     * function on a completely headless system. This especially includes Linux and Unix systems
     * that do not have the X11 libraries installed, which are required for the AWT subsystem to
     * operate. The resulting image will be smoothly scaled using bilinear filtering.
     *
     * @param source     The image to convert
     * @param width      The desired image width
     * @param height     The desired image height
     * @param formatName String
     * @return The converted image
     */
    public static BufferedImage createHeadlessSmoothBufferedImage(BufferedImage source, String formatName, int width, int height) {
        int type;
        if (StringUtils.equalsIgnoreCase(formatName, "png") && hasAlpha(source)) {
            type = BufferedImage.TYPE_INT_ARGB;
        } else {
            type = BufferedImage.TYPE_INT_RGB;
        }

        BufferedImage dest = new BufferedImage(width, height, type);

        int sourcex;
        int sourcey;

        double scalex = (double) width / source.getWidth();
        double scaley = (double) height / source.getHeight();

        int x1;
        int y1;

        double xdiff;
        double ydiff;

        int rgb;
        int rgb1;
        int rgb2;

        for (int y = 0; y < height; y++) {
            sourcey = y * source.getHeight() / dest.getHeight();
            ydiff = scale(y, scaley) - sourcey;

            for (int x = 0; x < width; x++) {
                sourcex = x * source.getWidth() / dest.getWidth();
                xdiff = scale(x, scalex) - sourcex;

                x1 = Math.min(source.getWidth() - 1, sourcex + 1);
                y1 = Math.min(source.getHeight() - 1, sourcey + 1);

                rgb1 = getRGBInterpolation(source.getRGB(sourcex, sourcey), source.getRGB(x1, sourcey), xdiff);
                rgb2 = getRGBInterpolation(source.getRGB(sourcex, y1), source.getRGB(x1, y1), xdiff);

                rgb = getRGBInterpolation(rgb1, rgb2, ydiff);

                dest.setRGB(x, y, rgb);
            }
        }

        return dest;
    }

    private static double scale(int point, double scale) {
        return point / scale;
    }

    private static int getRGBInterpolation(int value1, int value2, double distance)
    {
        int alpha1 = (value1 & 0xFF000000) >>> 24;
        int red1 = (value1 & 0x00FF0000) >> 16;
        int green1 = (value1 & 0x0000FF00) >> 8;
        int blue1 = (value1 & 0x000000FF);

        int alpha2 = (value2 & 0xFF000000) >>> 24;
        int red2 = (value2 & 0x00FF0000) >> 16;
        int green2 = (value2 & 0x0000FF00) >> 8;
        int blue2 = (value2 & 0x000000FF);

        int rgb = ((int) (alpha1 * (1.0 - distance) + alpha2 * distance) << 24)
                | ((int) (red1 * (1.0 - distance) + red2 * distance) << 16)
                | ((int) (green1 * (1.0 - distance) + green2 * distance) << 8)
                | (int) (blue1 * (1.0 - distance) + blue2 * distance);

        return rgb;
    }

    /**
     * Determines if the image has transparent pixels.
     *
     * @param image The image to check for transparent pixel.s
     * @return <code>true</code> of <code>false</code>, according to the result
     */
    public static boolean hasAlpha(Image image) 
    {
        try 
        {
            PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
            pg.grabPixels();

            return pg.getColorModel().hasAlpha();
        }
        catch (InterruptedException e) {
            return false;
        }
    }

    public static void main(String[] args) 
    {
        String originalPath = "D:\\qq.jpg";
        String destinalPath = "D:\\qq100_50.jpg";
        try 
        {
        	boolean b = CutImage(originalPath,destinalPath, 0,0,50,400);
        	System.out.println ("cutImage return :" + b);
            
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        
    }
}
