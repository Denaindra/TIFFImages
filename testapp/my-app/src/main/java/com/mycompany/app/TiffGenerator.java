package com.mycompany.app;
import com.sun.media.jai.codec.*;

import javax.imageio.ImageIO;
import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

public class TiffGenerator {

    public TiffGenerator(){

    }
    public void startTiffgenerte(String fileDirectory){
        File faxSource = new File(fileDirectory);
        File file[] = faxSource.listFiles();
        int numImages = file.length;

        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        try {
            for (int i = 0; i < 1; i++) {
                SeekableStream ss = new FileSeekableStream(file[i]);
                ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", ss, null);

                int numPages = decoder.getNumPages();

                for(int j = 0; j < numPages; j++)
                {
                    PlanarImage op = new NullOpImage(decoder.decodeAsRenderedImage(j), null, null, OpImage.OP_IO_BOUND);
                    images.add(op.getAsBufferedImage());
                }
            }
            TIFFEncodeParam params = new TIFFEncodeParam();
            OutputStream out = new FileOutputStream(fileDirectory + "\\combined.tiff");
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
}
