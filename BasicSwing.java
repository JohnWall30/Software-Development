import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BasicSwing extends JFrame{
  
  public static void main(String[] args)
  {new BasicSwing(); }
  
  public BasicSwing()
  {
   super("CAMO PROJECT");//first
   
     JPanel p = new JPanel(); 
     JLabel el = new JLabel("Encoding Information");
     JLabel dl = new JLabel("Decoding Information");
     JButton encode1 = new JButton("Encode Text into Image");
     JButton encode2 = new JButton("Encode Image into Image");
     JButton decode1 = new JButton("Decode Text from Image");
     JButton decode2 = new JButton("Decode Image from Image");
      
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()); 
   setResizable(true);
   p.setLayout(null);
   el.setBounds(450, 100, 200, 20);
   dl.setBounds(990, 100, 200, 20);
 
   encode1.setBounds(300, 200, 200, 20);
   encode2.setBounds(520, 200, 200, 20);
   decode1.setBounds(840, 200, 200, 20);
   decode2.setBounds(1060, 200, 200, 20);
     
   p.add(el);//labels
   p.add(dl);
    
   p.add(encode1);//buttons
   p.add(encode2);
   p.add(decode1);
   p.add(decode2);
    
    encode1.addActionListener(new ActionListener()
    { public void actionPerformed(ActionEvent e)
      {//dispose();
      new EncodeText(); dispose();}
    });
    encode2.addActionListener(new ActionListener()
    { public void actionPerformed(ActionEvent e)
      {new EncodeImage(); dispose();}
    });  
    decode1.addActionListener(new ActionListener()
    { public void actionPerformed(ActionEvent e)
      {new DecodeText(); dispose();}
    });
    decode2.addActionListener(new ActionListener()
    { public void actionPerformed(ActionEvent e)
      {new DecodeImage(); dispose();}
    });

   add(p);

   setDefaultCloseOperation(EXIT_ON_CLOSE);
   setVisible(true);//last
  }
}
