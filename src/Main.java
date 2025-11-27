import com.Login.LoginEkranı;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        LoginEkranı.loginwp = null;

        try {
            LoginEkranı.loginwp = ImageIO.read(new File("loginarkaplan.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(LoginEkranı.loginwp != null){

            LoginEkranı loginEkranı = new LoginEkranı();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    loginEkranı.setVisible(true);
                }
            });

        }

    }
}