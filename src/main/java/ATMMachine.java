
import java.util.Scanner;

/**
 * Student ID: 300340437
 * Student Name: Alessandro de Lima
 * Date: 2023-10-09
 *
 * Program steps for the creation of three Accounts and operations over it;
 * 1 - Creation of three Accounts
 * 2 - Selection of an Account based on its ID
 * 3 - Main Menu to provide operations into account (CHECK BALANCE, WITHDRAW, DEPOSIT AND ACCOUNT DETAILS)
 */
public class ATMMachine {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
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

    }
}