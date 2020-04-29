import bank.dataaccess.AccountDataAccess;
import bank.dataaccess.ConnectionUtils;
import bank.dataaccess.PostGresConnectionUtil;

public class Main {
    public static void main(String[] args) {
        ConnectionUtils connectionUtils = new PostGresConnectionUtil();
        AccountDataAccess dao = new AccountDataAccess(connectionUtils);
        dao.findAllAccounts();
    }
}
