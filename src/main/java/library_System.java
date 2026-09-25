import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class library_System {
    private ArrayList<user> userList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String fileName;

    public void displayMenu(){
        System.out.println("Enter file name to be opened: ");
        fileName = scanner.nextLine();
        loadFromFile(fileName);

        String choice = "";
        do {System.out.println("---Welcome to the Library System---");
            System.out.println("Enter a number for the correct submenu");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Update User");
            System.out.println("4. Display All Users");
            System.out.println("5. Exit");

            choice = scanner.nextLine();
            switch (choice){
                case "1" -> addUser();
                case "2" -> removeUser();
                case "3" -> updateUser();
                case "4" -> displayUserList();
                case "5" -> System.out.println("Exiting menu now!");

            }

        }
        while (!choice.equals("5"));
    }

    private void addUser() {
        String userID;
        do{
            System.out.print("Enter user ID: ");
            userID = scanner.nextLine();
            if(userID.length() != 7){
                System.out.println("Invalid user ID");
            }
        } while (userID.length() != 7);

        System.out.println("Enter users name: ");
        String name = scanner.nextLine();
        System.out.println("Enter users address: ");
        String address = scanner.nextLine();

        double balance;
        do{
            System.out.println("Enter balance: ");
            balance = scanner.nextDouble();
            scanner.nextLine();

            if(balance < 0 || balance > 250){
                System.out.println("Balance must be between 0 and 250");
            }
        }
        while (balance < 0 || balance > 250);

        userList.add(new user(userID, name, address, balance));
        System.out.println("<Created new user>");

        saveToFile(fileName);
    }
    private void removeUser() {
        System.out.println("Enter user ID number to remove account: ");
        String userID = scanner.nextLine();
        boolean remove = userList.removeIf(user -> user.getUserID().equals(userID) );
        if (remove) {
            System.out.println("<Removed user>");
        }
        else {
            System.out.println("<User not found>");
        }
        saveToFile(fileName);
    }
    private void updateUser() {
        System.out.println("Enter user ID number to update account: ");
        String userID = scanner.nextLine();

        user foundUser = null;
        for (user user : userList){
            if (user.getUserID().equals(userID)){
                foundUser = user;
            }
        }
        if (foundUser != null){
            System.out.println("Enter new balance");
            double newBalance = scanner.nextDouble();
            scanner.nextLine();

            foundUser.setBalance(newBalance);
            System.out.println("<Updated user>");
        }
        else {
            System.out.println("<User not found>");
        }
        saveToFile(fileName);
    }

    private void displayUserList() {
        if (userList.isEmpty()) {
            System.out.println("No users found");
        }
        else {
            for (user user : userList) {
                System.out.println(user);
            }
        }
    }
    private void saveToFile(String fileName)  {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            for (user user : userList) {
                printWriter.println(user.getUserID() + "," + user.getName() + "," + user.getAddress() + "," + user.getBalance());
            }
        }
        catch (IOException e) {
            System.out.println("--Error saving data to Users.txt--");
        }
    }
    private void loadFromFile(String fileName) {
        try{
            File file = new File(fileName);
            if(!file.exists()){
            return; }

            Scanner filescanner = new Scanner(file);
            while (filescanner.hasNextLine()) {
                String line = filescanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length==4){
                    String userID = parts[0];

                    if(userID.length() != 7){
                        System.out.println("Skipping invalid user ID" + userID);
                        continue;
                    }
                    String name = parts[1];
                    String address = parts[2];
                    try {
                        Double balance = Double.parseDouble(parts[3]);
                        userList.add(new user(userID, name, address, balance));
                    }
                    catch (NumberFormatException e){
                        System.out.println("Improper balance format; Line being skipped" + line);
                    }
                    }
            }
            filescanner.close();
        }catch (IOException e) {
            System.out.println("--Error loading data from Users.txt--");
        }
    }
}

