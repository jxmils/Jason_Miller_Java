package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    Customer cust;

    @BeforeEach
    public void setUp() {
        cust = new Customer();
    }
    @Test
    public void getBalanceOneCharge() {
        cust.setId(0);
        cust.setName("Netflix");
        List<AccountRecord> custCharges = cust.getCharges();
        cust.getCharges().add(new AccountRecord() {{
            setCharge(10);
            setChargeDate("2023-06-15");
        }});
        assertEquals(10, cust.getBalance());
    }

    @Test
    public void getBalanceMoreThanOneCharge() {
        cust.setId(0);
        cust.setName("Netflix");
        List<AccountRecord> custCharges = cust.getCharges();
        cust.getCharges().add(new AccountRecord() {{
            setCharge(10);
            setChargeDate("2023-06-15");
        }});
        cust.getCharges().add(new AccountRecord() {{
            setCharge(30);
            setChargeDate("2023-06-15");
        }});
        assertEquals(40, cust.getBalance());
    }

    @Test
    public void testToString() {
        cust.setId(0);
        cust.setName("Netflix");
        List<AccountRecord> custCharges = cust.getCharges();
        cust.getCharges().add(new AccountRecord() {{
            setCharge(10);
            setChargeDate("2023-06-15");
        }});
        String answer = "Customer ID: " + cust.getId() + "\n"
                + "Customer Name: " + cust.getName() + "\n"
                + "Customer Balance: " + cust.getBalance();
        assertEquals(answer, cust.toString());
    }
}