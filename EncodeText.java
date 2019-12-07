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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;

public class EncodeText extends JFrame{

  private JTextArea textInput;
  private String stat_path = "";
  private String stat_name = "";
  private String ext = "";
  private  String imagest;
  private String text;
  private String newFileName;
  private JLabel image;
  private JLabel image2;
  
  
  public EncodeText()
  {
    super("Encode Text into Image");//NAME THE JFRAME
    JPanel pa = new JPanel(); //CREATE THE PANEL
    JLabel encodingtextlabel1 = new JLabel("Select an Image file");//LABEL FOR SEARCH BUTTON
    JLabel encodingtextlabel2 = new JLabel("Input 500 characters or less of text to encode");//LABEL FOR TEXT BOX
    image = new JLabel();//IMAGE TO CONTAIN THE TEXT
    image2 = new JLabel();//ENCODED IMAGE
    textInput = new JTextArea();//TEXT AREA INPUT
    textInput.setLineWrap(true);    
    JComboBox<String> c1 = new JComboBox<String>(new String[] {"One Bit Encoding"});
   // JComboBox c1 = new JComboBox(s1);
    
     //ALL THE BUTTONS
    JButton encodetextbt = new JButton("Encode");
    JButton searchbt = new JButton("Search");
    JButton back1 = new JButton("Back");
    JButton clearimage = new JButton("Clear");
    JButton cleartext = new JButton("Clear");
    JButton zoomone = new JButton("Zoom In/Out");
    JButton zoomtwo = new JButton("Zoom In/Out");

    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
    public void actionPerformed(ActionEvent e) 
    { new BasicSwing();
      dispose(); 
    }
    });
        
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
       image.setIcon(ResizeImage(""));
       image2.setIcon(ResizeImage(""));
       stat_path = "";
       stat_name = "";
       ext = "";
    }
    });
    
    cleartext.addActionListener(new ActionListener() {//CLEAR TEXT LISTENER
    public void actionPerformed(ActionEvent e) {
       textInput.setText("");
    }
    });
    
    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ORIGINAL IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
     try{new ImageZoom( stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Original Image");
     }
     catch(Exception except){}
     }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE
    public void actionPerformed(ActionEvent e) {
     try{new ImageZoom(stat_path + "/"  + newFileName + "." + ext, "Image Zoom In and Zoom Out Encoded Image");
     }
     catch(Exception except){}
     }
    });     

//  JScrollPane scroller = new JScrollPane(pa);
    //./this.getContentPane().add(scroller, BorderLayout.CENTER);
    
    searchbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
    public void actionPerformed(ActionEvent e) {
 
      image2.setIcon(ResizeImage(""));
      
    JFileChooser chooser = new JFileChooser("./");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    chooser.setFileFilter(new Checker());
    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION){
      File directory = chooser.getSelectedFile();

        try{
          ext  = Checker.getExtension(directory);
          imagest = directory.getPath();
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
      
        encodetextbt.addActionListener(new ActionListener() {//ENCODE BUTTON LISTENER
        public void actionPerformed(ActionEvent e) {
        
        text = textInput.getText(); //text to encode into the image
                 
        //Check the text length
        Checker textcheck = new Checker();
        boolean texttooLarge = textcheck.textChecker(text);
        if(texttooLarge)
        {
        JOptionPane.showMessageDialog(null, "Please input 500 characters or less", "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;
        }
            
        //Check the image size
        boolean tooSmall = textcheck.textimageChecker(imagest);
        if(tooSmall)
        {
        JOptionPane.showMessageDialog(null, "The Image is Too Small to Contain The Message", "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
         Date date = new Date();
         newFileName = dateFormat.format(date);
         newFileName =  newFileName  + "Text" +  stat_name;  
         String holder = stat_path + "/"  + newFileName + "." + ext;//the entire path
        
         //String newFileName = JOptionPane.showInputDialog("Enter a File Name for the Encoded Image:");//get the new file name
         //File temholder =new File(holder);//does this file already exists?
         //boolean exists = temholder.exists();//does this file already exists?
         /*//File nameing 
         while(exists || newFileName.length() == 0)//While this file exists ask for another file name
         {  
         JOptionPane.showMessageDialog(null, "This File Already Exists Please Try Again", "Error!", JOptionPane.INFORMATION_MESSAGE);
         newFileName = JOptionPane.showInputDialog("Enter a UNIQUE File Name for the Encoded Image:");//get the new file name
         holder = stat_path + "/" + newFileName + "." + ext;
         temholder =new File(holder);
         exists = temholder.exists();
          }
         */
         if(stat_path =="" && stat_name=="")
        {
        JOptionPane.showMessageDialog(null, "No image was selected!\nPlease select an image.", "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;
        }
         //TextEncoder model = new TextEncoder();
         //model.textencode(stat_path, stat_name, ext, newFileName, text);//encodes image and saves images
         image2.setIcon(ResizeImage(holder));

         Logger textEncodeLog = new Logger();
         textEncodeLog.logger("Text Encoded into Image, File Name: " +" "+ newFileName);
        }
});
    
   //SET UP THE PANEL SIZE FOR DISPLAY      
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());   
   setResizable(true);
   pa.setLayout(null);
   
   //SET UP THE POSITIONS OF THE OBJECTS ON THE PANEL
   encodingtextlabel1.setBounds(200, 10, 200, 20);
   searchbt.setBounds(320, 10, 100, 20);
   image.setBounds(200 ,40, 500, 500);
   image2.setBounds(710 ,40, 500, 500);
   textInput.setBounds(200 ,620, 1100, 100);
   encodetextbt.setBounds(1200, 750, 100, 20);
   encodingtextlabel2.setBounds(200, 600, 300, 20);
   back1.setBounds(20, 10, 100, 20);
   clearimage.setBounds(430, 10, 100, 20);
   cleartext.setBounds(200, 750, 100, 20);
   zoomone.setBounds(400, 550, 120, 20);
   zoomtwo.setBounds(910, 550, 120, 20);
   c1.setBounds(1040, 750, 150, 20);
    
     //PLACE THE OBJECTS ON THE PANEL
     pa.add(c1);
     pa.add(cleartext);
     pa.add(clearimage);
     pa.add(zoomone);
     pa.add(zoomtwo);
     pa.add(back1);
     pa.add(encodingtextlabel1);
     pa.add(encodingtextlabel2);
     pa.add(searchbt);
     pa.add(image);
     pa.add(image2);
     pa.add(textInput);
     pa.add(encodetextbt);
     //ADD THE PANLE TO THE JFRAME
     add(pa);
     //SET CLOSE OPERATION  
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     //MAKE VISABLE
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