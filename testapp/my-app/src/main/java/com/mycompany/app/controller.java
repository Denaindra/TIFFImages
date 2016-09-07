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
    private byte[] frontImage,backimage;
    private String uniqueID;
    private BufferedImage forntImageBuffer,backImageFileBuffer,grrasCaleImage;


    public controller(String unqueID,byte[] frontImage,byte[] backimage){
        this.uniqueID=unqueID;
        this.frontImage=frontImage;
        this.backimage=backimage;
    }
    public controller(){

    }
    public void createImages() throws IOException {
        createbackImage();
        createforntImage();
        createGrayImage();
        generateTiffFile();
    }

    private void createGrayImage()throws IOException {
        InputStream in = new ByteArrayInputStream(this.frontImage);
        this.forntImageBuffer = ImageIO.read(in);
        this.grrasCaleImage=imgCompress.StartCompress(forntImageBuffer);
    }

    private void createforntImage() throws IOException {


        InputStream in = new ByteArrayInputStream(this.frontImage);
        this.forntImageBuffer = ImageIO.read(in);

        imagBinary.setImageBinary(this.forntImageBuffer);
        this.forntImageBuffer= imagBinary.BinaryCompresstion();
    }
    private void createbackImage() throws IOException {

        //write byte array to buffere image
        InputStream in = new ByteArrayInputStream(this.backimage);
        this.backImageFileBuffer = ImageIO.read(in);

        imagBinary.setImageBinary(this.backImageFileBuffer);
        this.backImageFileBuffer=imagBinary.BinaryCompresstion();
    }

    public void generateTiffFile() throws IOException {
        imgTiffgen.generateTiFFFile(this.uniqueID,this.forntImageBuffer,this.backImageFileBuffer,this.grrasCaleImage);

    }
    public void StartController() throws IOException {

        imgCompress.StartCompress("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\cmpresseimg.tiff");
        imagBinary.setImageBinary("C:\\Users\\admin\\Desktop\\frontimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\frontBinary.tiff");
        imagBinary.BinaryCompress();
        imagBinary.setImageBinary("C:\\Users\\admin\\Desktop\\backimage.jpg","C:\\Users\\admin\\Desktop\\chequeOutput\\backbinary.tiff");
        imagBinary.BinaryCompress();
        imgTiffgen.generateTiFFFile(this.uniqueID);

    }


}
