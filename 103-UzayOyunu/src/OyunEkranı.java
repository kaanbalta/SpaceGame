import javax.swing.*;
import java.awt.*;

public class OyunEkranı extends JFrame {



    public static void main(String[] args) {

        OyunEkranı ekran = new OyunEkranı();

        ekran.setResizable(false); //Kullanıcı pencereyi yeniden boyutlandıramaz
        ekran.setFocusable(false); //JFrameye değil birazdan oluşturulacak JPanele odaklanması için
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pencere kapatılınca uygulama sonlanır
        ekran.setLocationRelativeTo(null); //Uygulamayı ekranın ortasında başlatır

        Oyun oyun = new Oyun();
        oyun.requestFocus(); //Klavye işlemlerini anlaması için kullanılır,odağı üstüne alır

        oyun.addKeyListener(oyun); //a  d Klavyeden işlemleri almayı sağlar
        //Keylistenera oyunu verince sadece jpanel üzerinde çalışmış olacak

        oyun.setFocusable(true); //odağı JPanele vermeyi sağlar
        oyun.setFocusTraversalKeysEnabled(false); //Klavye işlemlerini anlaması için gerekli(false diyince işlemler direkt gerçekleşir)

        ekran.add(oyun);
        ekran.setVisible(true);





    }


}
