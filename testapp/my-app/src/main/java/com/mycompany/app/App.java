package com.mycompany.app;
import java.io.IOException;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {


        //pass by local images
        controller con=new controller();
        con.StartController();

        //pass by byte arrasy
//        controller con=new controller(String unqueID,byte[] frontImage,byte[] backimage);
//        con.StartController();
    }

}
