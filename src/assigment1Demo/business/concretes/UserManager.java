package assigment1Demo.business.concretes;

import assigment1Demo.business.abstracts.UserService;
import assigment1Demo.dataAccess.abstracts.UserDao;
import assigment1Demo.entities.concretes.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements UserService {

    private static final Pattern VALID_EMAIL_TYPE = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private UserDao dao;

    public UserManager(UserDao dao){
        this.dao = dao;
    }

    @Override
    public void add(User user) {
        this.dao.add(user);
    }

    @Override
    public void delete(User user) {
        if(!(getByMail(user.getMail()) != null) && isVerified(user)){
            System.out.println("Kullanıcı silinemez.");
            return;
        }
        this.dao.delete(user);
    }

    @Override
    public User get(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<User> getAll() {
        return this.dao.getAll();
    }

    public boolean checkMailCorrect(String mail){
        Matcher matcher = VALID_EMAIL_TYPE.matcher(mail);
        return matcher.find();
    }

    public User getByMail(String mail){
        User foundedUser = this.dao.getByMail(mail);
        return foundedUser;
    }

    public boolean isVerified(User user){
        return this.dao.isVerified(user);
    }

    public void verificate(User user){
        user.setVerified(true);
    }
}
