import java.util.Scanner;

public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }
        while(retString.length() == 0);

        return retString;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an integer
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        // declaring variables
            int retInt = 0;
            String trash = "";
            boolean gotInt = false;

        do // loop that confirms user inputs int
        {
            // show prompt
            System.out.print("\n" + prompt + ": ");

            // checking if input is integer

                if (pipe.hasNextInt()) // is int
                {
                    // setting input to the return value
                    retInt = pipe.nextInt();
                    pipe.nextLine();

                    // exit loop
                    gotInt = true;
                }
                else // not int
                {
                    // set input to trash
                    trash = pipe.nextLine();

                    // show user their incorrect input
                    System.out.println("Please enter an integer: " + trash);

                    // stay in loop
                    gotInt = false;
                }
        }
        while(!gotInt);

        return retInt;

    }

    /**
     *
     * @param pipe a scanner opened to read System.in
     * @param prompt prompt for the user
     * @return returns a double
     */
    public static double getDouble(Scanner pipe, String prompt)
    {
        // declaring variables
        double retDouble = 0;
        String trash = "";
        boolean gotDouble = false;

        do // loop that confirms user inputs double
        {
            // show prompt
            System.out.print("\n" + prompt + ": ");

            // checking if input is double

            if (pipe.hasNextDouble()) // is double
            {
                // setting input to the return value
                retDouble = pipe.nextDouble();
                pipe.nextLine();

                // exit loop
                gotDouble = true;
            }
            else // not double
            {
                // set input to trash
                trash = pipe.nextLine();

                // show user their incorrect input
                System.out.println("Please enter a decimal: " + trash);

                // stay in loop
                gotDouble = false;
            }
        }
        while(!gotDouble);

        return retDouble;

    }

    /**
     *
     * @param pipe a scanner opened to read System.in
     * @param prompt prompt for the user
     * @param low the low range of the int
     * @param high the high range of the int
     * @return an int in a certain range
     */

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        // declaring variables
        int retRangedInt = 0;
        String trash = "";
        boolean gotRangedInt = false;

        do // loop that confirms user inputs ranged int
        {
            // show prompt
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            // checking if input is int

            if (pipe.hasNextInt()) // is int
            {
                // setting input to the variable
                retRangedInt = pipe.nextInt();
                pipe.nextLine();

                if (retRangedInt >= low && retRangedInt <= high) // int is in range
                {
                    // exit loop
                    gotRangedInt = true;
                }
                else // int not in range
                {
                    System.out.println("Please enter an integer within range [" + low + " - " + high + "]: " + retRangedInt);

                    gotRangedInt = false;
                }

            }
            else // not int
            {
                // set input to trash
                trash = pipe.nextLine();

                // show user their incorrect input
                System.out.println("Please enter an integer within range [" + low + " - " + high + "]: " + trash);

                // stay in loop
                gotRangedInt = false;
            }
        }
        while(!gotRangedInt);

        return retRangedInt;

    }

    /**
     *
     * @param pipe a scanner opened to read System.in
     * @param prompt prompt for the user
     * @param low low double range
     * @param high high double range
     * @return returns a double in low-high range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        // declaring variables
        double retRangedDouble = 0;
        String trash = "";
        boolean gotRangedDouble = false;

        do // loop that confirms user inputs double
        {
            // show prompt
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            // checking if input is double

            if (pipe.hasNextDouble()) // is double
            {
                // setting input to the variable
                retRangedDouble = pipe.nextDouble();
                pipe.nextLine();

                if (retRangedDouble >= low && retRangedDouble <= high) // if double is in range
                {
                    // exit loop
                    gotRangedDouble = true;
                }
                else // double not in range
                {
                    System.out.println("Please enter a double within range [" + low + " - " + high + "]: " + retRangedDouble);

                    gotRangedDouble = false;
                }

            }
            else // not double
            {
                // set input to trash
                trash = pipe.nextLine();

                // show user their incorrect input
                System.out.println("Please enter a double within range [" + low + " - " + high + "]: " + trash);

                // stay in loop
                gotRangedDouble = false;
            }
        }
        while(!gotRangedDouble);

        return retRangedDouble;

    }

    /**
     *
     * @param pipe a scanner opened to read System.in
     * @param prompt prompt for user
     * @return returns true or false
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        // declaring variables
        String response = "";
        String trash = "";
        boolean gotBoolean = false;
        boolean retBoolean = true;

        do // loop that confirms user inputs Y or N
        {
            // show prompt
            System.out.print("\n" + prompt + " [Y/N]: ");

            // setting input to the variable
            response = pipe.nextLine();

            // makes all response uppercase
            response = response.toUpperCase();

            // checking if input is Y or N

            if (response.equals("Y")) // is Y
            {
                // set boolean
                retBoolean = true;

                // exit loop
                gotBoolean = true;
            }
            else if (response.equals("N")) // is N
            {
                // set boolean
                retBoolean = false;

                // exit loop
                gotBoolean = true;
            }

            else // not Y or N
            {
                // set input to trash
                trash = response;

                // show user their incorrect input
                System.out.println("Please enter a letter [Y/N]: " + trash);

                // stay in loop
                gotBoolean = false;
            }
        }
        while(!gotBoolean);

        return retBoolean;

    }

    /**
     *
     * @param pipe a scanner opened to read System.in
     * @param prompt prompt for the user
     * @param regEx regEx pattern user must match
     * @return value that matches regEx pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        // variables
        String value = "";
        boolean gotAValue = false;


        // do while loop
        do {
            // show prompt
            System.out.print(prompt + ": ");

            // collect input
            value = pipe.nextLine();

            // test to see it is correct

            if (value.matches(regEx)) // match
            {
                // exits loop
                gotAValue = true;
            }
            else
            {
                System.out.println("\nInvalid input: " + value);
            }
        }
        while(!gotAValue);

        // returns the value user inputted
        return value;
    }

    public static String prettyHeader(String msg)
    {
        // variables
        int x = 0;
        int msgLength = 0;
        int space = 0;
        String prettyHeader = "";

        // first line
        for (x = 1; x<=60; x++)
        {
            System.out.print("*");
        }

        // second line
        msgLength = msg.length();
        space = (54 - msgLength)/2;

        // three stars at front
        System.out.print("\n***");

        // printing spaces to center text
        for (x=1; x<=space; x++)
        {
            System.out.print(" ");
        }

        // print text
        System.out.print(msg);

        // printing spaces after text
        for (x=1; x<=space; x++)
        {
            System.out.print(" ");
        }

        // three stars at end
        System.out.println("***");

        // third line
        for (x = 1; x<=60; x++)
        {
            System.out.print("*");
        }

        return prettyHeader;
    }
}
