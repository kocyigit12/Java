package game;

import javax.swing.*;
import java.awt.*;


    public class Main {


        public static void main(String[]args)  {


            GamePlay gamePlay=new GamePlay();
            JFrame object =new JFrame();





            object.setBounds(10, 10,905, 700);
            object.setBackground(Color.DARK_GRAY);
            object.setResizable(false);
            object.setVisible(true);
            object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            object.add(gamePlay) ;







        }
    }


