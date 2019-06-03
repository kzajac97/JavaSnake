package snakeapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.TimerTask;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;


class MainWindow extends JFrame 
{
    private JTable tablePeople;
    private JTextField textFieldSurname;
    private JTextField textFieldAge;
    private JPanel panelMain;
    private JButton buttonAddNewPerson;
    private JButton buttonTeleport;
    private JTextField textFieldName;
    private JButton buttonAnimated;

    int a,b, next_x, next_y;
    float af;
    boolean way;

    MainWindow() 
    {
        this.setSize(600, 500);
        this.setTitle("Platformy programistyczne .Net i Java, labolatorium, Java, lab01");
        this.setContentPane(panelMain);

        Timer animationTimer = new Timer(1000/30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               //if(way) buttonTeleport.setLocation(buttonTeleport.getX()+1,a*buttonTeleport.getY()+b);
               // else
                if(buttonAnimated.getX() != next_x) {
                    if (way)
                        buttonAnimated.setLocation(buttonAnimated.getX() + 1, Math.round(af * buttonAnimated.getX()) + b);
                    else
                        buttonAnimated.setLocation(buttonAnimated.getX() - 1, Math.round(af * buttonAnimated.getX()) + b);
                }
                else ((Timer)e.getSource()).stop();
            }
        });

        buttonAddNewPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{

                    Person person = new Person();
                    person.setName(textFieldName.getText());
                    person.setSurname(textFieldSurname.getText());
                    person.setAge(Integer.parseInt(textFieldAge.getText()));
                    addPersonToList(person);
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelMain, "Wrong age passed.");
                }
            }
        });
        buttonTeleport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                buttonTeleport.setLocation(r.nextInt(panelMain.getSize().width),r.nextInt(panelMain.getSize().height));
            }
        });
        buttonAnimated.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                next_x = r.nextInt(panelMain.getSize().width);
                next_y = r.nextInt(panelMain.getSize().height);
                int old_x = buttonAnimated.getX();
                int old_y = buttonAnimated.getY();

                countLineFactors(old_x, old_y, next_x, next_y);
                if(old_x < next_x) way = true;
                else way = false;

                animationTimer.start();

            }
        });


    }

    private void addPersonToList(Person person){
        DefaultTableModel tableModel = (DefaultTableModel)tablePeople.getModel();
        tableModel.addRow(new Object[] {
                person.getName(),
                person.getSurname(),
                person.getAge()
        });
    }

    private void countLineFactors(int x1, int y1, int x2, int y2){
        b = (x2*y1-y2*x1)/(x2-x1);
        a = (y2 - b)/x2;
        af = ((float)y2 - (float)b)/(float)x2;
    }

    private void createUIComponents() {
        Object[] columnNames = {
                "Name",
                "Surname",
                "Age"
        };
        tablePeople = new JTable(new DefaultTableModel(columnNames, 0));
    }

}
