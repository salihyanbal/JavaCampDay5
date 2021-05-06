package assigment1Demo.business.concretes;

import assigment1Demo.business.abstracts.VerificationService;
import assigment1Demo.core.services.MailSender;
import assigment1Demo.entities.concretes.User;

import java.util.Scanner;

public class VerificationManager implements VerificationService {
    private MailSender mailSender;
    public VerificationManager(MailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public boolean verificate(String mail) {
        String code = "";
        try{
            Thread.sleep(1000);
            code = mailSender.send(mail);
        }catch (Exception e){
            System.out.println("HATA!!!");
        }
        Scanner scan = new Scanner(System.in);
        System.out.print("Lütfen gelen kodu giriniz: ");
        String enteredCode = scan.nextLine();
        if(!code.equals(enteredCode)){
            return false;
        }
        return true;
    }
}
