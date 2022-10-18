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
    static String singleContacts;
    static List<String> contactFormat;
    static List<List<String>> subContact = new ArrayList<>();

    static {
        try {
            contactList = Files.readAllLines(currentDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ContactsApp() {
    }

    public static void main(String[] args) throws IOException {
        contactList();
        mainMenu();
    }

    public static void addContact() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("First Name: ");
        String userFirst = scan.nextLine();
        System.out.println("Last Name: ");
        String userLast = scan.nextLine();
        System.out.println("Phone Number: ");
        String userNumber = scan.nextLine();

        Contacts contact = new Contacts(userFirst.stripLeading().stripTrailing(), userLast.stripLeading().stripTrailing(), userNumber.stripLeading().stripTrailing());
        contactFormat = Collections.singletonList(contact.getFirstName() + " " + contact.getLastName() + " | " + contact.getPhoneNumber());
        Files.write(currentDir, contactFormat, StandardOpenOption.APPEND);
        contactList.add(contactFormat.toString());
        System.out.println("Contact was successfully added!");
        System.out.println("");
        System.out.println("Would you like to add another contact? [y/n]");
        String userContinue = scan.nextLine();
        if(userContinue.equalsIgnoreCase("y")) {
            addContact();
        }
        menuRecursion();
    }
    public static void searchContact() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Search by First or Last Name: ");
        String userSearch = scan.nextLine();
        boolean check = false;
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).contains(userSearch)) {
                check = true;
                System.out.println(contactList.get(i).replace("[", "").replace("]", ""));
                System.out.println("");
                System.out.println("Would you like to search for another contact? [y/n]");
                String userContinue = scan.nextLine();
                if(userContinue.equalsIgnoreCase("y")) {
                    searchContact();
                }
                menuRecursion();
            }
        }
        if(!check) {
            System.out.println(userSearch + " was not found!");
            searchContact();
        }
    }
    public static void mainMenu() throws IOException {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("1: View Contacts.");
        System.out.println("2: Add a new contact.");
        System.out.println("3: Search a contact by name.");
        System.out.println("4: Delete an existing account.");
        System.out.println("5: Exit");
        System.out.println("Enter an option (1, 2, 3, 4, or 5):");
        int userInput = myScanner.nextInt();
        switch(userInput) {
            case 1:
                contactList();
                mainMenu();
                break;
            case 2:
                addContact();
                break;
            case 3:
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                endProgram();
                break;
        }
    }
    public static void contactList() {
        for(int i = 0; i < contactList.size(); i += 1) {
            subContact.add(contactList.subList(i, Math.min(i + 1, contactList.size())));
            singleContacts = contactList.subList(i, Math.min(i + 1, contactList.size())).toString();
            System.out.println(singleContacts.replace("[", "").replace("]", ""));
        }
    }
    public static void deleteContact() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the contact you would like to delete: ");
        String userDelete = scan.nextLine();
        boolean check = false;
        for(int i = 0; i <= contactList.size(); i++) {
            if(contactList.get(i).contains(userDelete)) {
                check = true;
                System.out.println(contactList.get(i).replace("[", "").replace("]", "") + " was deleted!");
                contactList.remove(contactList.get(i));
                Files.write(currentDir, contactList);
                System.out.println("");
                System.out.println("Would you like to delete another contact? [y/n]");
                String continueDelete = scan.nextLine();
                if(continueDelete.equalsIgnoreCase("y")) {
                    deleteContact();
                }
                menuRecursion();
            }
        }
        if(!check) {
            System.out.println(userDelete + " was not found!");
            deleteContact();
        }
    }
    public static void endProgram() {
        System.out.println("Okay, Goodbye!");
        System.exit(0);
    }
    public static void menuRecursion() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to go back to the menu? [y/n]");
        String userMenu = scan.nextLine();
        if(userMenu.equalsIgnoreCase("y")) {
            mainMenu();
        } else if(userMenu.equalsIgnoreCase("n")) {
            endProgram();
        }
    }
}


