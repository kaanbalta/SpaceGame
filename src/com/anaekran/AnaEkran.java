package com.anaekran;

import com.login.LoginEkrani;
import com.oyun.Oyun;
import com.oyun.OyunEkrani;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnaEkran extends JFrame {
    private JPanel panel;
    private JButton oynaButton;
    private JButton ayarlarButton;
    private JButton recordsButton;
    private JButton magazaButton;
    private JButton cikisButton;
    private JButton oturumuKapatButton;
    public JLabel label;
    public String kullaniciAdi;
    public BufferedImage arkaPlan;

    public AnaEkran(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;

        try {
            arkaPlan = ImageIO.read(new File("img/anaekranpp.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        panel = new CustomPanel(); // Özel JPanel kullanarak arka plan resmi ekliyoruz
        panel.setLayout(null); // Null layout kullanarak bileşenlerin konumunu manuel ayarlayacağız


        oynaButton = new JButton("Oyna");
        ayarlarButton = new JButton("Ayarlar");
        recordsButton = new JButton("Records");
        magazaButton = new JButton("Mağaza");
        cikisButton = new JButton("Çıkış");
        oturumuKapatButton = new JButton("Oturumu Kapat");
        label = new JLabel("Kullanıcı :  " + kullaniciAdi);

        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        label.setBounds(550, 40, 250, 33);
        oynaButton.setBounds(40, 130, 300, 33);
        ayarlarButton.setBounds(40, 180, 300, 33);
        recordsButton.setBounds(40, 230, 300, 33);
        magazaButton.setBounds(40, 280, 300, 33);
        oturumuKapatButton.setBounds(40,330, 300, 33);
        cikisButton.setBounds(40, 380, 300, 33);

        panel.add(label);
        panel.add(oynaButton);
        panel.add(ayarlarButton);
        panel.add(recordsButton);
        panel.add(magazaButton);
        panel.add(cikisButton);
        panel.add(oturumuKapatButton);

        add(panel);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        oynaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OyunEkrani oyunEkrani = new OyunEkrani();
                Oyun.kullaniciAdi = kullaniciAdi;
                setVisible(false);
                oyunEkrani.setVisible(true);
            }
        });
        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ayarlarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ayarlar ayarlar = new Ayarlar();
                ayarlar.setVisible(true);
            }
        });
        recordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Records records = new Records();
                records.setVisible(true);
            }
        });
        oturumuKapatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginEkrani loginEkranı = new LoginEkrani();
                setVisible(false);
                loginEkranı.setVisible(true);
            }
        });
    }

    // Özel JPanel sınıfı, arka plan resmini çizer
    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(arkaPlan, 0, 0,800,500, this);
        }
    }

}