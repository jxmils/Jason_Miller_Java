package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        for (String[] record : customerData) {

            // Parse the data
            int customerId = Integer.parseInt(record[0]);
            String customerName = record[1];
            int chargeAmount = Integer.parseInt(record[2]);
            String chargeDate = record[3];

            // Check if the customer already exists
            Customer existingCustomer = findCustomer(customers, customerId);
            if (existingCustomer != null) {
                // Customer exists, add the charge to their list
                AccountRecord charge = new AccountRecord();
                charge.setCharge(chargeAmount);
                charge.setChargeDate(chargeDate);
                existingCustomer.getCharges().add(charge);
            } else {
                // Create a new customer and add the charge
                Customer newCustomer = new Customer();
                newCustomer.setId(customerId);
                newCustomer.setName(customerName);
                AccountRecord charge = new AccountRecord();
                charge.setCharge(chargeAmount);
                charge.setChargeDate(chargeDate);
                newCustomer.getCharges().add(charge);
                customers.add(newCustomer);
            }
        }
    }

    private static Customer findCustomer(List<Customer> customers, int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
