package game;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.security.SecureRandom;
import java.util.*;




public class GamePlay extends JPanel  implements KeyListener, ActionListener  {


    private String highScore= "";
    boolean isOver=false;

    File file = new File("scores.txt");
    private int bonus=0;

    private int[] snakeXLength=new int[750];
    private int[] snakeYLength=new int[750];

    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private int lengthOfSnake=3;

    private Timer timer;
    private int delay=100;


    private ImageIcon snakeImage;

    private int[] enemyXPosition={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyYPosition={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private int [] bombXPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] bombYPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private int [] bonusXPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] bonusYPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private ImageIcon enemyImage;
    private ImageIcon bombImage;
    private ImageIcon bonusImage;



    private SecureRandom random= new SecureRandom();

    private int xPosition= random.nextInt(34);
    private int yPosition=random.nextInt(23);

    private int bonusXPosition= random.nextInt(34);
    private int bonusYPosition=random.nextInt(23);



    private int xBomb    = random.nextInt(34);
    private int yBomb = random.nextInt(23);


    private int score;

    public void setScore(int score) {
        this.score = score;
    }



    public int getScore() {
        return score;
    }

    private int moves=0;


    private ImageIcon titleImage;





    public void paint(Graphics g){
        T.start();
        if (highScore.equals(""))
        {
            highScore=this.takeHighScore();
        }

        if(moves==0){
            snakeXLength[2]=50;
            snakeXLength[1]=75;
            snakeXLength[0]=100;

            snakeYLength[2]=100;
            snakeYLength[1]=100;
            snakeYLength[0]=100;
        }

        // DRAW TITLE IMAGE BORDER

        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);

        //draw title image
        titleImage=new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        // draw border for gamePlay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        // draw background for gamePlay
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score: "+score,780,30);
        g.drawString("HighScore :"+highScore,600,30);

        // draw calendar

        Calendar now= Calendar.getInstance();
        Date t= now.getTime();
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Time: "+t, 50, 30);

        // draw length of snake

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length: "+lengthOfSnake,780,50);


