package com.game.service.accountservices;

import com.game.models.Account;

public class ModificationServiceImp implements ModificationService{
    Account changed;
    AccountDetailService accountDetailService;

    ModificationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void deposit(int amount) {
        changed.addBalance(amount);
    }

    @Override
    public void withdraw(int amount) {
        if (amount>changed.getBalance()){
            //checks if you have enough
            return;
        }
        changed.subtractBalance(amount);
        accountDetailService.update(changed);
    }

    @Override
    public void changePassword(String password) {
        changed.setPassword(password);
        accountDetailService.update(changed);
    }

    @Override
    public void changeBankAccount(String bankAccount) {
        changed.setBankAccount(bankAccount);
        accountDetailService.update(changed);
    }

    @Override
    public void adminAccess(String username) {
        changed = accountDetailService.findByID(username);
    }

    @Override
    public void standardAccess() {
        changed = accountDetailService.getCurr();
    }
}
