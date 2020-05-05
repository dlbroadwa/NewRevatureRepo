package clients;

import com.data.IAccounts;
import com.model.Accounts;
import com.model.Users;

import java.util.List;


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
    public List<Accounts> findAll(){
        return this.account.findAll();
    }

    public Accounts createNewAccount(Users user, String type, float initialBalance){
        return this.account.createNewAccount(user, type, initialBalance);
    }
}
