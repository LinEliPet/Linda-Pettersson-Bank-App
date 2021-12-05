package com.company;

public class Account {

        private float balance;
        private long accountNumber;
        private String accountType;


        public Account() {
            this.accountType = "Debit";
            this.balance = 0;
        }

        public void createAccountNr(long accountNr) {
            this.accountNumber = accountNr;
        }
        public long getAccountNr() {
            return accountNumber;
        }
        public String getType() {
            return accountType;
        }

        public void deposit(int amount) {
            this.balance += amount;
        }
        public boolean withdraw(float amount) {
            if (amount >= balance) this.balance -= amount;
            return (amount >= balance);
        }
        public float getBalance() {
            return balance;
        }
    }