        rightMouth=new ImageIcon("rightMouth.png");
        rightMouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);

        for(int a =0;a< lengthOfSnake;a++){
            if(a==0 && right){
                rightMouth=new ImageIcon("rightMouth.png");
                rightMouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            } if(a==0 && left){
                leftMouth=new ImageIcon("leftMouth.png");
                leftMouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            } if(a==0 && up){
                upMouth=new ImageIcon("upMouth.png");
                upMouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            } if(a==0 && down){
                downMouth=new ImageIcon("downmouth.png");
                downMouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }if(a!=0){
                snakeImage=new ImageIcon("snakeImage.png");
                snakeImage.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }
        }


        bombImage = new ImageIcon("enemy.png");
        bombImage.paintIcon(this,g,bombXPos[xBomb],bombYPos[yBomb]);

        if (bombXPos[xBomb]==snakeXLength[0] && bombYPos[yBomb]==snakeYLength[0]){



            g.setColor(Color.BLUE);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("WRONG ONE",320,250);



        }
        bonusImage=new ImageIcon("bonus.png");





        enemyImage= new ImageIcon("apple.png");



        if(enemyXPosition[xPosition]==snakeXLength[0] && enemyYPosition[yPosition]==snakeYLength[0]){

            score++;

            if(score%10==0 && delay>50){



                delay-=10;
                timer.setDelay(delay);



            }
            if(score%15==0 ){
                bonus+=2;
            }

            lengthOfSnake++;


            xPosition=random.nextInt(34);
            yPosition=random.nextInt(23);
        }
        if(bonus>0 && score%15==0) {

            bonusImage.paintIcon(this, g, bonusXPos[bonusXPosition], bonusYPos[bonusYPosition]);


        }



        if(bonusXPos[bonusXPosition]==snakeXLength[0] && bonusYPos[bonusYPosition]==snakeYLength[0]){
            score+=2;
            lengthOfSnake+=2;

            if(bonus>0 && score%15==0) {

                bonusXPosition = random.nextInt(34);
                bonusYPosition = random.nextInt(23);




            }  bonusImage.paintIcon(this, g, bonusXPos[bonusXPosition], bonusYPos[bonusYPosition]);

        }


        enemyImage.paintIcon(this,g,enemyXPosition[xPosition],enemyYPosition[yPosition]);



        for(int b=1; b<lengthOfSnake;b++){

            if(snakeXLength[b]==snakeXLength[0] && snakeYLength[b]==snakeYLength[0]||(bombXPos[xBomb]==snakeXLength[0] && bombYPos[yBomb]==snakeYLength[0])){

                T.stop();


                double seconds= T.getElapsedSeconds();
                right=false;
                left=false;
                up=false;
                down=false;

                isOver=true;

                setScore(score);



                g.setColor(Color.CYAN);
                g.setFont(new Font("arial",Font.BOLD,15));
                g.drawString("Total Time: "+seconds+" seconds",370,365);


                g.setColor(Color.RED);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("GAME OVER",300,300);

                g.setColor(Color.GREEN);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Space to Restart",350,340);

                checkScore();



                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));


                    writer.append("score: "+getScore());
                    writer.newLine();
                    writer.append("estimated time: "+seconds);
                    writer.newLine();


                    writer.flush();
                    writer.close();


                } catch (IOException e) {

                }


            }}

        g.dispose();
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        timer.start();


        if(right){

            for(int r=lengthOfSnake-1;r>=0;r--){
                snakeYLength[r+1]=snakeYLength[r];
            }
            for(int r=lengthOfSnake;r>=0;r--){

                if(r==0){
                    snakeXLength[r]=snakeXLength[r]+25;
                }
                else{
                    snakeXLength[r]=snakeXLength[r-1];
                }
                if(snakeXLength[r]>850){
                    snakeXLength[r]=25;
                }

            } repaint();

        }
        if(left){
            for(int r=lengthOfSnake-1;r>=0;r--){
                snakeYLength[r+1]=snakeYLength[r];
            }
            for(int r=lengthOfSnake;r>=0;r--){

                if(r==0){
                    snakeXLength[r]=snakeXLength[r]-25;
                }
                else{
                    snakeXLength[r]=snakeXLength[r-1];
                }
                if(snakeXLength[r]<25){
                    snakeXLength[r]=850;
                }

            } repaint();

        }
        if(up){
            for(int r=lengthOfSnake-1;r>=0;r--){
                snakeXLength[r+1]=snakeXLength[r];
            }
            for(int r=lengthOfSnake;r>=0;r--){

                if(r==0){
                    snakeYLength[r]=snakeYLength[r]-25;
                }
                else{
                    snakeYLength[r]=snakeYLength[r-1];
                }
                if(snakeYLength[r]<75){
                    snakeYLength[r]=625;
                }

            } repaint();

        }
        if(down){
            for(int r=lengthOfSnake-1;r>=0;r--){
                snakeXLength[r+1]=snakeXLength[r];
            }
            for(int r=lengthOfSnake;r>=0;r--){

                if(r==0){
                    snakeYLength[r]=snakeYLength[r]+25;
                }
                else{
                    snakeYLength[r]=snakeYLength[r-1];
                }
                if(snakeYLength[r]>625){
                    snakeYLength[r]=75;
                }

            } repaint();

        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode()==KeyEvent.VK_SPACE){

            for(int b=1; b<lengthOfSnake;b++){

                if((snakeXLength[b]==snakeXLength[0] && snakeYLength[b]==snakeYLength[0] )|| (bombXPos[xBomb]==snakeXLength[0] && bombYPos[yBomb]==snakeYLength[0])){

                    right=false;
                    left=false;
                    up=false;
                    down=false;

                    moves=0;
                    score=0;
                    lengthOfSnake=3;
                    bonus=0;
                    isOver=false;

                    repaint();
                    T.start();

                }else{
                    right=false;
                    left=false;
                    up=false;
                    down=false;
                }
            }}

        if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT && ! isOver){
            moves++;
            right=true;
            if(!left){
                right=true;
            }else{
                right=false;
                left=true;
            }

            up=false;
            down=false;
        }
        if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT && ! isOver){
            moves++;
            left=true;
            if(!right){
                left=true;
            }else{
                left=false;
                right=true;
            }

            up=false;
            down=false;
        }
        if(keyEvent.getKeyCode()==KeyEvent.VK_UP && ! isOver){
            moves++;
            up=true;
            if(!down){
                up=true;
            }else{
                up=false;
                down=true;
            }

            right=false;
            left=false;
        }
        if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN && ! isOver){
            moves++;
            down=true;
            if(!up){
                down=true;
            }else{
                down=false;
                up=true;
            }

            left=false;
            right=false;
        }
        if(keyEvent.getKeyCode()== keyEvent.VK_X){
            right=false;
            left=false;
            up=false;
            down=false;


            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                long leng=0;
                String line;
                while((line =reader.readLine())!=null){

                    if(line.isEmpty()){
                        break;
                    }
                    System.out.println(line);


                    leng+=line.length();}




            } catch (FileNotFoundException e) {
                System.out.println("No previous information ");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public GamePlay(){
        int d=30000;
        ActionListener taskPerformer= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                xBomb   = random.nextInt(34);
                yBomb = random.nextInt(23); }};
        new Timer(d,taskPerformer).start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void checkScore(){

        if(highScore.equals("")){
            return ;}

        if (score>Integer.parseInt(highScore.split(":")[1]))
        {

            Scanner sc = new Scanner(System.in);
            System.out.println("write your name");
            String name =sc.next();
            highScore= name+ ":"+ score;


            File scoreFile = new File("sc.txt");
            if (! scoreFile.exists())
            {
                try {
                    scoreFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            FileWriter writeFile= null;
            BufferedWriter writer= null;
            try {
                writeFile= new FileWriter(scoreFile);
                writer= new BufferedWriter(writeFile);
                writer.write(this.highScore);

            }catch (Exception e){

            }
            finally {
                try {
                    if (writer!=null)
                        writer.close();
                }catch (Exception e){

                }
            }

        }
    }

    public String takeHighScore() {

        FileReader readFile=null;
        BufferedReader reader=null;
        try {
            readFile = new FileReader("sc.txt");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e){
            return "0";
        }
        finally {
            try {
                if (reader!=null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }

}


