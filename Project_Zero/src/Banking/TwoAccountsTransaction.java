package Banking;

import java.math.BigDecimal;

public abstract class TwoAccountsTransaction extends Transaction {
    private BigDecimal previousBalanceOfFirstAccount;
    private BigDecimal updatedBalanceOfFirstAccount;
    private BigDecimal previousBalanceOfSecondAccount;
    private BigDecimal updatedBalanceOfSecondAccount;
    private int accountIDOfFirstAccount;
    private int accountIDOfSecondAccount;
}
