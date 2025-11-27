package com.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import com.Database.Database;

public class HesapOluştur extends JFrame{
    private Database database = new Database();
    public BufferedImage hesapolusturwp;
    private JPanel panel;
    private JButton kayıtOlButton;
    private JButton cikisButton;
    private JTextField kullanıcıadı;
    private JPasswordField parola;
    private JPasswordField parolatekrarı;
    private JLabel kadı;
    private JLabel paro;
    private JLabel parotek;
    public JLabel Mesaj;

    public HesapOluştur(){

        panel = new LoginEkranı.CustomPanel();
        panel.setLayout(null);

        kullanıcıadı = new JTextField();
        parola = new JPasswordField();
        parolatekrarı = new JPasswordField();
        kayıtOlButton = new JButton("Hesap Oluştur");
        cikisButton = new JButton("Çıkış");
        Mesaj = new JLabel();
        kadı = new JLabel("Kullanıcı adı : ");
        paro = new JLabel("Parola : ");
        parotek = new JLabel("Parola Tekrarı : ");

        kadı.setFont(new Font("Arial", Font.BOLD, 14));
        paro.setFont(new Font("Arial", Font.BOLD, 14));
        parotek.setFont(new Font("Arial", Font.BOLD, 14));


        kadı.setBounds(130,130,110,33);
        paro.setBounds(130,180,110,33);
        parotek.setBounds(130,230,110,33);
        kullanıcıadı.setBounds(240,130,300,33);
        parola.setBounds(240,180,300,33);
        parolatekrarı.setBounds(240,230,300,33);
        kayıtOlButton.setBounds(130,280,205,33);
        cikisButton.setBounds(335,280,205,33);
        Mesaj.setBounds(250,330,300,33);

        panel.add(kadı);
        panel.add(paro);
        panel.add(parotek);
        panel.add(kullanıcıadı);
        panel.add(parola);
        panel.add(parolatekrarı);
        panel.add(kayıtOlButton);
        panel.add(cikisButton);
        panel.add(Mesaj);

        add(panel);

        setSize(700,460);
        setResizable(false);

        kayıtOlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isim = kullanıcıadı.getText();
                String password = parola.getText();

                if(kullanıcıadı.getText().isEmpty() || parola.getText().isEmpty() || parolatekrarı.getText().isEmpty()){
                    Mesaj.setForeground(Color.RED);
                    Mesaj.setText("Gerekli yerleri doldurun");
                }
                else {
                    if(parola.getText().equals(parolatekrarı.getText())){
                        if(database.kullanıcıekle(isim,password)){
                            Mesaj.setForeground(Color.green);
                            Mesaj.setText("Kullanıcı kaydı başarılı");
                        }
                        else {
                            Mesaj.setForeground(Color.RED);
                            Mesaj.setText("Bu isimde bir kullanıcı bulunmakta");
                        }
                    }
                    else {
                        Mesaj.setForeground(Color.RED);
                        Mesaj.setText("Parolalar eşleşmiyor");
                    }
                }

            }
        });

        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesaj.setText("");
                setVisible(false);
            }
        });
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(hesapolusturwp, 0, 0,700,460, this);
        }
    }
}
