import java.io.File;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class DecodeText extends JFrame{

  private String  stat_path = "";
  private String  stat_name = "";
  private String ext ="";
  private String mess;
  private JLabel image;
  private JTextArea outmess;
  
  public DecodeText()
  {
    super("Decode Text From Image");//NAME THE JFRAME
    JPanel pa = new JPanel(); //CREATE THE PANEL
    JLabel decodingtextlabel = new JLabel("Select an Image file");//LABEL FOR SEARCH BUTTON
    image = new JLabel();//ENCODED IMAGE
    outmess = new JTextArea();//TEXT AREA OUTPUT
    outmess.setLineWrap(true);//ALLOW LINE OF TEXT TO WRAP    


    //ALL THE BUTTONS
    JButton decodetextbt = new JButton("Decode");
    JButton searchdecodetextbt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton back1 = new JButton("Back");
    JButton cleartext = new JButton("Clear");
    
    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
    public void actionPerformed(ActionEvent e) 
    { new BasicSwing();
      dispose(); }
    });
  
    cleartext.addActionListener(new ActionListener() {//CLEAR TEXT BUTTON LISTENER
    public void actionPerformed(ActionEvent e) 
    {     outmess.setText(""); }
    });
      
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) 
    { 
    image.setIcon(ResizeImage(""));
    
    stat_path = "";
    stat_name = "";
    ext ="";
    }
    });
        
    searchdecodetextbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
     
              outmess.setText("");
      JFileChooser chooser = new JFileChooser("./");
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      chooser.setFileFilter(new Checker());
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION){
        File directory = chooser.getSelectedFile();
   
        try{
           ext = Checker.getExtension(directory);
           String imagest = directory.getPath();
           stat_name = directory.getName();
           stat_path = directory.getPath();
           stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
           stat_name = stat_name.substring(0, stat_name.length()-4);

           image.setIcon(ResizeImage(imagest));
       }
       catch(Exception except) {
        JOptionPane.showMessageDialog(null, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
   }
  }
});
      decodetextbt.addActionListener(new ActionListener() {//DECODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
        if(stat_name == "" && stat_path =="")
        {
        JOptionPane.showMessageDialog(null, "Please select an image to decode" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
        //TextDecoder model = new TextDecoder();
       // mess = model.textdecode(stat_path, stat_name, ext);
        outmess.setText(mess);
         if(stat_name == "" && stat_path =="")
        {
        JOptionPane.showMessageDialog(null, "The image to encode must have a smaller width and height than the containing image" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
         Logger textDecodeLog = new Logger();
         textDecodeLog.logger("Text Decoded From Image, File Name: " +" "+ stat_name);
   
        }
});
   
      
    //SET UP THE PANEL SIZE FOR DISPLAY   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()); 
    setResizable(true);
    pa.setLayout(null);
    
    //SET UP THE POSITIONS OF THE OBJECTS ON THE PANEL
    decodingtextlabel.setBounds(200, 10, 200, 20);
    outmess.setBounds(200 ,570, 1100, 150);
    searchdecodetextbt.setBounds(370, 10, 100, 20);
    image.setBounds(200 ,40, 500, 500);
    decodetextbt.setBounds(1200, 750, 100, 20);
    clearimage.setBounds(480, 10, 100, 20);
    back1.setBounds(20, 10, 100, 20);
    cleartext.setBounds(1090, 750, 100, 20);
   

    //PLACE THE OBJECTS ON THE PANEL

     pa.add(clearimage);
     pa.add(back1);
     pa.add(decodingtextlabel);
     pa.add(searchdecodetextbt);
     pa.add(image);
     pa.add(outmess);
     pa.add(cleartext);
     pa.add(decodetextbt);
     //ADD THE PANLE TO THE JFRAME
     add(pa);
     //SET CLOSE OPERATION  
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     //MAKE VISIABLE
     setVisible(true);
  }
 
   //RESIZE THE INAGE FOR THE DISPLAY
  public ImageIcon ResizeImage(String ImagePath)
  {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        double width = MyImage.getIconWidth();
        double height = MyImage.getIconHeight();
        if(width > height)
        {height = (500/width)*(height); width = 500;}
        else
        {width = (500/height)*(width); height = 500;}
 
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
