package ContactsApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Path currentDir = Paths.get("ContactsApplication/contacts.txt");


//        System.out.println("Name: ");
//        String userName = scan.nextLine();
//        List<String> contactName = Arrays.asList(userName);
//        System.out.println(contactName);
//        System.out.println("Number: ");
//        String userNumber = scan.nextLine();
//        List<String> contactNumber = Arrays.asList(userNumber);
//        System.out.println(contactNumber);


//        Files.write(currentDir, contactName);
//        Files.write(currentDir, contactNumber, StandardOpenOption.APPEND);

        List<String> contactList = Files.readAllLines(currentDir);
        for(int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ": " + contactList.get(i));
        }

    }

}


