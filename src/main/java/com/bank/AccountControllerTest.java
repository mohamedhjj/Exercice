package com.bank;

import com.bank.model.Account;
import com.bank.model.StatementEntry;
import com.bank.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private AccountRepository accountRepository;

    private RestTemplate rest = new RestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/accounts";
    }

    @Test
    public void testDepositWithdrawalAndStatement() {
        // Create account
        Account account = rest.postForObject(baseUrl(), null, Account.class);
        assertThat(account.getBalance()).isZero();

        // Deposit
        rest.postForEntity(baseUrl() + "/" + account.getId() + "/deposit?amount=100", null, Account.class);
        // Withdraw
        rest.postForEntity(baseUrl() + "/" + account.getId() + "/withdraw?amount=40", null, Account.class);

        // Get statement
        ResponseEntity<StatementEntry[]> response = rest.getForEntity(baseUrl() + "/" + account.getId() + "/statement", StatementEntry[].class);
        StatementEntry[] entries = response.getBody();
        assertThat(entries).isNotNull();
        assertThat(entries.length).isEqualTo(2);
        assertThat(entries[0].getAmount()).isEqualTo(100);
        assertThat(entries[1].getAmount()).isEqualTo(-40);
    }
}