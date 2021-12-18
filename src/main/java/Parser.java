import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String link ;
    private List<String> listOfUrlImage;

    Parser(String link ){
        if(link .isEmpty()==true) {
            link  = "https://forum.awd.ru/viewtopic.php?f=1011&t=165935"; // установка дефолт url
        }
        this.link  = link ;
    }

    public String getlink (){return this.link;}


    private Document getPage()  {
    Document page = null;
    try {
        page = Jsoup.connect(this.link).get();
    } catch (IOException e) {
        System.out.println("Не удалось подключиться к серверу, ошибка: "+ e);
    }
    return page;
}

    public List<String> parserUrlImage(){
        Document document = getPage();
        Elements elementImg = document.select("img");
        listOfUrlImage = new ArrayList<>();
        for (Element element:elementImg) {
            String str = element.attr("src");
            if (str.contains("://") == true && str.isEmpty()==false){
            if (str.contains("https://")==false){
                str = str.replaceFirst("http","https");
            }
            this.listOfUrlImage.add(str);
            }
        }
        return listOfUrlImage;
    }
}
