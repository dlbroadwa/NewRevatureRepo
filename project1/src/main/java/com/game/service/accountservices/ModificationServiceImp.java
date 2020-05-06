package com.game.service.accountservices;

import com.game.data.MessageSQLRepo;
import com.game.models.Account;
import org.apache.log4j.Logger;

public class ModificationServiceImp implements ModificationService{
    AccountDetailService accountDetailService;
    static final Logger logger = Logger.getLogger(MessageSQLRepo.class);

    public ModificationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public boolean deposit(int amount, String user) {
        Account temp = accountDetailService.getAccount(user);
        if (temp.getCardNumber()==null){
            logger.debug("No card number stored");
            return false;
        }
        if (amount<=0){
            logger.debug("Can't deposit less or equal to zero");
            return false;
        }
        temp.addBalance(amount);
        accountDetailService.update(temp);
        return true;
    }

    @Override
    public boolean withdraw(int amount, String user) {
        Account temp = accountDetailService.getAccount(user);
        if(amount>temp.getBalance()||amount<=0){
            logger.debug("Invalid amount requested");
            return false;
        }
        temp.subtractBalance(amount);
        accountDetailService.update(temp);
        return true;
    }

    @Override
    public void changePassword(String password,String user) {
        Account changed = accountDetailService.getAccount(user);
        changed.setPassword(password);
        accountDetailService.update(changed);
    }

    @Override
    public boolean changeBankAccount(String bankAccount, String user) {
        if (validCard(bankAccount)) {
            Account changed = accountDetailService.getAccount(user);
            changed.setCardNumber(bankAccount);
            accountDetailService.update(changed);
            return true;
        }
        return false;
    }

    private boolean validCard(String card){
        if (card.length()>=13&&card.length()<=16){
            return false;
        }
        String f;
        String f2;
        f=card.substring(0,1);
        f2=card.substring(0,2);
        if (!(f.equals("4")||f.equals("5")||f.equals("6")||f2.equals("37"))){
            return false;
        }

        return oddDoubleEvenSum(card) % 10 == 0;
    }

    private int oddDoubleEvenSum(String card){
        int sum = 0;
        String tempString="";
        int tempNum;
        for (int i = 0; i<card.length(); i++) {
            tempNum = Integer.parseInt(card.charAt(i) + "");
            if (i%2==0){
                sum += tempNum;
            }
            else {
                tempNum *= 2;
                if (tempNum / 10 == 0) {
                    sum += tempNum;
                } else {
                    tempString = tempNum + "";
                    sum += Integer.parseInt(tempString.charAt(0) + "") +
                            Integer.parseInt(tempString.charAt(1) + "");
                }
            }
        }
        return sum;
    }
}
