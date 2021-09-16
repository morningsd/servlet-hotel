package edu.demian.wp.model.dao;

import edu.demian.wp.model.entity.Account;

public interface AccountDao {
    Account find(long id);
    Account findByEmail(String email);
    Account findByEmailAndPassword(String email, String passwordSha);

    void save(Account account, String passwordSha);
}
