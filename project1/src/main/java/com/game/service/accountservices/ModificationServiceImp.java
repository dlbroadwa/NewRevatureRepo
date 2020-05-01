package com.game.service.accountservices;

import com.game.models.Account;

public class ModificationServiceImp implements ModificationService{
    AccountDetailService accountDetailService;

    ModificationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void deposit(int amount,String user) {
        Account changed = accountDetailService.getAccount(user);
        changed.addBalance(amount);
    }

    @Override
    public boolean withdraw(int amount,String user) {
        Account changed = accountDetailService.getAccount(user);
        if (amount>changed.getBalance()){
            //checks if you have enough
            return false;
        }
        changed.subtractBalance(amount);
        accountDetailService.update(changed);
        return true;
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
        changed.setBankAccount(bankAccount);
        accountDetailService.update(changed);
    }
}
