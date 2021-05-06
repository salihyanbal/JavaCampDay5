package assigment1Demo;

import assigment1Demo.business.abstracts.UserService;
import assigment1Demo.business.concretes.AuthManager;
import assigment1Demo.business.concretes.UserManager;
import assigment1Demo.business.concretes.VerificationManager;
import assigment1Demo.core.services.LoginWithGmailAdapter;
import assigment1Demo.core.services.VerificationMailSender;
import assigment1Demo.dataAccess.concretes.UserInMemoryDao;
import assigment1Demo.entities.concretes.User;

import java.util.Scanner;

public class Main {
    static boolean loggedIn;
    static Scanner scan = new Scanner(System.in);
    static UserService userService = new UserManager(new UserInMemoryDao());
    static AuthManager authManager = new AuthManager(userService,new VerificationManager(new VerificationMailSender()),new LoginWithGmailAdapter());
    public static void main(String[] args) {


        String mainMessage = "********** xxx sistemine hoş geldiniz **********\n\n" +
                "1. Giriş yap\n" +
                "2. Google ile giriş yap\n" +
                "3. Kayıt ol\n" +
                "4. Çıkış Yap\n" +
                "0. Ana mesajı göster\n" +
                "************************************************";
        String loggedMessage = "********** xxx sistemine hoş geldiniz **********\n\n" +
                "1. Hesabımı sil\n"+
                "2. Çıkış yap\n" +
                "0. Mesajı göster\n" +
                "************************************************";;

        /*User user = new User(1,"Salih","Yanbal","test@test.com","1234567");
        User user1 = new User("test@test.com","1234567");
        authManager.register(user);
        authManager.login(user1);*/
        System.out.println(mainMessage);
        int key;
        while (true){
            System.out.print("Lütfen işlem seçiniz: ");
            key = scan.nextInt();
            switch (key){
                case 1:
                    login();
                    break;
                case 2:
                    loginWithGoogle();
                    break;
                case 3:
                    register();
                    break;
                case 4:
                    return;
                case 0:
                    System.out.println(mainMessage);
                    break;
            }
            loggedloop:
            while (loggedIn){
                System.out.print("Lütfen işlem seçiniz: ");
                key = scan.nextInt();
                switch (key){
                    case 1:
                        deleteAccount();
                        break;
                    case 2:
                        System.out.println(mainMessage);
                        loggedIn = false;
                        break loggedloop;
                    case 0:
                        System.out.println(loggedMessage);
                        break;
                }
            }
        }
    }

    public static void register(){
        scan.nextLine();
        String firstName,lastName,mail,password;
        System.out.print("Lütfen adınızı giriniz: ");
        firstName = scan.nextLine();
        System.out.print("Lütfen soyadınızı giriniz: ");
        lastName = scan.nextLine();
        System.out.print("Lütfen mailinizi giriniz: ");
        mail = scan.nextLine();
        System.out.print("Lütfen parolanızı giriniz: ");
        password = scan.nextLine();
        User user = new User(UserInMemoryDao.getLastId() + 1,firstName,lastName,mail,password);
        loggedIn = authManager.register(user);
    }

    public static void login(){
        scan.nextLine();
        String mail,password;
        System.out.print("Lütfen mailinizi giriniz: ");
        mail = scan.nextLine();
        System.out.print("Lütfen parolanızı giriniz: ");
        password = scan.nextLine();
        User user = new User(mail,password);
        loggedIn = authManager.login(user);
    }

    public static void loginWithGoogle(){
        scan.nextLine();
        String mail,password;
        System.out.print("Lütfen mailinizi giriniz: ");
        mail = scan.nextLine();
        System.out.print("Lütfen parolanızı giriniz: ");
        password = scan.nextLine();
        User user = new User(mail,password);
        loggedIn = authManager.loginWithGoogle(user);
    }

    public static void deleteAccount(){
        System.out.println("Sistem henüz eklenmemiş!");
    }
}
