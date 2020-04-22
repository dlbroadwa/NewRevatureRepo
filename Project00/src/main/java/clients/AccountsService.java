package clients;

import data.IAccounts;
import model.Accounts;


public class AccountsService {
    private IAccounts <Accounts, Integer > account;

    public AccountsService (IAccounts<Accounts, Integer> account){
        this.account = account;
    }

    public void updateBalance(Accounts obj, float amount){
        this.account.updateBalance(obj, amount);

    }
    public Accounts findByAccount(String email){
        Accounts account = this.account.findByAccount(email);
        return account;
    }

}
