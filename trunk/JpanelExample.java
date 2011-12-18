// JPanel Example
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class JPanelExample {
	static int a = 1;
	static int b = 2;
	static int c = -3;
	static int d = 1;
	static int droot = 1;
    static String x2String = "";
    static String x1String = "";
    static JFrame frame;
	
    //Calculate it
    public static void calculate(){
 	   d = (b*b)+(-4)*(a*c);
 	   if(d > 0){
 		   droot = (int) Math.sqrt(d);
 		   x1String = Integer.toString((-b + droot)/2*a);
 		   x2String = Integer.toString((-b - droot)/2*a);
 		   System.out.println("x1String: " + x1String + "x2String: " + x2String);
 	   }else{
 		   System.out.println("D < 0 no x1,x2");
 		   x1String = "--";
 		   x2String = "--";
 	   }
    }

   public static void main(String[] args) {
	  calculate();
	  //menu
	  JMenu jmenu = new JMenu("File");
	  JMenuItem calc = new JMenuItem("Calculate");
	  calc.addActionListener(new ActionListener() {
	         public void actionPerformed (ActionEvent e) {
	        	 Calculator.main2();
				frame.setVisible(false);

	          }
	       });
	  jmenu.add(calc);
	   //Panels
	  JPanel jpanel_2 = new JPanel();
	  JPanel jpanel_1 = new JPanel();
      JPanel jpanel_0 = new JPanel();
      JPanel jpanel_3 = new JPanel();
      //Backgrounds
      jpanel_0.setBackground(Color.green);
      jpanel_1.setBackground(Color.magenta);
      jpanel_2.setBackground(Color.yellow);
      //Labels
      final JLabel x2label = new JLabel("x2 =");
      final JLabel xlabel = new JLabel("x1 =");
      jpanel_0.add(new JLabel("a ="));
      jpanel_1.add(new JLabel("b ="));
      jpanel_2.add(new JLabel("c ="));
      jpanel_3.add(xlabel,BorderLayout.SOUTH);
      jpanel_3.add(x2label);
      //
      xlabel.setHorizontalAlignment(SwingConstants.CENTER);
      x2label.setHorizontalAlignment(SwingConstants.CENTER);
      //Fields
      final JTextField afield = new JTextField(+5);
      final JTextField bfield = new JTextField(+5);
      final JTextField cfield = new JTextField(+5);
      jpanel_0.add(afield);
      jpanel_1.add(bfield);
      jpanel_2.add(cfield);
      //Buttons
      JButton jbutton = new JButton("Calculate");
      jpanel_3.add(jbutton,BorderLayout.NORTH);
      
      //Action Listener
      ActionListener get = new ActionListener() {
          public void actionPerformed(ActionEvent el) {
        	  a = Integer.parseInt(afield.getText());
        	  b = Integer.parseInt(bfield.getText());
        	  c = Integer.parseInt(cfield.getText());
        	  calculate();
        	  xlabel.setText("X1="+(String)x1String);
        	  xlabel.setHorizontalAlignment(SwingConstants.CENTER);
        	  
        	  x2label.setText("X2="+(String)x2String);
        	  x2label.setHorizontalAlignment(SwingConstants.CENTER);
        	  x2label.repaint();
        	  return;
          }
       };
      //Add al
      jbutton.addActionListener(get);
      //Menu Bar
      JMenuBar bar = new JMenuBar();
      bar.add(jmenu);
      //Panel,frame stuff
      frame = new JFrame();
      frame.setVisible(true);
      Container cp = frame.getContentPane();
      frame.setJMenuBar(bar);
      cp.add(jpanel_0,BorderLayout.WEST);
      cp.add(jpanel_1,BorderLayout.CENTER);
      cp.add(jpanel_2,BorderLayout.EAST);
      cp.add(jpanel_3,BorderLayout.AFTER_LAST_LINE);
      //End
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //frame.setVisible(true);

   }
}