import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

/**
 *
 * @author mglor
 */
public class Calculator extends JFrame implements ActionListener {


    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Calculator();
                
            }
        });
         //TODO code application logic here
    }
    

    JPanel[] row = new JPanel[5];
    JButton[] button = new JButton[19];
    String[] buttonString = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "*",
                             ".", "/", "OFF","RESET",
                             "Del", "=", "0"};
    int[] dimW = {300,45,100,90};
    int[] dimH = {35, 40};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    Dimension zeroButDimension = new Dimension(dimW[1], dimH[1]);
    Dimension dotButDimension = new Dimension(dimW[1], dimH[1]);
    Dimension divideButDimension = new Dimension(dimW[1], dimH[1]);
    Dimension equalsButDimension = new Dimension(dimW[2], dimH[1]);
    boolean[] function = new boolean[19];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(1,20);
    Font font = new Font("Times new Roman", Font.BOLD, 12);
    
    Calculator(){
        super("Calculator");
        setDesign();
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout (5,5);
        setLayout(grid);
         setVisible(true);
         show(true);
         
         
        
        for(int i = 0; i < 19; i++)
            function[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
        for(int i = 0; i < 5; i++)
            row[i] = new JPanel();
        row[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
            row[i].setLayout(f2);
        for(int i = 0; i < 19; i++){
        
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);}
        display.setFont(font);
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        for(int i = 0; i < 14; i++)
            button[i].setPreferredSize(regularDimension);
        for(int i = 14; i < 18; i++)
            button[i].setPreferredSize(rColumnDimension);
        button[18].setPreferredSize(zeroButDimension);
        button[12].setPreferredSize(dotButDimension);
        button[13].setPreferredSize(divideButDimension);
        button[17].setPreferredSize(equalsButDimension);
        row[0].add(display);
        add(row[0]);
        
        for(int i = 0; i < 4; i++)
            row[1].add(button[i]);
        row[1].add(button[14]);
        add(row[1]);
        for(int i = 4; i < 8; i++)
            row[2].add(button[i]);
        row[2].add(button[15]);
        add(row[2]);
        
        for(int i = 8; i < 12; i++)
            row[3].add(button[i]);
        row[3].add(button[16]);
        add(row[3]);
        
        row[4].add(button[18]);
        for(int i = 12; i < 14; i++)
            row[4].add(button[i]);
        row[4].add(button[17]);
        add(row[4]);
        
       
        
    }
     
     
public void clear() {
    try{
        display.setText("");
        for (int i = 0; i < 4; i++)
            function[i] = false;
        for(int i = 0; i < 2; i++)
            temporary[i] = 0;
    }catch(NullPointerException e) {
    }
}


public String del (String k){
    int n=k.length();
    String newstring="";
    if(n!=0)
    {
        newstring=k.substring(0, (n-1));
    }
    return newstring;
}
    
public void getResult() {
    double result = 0;
    temporary[1] = Double.parseDouble(display.getText());
    String temp0 = Double.toString(temporary[0]);
    String temp1 = Double.toString(temporary[1]);
    try {
        if(temp0.contains("-")) {
            String[] temp00 = temp0.split("-", 2);
            temporary[0] = (Double.parseDouble(temp00[1]) * -1);
        }
        if(temp1.contains("-")) {
            String[] temp11 = temp1.split("-", 2);
            temporary[1] = (Double.parseDouble(temp11[1]) * -1);
        }
    }
    catch(ArrayIndexOutOfBoundsException e) {
    }
    try {
        if(function[2] == true)
            result = temporary[0] * temporary[1];
        else if(function[3] == true)
            result = temporary[0] / temporary[1];
        else if(function[0] == true)
            result = temporary[0] + temporary[1];
        else if(function[1] == true)
            result = temporary[0] - temporary[1];
        display.setText(Double.toString(result));
        for(int i = 0; i < 4; i++)
            function[i] = false;
    }
    catch(NumberFormatException e) {
    }
}
public final void setDesign(){
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {
        }
    }

    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource() == button[0])
           display.append("7");
       if(ae.getSource() == button[1])
           display.append("8");
       if(ae.getSource() == button[2])
           display.append("9");
       if(ae.getSource() == button[3]) {
           
           temporary[0] = Double.parseDouble(display.getText());
           function[0] = true;
           display.setText("");
       }
       if(ae.getSource() == button[4])
           display.append("4");
       if(ae.getSource() == button[5])
       display.append("5");
       if(ae.getSource() == button[6])
           display.append("6");
       if(ae.getSource() == button[7]) {
           
           temporary[0] = Double.parseDouble(display.getText());
           function[1] = true;
           display.setText("");
       }
       if(ae.getSource() == button[8])
           display.append("1");
       if(ae.getSource() == button[9])
       display.append("2");
       if(ae.getSource() == button[10])
       display.append("3");
       if(ae.getSource() == button[11]) {
           
           temporary[0] = Double.parseDouble(display.getText());
           function[2] = true;
           display.setText("");
       }
       if(ae.getSource() == button[12])
           display.append(".");
       if(ae.getSource() == button[13]) {
           
           temporary[0] = Double.parseDouble(display.getText());
           function[3] = true;
           display.setText("");
       }
       if(ae.getSource() == button[14])
           System.exit(0);  
       if(ae.getSource() == button[15])
           clear();
       if(ae.getSource() == button[16])
       {String d=del(display.getText().trim());
            display.setText(d);}
       if(ae.getSource() == button[17])
           getResult();
       if(ae.getSource() == button[18])
           display.append("0");
    }
}

   