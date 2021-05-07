package assigment1Demo.business.concretes;

import assigment1Demo.business.abstracts.AuthService;
import assigment1Demo.business.abstracts.UserService;
import assigment1Demo.business.abstracts.VerificationService;
import assigment1Demo.core.services.LoginWithGmailService;
import assigment1Demo.core.services.MailSender;
import assigment1Demo.entities.concretes.User;

import java.util.Scanner;

public class AuthManager implements AuthService {

    private UserService userService;
    private VerificationService verificationService;
    private LoginWithGmailService loginWithGmailService;

    public AuthManager(UserService userService, VerificationService verificationService, LoginWithGmailService loginWithGmailService){
        this.userService = userService;
        this.verificationService = verificationService;
        this.loginWithGmailService = loginWithGmailService;
    }
    @Override
    public boolean login(User user) {
        User userToCheck = this.userService.getByMail(user.getMail());
        if(userToCheck == null){
            System.out.println("Kayıtlı kullanıcı bulunamadı");
            return false;
        }
        if(!user.getPassword().equals(userToCheck.getPassword())){
            System.out.println("Mail ya da parola yanlış");
            return false;
        }
        System.out.println("Giriş başarılı...");
        return true;
    }

    @Override
    public boolean register(User user) {
        //checkmailcorrect ve  ad soyad şifre uzunluk kontrolü business içinde yanlıştır, hocamız daha validate'i anlatmadığı için buraya eklemek zorunda kaldım
        if(!(this.userService.checkMailCorrect(user.getMail()) &&
                this.userService.getByMail(user.getMail()) == null &&
                user.getFirstName().length() >= 2 && user.getLastName().length() >= 2 &&
                user.getPassword().length() >= 6)){
            System.out.println("Kayıt başarısız...");
            return false;
        }
        System.out.println("Mail gönderiliyor...");
        if(!verificationService.verificate(user.getMail())){
            System.out.println("Kodu doğru girmelisiniz!");
            return false;
        }
        System.out.println("Hesabınız onaylandı ve kaydınız yapıldı.");
        this.userService.add(user);
        return true;

    }

    @Override
    public boolean loginWithGoogle(User user) {
        if(!loginWithGmailService.login(user.getMail(),user.getPassword())){
            System.out.println("Google bilgilerinizi onaylamadı");
            return false;
        }
        if(this.userService.getByMail(user.getMail()) == null){
            this.userService.add(user);
        }
        System.out.println("Giriş başarılı...");
        return true;
    }


}
