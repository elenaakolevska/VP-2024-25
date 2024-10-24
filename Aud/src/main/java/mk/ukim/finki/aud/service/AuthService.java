package mk.ukim.finki.aud.service;

import mk.ukim.finki.aud.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password,
                  String repeatPassword, String name, String surname);

}
