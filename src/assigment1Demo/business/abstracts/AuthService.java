package assigment1Demo.business.abstracts;

import assigment1Demo.entities.concretes.User;

public interface AuthService {
    boolean login(User user);
    boolean register(User user);
    boolean loginWithGoogle(User user);
}
