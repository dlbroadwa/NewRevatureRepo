import bank.dataaccess.AccountDataAccess;
import bank.dataaccess.ConnectionUtils;
import bank.dataaccess.PostGresConnectionUtil;

public class Main {
    public static void main(String[] args) {
        ConnectionUtils connectionUtils = new PostGresConnectionUtil(
                "jdbc:postgresql://postgres.cls1tahxfwjt.us-east-2.rds.amazonaws.com:5432/postgres","online_banking_user", "dontbother!135",
                "public");
        AccountDataAccess dao = new AccountDataAccess(connectionUtils);
        dao.findAllAccounts();
    }
}
