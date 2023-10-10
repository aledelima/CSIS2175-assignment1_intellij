import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountsListTest {

    private Account account1;
    private Account account2;
    private Account account3;
    private AccountsList accounts;

    @BeforeEach
    void initializer() {
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

    @Test
    void checkIdExistence() {

        assertFalse(accounts.checkIdExistence(5000));
        assertTrue(accounts.checkIdExistence(4500));
        assertTrue(accounts.checkIdExistence(5555));
        assertTrue(accounts.checkIdExistence(6666));
    }

    @Test
    void getAccountById() {

        assertEquals(4500, accounts.getAccountById(4500).getId());
        assertEquals(5555, accounts.getAccountById(5555).getId());
        assertEquals(6666, accounts.getAccountById(6666).getId());
        assertEquals(6666, accounts.getAccountById(7000).getId()); // Nonexistent return the last one
    }
}