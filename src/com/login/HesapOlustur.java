package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import com.database.Database;

public class HesapOlustur extends JFrame{
    private Database database = new Database();
    public BufferedImage hesapOlusturWp;
    private JPanel panel;
    private JButton kayitOlButton;
    private JButton cikisButton;
    private JTextField kullaniciAdi;
    private JPasswordField parola;
    private JPasswordField parolaTekrari;
    private JLabel kullaniciAdiLabel;
    private JLabel parolaLabel;
    private JLabel parolaTekrariLabel;
    public JLabel mesaj;

    public HesapOlustur(){

        panel = new LoginEkrani.CustomPanel();
        panel.setLayout(null);

        kullaniciAdi = new JTextField();
        parola = new JPasswordField();
        parolaTekrari = new JPasswordField();
        kayitOlButton = new JButton("Hesap Oluştur");
        cikisButton = new JButton("Çıkış");
        mesaj = new JLabel();
        kullaniciAdiLabel = new JLabel("Kullanıcı adı : ");
        parolaLabel = new JLabel("Parola : ");
        parolaTekrariLabel = new JLabel("Parola Tekrarı : ");

        kullaniciAdiLabel.setFont(new Font("Arial", Font.BOLD, 14));
        parolaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        parolaTekrariLabel.setFont(new Font("Arial", Font.BOLD, 14));


        kullaniciAdiLabel.setBounds(130,130,110,33);
        parolaLabel.setBounds(130,180,110,33);
        parolaTekrariLabel.setBounds(130,230,110,33);
        kullaniciAdi.setBounds(240,130,300,33);
        parola.setBounds(240,180,300,33);
        parolaTekrari.setBounds(240,230,300,33);
        kayitOlButton.setBounds(130,280,205,33);
        cikisButton.setBounds(335,280,205,33);
        mesaj.setBounds(250,330,300,33);

        panel.add(kullaniciAdiLabel);
        panel.add(parolaLabel);
        panel.add(parolaTekrariLabel);
        panel.add(kullaniciAdi);
        panel.add(parola);
        panel.add(parolaTekrari);
        panel.add(kayitOlButton);
        panel.add(cikisButton);
        panel.add(mesaj);

        add(panel);

        setSize(700,460);
        setResizable(false);

        kayitOlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isim = kullaniciAdi.getText();
                String password = parola.getText();

                if(kullaniciAdi.getText().isEmpty() || parola.getText().isEmpty() || parolaTekrari.getText().isEmpty()){
                    mesaj.setForeground(Color.RED);
                    mesaj.setText("Gerekli yerleri doldurun");
                }
                else {
                    if(parola.getText().equals(parolaTekrari.getText())){
                        if(database.kullaniciEkle(isim,password)){
                            mesaj.setForeground(Color.green);
                            mesaj.setText("Kullanıcı kaydı başarılı");
                        }
                        else {
                            mesaj.setForeground(Color.RED);
                            mesaj.setText("Bu isimde bir kullanıcı bulunmakta");
                        }
                    }
                    else {
                        mesaj.setForeground(Color.RED);
                        mesaj.setText("Parolalar eşleşmiyor");
                    }
                }

            }
        });

        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.setText("");
                setVisible(false);
            }
        });
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(hesapOlusturWp, 0, 0,700,460, this);
        }
    }
}
