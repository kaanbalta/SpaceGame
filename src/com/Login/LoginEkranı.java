package com.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import com.AnaEkran.AnaEkran;
import com.Database.Database;

public class LoginEkranı extends JFrame {

    private Database database = new Database();
    public static BufferedImage loginwp;
    private JButton oturumAçButton;
    private JButton hesapOluşturButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel panel;
    private JButton çıkışButton;
    private JLabel mesaj;
    private JLabel Kadılabel;
    private JLabel şifrelabel;
    private HesapOluştur hesapOluştur = new HesapOluştur();
    public static String Kullanıcıadı;

    public String getTextField1() {
        return textField1.getText();
    }

    public LoginEkranı(){

        panel = new CustomPanel();
        panel.setLayout(null);

        oturumAçButton = new JButton("Oturum Aç");
        hesapOluşturButton = new JButton("Hesap Oluştur");
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        çıkışButton = new JButton("Çıkış");
        mesaj = new JLabel();
        Kadılabel = new JLabel("Kullanıcı adı : ");
        şifrelabel = new JLabel("Parola : ");

        Kadılabel.setFont(new Font("Arial", Font.BOLD, 14));
        şifrelabel.setFont(new Font("Arial", Font.BOLD, 14));

        Kadılabel.setBounds(130,130,100,33);
        şifrelabel.setBounds(130,180,100,33);
        textField1.setBounds(230,130,300,33);
        passwordField1.setBounds(230,180,300,33);
        oturumAçButton.setBounds(130,230,200,33);
        hesapOluşturButton.setBounds(330,230,200,33);
        çıkışButton.setBounds(130,270,400,33);
        mesaj.setBounds(230,300,300,33);

        panel.add(Kadılabel);
        panel.add(şifrelabel);
        panel.add(textField1);
        panel.add(passwordField1);
        panel.add(oturumAçButton);
        panel.add(hesapOluşturButton);
        panel.add(çıkışButton);
        panel.add(mesaj);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,460);
        setLocationRelativeTo(null);
        setResizable(false);

        oturumAçButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.setText("");
                String girilenKullaniciAdi = textField1.getText();
                String girilenSifre = new String(passwordField1.getPassword());

                LinkedHashMap<String, String> kullanıcılar = database.Kullanıcıgetir();

                if (kullanıcılar.containsKey(girilenKullaniciAdi)) {

                    String kayitliSifre = kullanıcılar.get(girilenKullaniciAdi);

                    if (kayitliSifre.equals(girilenSifre)) {
                        Kullanıcıadı = girilenKullaniciAdi;
                        AnaEkran anaEkran = new AnaEkran(Kullanıcıadı);
                        setVisible(false);
                        anaEkran.setVisible(true);
                    }
                    else {
                        mesaj.setForeground(Color.RED);
                        mesaj.setText("Kullanıcı adı veya parola yanlış");
                    }
                }
                else {
                    mesaj.setForeground(Color.RED);
                    mesaj.setText("Kullanıcı adı veya parola yanlış");                }
            }
        });

        hesapOluşturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hesapOluştur.hesapolusturwp = loginwp;
                hesapOluştur.setVisible(true);
            }
        });


        çıkışButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    static class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(loginwp, 0, 0,700,460, this);
        }
    }
}
