import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InformationImage {

    private List<String> listOfUrlImage;
    private List<List<String>> listImageInfo;


    InformationImage(List<String> listOfUrlImage){
        this.listOfUrlImage = listOfUrlImage;
    }

    public void ImageInfo (){
         listImageInfo = new ArrayList<>();
        for(int i =0; i<listOfUrlImage.size();i++){
            //System.out.print(i+" ");
            listImageInfo.add(URLInfo(listOfUrlImage.get(i)));
        }
    }

    public void outlistImageInfo (){
        for(int i=0,j=1;i<listImageInfo.size();i++,j++ ){
            System.out.println(j + " URL: " + listImageInfo.get(i).get(0) + " " +
                    listImageInfo.get(i).get(1)  + "px, " + listImageInfo.get(i).get(2) + "KB");
        }
    }

    private List<String> URLInfo(String urlImage){
        List<String> result = new ArrayList();
        result.add(urlImage);
        URL url = null;
        try {
            url = new URL(urlImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url.openStream();
        byte[] b = new byte[2^16];
        int read = is.read(b);
        while (read>-1) {
            baos.write(b,0,read);
            read = is.read(b);
        }
        float countInBytes = (float) baos.toByteArray().length /1000;
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Image image = ImageIO.read(bais);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        result.add(width + "x" + height);
        result.add(Float.toString(countInBytes));
    } catch (IOException e) {
            System.out.println("URL " + urlImage + " ошибка: "+e);
        }
        return result;
    }
}

