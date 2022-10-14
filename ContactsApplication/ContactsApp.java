package ContactsApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsApp {

    static Path currentDir = Paths.get("ContactsApplication/contacts.txt");
    static List<String> contactList;

    static {
        try {
            contactList = Files.readAllLines(currentDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ContactsApp() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        addContact();
    }
    public static void addContact() throws IOException {

        Scanner scan = new Scanner(System.in);
        Path currentDir = Paths.get("ContactsApplication/contacts.txt");
        System.out.println("First Name: ");
        String userFirst = scan.nextLine();
        System.out.println("Last Name: ");
        String userLast = scan.nextLine();
        System.out.println("Phone Number: ");
        String userNumber = scan.nextLine();

        Contacts contact = new Contacts(userFirst, userLast, userNumber);
        String contactFormat = contact.getFirstName() + " " + contact.getLastName() + " | " + contact.getPhoneNumber();
        contactList.add(contactFormat);
        System.out.println(contactList);
    }

    public static void searchContact() throws IOException {
        Scanner scan = new Scanner(System.in);
        Path currentDir = Paths.get("ContactsApplication/contacts.txt");
        System.out.println("Search by First or Last Name: ");
        String userSearch = scan.nextLine();

        if(userSearch.contains((CharSequence) contactList)) {

        }

    }

}


