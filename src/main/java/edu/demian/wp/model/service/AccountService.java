package edu.demian.wp.model.service;

import edu.demian.wp.model.entity.Account;

public interface AccountService {
    void validateRegister(Account account);
    void register(Account account, String password);

    Account login(String email, String password);
}
