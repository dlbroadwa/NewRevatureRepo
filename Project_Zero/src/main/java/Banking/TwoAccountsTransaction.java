package Banking;

import java.math.BigDecimal;

public abstract class TwoAccountsTransaction extends Transaction {
    protected BigDecimal previousBalanceOfFirstAccount;
    protected BigDecimal updatedBalanceOfFirstAccount;
    protected BigDecimal previousBalanceOfSecondAccount;
    protected BigDecimal updatedBalanceOfSecondAccount;
    protected int accountIDOfFirstAccount;
    protected int accountIDOfSecondAccount;
}
