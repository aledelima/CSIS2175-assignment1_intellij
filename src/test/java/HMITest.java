import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HMITest {

    private Account account1;
    private Account account2;
    private Account account3;
    private AccountsList accounts;

    @BeforeEach
    private void initializer() {
        account1 =
                new Account(
                        4500,
                        4500,
                        new Person("Sam K.", "7500 Burnaby", LocalDate.of(2000, 2, 23)),
                        LocalDate.now()
                );
        account2 =
                new Account(
                        5555,
                        6000,
                        new Person("Yun", "123 Coquitlam", LocalDate.of(2001, 3,07)),
                        LocalDate.now()
                );
        account3 =
                new Account(
                        6666,
                        9000,
                        new Person("Jim J.", "3400 Vancouver", LocalDate.of(2003, 4,8)),
                        LocalDate.now()
                );

        accounts = new AccountsList();
        accounts.setAccount1(account1);
        accounts.setAccount2(account2);
        accounts.setAccount3(account3);
    }

    @AfterEach
    private void resetInputOutputConfig() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void testDisplayMessage() {

        final String EXPECTED_OUTPUT = "Test Message\n";

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HMI hmi = new HMI(new Scanner(System.in));

        hmi.displayMessage(EXPECTED_OUTPUT.trim());

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCatchStringValue() {

        final String SIMULATED_INPUT  = "Jim K.";
        final String EXPECTED_OUTPUT = "Enter name\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        String strValue = hmi.catchStringValue(EXPECTED_OUTPUT.trim());

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
        assertEquals(SIMULATED_INPUT, strValue);
    }

    @Test
    void testCatchIntValue() {

        final String SIMULATED_INPUT  = "2\n";
        final String EXPECTED_OUTPUT = "Enter the day of birth e.g. 7\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        int intValue = hmi.catchIntValue(EXPECTED_OUTPUT.trim());

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
        assertEquals(Integer.valueOf(SIMULATED_INPUT.trim()), intValue);
    }

    @Test
    void testCatchDoubleValue() {

        final String SIMULATED_INPUT  = "4500.5\n";
        final String EXPECTED_OUTPUT = "Enter the account's initial balance\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        double intValue = hmi.catchDoubleValue(EXPECTED_OUTPUT.trim());

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
        assertEquals(Double.valueOf(SIMULATED_INPUT), intValue);
    }

    @Test
    void testDisplayGoodbye() {

        final String EXPECTED_OUTPUT = "Good bye\n";

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HMI hmi = new HMI(new Scanner(System.in));

        hmi.displayGoodbye();

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCatchAccountId() {

        final String SIMULATED_INPUT  = "1110\n10000\n4500\n1111\n";
        final String EXPECTED_OUTPUT = "Enter account id\ninvalid ID, enter the ID again\ninvalid ID, enter the ID again\ninvalid ID, enter the ID again\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        int id = hmi.catchAccountId(accounts);

        assertEquals(1111, id);
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCatchYearOfBirth() {

        final String SIMULATED_INPUT  = "0\n10000\n2000\n";
        final String EXPECTED_OUTPUT = "Enter the year of birth\ninvalid year, enter the year of birth again\ninvalid year, enter the year of birth again\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        int year = hmi.catchYearOfBirth();

        assertEquals(2000, year);
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCatchDayOfBirth() {

        final String SIMULATED_INPUT  = "0\n32\n25\n";
        final String EXPECTED_OUTPUT = "Enter the day of birth e.g. 7\ninvalid day, enter the day of birth again\ninvalid day, enter the day of birth again\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        int day = hmi.catchDayOfBirth();

        assertEquals(25, day);
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCatchMonthOfBirth() {

        final String SIMULATED_INPUT  = "0\n13\n12\n";
        final String EXPECTED_OUTPUT = "Enter month of birth e.g. for Jan enter 1\ninvalid month, enter the month of birth again\ninvalid month, enter the month of birth again\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        int month = hmi.catchMonthOfBirth();

        assertEquals(12, month);
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayAccountDetails() {

        // Expected terminal printing
        final String EXPECTED_OUTPUT =
                "Account holder information\n" +
                "Name Sam K.\n" +
                "Address 7500 Burnaby\n" +
                " DOB          : 2000-02-23\n" +
                " id           : 4500\n" +
                " balance      : 4500.0\n" +
                " Date         : " + LocalDate.now() + "\n";

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HMI hmi = new HMI(new Scanner(System.in));

        hmi.displayAccountDetails(account1);

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayAccountBalance() {

        // Expected terminal printing
        final String EXPECTED_OUTPUT = "The balance is 4500.0\n";

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HMI hmi = new HMI(new Scanner(System.in));

        hmi.displayAccountBalance(account1);

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayManyOp1() {

        final String SIMULATED_INPUT  = "1\n5\n";

        final String EXPECTED_OUTPUT =
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
                        "Good bye\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        hmi.displayMainMenu(account1);

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayManyOp2() {

        final String SIMULATED_INPUT  = "2\n100\n5\n";

        final String EXPECTED_OUTPUT =
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
                        "Good bye\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        hmi.displayMainMenu(account1);

        assertEquals(4400.0, account1.getBalance());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayManyOp3() {

        final String SIMULATED_INPUT  = "3\n300\n5\n";

        final String EXPECTED_OUTPUT =
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
                        "Good bye\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        hmi.displayMainMenu(account1);

        assertEquals(4800.0, account1.getBalance());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayManyOp4() {

        final String SIMULATED_INPUT  = "4\n5\n";

        final String EXPECTED_OUTPUT =
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
                        " balance      : 4500.0\n" +
                        " Date         : " + LocalDate.now() + "\n" +
                        "\nMAIN MENU\n" +
                        "1: check balance\n" +
                        "2: withdraw\n" +
                        "3: Deposit\n" +
                        "4: account details\n" +
                        "5: exit\n" +
                        "Enter a choice\n" +
                        "Good bye\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);

        hmi.displayMainMenu(account1);

        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testDisplayWelcome() {

        final String SIMULATED_INPUT  = "67\n67\n67\n4500\n";
        final String EXPECTED_OUTPUT =
                "Welcome to ATM\n" +
                "Enter an id to search for:\n" +
                "ID does not exist\n" +
                "Enter an id to search for:\n" +
                "ID does not exist\n" +
                "Enter an id to search for:\n" +
                "ID does not exist\n" +
                "Enter an id to search for:\n";

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        Account account = hmi.displayWelcome(accounts);

        assertEquals(4500, account.getId());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCreateAccountWithInvalidDayInput() {

        final String SIMULATED_INPUT  =
                "Sam K.\n" +
                "7500 Burnaby\n" +
                "2\n" +
                "34\n" +
                "0\n" +
                "-34\n" +
                "23\n" +
                "2000\n" +
                "4500\n" +
                "4500\n";

        final String EXPECTED_OUTPUT =
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
                        "Enter the account's initial balance\n";

        AccountsList list = new AccountsList();
        list.setAccount1(new Account());
        list.setAccount2(new Account());
        list.setAccount3(new Account());

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        Account account = hmi.createAccount(list);

        assertEquals(4500, account.getId());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

    @Test
    void testCreateAccountWithInvalidYearInput() {

        final String SIMULATED_INPUT  =
                "Sam K.\n" +
                        "7500 Burnaby\n" +
                        "2\n" +
                        "23\n" +
                        "-10\n" +
                        "0\n" +
                        "10000\n" +
                        "2000\n" +
                        "4500\n" +
                        "4500\n";

        final String EXPECTED_OUTPUT =
                "Enter account holder information\n" +
                        "Enter name\n" +
                        "Enter address\n" +
                        "Enter month of birth e.g. for Jan enter 1\n" +
                        "Enter the day of birth e.g. 7\n" +
                        "Enter the year of birth\n" +
                        "invalid year, enter the year of birth again\n" +
                        "invalid year, enter the year of birth again\n" +
                        "invalid year, enter the year of birth again\n" +
                        "Enter account id\n" +
                        "Enter the account's initial balance\n";

        AccountsList list = new AccountsList();
        list.setAccount1(new Account());
        list.setAccount2(new Account());
        list.setAccount3(new Account());

        // Input configuration
        InputStream inputContent = new ByteArrayInputStream(SIMULATED_INPUT.getBytes());
        System.setIn(inputContent);

        // Output configuration
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner sc = new Scanner(inputContent);
        HMI hmi = new HMI(sc);
        Account account = hmi.createAccount(list);

        assertEquals(4500, account.getId());
        assertEquals(EXPECTED_OUTPUT, outContent.toString());
    }

}