package com.Oyun;

import com.AnaEkran.AnaEkran;
import com.Database.Database;
import com.Login.LoginEkranı;

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

    public static String kullanıcıadı = "";
    private int geçenSüre = 0;
    private int harcananAteş = 0;
    private int yokolanmeteor = 0;
    private int vurulanmeteor = 0;
    private double puan = 0;
    private double level = 0;
    public BufferedImage image;
    public BufferedImage Arkaplan;
    public BufferedImage canavar;
    public BufferedImage füze;
    private ArrayList<Ates> ateşler = new ArrayList<Ates>();
    private ArrayList<Meteor> meteorlar = new ArrayList<Meteor>();
    private int ateşdirY = 4; //Ateşler hareket ederken y kordinatına eklenecek değer
    private int uzaygemisiX = 0;
    private int diruzaygemisiX = 7; //Klavyeye basıldığında uzay gemisinin kayması için eklenecek değer
    private JFrame frame;


    public boolean kontrolEt(){

        if(yokolanmeteor >= 10){
            if(vurulanmeteor==0 || harcananAteş==0){
                puan=0;
            }
            else {
                puan = (vurulanmeteor * 12) + ((double) geçenSüre / 1000) - (harcananAteş*2);
                level = puan;

                if(puan<0){
                    puan = 0;
                }
            }
            database.puangüncelle(kullanıcıadı,(int)puan);
            return true;
        }

        for(int i = 0;i < ateşler.size();i++){

            for(int j=0; j < meteorlar.size();j++){

                Rectangle ateşRect = new Rectangle(ateşler.get(i).getX(), ateşler.get(i).getY(), füze.getWidth() / 40, füze.getHeight() / 50);
                Rectangle meteorRect = new Rectangle(meteorlar.get(j).getX(), meteorlar.get(j).getY(), canavar.getWidth() / 21, canavar.getHeight() / 21);

                if(ateşRect.intersects(meteorRect)){
                    meteorlar.remove(j);
                    ateşler.remove(i);
                    vurulanmeteor++;
                    break;

                }

            }


        }

        for(int i = 0;i < meteorlar.size();i++){

            Rectangle meteorRect = new Rectangle(meteorlar.get(i).getX(), meteorlar.get(i).getY(), canavar.getWidth() / 21, canavar.getHeight() / 21);
            Rectangle gemiRect = new Rectangle(uzaygemisiX,640,image.getWidth() / 25,image.getHeight() / 25);


            if(meteorRect.intersects(gemiRect)){
                if(vurulanmeteor==0 || harcananAteş==0){
                    puan=0;
                }
                else {
                    puan = (vurulanmeteor * 12) + ((double) geçenSüre / 1000) - (harcananAteş*2);
                    level = puan;
                    if(puan<0){
                        puan = 0;
                    }
                }
                database.puangüncelle(kullanıcıadı,(int)puan);
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
        geçenSüre+=5;
        g.setColor(Color.RED);
        g.drawImage(Arkaplan,0,0,1000,750,this);
        g.drawImage(image,uzaygemisiX,640,image.getWidth() / 25,image.getHeight() / 25,this);

        for(int i = 0;i < ateşler.size();i++) {
            if (ateşler.get(i).getY() < 0) {
                ateşler.remove(ateşler.get(i));
            }
        }

        for (int j = 0;j < ateşler.size();j++){
            g.drawImage(füze,ateşler.get(j).getX(),ateşler.get(j).getY(),füze.getWidth() / 40, füze.getHeight() / 50, this);
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
                uzaygemisiX = 0;
                ateşler.clear();
                meteorlar.clear();
                yokolanmeteor = 0;
                vurulanmeteor = 0;
                harcananAteş = 0;
                geçenSüre = 0;
                puan = 0;
                timer.start();
                timer1.start();
            }
            else if(seçim == JOptionPane.NO_OPTION){
                AnaEkran anaEkran = new AnaEkran(LoginEkranı.Kullanıcıadı);
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
        for (int i = 0;i < ateşler.size();i++){
            ateşler.get(i).setY(ateşler.get(i).getY() - ateşdirY);
        }

        for(int i = 0;i < meteorlar.size();i++){
            meteorlar.get(i).setY(meteorlar.get(i).getY() + 2);

            if(meteorlar.get(i).getY() > 750){
                meteorlar.remove(i);
                yokolanmeteor++;
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
            ateşler.add(new Ates(uzaygemisiX + 12,570));
            harcananAteş++;

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
                    AnaEkran anaEkran = new AnaEkran(LoginEkranı.Kullanıcıadı);
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
            if (uzaygemisiX > 0) {
                uzaygemisiX -= diruzaygemisiX;
            }
        }
    });

    Timer  sağtimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (uzaygemisiX < 955) {
                uzaygemisiX += diruzaygemisiX;
            }
        }
    });



}