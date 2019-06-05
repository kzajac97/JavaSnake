
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class GameFrame extends JFrame implements KeyListener
{
    final Point frameLimit[] = { new Point(20, 60), 
                                 new Point(480, 360) };
                                    
    final Dimension size = new Dimension(700, 700);
    private final Timer timer; 
    int startSpeed = 100;
    int speed;
    int scoreValue;    
    JPanel panel;
    GamePanel gamePanel;
    boolean game_on;
    JButton start;
    JButton exit;
    JLabel score;
    JLabel pause;
    JLabel nn;
    JTextField nick;
    HighScore highScore;

    JComboBox speedList;
    JLabel nn1, nn2, nn3, scores;

    public GameFrame()
    {         
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);   

        speed = startSpeed;

        highScore = new HighScore();

        timer = new Timer();   

        panel = new JPanel();
        panel.setOpaque(true); 
        panel.setPreferredSize(new Dimension(640,100));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        panel.setLayout(null);
        
        start = new JButton("Start");
        start.setBounds(50, 5, 80, 30);

        exit = new JButton("Exit");
        exit.setBounds(500, 5, 80, 30);
        exit.setBackground(Color.red);

        score = new JLabel("Press Start");
        score.setBounds(320, 5, 100, 30);

        pause = new JLabel("Press 'P' to pause");
        pause.setBounds(150,5,150,30);

        nn = new JLabel("Nickname:");
        nn.setBounds(400,3,100,15);

        nick = new JTextField(15);
        nick.setBounds(400,18,100,16);
        nick.setText("John Galt");

        scores = new JLabel("High scores:");
        scores.setBounds(50, 40, 100 ,15);

        nn1 = new JLabel(highScore.position.get(0).nickname + " " + highScore.position.get(0).score );
        nn1.setBounds(150, 40, 100 ,15);
        nn2 = new JLabel(highScore.position.get(1).nickname + " " + highScore.position.get(1).score );
        nn2.setBounds(150, 55, 100 ,15);
        nn3 = new JLabel(highScore.position.get(2).nickname + " " + highScore.position.get(2).score );
        nn3.setBounds(150, 70, 100 ,15);


        String[] speedStrings = { "Pyton", "Żmija", "Grzechotnik" };

        speedList = new JComboBox(speedStrings);
        speedList.setBounds(300,50,100,20);


        game_on=false;

        panel.add(start);
        panel.add(exit);
        panel.add(score);      
        panel.add(pause);
        panel.add(nn);
        panel.add(nick);
        panel.add(nn1);
        panel.add(nn2);
        panel.add(nn3);
        panel.add(scores);
        panel.add(speedList);

        panel.setVisible(true);

        speedList.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch (speedList.getSelectedIndex()) {
                    case 0:
                        System.out.println("case 0");
                        setSpeed(100);
                        break;
                    case 1:
                        System.out.println("case 1");
                        setSpeed(200);
                        break;
                    case 2:
                        System.out.println("case 2");
                        setSpeed(300);
                        break;
                }
            }
        });

        start.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
                start();
                start.setVisible(false);
            }
        });
        
        exit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
                { System.exit(0); }
        });
        
       
        
       
        getContentPane().add(panel);       
        setLayout(new FlowLayout());        
        setUndecorated(true);
        setSize(size);
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run() 
            {   
                if(game_on && gamePanel != null)
                {
                    gamePanel.snake.move();
                    checkBorderCollision(gamePanel);
                    checkSnakeCollision(gamePanel);
                    checkFoodCollision(gamePanel);
                    score.setText("Score: " + String.valueOf(scoreValue));                
                    repaint();     
                }                
            }
        }, 0, speed);        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) 
    {    
        int symbol = e.getKeyCode();
            if(gamePanel != null)
            {
                switch(symbol)
                {
                    case KeyEvent.VK_P:
                        pause();
                        break;
                    case KeyEvent.VK_LEFT:
                        if(gamePanel.snake.getDirection()!='R')
                            { gamePanel.setSnakeDirection('L'); }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(gamePanel.snake.getDirection()!='L')
                            { gamePanel.setSnakeDirection('R'); }
                        break;
                    case KeyEvent.VK_UP:
                        if(gamePanel.snake.getDirection()!='D')
                            { gamePanel.setSnakeDirection('U'); }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(gamePanel.snake.getDirection()!='U')
                            { gamePanel.setSnakeDirection('D'); } 
                        break;                
                }

                try
                    { TimeUnit.MILLISECONDS.sleep(startSpeed); }
                catch(Exception ex)
                    { System.err.println("Cannot wait"); }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
      
    void setSpeed(int newSpeed)
        { speed = newSpeed; }
       
    void checkFoodCollision(GamePanel gamepanel)
    {
        Point snakeHead = gamepanel.snake.getHeadPosition();
        if((snakeHead.x == gamepanel.generator.getFoodPosition().x) && (snakeHead.y==gamepanel.generator.getFoodPosition().y))
        {
            gamepanel.snake.grow();
            gamepanel.generator.generateFood();            
            scoreValue++;
            Point tmp_f = gamepanel.generator.food.getPosition();

            for(int point = 0; point < gamepanel.snake.body.size()-1 ; point++)
            {                
                Point tmp_h = gamepanel.snake.body.get(point).getPosition();
                if((tmp_f.x == tmp_h.x)&&(tmp_f.y == tmp_h.y))
                {
                    gamepanel.generator.generateFood();
                    break;
                }
            }
            
            
        }       
    }
    
    void checkBorderCollision(GamePanel gamepanel)
    {
        Point snakeHead = gamepanel.snake.getHeadPosition();
        if(snakeHead.x<frameLimit[0].x || snakeHead.x>frameLimit[1].x 
        || snakeHead.y<frameLimit[0].y || snakeHead.y>frameLimit[1].y)
            { stop(); }     
    }
    
    void checkSnakeCollision(GamePanel gamepanel)
    {        
        Point snakeHead = gamepanel.snake.getHeadPosition();

        for(int i=1;i<gamepanel.snake.body.size();i++)
        {
            Point segmentPosition = gamepanel.snake.body.get(i).getPosition();

            if((snakeHead.x == segmentPosition.x) && (snakeHead.y == segmentPosition.y))
                { stop(); }
        }       
    }   
    
    void start()
    {
        scoreValue = 0;
        gamePanel = new GamePanel();  
        gamePanel.setPreferredSize(new Dimension(520, 400));       
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); 
        super.getContentPane().add(gamePanel);
        game_on = true;
    }
    void pause()
    {
        if(game_on)
        {
            game_on = false;
            pause.setText("Press 'P' to resume");
        }

        else
        {
            game_on = true;
            pause.setText("Press 'P' to pause");
        }
    }
    
    void restart()
    {
        updateHighscore();
        gamePanel.snake.destroySnake();
        gamePanel.snake = new Snake();
        gamePanel.generator.generateFood();
        scoreValue = 0;
        game_on = true;        
    }
    
    void stop()
    {
        game_on = false;
        int decision = JOptionPane.showConfirmDialog(null, "Do You want to play again?", "GAME OVER", JOptionPane.YES_NO_OPTION);

        if(decision == 0)
            { restart(); }

        else if(decision == 1)
            { System.exit(0);}
    }

    void updateHighscore()
    {
        highScore.addToHighScore(nick.getText(),scoreValue);

        nn1.setText(highScore.position.get(0).nickname + " " + highScore.position.get(0).score );

        nn2.setText(highScore.position.get(1).nickname + " " + highScore.position.get(1).score );

        nn3.setText(highScore.position.get(2).nickname + " " + highScore.position.get(2).score );

    }
}
