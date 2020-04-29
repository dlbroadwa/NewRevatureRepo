import bank.dataaccess.AccountDataAccess;
import bank.dataaccess.ConnectionUtils;
import bank.dataaccess.PostGresConnectionUtil;

public class Main {
    public static void main(String[] args) {
        ConnectionUtils connectionUtils = new PostGresConnectionUtil(
                System.getenv("POSTGRES_URL"),System.getenv("POSTGRES_USERNAME"), System.getenv("POSTGRES_PASSWORD"), System.getenv("POSTGRES_DEFAULT_SCHEMA"));
        AccountDataAccess dao = new AccountDataAccess(connectionUtils);
        dao.findAllAccounts();
    }
}
