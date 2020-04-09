package Application;

import java.math.BigDecimal;

public abstract class SingleAccountTransaction extends Transaction {
    protected BigDecimal previousBalance;
    protected BigDecimal updatedBalance;
}
