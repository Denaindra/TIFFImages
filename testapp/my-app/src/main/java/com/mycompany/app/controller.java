package com.mycompany.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by admin on 06/09/2016.
 */
public class controller {

    private ImageBinaralized imagBinary=new ImageBinaralized();
    private ImageCompression imgCompress=new ImageCompression();
    private TiffGenerator imgTiffgen=new TiffGenerator();
    private testbibary resize=new testbibary();
    private byte[] frontImage,backimage,grayimage;
    private String uniqueID;
    private BufferedImage forntImageBuffer,backImageFileBuffer;




    public controller(String unqueID,byte[] frontImage,byte[] backimage){
        this.uniqueID=unqueID;
        this.frontImage=frontImage;
        this.grayimage=frontImage;
        this.backimage=backimage;
    }
    public controller(){

    }
    public void createImages() throws IOException {
        createbackImage();
        createforntImage();
        createGrayImage();
    }

    private void createGrayImage()throws IOException {
        InputStream in = new ByteArrayInputStream(this.grayimage);
        this.forntImageBuffer = ImageIO.read(in);
        imgCompress.StartCompress("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\cmpresseimg.jpg");
    }

    private void createforntImage() throws IOException {
       // convert image to byte array

        // write byte array to buffere image
        InputStream in = new ByteArrayInputStream(this.frontImage);
        this.forntImageBuffer = ImageIO.read(in);

        imagBinary.setImagePath("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\frontBinary.tiff");
        imagBinary.BinaryCompress();
    }
    private void createbackImage() throws IOException {
        //converto image to byet array

        //write byte array to buffere image
        InputStream in = new ByteArrayInputStream(this.backimage);
        this.backImageFileBuffer = ImageIO.read(in);

        imagBinary.setImagePath("C:\\Users\\admin\\Desktop\\backimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\backbinary.tiff");
        imagBinary.BinaryCompress();
    }

    public void generateTiffFile() throws IOException {
        resize.generateTiFFFile();

    }
    public void StartController() throws IOException {

        imgCompress.StartCompress("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\cmpresseimg.jpg");
        imagBinary.setImagePath("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\frontBinary.tiff");
        imagBinary.BinaryCompress();
        imagBinary.setImagePath("C:\\Users\\admin\\Desktop\\backimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\backbinary.tiff");
        imagBinary.BinaryCompress();
        resize.convertTIFF();
        resize.generateTiFFFile();

//        //imgTiffgen.startTiffgenerte("C:\\Users\\admin\\Desktop\\chequeOutput");
//           // resize.testimage("","");
    }


}
