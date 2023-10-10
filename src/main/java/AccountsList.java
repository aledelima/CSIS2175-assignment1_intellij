
/**
 * Holds information of three Accounts to be used as a primitive list.
 */
public class AccountsList {

    private Account account1;
    private Account account2;
    private Account account3;

    /**
     * Default constructor. Create three instances of empty accounts.
     */
    public AccountsList() {

        this.account1 = new Account();
        this.account2 = new Account();
        this.account3 = new Account();
    }

    /**
     * Checks wether the parameter id is already in use by any of the three accounts.
     * @param id id number to be checked.
     * @return true when id is already in use; otherwise returns false.
     */
    public boolean checkIdExistence(int id) {

        return  (id == this.account1.getId() ||
                 id == this.account2.getId() ||
                 id == this.account3.getId()
                );
    }

    /**
     * Searches for account object based on account id
     * @param id Account ID to search for
     * @return
     */
    public Account getAccountById(int id) {

        Account accountFound;
        if (id == this.account1.getId())
            accountFound = account1;
        else if (id == this.account2.getId())
            accountFound = account2;
        else
            accountFound = account3;

        return accountFound;
    }

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public Account getAccount3() {
        return account3;
    }

    public void setAccount3(Account account3) {
        this.account3 = account3;
    }
}
