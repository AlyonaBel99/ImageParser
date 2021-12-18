import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите ссылку на сайт: ");
        Scanner scaner = new Scanner(System.in);
        String input = scaner.nextLine();   // ввод данных
        Parser parser = new Parser(input);
        System.out.println("Link = " + parser.getlink());
        System.out.println("Подождите идет загрузка ... ");
        InformationImage image = new InformationImage(parser.parserUrlImage());
        image.ImageInfo();
        image.outlistImageInfo();

    }
}
