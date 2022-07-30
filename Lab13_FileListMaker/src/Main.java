import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {

    // creating array list
    static ArrayList<String> list = new ArrayList<>();

    static Scanner in = new Scanner(System.in);

    static boolean saved = true;

    public static void main(String[] args) {
        final String menu = "A - Add  D - Delete  V - View  O - Open  S - Save  C - Clear  Q - Quit";
        boolean done = false;
        String cmd = "";

        do {
            // display the list
            displayList();

            // display the menu and get a menu choice
            cmd = SafeInput.getRegExString(in, menu, "[AaDdVvOoSsCcQq]"); // all the options
            cmd = cmd.toUpperCase();

            // execute the choice
            switch(cmd) {
                case "A":
                    addItems();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "V":
                    viewFile();
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    saveList();
                    break;
                case "C":
                    clearList();
                    break;
                case "Q":
                    done = quitProgram();
                    break;
            }



        }
        while (!done);
    }

    private static boolean quitProgram() {
        boolean quit = false;
        if (saved == false) {
            boolean confirm = SafeInput.getYNConfirm(in, "Do you want to save the list? The list has not been saved and will be terminated.");
            if (!confirm) {
                quit = SafeInput.getYNConfirm(in, "Do you want to quit the program");
            } else {
                saveList();
            }
        } else {
            quit = SafeInput.getYNConfirm(in, "Do you want to quit the program");
        }


        return quit;
    }

    private static void deleteItem() {
        // asks user which index they want to remove
        int index = SafeInput.getRangedInt(in, "Which index do you want to remove", 1, list.size());

        // subtracts 1 from index
        index -= 1;

        list.remove(index);

        // list has been changed and not saved
        saved = false;
    }

    private static void addItems() {
        // asks user what they want to add to the list
        String newItem = SafeInput.getNonZeroLenString(in, "What do you want to add to your list");

        // adds item to the list
        list.add(newItem);

        // list has been changed and not saved
        saved = false;
    }

    private static void saveList() {
        int userChoice = SafeInput.getRangedInt(in, "1) Save list in existing file 2) Create new file to save list", 1, 2);
        if (userChoice == 1) {
            boolean saving = SafeInput.getYNConfirm(in, "Saving the list in an existing file will terminate the existing information in the file. Are you sure you want to continue");

            if (saving) {
                System.out.println("Choose a file to save your list");

                JFileChooser chooser = new JFileChooser();
                Scanner inFile;
                String line;
                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve("src");
                // set the chooser to the project src directory
                chooser.setCurrentDirectory(target.toFile());

                try  // Code that might trigger the exception goes here
                {

                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                        // Typical java pattern of inherited classes
                        // we wrap a BufferedWriter around a lower level BufferedOutputStream
                        OutputStream out =
                                new BufferedOutputStream(Files.newOutputStream(target, CREATE));
                        BufferedWriter writer =
                                new BufferedWriter(new OutputStreamWriter(out));

                        // clear file

                        BufferedWriter bf = Files.newBufferedWriter(target,
                                StandardOpenOption.TRUNCATE_EXISTING);

                        // writing file

                        for(String rec : list)
                        {
                            writer.write(rec, 0, rec.length());
                            // 0 is where to start (1st char)
                            // rec. length() is how many chars to write (all)
                            writer.newLine();  // adds the new line

                        }
                        writer.close(); // must close the file to seal it and flush buffer
                        System.out.println("");
                        System.out.println("Data file saved!");

                        // the list has been saved
                        saved = true;

                    }
                    else   // User did not pick a file, closed the chooser
                    {
                        System.out.println("Sorry, you must select a file. Terminating!");
                        System.exit(0);
                    }
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found Error");
                    e.printStackTrace();
                }
                catch (IOException e) // code to handle this exception
                {
                    System.out.println("IOException Error");
                    e.printStackTrace();
                }
            }

        }

        else {
            String fileName = SafeInput.getNonZeroLenString(in, "Give the new file a name (end with .txt)");

            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

            try
            {
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                // writing the file

                for(String rec : list)
                {
                    writer.write(rec, 0, rec.length());  // syntax for write rec
                    // 0 is where to start (1st char)
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer

                // informs user data is saved
                System.out.println("");
                System.out.println("Data file written!");
                System.out.println("Data from list saved");

                // the list has been saved
                saved = true;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }



    }

    private static void clearList() {
        if (saved == false) {
            boolean confirm = SafeInput.getYNConfirm(in, "Do you want to save the list? The list has not been saved and will be terminated.");
            if (!confirm) {
                list.clear();
                System.out.println("The list has been cleared");
            }
            else {
                saveList();
            }
        }
        else {
            list.clear();
        }

    }

    private static void openFile() {
        if (saved) {
            // lets user select file
            JFileChooser chooser = new JFileChooser();
            Scanner inFile;
            String line;
            Path target = new File(System.getProperty("user.dir")).toPath();
            target = target.resolve("src");
            // set the chooser to the project src directory
            chooser.setCurrentDirectory(target.toFile());

            try  // Code that might trigger the exception goes here
            {

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                    inFile = new Scanner(target);

                    // clears old list and begins to write new one
                    list.clear();

                    while (inFile.hasNextLine()) {
                        line = inFile.nextLine();
                        list.add(line);
                    }

                    // good practice and does something good
                    inFile.close();

                } else   // User did not pick a file, closed the chooser
                {
                    System.out.println("Sorry, you must select a file. Terminating!");
                    System.exit(0);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found Error");
                e.printStackTrace();
            } catch (IOException e) // code to handle this exception
            {
                System.out.println("IOException Error");
                e.printStackTrace();
            }
        } else {
            boolean confirm = SafeInput.getYNConfirm(in, "Do you want to save the list? The list has not been saved and will be terminated.");
            if (!confirm) {
                // lets user select file
                JFileChooser chooser = new JFileChooser();
                Scanner inFile;
                String line;
                Path target = new File(System.getProperty("user.dir")).toPath();
                target = target.resolve("src");
                // set the chooser to the project src directory
                chooser.setCurrentDirectory(target.toFile());

                try  // Code that might trigger the exception goes here
                {

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                        inFile = new Scanner(target);

                        // clears old list and begins to write new one
                        list.clear();

                        while (inFile.hasNextLine()) {
                            line = inFile.nextLine();
                            list.add(line);
                        }

                        // good practice and does something good
                        inFile.close();

                    } else   // User did not pick a file, closed the chooser
                    {
                        System.out.println("Sorry, you must select a file. Terminating!");
                        System.exit(0);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found Error");
                    e.printStackTrace();
                } catch (IOException e) // code to handle this exception
                {
                    System.out.println("IOException Error");
                    e.printStackTrace();
                }
            }
            else {
                saveList();
            }
        }
    }

    private static void displayList() {
        System.out.println("===========================================================================================================================================================");
        if (list.size() !=0) {
            for (int x = 0; x < list.size(); x++) {
                System.out.printf("%-3d%-15s", x+1, list.get(x));
            }
        }
        else {
            System.out.print("===                                                                        Empty List                                                                   ===");
        }
        System.out.println("\n===========================================================================================================================================================");
    }

    private static void viewFile() {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());

        System.out.println("\nChoose a file to view");
        System.out.println("\nViewing File:");
        System.out.println(target);

        try  // Code that might trigger the exception goes here
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    System.out.println(line);
                }

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file. Terminating");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }


}