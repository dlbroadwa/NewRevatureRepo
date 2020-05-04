package com.game.service.accountservices;

import com.game.models.Account;

public class ModificationServiceImp implements ModificationService{
    AccountDetailService accountDetailService;

    public ModificationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void deposit(int amount, String user) {
        Account temp = accountDetailService.getAccount(user);
        if (temp.getCardNumber()==null){
            return;
        }
        temp.addBalance(amount);
    }

    @Override
    public boolean withdraw(int amount, String user) {
        return false;
    }

    @Override
    public void changePassword(String password,String user) {
        Account changed = accountDetailService.getAccount(user);
        changed.setPassword(password);
        accountDetailService.update(changed);
    }

    @Override
    public void changeBankAccount(String bankAccount,String user) {
        Account changed = accountDetailService.getAccount(user);
        changed.setCardNumber(bankAccount);
        accountDetailService.update(changed);
    }
}
