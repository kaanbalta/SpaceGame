package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import com.anaekran.AnaEkran;
import com.database.Database;

public class LoginEkrani extends JFrame {

    private Database database = new Database();
    public static BufferedImage loginImg;
    private JButton oturumAcButton;
    private JButton hesapOlusturButton;
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanel panel;
    private JButton cikisButton;
    private JLabel mesaj;
    private JLabel kullaniciAdiLabel;
    private JLabel sifreLabel;
    private HesapOlustur hesapOlustur = new HesapOlustur();
    public static String kullaniciAdi;

    public String getTextField() {
        return textField.getText();
    }

    public LoginEkrani(){

        panel = new CustomPanel();
        panel.setLayout(null);

        oturumAcButton = new JButton("Oturum Aç");
        hesapOlusturButton = new JButton("Hesap Oluştur");
        textField = new JTextField();
        passwordField = new JPasswordField();
        cikisButton = new JButton("Çıkış");
        mesaj = new JLabel();
        kullaniciAdiLabel = new JLabel("Kullanıcı adı : ");
        sifreLabel = new JLabel("Parola : ");

        kullaniciAdiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sifreLabel.setFont(new Font("Arial", Font.BOLD, 14));

        kullaniciAdiLabel.setBounds(130,130,100,33);
        sifreLabel.setBounds(130,180,100,33);
        textField.setBounds(230,130,300,33);
        passwordField.setBounds(230,180,300,33);
        oturumAcButton.setBounds(130,230,200,33);
        hesapOlusturButton.setBounds(330,230,200,33);
        cikisButton.setBounds(130,270,400,33);
        mesaj.setBounds(230,300,300,33);

        panel.add(kullaniciAdiLabel);
        panel.add(sifreLabel);
        panel.add(textField);
        panel.add(passwordField);
        panel.add(oturumAcButton);
        panel.add(hesapOlusturButton);
        panel.add(cikisButton);
        panel.add(mesaj);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,460);
        setLocationRelativeTo(null);
        setResizable(false);

        oturumAcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.setText("");
                String girilenKullaniciAdi = textField.getText();
                String girilenSifre = new String(passwordField.getPassword());

                LinkedHashMap<String, String> kullanicilar = database.kullaniciGetir();

                if (kullanicilar.containsKey(girilenKullaniciAdi)) {

                    String kayitliSifre = kullanicilar.get(girilenKullaniciAdi);

                    if (kayitliSifre.equals(girilenSifre)) {
                        kullaniciAdi = girilenKullaniciAdi;
                        AnaEkran anaEkran = new AnaEkran(kullaniciAdi);
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

        hesapOlusturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hesapOlustur.hesapOlusturWp = loginImg;
                hesapOlustur.setVisible(true);
            }
        });


        cikisButton.addActionListener(new ActionListener() {
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
            g.drawImage(loginImg, 0, 0,700,460, this);
        }
    }
}
