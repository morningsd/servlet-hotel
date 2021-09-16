package edu.demian.wp.model.service.impl;

import edu.demian.wp.model.dao.AccountDao;
import edu.demian.wp.model.dao.impl.AccountDaoImpl;
import edu.demian.wp.model.entity.Account;
import edu.demian.wp.model.exception.ServiceException;
import edu.demian.wp.model.service.AccountService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void validateRegister(Account account) {
        if (accountDao.findByEmail(account.getEmail()) != null) {
            throw new ServiceException("Account with such email already exists");
        }
    }

    @Override
    public void register(Account account, String password) {
        accountDao.save(account, getPasswordSha(password));
    }

    @Override
    public Account login(String email, String password) {
        Account account = accountDao.findByEmailAndPassword(email, getPasswordSha(password));
        if (account == null) {
            throw new ServiceException("No user with such email and password");
        }
        return account;
    }

    public static String getPasswordSha(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
