package com.game.service.accountservices;

import com.game.models.Account;

public class ModificationServiceImp implements ModificationService{
    AccountDetailService accountDetailService;

    public ModificationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public boolean deposit(int amount, String user) {
        Account temp = accountDetailService.getAccount(user);
        if (temp.getCardNumber()==null){
            return false;
        }
        if (amount<=0){
            return false;
        }
        temp.addBalance(amount);
        accountDetailService.update(temp);
        return true;
    }

    @Override
    public boolean withdraw(int amount, String user) {
        Account temp = accountDetailService.getAccount(user);
        if(amount>temp.getBalance()){
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
            long card = Long.parseLong(bankAccount);
            Account changed = accountDetailService.getAccount(user);
            changed.setCardNumber(bankAccount);
            accountDetailService.update(changed);
            return false;
        }
        return true;
    }

    private boolean validCard(String card){
        if (card.length()>=13&&card.length()<=16){
            return false;
        }
        String f,f2;
        f=card.substring(0,1);
        f2=card.substring(0,2);
        if (!(f.equals("4")||f.equals("5")||f.equals("6")||f2.equals("37"))){
            return false;
        }
        int sumEven = 0,sumOdd = 0;
        for (int i = 0; i<card.length(); i++) {
            if (i%2==0){
                sumEven+=Integer.parseInt(card.charAt(i)+"");
            }
            else {
                sumOdd+=Integer.parseInt(card.charAt(i)+"");
            }
        }

        return (sumEven + sumOdd) % 10 == 0;
    }
}
