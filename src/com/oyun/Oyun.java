package com.oyun;

import com.anaekran.AnaEkran;
import com.database.Database;
import com.login.LoginEkrani;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Oyun extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(7,this);

    private Database database = new Database();

    public static String kullaniciAdi = "";
    private int gecenSure = 0;
    private int harcananAtes = 0;
    private int yokOlanMeteor = 0;
    private int vurulanMeteor = 0;
    private double puan = 0;
    private double level = 0;
    public BufferedImage image;
    public BufferedImage arkaPlan;
    public BufferedImage canavar;
    public BufferedImage fuze;
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    private ArrayList<Meteor> meteorlar = new ArrayList<Meteor>();
    private int atesDirY = 4; //Ateşler hareket ederken y kordinatına eklenecek değer
    private int uzayGemisiX = 0;
    private int uzaygemisiDirX = 7; //Klavyeye basıldığında uzay gemisinin kayması için eklenecek değer
    private JFrame frame;


    public boolean kontrolEt(){

        if(yokOlanMeteor >= 10){
            if(vurulanMeteor==0 || harcananAtes==0){
                puan=0;
            }
            else {
                puan = (vurulanMeteor * 12) + ((double) gecenSure / 1000) - (harcananAtes*2);
                level = puan;

                if(puan<0){
                    puan = 0;
                }
            }
            database.puanGuncelle(kullaniciAdi,(int)puan);
            return true;
        }

        for(int i = 0;i < atesler.size();i++){

            for(int j=0; j < meteorlar.size();j++){

                Rectangle ateşRect = new Rectangle(atesler.get(i).getX(), atesler.get(i).getY(), fuze.getWidth() / 40, fuze.getHeight() / 50);
                Rectangle meteorRect = new Rectangle(meteorlar.get(j).getX(), meteorlar.get(j).getY(), canavar.getWidth() / 21, canavar.getHeight() / 21);

                if(ateşRect.intersects(meteorRect)){
                    meteorlar.remove(j);
                    atesler.remove(i);
                    vurulanMeteor++;
                    break;

                }

            }


        }

        for(int i = 0;i < meteorlar.size();i++){

            Rectangle meteorRect = new Rectangle(meteorlar.get(i).getX(), meteorlar.get(i).getY(), canavar.getWidth() / 21, canavar.getHeight() / 21);
            Rectangle gemiRect = new Rectangle(uzayGemisiX,640,image.getWidth() / 25,image.getHeight() / 25);


            if(meteorRect.intersects(gemiRect)){
                if(vurulanMeteor==0 || harcananAtes==0){
                    puan=0;
                }
                else {
                    puan = (vurulanMeteor * 12) + ((double) gecenSure / 1000) - (harcananAtes*2);
                    level = puan;
                    if(puan<0){
                        puan = 0;
                    }
                }
                database.puanGuncelle(kullaniciAdi,(int)puan);
                return true;

            }
        }

        return false;
    }

    public Oyun(JFrame frame){

        this.frame = frame;
        timer.start();
        timer1.start();

    }


    @Override
    public void paint(Graphics g) {
        gecenSure+=5;
        g.setColor(Color.RED);
        g.drawImage(arkaPlan,0,0,1000,750,this);
        g.drawImage(image,uzayGemisiX,640,image.getWidth() / 25,image.getHeight() / 25,this);

        for(int i = 0;i < atesler.size();i++) {
            if (atesler.get(i).getY() < 0) {
                atesler.remove(atesler.get(i));
            }
        }

        for (int j = 0;j < atesler.size();j++){
            g.drawImage(fuze,atesler.get(j).getX(),atesler.get(j).getY(),fuze.getWidth() / 40, fuze.getHeight() / 50, this);
        }

        for(int i = 0;i < meteorlar.size();i++) {
            g.drawImage(canavar,meteorlar.get(i).getX(),meteorlar.get(i).getY(),canavar.getWidth() / 21,canavar.getHeight() / 21,this);
        }

        if(kontrolEt()){
            timer.stop();
            timer1.stop();
            soltimer.stop();
            sağtimer.stop();
            Object [] option = {"OYNA","ÇIK"};
            int seçim = JOptionPane.showOptionDialog(null,"Puanınız : " + (int)puan +  "\nTekrar oynamak istermisiniz ?","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
            if(seçim == JOptionPane.YES_OPTION){
                uzayGemisiX = 0;
                atesler.clear();
                meteorlar.clear();
                yokOlanMeteor = 0;
                vurulanMeteor = 0;
                harcananAtes = 0;
                gecenSure = 0;
                puan = 0;
                timer.start();
                timer1.start();
            }
            else if(seçim == JOptionPane.NO_OPTION){
                AnaEkran anaEkran = new AnaEkran(LoginEkrani.kullaniciAdi);
                frame.dispose();
                anaEkran.setVisible(true);

            }

        }

    }

    @Override
    public void repaint() {
        super.repaint();
        //Tekrardan paint methodunun çağırılmasını sağlar
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0;i < atesler.size();i++){
            atesler.get(i).setY(atesler.get(i).getY() - atesDirY);
        }

        for(int i = 0;i < meteorlar.size();i++){
            meteorlar.get(i).setY(meteorlar.get(i).getY() + 2);

            if(meteorlar.get(i).getY() > 750){
                meteorlar.remove(i);
                yokOlanMeteor++;
                repaint();
            }
        }

        repaint(); //topun konumu her güncellendiğinde repaint yapmış oluruz
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if(c == KeyEvent.VK_SHIFT){
            atesler.add(new Ates(uzayGemisiX + 12,570));
            harcananAtes++;

        }

        else if(c == KeyEvent.VK_A){

            if (!soltimer.isRunning()) {
                soltimer.start();
            }

        }
        else if(c == KeyEvent.VK_D){
            if (!sağtimer.isRunning()) {
                sağtimer.start();
            }

        }
        else if(c == KeyEvent.VK_Q){

                timer.stop();
                timer1.stop();
                soltimer.stop();
                sağtimer.stop();
                Object [] option = {"Devam Et","Çık"};
                int seçim1 = JOptionPane.showOptionDialog(null,"Çıkmak istediğinize emin misiniz ? ","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                if(seçim1 == JOptionPane.YES_OPTION){
                    timer.start();
                    timer1.start();
                }
                else {
                    AnaEkran anaEkran = new AnaEkran(LoginEkrani.kullaniciAdi);
                    frame.dispose();
                    anaEkran.setVisible(true);
                }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_A) {
            // Sol hareket durdur
            soltimer.stop();
        } else if (c == KeyEvent.VK_D) {
            // Sağ hareket durdur
            sağtimer.stop();
        }
    }

    Timer timer1 = new Timer(750,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random random = new Random();
            int meteorx = random.nextInt(966);

            meteorlar.add(new Meteor(meteorx,0));
        }
    });

    Timer soltimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (uzayGemisiX > 0) {
                uzayGemisiX -= uzaygemisiDirX;
            }
        }
    });

    Timer  sağtimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (uzayGemisiX < 955) {
                uzayGemisiX += uzaygemisiDirX;
            }
        }
    });



}