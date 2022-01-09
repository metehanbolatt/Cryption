package app;

import service.CryptionService;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

public class MainApp {

    File file = new File("secure.txt");

    public static void main(String[] args) {
        String key128Bit = "SgVkYp3s6v9y/B?E";

        while (true){
            System.out.println("1 -> Mesaj şifrelemek\n2 -> Şifreli mesajı çözmek");
            Scanner menuScanner = new Scanner(System.in);
            String menu = menuScanner.next();
            if (menu.equals("1")){
                System.out.println("Lütfen mesajını yaz.");
                Scanner messageScanner = new Scanner(System.in);
                String message = messageScanner.next();
                System.out.println("Lütfen mesajını şifrelemek için bir şifre oluştur.");
                Scanner passwordScanner = new Scanner(System.in);
                String password = passwordScanner.next();
                String cipherText = CryptionService.encryption(key128Bit, message, password);
                System.out.println("Şifreli mesajın: " + cipherText);
                break;
            }else if (menu.equals("2")){
                System.out.println("Şifresini çözmek istediğiniz mesajı girin lütfen");
                Scanner messageScanner = new Scanner(System.in);
                StringBuilder messageBuilder = new StringBuilder();
                while (messageScanner.hasNextLine()){
                    messageBuilder.append(messageScanner.nextLine());
                    System.out.println(messageBuilder);
                    System.out.println("Şifreyi giriniz...");
                    Scanner passwordScanner = new Scanner(System.in);
                    String password = passwordScanner.next();
                    String decMessage = CryptionService.decryption(key128Bit, messageBuilder.toString(), password);
                    System.out.println(decMessage);
                }
                break;
            }else{
                System.out.println("Yanlış tuşlama...");
            }
        }

    }

    // Eğer dosyadan veri okumak istersek..
    public String readNio() throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        StringBuilder newString = new StringBuilder();
        Files.readAllLines(path).forEach(newString::append);
        return newString.toString();
    }

    // Eğer dosyaya veri yazmak istersek..
    public void writeNio(String cipherText) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        Files.write(path, Collections.singleton(cipherText));
    }
}
