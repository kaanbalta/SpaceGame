import com.Login.LoginEkrani;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        LoginEkrani.loginwp = null;

        try {
            LoginEkrani.loginwp = ImageIO.read(new File("img/loginarkaplan.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(LoginEkrani.loginwp != null){

            LoginEkrani loginEkranı = new LoginEkrani();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    loginEkranı.setVisible(true);
                }
            });

        }

    }
}