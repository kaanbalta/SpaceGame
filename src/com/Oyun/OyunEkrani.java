package com.Oyun;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class OyunEkrani extends JFrame {

    public OyunEkrani(){

        setResizable(false);
        setFocusable(false);
        setSize(1000,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Oyun oyun = new Oyun(this);
        try {
            oyun.image = ImageIO.read(new FileImageInputStream(new File("img/uzaygemisi.png")));
            oyun.Arkaplan = ImageIO.read(new FileImageInputStream(new File("img/arkaplan1.png")));
            oyun.canavar = ImageIO.read(new FileImageInputStream(new File("img/canavar.png")));
            oyun.füze = ImageIO.read(new FileImageInputStream(new File("img/füze.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        add(oyun);

    }

}
