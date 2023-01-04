//Använder Swing för att bygga en GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator implements ActionListener{

    //Skapar alla element för GUIn
    JFrame frame;
    JTextField textfield;
    JButton subBtn, divBtn, multBtn, addBtn, commaBtn, equBtn, backBtn, clrBtn;
    JButton[] numBtns = new JButton[10];
    JButton[] funcBtns = new JButton[8];
    JPanel panel;

    Font standardFont = new Font("Fixedsys",Font.PLAIN,25);

    //Doubles ser till att decimaler funkar
    double num1=0,num2=0,result=0;
    char operator;

    Calculator(){

        //Skapar själva rutan och ser till att den kan stänga programmet med krysset
        frame = new JFrame("Miniräknare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,525);
        frame.setLayout(null);

        //Skapar textfältet
        textfield = new JTextField();
        textfield.setBounds(30,20,280,50);
        textfield.setFont(standardFont);
        textfield.setEditable(false);

        //Bestämmer vad funktionsknapparna har för "värde"
        subBtn = new JButton("-");
        divBtn = new JButton("/");
        multBtn = new JButton("*");
        addBtn = new JButton("+");
        commaBtn = new JButton(".");
        equBtn = new JButton("=");
        backBtn = new JButton("<-");
        clrBtn = new JButton("C");

        //Ger alla funktionsknappar en plats i en array så de kan användas i for-loopen nedan
        funcBtns[0] = addBtn;
        funcBtns[1] = subBtn;
        funcBtns[2] = multBtn;
        funcBtns[3] = divBtn;
        funcBtns[4] = commaBtn;
        funcBtns[5] = equBtn;
        funcBtns[6] = backBtn;
        funcBtns[7] = clrBtn;

        //Bestämmer beteendet och stilen på funktionsknapparna i en for-loop så man slipper skriva detta på varje knapp
        for(int i =0;i<8;i++) {
            funcBtns[i].addActionListener(this);
            funcBtns[i].setFont(standardFont);
            funcBtns[i].setFocusable(false);
        }

        //Samma som ovan fast för siffer-knapparna
        for(int i =0;i<10;i++) {
            numBtns[i] = new JButton(String.valueOf(i));
            numBtns[i].addActionListener(this);
            numBtns[i].setFont(standardFont);
            numBtns[i].setFocusable(false);
        }

        backBtn.setBounds(100,420,100,50);
        clrBtn.setBounds(200,420,100,50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));

        //Lägger till alla element i ordning i panelen
        panel.add(numBtns[1]);
        panel.add(numBtns[2]);
        panel.add(numBtns[3]);
        panel.add(addBtn);
        panel.add(numBtns[4]);
        panel.add(numBtns[5]);
        panel.add(numBtns[6]);
        panel.add(subBtn);
        panel.add(numBtns[7]);
        panel.add(numBtns[8]);
        panel.add(numBtns[9]);
        panel.add(multBtn);
        panel.add(commaBtn);
        panel.add(numBtns[0]);
        panel.add(equBtn);
        panel.add(divBtn);

        //Lägger till panelen samt några extra knappar i rutan och gör den synlig
        frame.add(panel);
        frame.add(backBtn);
        frame.add(clrBtn);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        //Alla actions för när man trycker in ett nummer samt en operator
        for (int i=0;i<10;i++){
            if(e.getSource() == numBtns[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==commaBtn) {
            textfield.setText(textfield.getText().concat("."));
        }
        if(e.getSource()==subBtn) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='-';
            textfield.setText("");
        }
        if(e.getSource()==addBtn) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='+';
            textfield.setText("");
        }
        if(e.getSource()==divBtn) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='/';
            textfield.setText("");
        }
        if(e.getSource()==multBtn) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='*';
            textfield.setText("");
        }

        //Kollar vilken operator man tryckt in och skapar ett resultat
        if(e.getSource()==equBtn){
            num2 = Double.parseDouble(textfield.getText());

            switch (operator){
                case'+':
                    result=num1+num2;
                    break;
                case'-':
                    result=num1-num2;
                    break;
                case'*':
                    result=num1*num2;
                    break;
                case'/':
                    result=num1/num2;
                    break;
            }
            //Skriver in resultatet i rutan
            textfield.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==backBtn){
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0;i<string.length()-1;i++){
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }
        if(e.getSource()==clrBtn){
            textfield.setText("");
        }


    }

}