
import java.time.LocalDate;
import java.util.Scanner;

/**
 * HMI - Human Machine Interface.
 * Class devoted to print output information and catch user's inputs
 */
public class HMI {

    private final Scanner sc;

    /**
     * Constructor method using a Scanner class
     * @param sc Scanner class for input capturing
     */
    public HMI(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Instantiates an Account object according to VALID information provided by user's inputs.
     * @return main.Account type
     */
    public Account createAccount(AccountsList accounts) {

        String name;
        String address;
        int monthOfBirth;
        int dayOfBirth;
        int yearOfBirth;
        int accountId;
        double accountBalance;

        // Header
        displayMessage("Enter account holder information");

        // catch inputs
        name = catchStringValue("Enter name");
        address = catchStringValue("Enter address");
        monthOfBirth = catchMonthOfBirth();
        dayOfBirth = catchDayOfBirth();
        yearOfBirth = catchYearOfBirth();
        accountId = catchAccountId(accounts);
        accountBalance = catchDoubleValue("Enter the account's initial balance");

        // Account object return
        return new Account(
                accountId,
                accountBalance,
                new Person(name, address, LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth)),
                LocalDate.now()
        );
    }

    /**
     * Captures and validated account id from user's input. ID must be within 1111 and 9999
     * inclusive and cannot be repeated from other Accounts IDs.
     * @param accounts required to check whether id already exists
     * @return validated integer input
     */
    public int catchAccountId(AccountsList accounts) {
        int accountId;
        accountId = catchIntValue("Enter account id");
        while (accountId < 1111 || accountId > 9999 || accounts.checkIdExistence(accountId))
            accountId = catchIntValue("invalid ID, enter the ID again");
        return accountId;
    }

    /**
     * Captures and validates year of birth from user's input
     * @return validated integer value
     */
    public int catchYearOfBirth() {
        int yearOfBirth;
        yearOfBirth = catchIntValue("Enter the year of birth");
        while (yearOfBirth < 1 || yearOfBirth > 9999)
            yearOfBirth = catchIntValue("invalid year, enter the year of birth again");
        return yearOfBirth;
    }

    /**
     * Captures and validates day of birth from user's input
     * @return validated integer value
     */
    public int catchDayOfBirth() {
        int dayOfBirth;
        dayOfBirth = catchIntValue("Enter the day of birth e.g. 7");
        while (dayOfBirth < 1 || dayOfBirth > 31)
            dayOfBirth = catchIntValue("invalid day, enter the day of birth again");
        return dayOfBirth;
    }

    /**
     * Captures and validates month of birth from user's input
     * @return validated integer value
     */
    public int catchMonthOfBirth() {
        int monthOfBirth;
        monthOfBirth = catchIntValue("Enter month of birth e.g. for Jan enter 1");
        while (monthOfBirth < 1 || monthOfBirth > 12)
            monthOfBirth = catchIntValue("invalid month, enter the month of birth again");
        return monthOfBirth;
    }

    /**
     * Selects Account Object according to Account ID inputted
     * @return Account chosen based on id inputted
     */
    public Account displayWelcome(AccountsList accounts) {

        //Input variables
        int id;

        displayMessage("Welcome to ATM");
        id = catchIntValue("Enter an id to search for:");
        // Validate ID out of range
        while (!accounts.checkIdExistence(id)) {
            displayMessage("ID does not exist");
            id = catchIntValue("Enter an id to search for:");
        }

        // Returns proper account according to id typed
        return accounts.getAccountById(id);
    }

    /**
     * Provide a menu with operations to be performed over an account.
     * Those are CHECK BALANCE, WITHDRAW, DEPOSIT AND ACCOUNT DETAILS.
     * Menu terminates when user inputs EXIT.
     * @param account
     */
    public void displayMainMenu(Account account) {

        int op;

        do {
            // Printing Options
            System.out.println();
            System.out.println("MAIN MENU");
            System.out.println("1: check balance");
            System.out.println("2: withdraw");
            System.out.println("3: Deposit");
            System.out.println("4: account details");
            System.out.println("5: exit");
            System.out.println("Enter a choice");
            op = this.sc.nextInt();
            this.sc.nextLine();

            // Choice processing
            switch (op) {
                case 1:
                    displayAccountBalance(account);
                    break;
                case 2:
                    withdrawAmount(account);
                    break;
                case 3:
                    depositAmount(account);
                    break;
                case 4:
                    displayAccountDetails(account);
                    break;
                case 5:
                    displayGoodbye();
                    break;
                default:
            }
        }  while (op != 5);
    }

    /**
     * Reads an amount from user and deposits it into an Account.
     * @param account target account
     */
    public void depositAmount(Account account) {
        double amount;
        System.out.println("Enter the amount to deposit");
        amount = this.sc.nextDouble();
        this.sc.nextLine();

        account.deposit(amount);
    }

    /**
     * Reads an amount from user and withdraw it from an Account.
     * @param account target account
     */
    public void withdrawAmount(Account account) {
        double amount;
        System.out.println("Enter the amount to withdraw");
        amount = this.sc.nextDouble();
        this.sc.nextLine();

        account.withdraw(amount);
    }

    /**
     * Prints the current balance of an Account
     * @param account target account
     */
    public void displayAccountBalance(Account account) {
        System.out.printf("The balance is %.1f\n", account.getBalance());
    }

    /**
     * Displays information about an Account.
     * Those are NAME, ADDRESS, DOB, IDM, BALANCE, DATE OF CREATION
     * @param account target account
     */
    public void displayAccountDetails(Account account) {
        account.display();
    }

    /**
     * Displays a greeting message
     */
    public void displayGoodbye() {
        System.out.println("Good bye");
    }

    /**
     * Displays a message according to the string 'message'
     * @param message string message to be printed
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a string value from an input
     * @param message message displayed before reading the input
     * @return string value
     */
    public String catchStringValue(String message) {
        displayMessage(message);
        String value = this.sc.nextLine();

        return value;
    }

    /**
     * Reads an integer value from an input.
     * @param message message displayed before reading the input
     * @return integer value
     */
    public int catchIntValue(String message) {
        displayMessage(message);
        int value = this.sc.nextInt();
        this.sc.nextLine();

        return value;
    }

    /**
     * Reads a double value from an input.
     * @param message message displayed before reading the input
     * @return double value
     */
    public double catchDoubleValue(String message) {
        displayMessage(message);
        double value = this.sc.nextDouble();
        this.sc.nextLine();

        return value;
    }

}
