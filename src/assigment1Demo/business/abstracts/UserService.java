package assigment1Demo.business.abstracts;

import assigment1Demo.entities.concretes.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(User user);
    User get(int id);
    List<User> getAll();
    boolean checkMailCorrect(String mail);
    User getByMail(String mail);
    boolean isVerified(User user);
    void verificate(User user);
}
