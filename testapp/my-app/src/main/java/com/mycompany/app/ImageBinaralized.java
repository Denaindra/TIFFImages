package com.mycompany.app;

import com.sun.media.jai.codec.TIFFEncodeParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageBinaralized {

    private BufferedImage original, binarized;
    private String outFilepath;
    public ImageBinaralized(){

    }
    public void setImagePath(String inputFilepath,String outputFilepath) throws IOException {

        File original_f = new File(inputFilepath);
        original = ImageIO.read(original_f);
        binarized = binarize(original);
        this.outFilepath=outputFilepath;
//        File outputfile = new File(outputFilepath);
//        ImageIO.write(binarized, "jpg", outputfile);
    }
    public BufferedImage binarize(BufferedImage original) {
        int red;
        int newPixel;
        int threshold = otsuTreshold(original);
        System.out.println("threshold value "+threshold);
        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
                red = new Color(original.getRGB(i, j)).getRed();
                int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel);
            }
        }
        return binarized;
    }
    private int otsuTreshold(BufferedImage original) {
        int[] histogram = imageHistogram(original);
        int total = original.getHeight() * original.getWidth();

        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;

            if(wF == 0) break;

            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
    private int[] imageHistogram(BufferedImage input) {
        int[] histogram = new int[256];
        for(int i=0; i<histogram.length; i++) histogram[i] = 0;
        for(int i=0; i<input.getWidth(); i++) {
            for(int j=0; j<input.getHeight(); j++) {
                int red = new Color(input.getRGB (i, j)).getRed();
                histogram[red]++;
            }
        }
        return histogram;
    }
    private int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }
    public void BinaryCompress() throws IOException {
        BufferedImage frontImage,frontBinary;
        frontImage= this.binarized;
        frontBinary=  new BufferedImage(frontImage.getWidth(), frontImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        frontBinary.getGraphics().drawImage(frontImage, 0, 0, null);


        TIFFEncodeParam params=   new TIFFEncodeParam();
        params.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
        FileOutputStream os    =   new FileOutputStream(this.outFilepath);


        javax.media.jai.JAI.create("encode", frontBinary, os, "TIFF", params);

    }
}
