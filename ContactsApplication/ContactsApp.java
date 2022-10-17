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
    static List<List<String>> subContact = new ArrayList<>();

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
        splitContact();
        addContact();
    }

    public static void addContact() throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("First Name: ");
        String userFirst = scan.nextLine();
        System.out.println("Last Name: ");
        String userLast = scan.nextLine();
        System.out.println("Phone Number: ");
        String userNumber = scan.nextLine();

        Contacts contact = new Contacts(userFirst, userLast, userNumber);
        List<String> contactFormat = Collections.singletonList(contact.getFirstName() + " " + contact.getLastName() + " | " + contact.getPhoneNumber());
        Files.write(currentDir, contactFormat, StandardOpenOption.APPEND);
        contactList.add(contactFormat.toString());
    }

    public static void searchContact() throws IOException {
        Scanner scan = new Scanner(System.in);
        Path currentDir = Paths.get("ContactsApplication/contacts.txt");
        System.out.println("Search by First or Last Name: ");
        String userSearch = scan.nextLine();
    }

    public static void splitContact() throws IOException {
        int singleContact = 1;
        for(int i = 0; i < contactList.size(); i += singleContact) {
            subContact.add(contactList.subList(i, Math.min(i + singleContact, contactList.size())));
        }
        for (List<String> strings : subContact) {
            System.out.println(strings);
        }
    }

}


