package com.williamchung.project0.services;

import com.williamchung.project0.models.User;
import com.williamchung.project0.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addNewUser(String username, String password){
        User newUser = new User(username, password);
        userRepository.save(newUser);
    }

    public User authenticateUser(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    //Deposit methods
    public void deposit(User user, String stringAmount){
        user.setCheckingBalance(user.getCheckingBalance() + Double.parseDouble(stringAmount));
        this.userRepository.update(user, user.getId());
    }
    public boolean depositValid(String stringAmount) {
        try {
            Double numericAmount = Double.parseDouble(stringAmount);
            if (numericAmount >= 0.00) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException exception) {
            System.out.println(exception);
            return false;
        }
    }


    //Withdrawal methods
    public void withdraw(User user, String stringAmount){
        user.setCheckingBalance(user.getCheckingBalance() - Double.parseDouble(stringAmount));
        this.userRepository.update(user, user.getId());
    }
    public boolean withdrawalValid(String stringAmount, User user) {
        try {
            Double numericAmount = Double.parseDouble(stringAmount);
            if (numericAmount >= 0.00 && numericAmount <= user.getCheckingBalance()) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException exception) {
            System.out.println(exception);
            return false;
        }
    }


    //Transfer methods
    public boolean transferValid(String stringAmount, User user, String stringRecipient) {
        try {
            Double numericAmount = Double.parseDouble(stringAmount);
            User recipient = this.getUserByUsername(stringRecipient);
            if (numericAmount >= 0.00 &&
                numericAmount <= user.getCheckingBalance() &&
                recipient != null &&
                ! recipient.getUsername().equals(user.getUsername())
            ){
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }
    public void transfer(User user, String stringAmount, String stringRecipient){
        Double numericAmount = Double.parseDouble(stringAmount);
        User recipient = this.getUserByUsername(stringRecipient);
        user.setCheckingBalance(user.getCheckingBalance() - numericAmount);
        recipient.setCheckingBalance(recipient.getCheckingBalance() + numericAmount);
        userRepository.update(user, user.getId());
        userRepository.update(recipient, recipient.getId());
    }
}
