package assigment1Demo.microServices.Google;

import java.util.ArrayList;
import java.util.List;

public class LoginWithGmail {
    static List<Gmail> gmails;
    public LoginWithGmail(){
        gmails = new ArrayList<>();
        gmails.add(new Gmail("Test","test","test@gmail.com","1234567"));
    }
    public boolean login(String mail,String password){
        if(find(mail) != null && find(mail).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public Gmail find(String mail){
        Gmail gmailToReturn = this.gmails.stream()
                .filter((user) -> user.getMail().equals(mail) )
                .findFirst()
                .orElse(null);
        return gmailToReturn;
    };

}
