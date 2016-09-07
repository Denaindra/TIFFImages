package com.mycompany.app;
import com.sun.media.jai.codec.TIFFEncodeParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
/**
 * image compress for fornt image
 */
public class ImageCompression  {
    public ImageCompression(){

    }

    public void StartCompress(String inputFilePath,String outputFilePath)throws IOException  {


        File original_f = new File(inputFilePath);
        BufferedImage img = ImageIO.read(original_f);
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(600,300,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0,600, 300, 0, 0,w, h, null);
        g.dispose();

        TIFFEncodeParam params=   new TIFFEncodeParam();
        params.setCompression(TIFFEncodeParam.COMPRESSION_JPEG_TTN2);
        FileOutputStream os=new FileOutputStream(outputFilePath);
        javax.media.jai.JAI.create("encode", dimg, os, "TIFF", params);

//        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
//        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        jpegParams.setCompressionQuality(0.5f);
//
//        final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
//        writer.setOutput(new FileImageOutputStream(new File(outputFilePath)));
//        writer.write(null, new IIOImage(dimg, null, null), jpegParams);
    }

    public BufferedImage StartCompress(BufferedImage forntImage)throws IOException  {


        //File original_f = new File(inputFilePath);
        BufferedImage img = forntImage;
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(600,300,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0,600, 300, 0, 0,w, h, null);
        g.dispose();

        TIFFEncodeParam params=   new TIFFEncodeParam();
        params.setCompression(TIFFEncodeParam.COMPRESSION_JPEG_TTN2);
        TIFFEncodeParam patram=new TIFFEncodeParam();
       // FileOutputStream os=new FileOutputStream(outputFilePath);
        //javax.media.jai.JAI.create("encode", dimg, os, "TIFF", params);
        return dimg;
//        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
//        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        jpegParams.setCompressionQuality(0.5f);
//
//        final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
//        writer.setOutput(new FileImageOutputStream(new File(outputFilePath)));
//        writer.write(null, new IIOImage(dimg, null, null), jpegParams);
    }
}
