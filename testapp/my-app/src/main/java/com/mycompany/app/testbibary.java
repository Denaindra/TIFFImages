package com.mycompany.app;
import com.sun.media.jai.codec.*;

import javax.imageio.ImageIO;
import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class testbibary {

    public testbibary(){
    }

    public void generateTiFFFile() throws IOException {
        String inputDir = "C:\\Users\\admin\\Desktop\\chequeOutput\\";
        File original_f1 = new File("C:\\Users\\admin\\Desktop\\chequeOutput\\frontBinary.tiff");
        File original_f2 = new File("C:\\Users\\admin\\Desktop\\chequeOutput\\backbinary.tiff");
        File original_f3 = new File("C:\\Users\\admin\\Desktop\\chequeOutput\\cmpresseimg.tiff");
        //ArrayList<File> file = new ArrayList<File>();
                  //file.add(original_f);
        File file[]={original_f1,original_f2,original_f3};
        int numImages = file.length;
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        try {
            for (int i = 0; i < numImages; i++) {

                SeekableStream ss = new FileSeekableStream(file[i]);
                //ImageDecoder decoder = getDecoder(ss);
                ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", ss, null);
                int numPages = decoder.getNumPages();
                System.out.print(numPages+"a*");
                for(int j = 0; j < numPages; j++)
                {
                    PlanarImage op = new NullOpImage(decoder.decodeAsRenderedImage(j), null, null, OpImage.OP_IO_BOUND);
                    BufferedImage obj=ImageIO.read(file[j]);
                    images.add(op.getAsBufferedImage());
                }
            }
            TIFFEncodeParam params = new TIFFEncodeParam();
            OutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\chequeOutput\\Combined.IMG");
            ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", out, params);
            ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
            for (int i = 1; i < images.size(); i++) {
                imageList.add(images.get(i));
            }
            params.setExtraImages(imageList.iterator());
            encoder.encode(images.get(0));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//JPEG
}
