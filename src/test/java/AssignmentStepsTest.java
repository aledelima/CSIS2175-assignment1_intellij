import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignmentStepsTest {

    @Test
    void testCreateAccount() {

        final String SIMULATED_INPUT  =
                // First Account creation
                "Sam K.\n" +
                "7500 Burnaby\n" +
                "2\n" +
                "34\n" +
                "34\n" +
                "34\n" +
                "23\n" +
                "2000\n" +
                "4500\n" +
                "4500\n" +
                // Second Account creation
                "Jim J.\n" +
                "123 Coquitlam\n" +
                "3\n" +
                "7\n" +
                "2001\n" +
                "5555\n" +
                "6000\n" +
                // Third Account creation
                "Yun\n" +
                "3400 Vancouver\n" +
                "4\n" +
                "8\n" +
                "2003\n" +
                "6666\n" +
                "9000\n" +
                // Welcome
                "67\n" +
                "67\n" +
                "67\n" +
                "4500\n" +
                "1\n" +
                "3\n" +
                "300\n" +
                "1\n" +
                "2\n" +
                "100\n" +
                "1\n" +
                "4\n" +
                "5\n"        ;

        final String EXPECTED_OUTPUT =
                // First Account creation
                "Let's create the first account\n" +
                        "Enter account holder information\n" +
                        "Enter name\n" +
                        "Enter address\n" +
                        "Enter month of birth e.g. for Jan enter 1\n" +
                        "Enter the day of birth e.g. 7\n" +
                        "invalid day, enter the day of birth again\n" +
                        "invalid day, enter the day of birth again\n" +
                        "invalid day, enter the day of birth again\n" +
                        "Enter the year of birth\n" +
                        "Enter account id\n" +
                        "Enter the account's initial balance\n" +
                        "Account holder information\n" +
                        "Name Sam K.\n" +
                        "Address 7500 Burnaby\n" +
                        " DOB          : 2000-02-23\n" +
                        " id           : 4500\n" +
                        " balance      : 4500.0\n" +
                        " Date         : " + LocalDate.now() + "\n" +
                        "\n" +
                        "\n" +
                        // Second Account Starts in here
                        "Let's create the second account\n" +
                        "Enter account holder information\n" +
                        "Enter name\n" +
                        "Enter address\n" +
                        "Enter month of birth e.g. for Jan enter 1\n" +
                        "Enter the day of birth e.g. 7\n" +
                        "Enter the year of birth\n" +
                        "Enter account id\n" +
                        "Enter the account's initial balance\n" +
                        "Account holder information\n" +
                        "Name Jim J.\n" +
                        "Address 123 Coquitlam\n" +
                        " DOB          : 2001-03-07\n" +
                        " id           : 5555\n" +
                        " balance      : 6000.0\n" +
                        " Date         : " + LocalDate.now() + "\n" +
                        "\n" +
                        "\n" +
                        // Third Account Starts in here
                        "Let's create the third account\n" +
                        "Enter account holder information\n" +
                        "Enter name\n" +
                        "Enter address\n" +
                        "Enter month of birth e.g. for Jan enter 1\n" +
                        "Enter the day of birth e.g. 7\n" +
                        "Enter the year of birth\n" +
                        "Enter account id\n" +
                        "Enter the account's initial balance\n" +
                        "Account holder information\n" +
                        "Name Yun\n" +
                        "Address 3400 Vancouver\n" +
                        " DOB          : 2003-04-08\n" +
                        " id           : 6666\n" +
                        " balance      : 9000.0\n" +
                        " Date         : " + LocalDate.now() + "\n" +
                        "\n" +
                        "\n" +
                        // Welcome section
                        "Welcome to ATM\n" +
                        "Enter an id to search for:\n" +
                        "ID does not exist\n" +
                        "Enter an id to search for:\n" +
                        "ID does not exist\n" +
                        "Enter an id to search for:\n" +
                        "ID does not exist\n" +
                        "Enter an id to search for:\n" +
                        // Main Menu Section
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "The balance is 4500.0\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "Enter the amount to deposit\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "The balance is 4800.0\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "Enter the amount to withdraw\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "The balance is 4700.0\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "Account holder information\n" +
                        "Name Sam K.\n" +
                        "Address 7500 Burnaby\n" +
                        " DOB          : 2000-02-23\n" +
                        " id           : 4500\n" +
                        " balance      : 4700.0\n" +
                        " Date         : " + LocalDate.now() + "\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "Good bye\n"
                ;

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));



        // Program Steps begins in here
        Scanner sc = new Scanner(inputContent);
        AccountsList accounts = new AccountsList();
        HMI hmi = new HMI(sc);

        // Creation of the first account
        hmi.displayMessage("Let's create the first account");
        Account account1 = hmi.createAccount(accounts);
        accounts.setAccount1(account1);
        account1.display();
        hmi.displayMessage("");
        hmi.displayMessage("");

        // Creation of the first account
        hmi.displayMessage("Let's create the second account");
        Account account2 = hmi.createAccount(accounts);
        accounts.setAccount2(account2);
        account2.display();
        hmi.displayMessage("");
        hmi.displayMessage("");

        // Creation of the first account
        hmi.displayMessage("Let's create the third account");
        Account account3 = hmi.createAccount(accounts);
        accounts.setAccount3(account3);
        account3.display();
        hmi.displayMessage("");
        hmi.displayMessage("");

        // Welcome section
        Account selAccount = hmi.displayWelcome(accounts);

        // Main Menu Section
        hmi.displayMainMenu(selAccount);

        assertEquals(4500, accounts.getAccount1().getId());
        assertEquals(4700, accounts.getAccount1().getBalance());
        assertEquals(5555, accounts.getAccount2().getId());
        assertEquals(6666, accounts.getAccount3().getId());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }


}