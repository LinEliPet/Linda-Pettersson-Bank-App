package com.company;

import java.util.ArrayList;
import java.util.List;

public class Bank{

    private String bankName;
    private List<Customer> customers;

    public Bank(){
        this.customers = new ArrayList<>();
    }

    public Bank(String bankName){
        this.bankName = bankName;
        this.customers = new ArrayList<>();
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    private long findCustomer(long SSN) {
        int customerIndex = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getSSN() == SSN){
                customerIndex = i;
            }
        }
        return SSN;
    }

    public List<String> getCustomers(){
        List<String> customerList = new ArrayList<>();

        for (Customer customer : customers) {
            customerList.add(
                    customer.getFirstName()
                            + " " +
                            customer.getLastName()
                            + " " +
                            customer.getSSN()
            );
        }
        return customerList;
    }

    public List<String> getCustomersAndAccounts(){
        List<String> customerList = new ArrayList<>();
        String accountInformation = "";

        for (Customer customer : customers) {
            //add account information
            accountInformation = "";
            for (int i = 0; i < customer.getAccounts().size(); i++) {
                accountInformation +=
                        customer.getAccounts().get(i).getAccountNr()
                                + " " +
                                customer.getAccounts().get(i).getType()
                                + " " +
                                customer.getAccounts().get(i).getBalance();
                if(i < (customer.getAccounts().size())-1) accountInformation += " , ";
            }
            customerList.add(
                    customer.getFirstName()
                            + " " +
                            customer.getLastName()
                            + " " +
                            customer.getSSN()
                            + " : " +
                            accountInformation
            );
        }
        return customerList;
    }

    public boolean addCustomer(String firstName, String lastName, long SSN){
        //if customer not found then add the customer
        long customerIndex = findCustomer(SSN);
        if (customerIndex==-1) customers.add(new Customer(firstName,lastName,SSN));
        return (customerIndex==-1);
    }

    public List<String> getCustomer(long SSN){
        //check if customer does not exist
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return null;

        List<String> customerInfo = new ArrayList<>();

        //add customer name
        customerInfo.add("Customer name: "+customers.get((int) customerIndex).getFirstName()+" "+customers.get((int) customerIndex).getLastName());

        //add account information
        for (long i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {
            customerInfo.add(
                    "Account number: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getAccountNr()
                            +", Balance: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getBalance()
                            +", Account type: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getType()
            );
        } return customerInfo;
    }

    public boolean changeCustomerName(String firstName, String lastName, long SSN){
        //check if customer does not exist
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return false;

        //set name if customer found
        customers.get((int) customerIndex).setFirstName(firstName);
        customers.get((int) customerIndex).setLastName(lastName);

        return true;
    }

    public List<String> removeCustomer(long SSN){
        //check if customer does not exist
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return null;

        List<String> accountInformation = new ArrayList<>();

        for (long i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {
            accountInformation.add(
                    "Account number: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getAccountNr()
                            +", Cashback: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getBalance()
                            +", Account type: "+
                            customers.get((int) customerIndex).getAccounts().get((int) i).getType()
            );
        }


        //remove customer after account is emptied
        customers.remove(customerIndex);


        return accountInformation;
    }

    public Object addAccount(long SSN, String accountType, float interestRate){
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return -1;

        return customers;
    }

    public boolean presetAccount(long SSN, String accountType, float interestRate, float balance, long accountNr){
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return false;

        customers.get((int) customerIndex);
        return true;
    }

    public String getAccount(long SSN, long accountNr){
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return "Cannot find customer";

        String returnString = "Account does not exist";
        for (long i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {
            if (customers.get((int) customerIndex).getAccounts().get((int) i).getAccountNr()==accountNr){
                returnString =
                        "Account number: "+
                                accountNr
                                +", Balance: "+
                                customers.get((int) customerIndex).getAccounts().get((int) i).getBalance()
                                +", Account type: "+
                                customers.get((int) customerIndex).getAccounts().get((int) i).getType();
            }
        }

        return returnString;
    }

    public boolean deposit(long SSN, long accountNr, int amount){
        //check if customer does not exist
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return false;

        for (int i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {
            if (customers.get((int) customerIndex).getAccounts().get(i).getAccountNr() == accountNr) {
                customers.get((int) customerIndex).getAccounts().get(i).deposit(amount);
            }
        }return true;
    }

    public boolean withdraw(long SSN, long accountNr, int amount){
        //check if customer does not exist
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return false;
        boolean returnVal = false;

        for (long i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {
            if (customers.get((int) customerIndex).getAccounts().get((int) i).getAccountNr() == accountNr) {
                returnVal = customers.get((int) customerIndex).getAccounts().get((int) i).withdraw(amount);
            }
        }return returnVal;

    }

    public String closeAccount(long SSN, long accountNr){
        long customerIndex = findCustomer(SSN);
        if (customerIndex == -1) return "Cannot find customer";

        String returnString = "Account does not exist"; //SHOULD BE CHECKED IN CUSTOMER.JAVA INSTEAD
        for (long i = 0; i < customers.get((int) customerIndex).getAccounts().size(); i++) {

            if (customers.get((int) customerIndex).getAccounts().get((int) i).getAccountNr()==accountNr){
                returnString =
                        "Account number: "+
                                accountNr
                                +", Balance: "+
                                customers.get((int) customerIndex).getAccounts().get((int) i).getBalance();
                customers.get((int) customerIndex).deleteAccount(accountNr);
            }
        }
        return returnString;


    }

}