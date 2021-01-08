import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class FileHandlingApp {
    private static Scanner scanner;
    private static File file;


    private static void mainMenu()
    {
        while (true)
        {
            System.out.println("Main menu");
            System.out.println("---------");
            System.out.println("1. Read All");
            System.out.println("2. Read One");
            System.out.println("3. Add");
            System.out.println("4. Update");
            System.out.println("5. Delete One");
            System.out.println("6. Delete File");

            int userSelection;
            do
            {
                System.out.print("Enter selection (0 to exit): ");
                userSelection = scanner.nextInt();
                scanner.nextLine();
            }
            while (userSelection < 0 || userSelection > 6);
            System.out.println();

            switch (userSelection)
            {
                case 1:
                    readAll();
                    break;
                case 2:
                    readOne();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    deleteOne();
                    break;
                case 6:
                    deleteFile();
                    break;
                default:
                    break;
            }

            // Exit if 0 is entered
            if (userSelection == 0)
                break;
        }
    }


    private static void readAll()
    {
        System.out.println("Read all from file");
        System.out.println("------------------");

        // Exit if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");
            System.out.println();
            return;
        }

        try
        {
            // Open the file in read & write mode
            RandomAccessFile raf = new RandomAccessFile(file, "r");

            // Read from the file and find
            int count = 0;
            String temp;
            String tempName = "";
            String tempID = "";
            int tempAge = -1;

            while (raf.getFilePointer() < raf.length())
            {
                // Read line and put it into temp
                temp = raf.readLine();

                // Get the userSearch from the file
                ArrayList<Integer> commaIndices = new ArrayList<>();
                for (int i = 0; i < temp.length(); i++)
                    if (temp.charAt(i) == ',')
                        commaIndices.add(i);

                count++;
                tempName = temp.substring(0, commaIndices.get(0));
                tempID = temp.substring(commaIndices.get(0) + 1, commaIndices.get(1));
                tempAge = Integer.parseInt(temp.substring(commaIndices.get(1) + 1));

                System.out.println(count + ". " + tempName + ", " + tempID + ", " + tempAge);
            }

            raf.close();
        }
        catch (IOException ioE)
        {
            System.out.println(ioE); 
        }

        System.out.println();
    }


    private static void readOne()
    {
        System.out.println("Read one from file");
        System.out.println("------------------");

        // Exit if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");
            System.out.println();
            return;
        }

        try
        {
            // Open the file in read & write mode
            RandomAccessFile raf = new RandomAccessFile(file, "r");

            // Get input from user
            String userSearch;
            do
            {
                System.out.print("Enter name: ");
                userSearch = scanner.nextLine();
            }
            while (userSearch.length() < 1);

            // Read from the file and find
            String temp;
            String tempName = "";
            String tempID = "";
            int tempAge = -1;
            boolean found = false;

            while (raf.getFilePointer() < raf.length())
            {
                // Read line and put it into temp
                temp = raf.readLine();

                // Get the userSearch from the file
                ArrayList<Integer> commaIndices = new ArrayList<>();
                for (int i = 0; i < temp.length(); i++)
                    if (temp.charAt(i) == ',')
                        commaIndices.add(i);

                tempName = temp.substring(0, commaIndices.get(0));
                tempID = temp.substring(commaIndices.get(0) + 1, commaIndices.get(1));
                tempAge = Integer.parseInt(temp.substring(commaIndices.get(1) + 1));

                if (tempName.equals(userSearch))
                {
                    found = true;
                    break;
                }  
            }

            if (found)
            {
                System.out.println(userSearch + " is found!");
                System.out.println("Name: " + tempName);
                System.out.println("ID: " + tempID);
                System.out.println("Age: " + tempAge);
            }
            else
            {
                System.out.println(userSearch + " is not found!");
            }

            raf.close();
        }
        catch (IOException ioE)
        {
            System.out.println(ioE); 
        }

        System.out.println();
    }

    
    private static void add()
    {
        System.out.println("Add to file");
        System.out.println("-----------");

        // Create the file if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");

            try
            {
                boolean success = file.createNewFile();

                if (success)
                    System.out.println("File created successfully.");
                else
                    System.out.println("Failed to create file.");
            }
            catch (IOException ioE)
            {
                System.out.println(ioE); 
            }

            System.out.println();
        }

        try
        {
            // Get data from user
            String name;
            do
            {
                System.out.print("Enter name: ");
                name = scanner.nextLine();
            }
            while (name.length() < 1);
            String id;
            do
            {
                System.out.print("Enter ID: ");
                id = scanner.nextLine();
            }
            while (id.length() < 1);
            int age;
            do
            {
                System.out.print("Enter age: ");
                age = scanner.nextInt();
            }
            while (age < 0);
            System.out.println();

            // Open the file in read & write mode
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Write to end of file
            String outputStr = name + "," + id + "," + age;
            raf.seek(raf.length());
            raf.writeBytes(outputStr);
            raf.writeBytes(System.lineSeparator());

            System.out.println("Written data to file.");

            raf.close();
        }
        catch (IOException ioE)
        {
            System.out.println(ioE); 
        }

        System.out.println();
    }


    private static void update()
    {
        System.out.println("Update file");
        System.out.println("-----------");

        // Exit if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");
            System.out.println();
            return;
        }

        try
        {
            // Open the file in read & write mode
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Get input from user
            String userSearch;
            do
            {
                System.out.print("Enter name to be updated: ");
                userSearch = scanner.nextLine();
            }
            while (userSearch.length() < 1);

            // Read from the file and find
            String temp;
            String tempName = "";
            boolean found = false;

            while (raf.getFilePointer() < raf.length())
            {
                // Read line and put it into temp
                temp = raf.readLine();

                // Get the userSearch from the file
                ArrayList<Integer> commaIndices = new ArrayList<>();
                for (int i = 0; i < temp.length(); i++)
                    if (temp.charAt(i) == ',')
                        commaIndices.add(i);

                tempName = temp.substring(0, commaIndices.get(0));

                if (tempName.equals(userSearch))
                {
                    found = true;
                    break;
                }  
            }

            if (found)
            {
                System.out.println(userSearch + " is found!");

                // Get data from user
                String name;
                do
                {
                    System.out.print("Enter updated name: ");
                    name = scanner.nextLine();
                }
                while (name.length() < 1);
                String id;
                do
                {
                    System.out.print("Enter updated ID: ");
                    id = scanner.nextLine();
                }
                while (id.length() < 1);
                int age;
                do
                {
                    System.out.print("Enter updated age: ");
                    age = scanner.nextInt();
                }
                while (age < 0);
                System.out.println();

                // Create a temp file
                File tempFile = new File("temp.csv");
                RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw");

                // Set the file pointer of the original file to the beginning position
                raf.seek(0);

                // Scan the original file
                while (raf.getFilePointer() < raf.length())
                {
                    // Read line and put it into temp
                    temp = raf.readLine();

                    // Get the userSearch from the file
                    ArrayList<Integer> commaIndices = new ArrayList<>();
                    for (int i = 0; i < temp.length(); i++)
                        if (temp.charAt(i) == ',')
                            commaIndices.add(i);

                    tempName = temp.substring(0, commaIndices.get(0));

                    // Edit the line on the target line
                    if (tempName.equals(userSearch))
                    {
                        temp = name + "," + id + "," + age;
                    }

                    // Write into temporary file
                    tempRaf.writeBytes(temp);
                    tempRaf.writeBytes(System.lineSeparator());
                }

                // Reset the file pointers
                raf.seek(0);
                tempRaf.seek(0);

                // Copy the contents from the temp to the original
                while (tempRaf.getFilePointer() < tempRaf.length())
                {
                    raf.writeBytes(tempRaf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                // Set the length of the original file to the temp one
                raf.setLength(tempRaf.length());

                // Close the temp file and delete it
                tempRaf.close();
                tempFile.delete();

                System.out.println(userSearch + " has been updated!");
            }
            else
            {
                System.out.println(userSearch + " is not found!");
            }

            raf.close();
        }
        catch (IOException ioE)
        {
            System.out.println(ioE); 
        }

        System.out.println();
    }


    private static void deleteOne()
    {
        System.out.println("Delete one from file");
        System.out.println("--------------------");

        // Exit if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");
            System.out.println();
            return;
        }

        try
        {
            // Open the file in read & write mode
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Get input from user
            String userSearch;
            do
            {
                System.out.print("Enter name to be deleted: ");
                userSearch = scanner.nextLine();
            }
            while (userSearch.length() < 1);

            // Read from the file and find
            String temp;
            String tempName = "";
            boolean found = false;

            while (raf.getFilePointer() < raf.length())
            {
                // Read line and put it into temp
                temp = raf.readLine();

                // Get the userSearch from the file
                ArrayList<Integer> commaIndices = new ArrayList<>();
                for (int i = 0; i < temp.length(); i++)
                    if (temp.charAt(i) == ',')
                        commaIndices.add(i);

                tempName = temp.substring(0, commaIndices.get(0));

                if (tempName.equals(userSearch))
                {
                    found = true;
                    break;
                }  
            }

            if (found)
            {
                System.out.println(userSearch + " is found!");

                // Create a temp file
                File tempFile = new File("temp.csv");
                RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw");

                // Set the file pointer of the original file to the beginning position
                raf.seek(0);

                // Scan the original file
                while (raf.getFilePointer() < raf.length())
                {
                    // Read line and put it into temp
                    temp = raf.readLine();

                    // Get the userSearch from the file
                    ArrayList<Integer> commaIndices = new ArrayList<>();
                    for (int i = 0; i < temp.length(); i++)
                        if (temp.charAt(i) == ',')
                            commaIndices.add(i);

                    tempName = temp.substring(0, commaIndices.get(0));

                    // Only add the non-deleted entry into the temp file
                    if (!tempName.equals(userSearch))
                    {
                        tempRaf.writeBytes(temp);
                        tempRaf.writeBytes(System.lineSeparator());
                    }
                }

                // Reset the file pointers
                raf.seek(0);
                tempRaf.seek(0);

                // Copy the contents from the temp to the original
                while (tempRaf.getFilePointer() < tempRaf.length())
                {
                    raf.writeBytes(tempRaf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                // Set the length of the original file to the temp one
                raf.setLength(tempRaf.length());

                // Close the temp file and delete it
                tempRaf.close();
                tempFile.delete();

                System.out.println(userSearch + " has been deleted!");
            }
            else
            {
                System.out.println(userSearch + " is not found!");
            }

            raf.close();
        }
        catch (IOException ioE)
        {
            System.out.println(ioE); 
        }

        System.out.println();
    }


    private static void deleteFile()
    {
        System.out.println("Delete file");
        System.out.println("-----------");

        // Exit if it does not exist
        if (!file.exists())
        {
            System.out.println("File does not exist.");
            System.out.println();
            return;
        }
        
        String userConfirmation;
        do
        {
            System.out.print("Are you sure you want to delete the file (Y to confirm, N to cancel): ");
            userConfirmation = scanner.nextLine();
        }
        while (!(userConfirmation.equals("Y") || userConfirmation.equals("N")));

        if (userConfirmation.equals("Y"))
        {
            if (file.delete())
                System.out.println("File deleted!");
            else
                System.out.println("Failed to delete file.");
        }
        else
            System.out.println("Cancelled.");
        System.out.println();
    }


    public static void main(String args[])
    {
        scanner = new Scanner(System.in);
        file = new File("friends.csv");

        System.out.println("===============");
        System.out.println("File IO Program");
        System.out.println("===============");
        System.out.println();

        // Run main menu
        mainMenu();

        scanner.close();
    }
}
