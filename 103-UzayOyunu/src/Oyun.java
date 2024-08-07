import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ateş{
    private int x;
    private int y;

    public Ateş(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


public class Oyun extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(5, this);

    private int geçenSüre = 0;
    private int harcananAteş = 0;
    private BufferedImage image;
    private BufferedImage Arkaplan;
    private ArrayList<Ateş> ateşler = new ArrayList<Ateş>();
    private int ateşdirY = 3; //Ateşler hareket ederken y kordinatına eklenecek değer
    private int topX = 0;
    private int topdirX = 2; //top hareket ederken topX e eklenecek değer
    private int uzaygemisiX = -20;
    private int diruzaygemisiX = 20; //Klavyeye basıldığında uzay gemisinin kayması için eklenecek değer


    public boolean kontrolEt(){

        for(int i = 0;i < ateşler.size();i++){

            if(new Rectangle(ateşler.get(i).getX(),ateşler.get(i).getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
                /*
                intersects fonksiyonu içerideki iki tane rectranglenin herhangi biryerinin çarpışıp çarpışmadığını kontrol eder
                biz burada topuda bir rectangle olarak tanımladık ve zaten ateşlerimizde öyleydi ikisini bu şekilde kontrol ediyouz
                 */
                return true;
            }

        }

        return false;

    }

    public Oyun(){

        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png"))); //Resmi okur
            Arkaplan = ImageIO.read(new FileImageInputStream(new File("arkaplan1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        geçenSüre+=5;
        g.setColor(Color.RED);
        g.drawImage(Arkaplan,0,0,800,600,this);
        g.drawImage(image,uzaygemisiX,490,image.getWidth() / 30,image.getHeight() / 30,this);
        g.fillOval(topX,0,20,20);

        for(int i = 0;i < ateşler.size();i++) {

            if (ateşler.get(i).getY() < 0) {
                ateşler.remove(ateşler.get(i));
            }
        }

        g.setColor(Color.BLUE);

        for (int j = 0;j < ateşler.size();j++){
            g.fillRect(ateşler.get(j).getX(),ateşler.get(j).getY(),10,20);
        }

        if(kontrolEt()){
            timer.stop();
            Object [] option = {"OYNA","ÇIK"};
            int seçim = JOptionPane.showOptionDialog(null,"Kazandınız!\n" +
                    "Harcanan ateş : " + harcananAteş +
                    "\nGeçen süre : " + (geçenSüre/1000.0) + "sn\nTekrar oynamak istermisiniz ?","",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
            if(seçim == JOptionPane.YES_OPTION){
                ateşler.clear();
                harcananAteş = 0;
                geçenSüre = 0;
                topX = 0;
                uzaygemisiX = 0;
                timer.start();
            }
            else {
                System.exit(0);
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

        topX += topdirX;

        if(topX >= 780){
            topdirX = -topdirX;
        }
        if(topX < 0){
            topdirX = -topdirX;
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
            ateşler.add(new Ateş(uzaygemisiX + 36,478));
            harcananAteş++;

        }

        else if(c == KeyEvent.VK_A){

            if(uzaygemisiX <=-20){
                uzaygemisiX = -20;
            }
            else {
                uzaygemisiX -=diruzaygemisiX;
            }

        }
        else if(c == KeyEvent.VK_D){

            if(uzaygemisiX >= 735){
                uzaygemisiX = 735;
            }
            else {
                uzaygemisiX += diruzaygemisiX;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
