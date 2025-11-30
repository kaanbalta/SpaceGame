import com.login.LoginEkrani;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        LoginEkrani.loginImg = null;

        try {
            LoginEkrani.loginImg = ImageIO.read(new File("img/loginarkaplan.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(LoginEkrani.loginImg != null){

            LoginEkrani loginEkranı = new LoginEkrani();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    loginEkranı.setVisible(true);
                }
            });

        }

    }
}