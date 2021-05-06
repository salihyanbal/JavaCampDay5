package assigment1Demo.dataAccess.abstracts;

import assigment1Demo.entities.concretes.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    void update(User user);
    void delete(User user);
    User get(int id);
    User getByMail(String mail);
    List<User> getAll();
    boolean isVerified(User user);
}
