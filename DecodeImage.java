import java.io.File;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;

public class DecodeImage extends JFrame{

  private String  stat_path = "";
  private String  stat_name = "";
  
  private JLabel image;
  private JLabel image2;
  private String ext ="";
  private String newFileName ="";
  
  public DecodeImage()
  {
    
    super("Decode Image From Image");//NAME THE JFRAME
    JPanel pa = new JPanel();//CREATE THE PANEL
    JLabel decodingimagelabel = new JLabel("Select an Image file");//LABEL FOR SEARCH BUTTON
    image = new JLabel();//ENCODED IMAGE
    image2 = new JLabel();//DECODED IMAGE
    
    //ALL THE BUTTONS
    JButton decodeimagebt = new JButton("Decode");
    JButton searchbt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton clearimage2 = new JButton("Clear");
    JButton back1 = new JButton("Back"); 
    JButton zoomone = new JButton("Zoom In/Out");
    JButton zoomtwo = new JButton("Zoom In/Out");

    //ALL THE LISTENERS
    back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
    public void actionPerformed(ActionEvent e) 
    { 
      new BasicSwing();
      dispose(); }
    });
        
    clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
       image.setIcon(ResizeImage(""));
       image2.setIcon(ResizeImage(""));
       stat_path = "";
       stat_name = "";
    }
    });
    
    clearimage2.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
       image2.setIcon(ResizeImage(""));
       newFileName ="";
    }
    });
        
    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
     try{
       
       if(stat_path ==  "")
         return;
       new ImageZoom(stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Original Image");
     }
     catch(Exception except){}
     }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR DECODED IMAGE LISTENER
    public void actionPerformed(ActionEvent e) {
     try{
              if(newFileName ==  "")
         return;
       new ImageZoom(stat_path + "/"  + newFileName + "." + ext, "Image Zoom In and Zoom Out Encoded Image");
     

     }
     catch(Exception except){}
     }
    });     

    searchbt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
 
      JFileChooser chooser = new JFileChooser("./");
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      chooser.setFileFilter(new Checker());
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION){
        File directory = chooser.getSelectedFile();
   
        try{
           ext  = Checker.getExtension(directory);
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
      decodeimagebt.addActionListener(new ActionListener() {//DECODE BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {       
      
        
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
         Date date = new Date();
         newFileName = dateFormat.format(date);
         newFileName = stat_name + "Decoded" + newFileName;
         String holder = stat_path + "/"  + newFileName + "." + ext;
        
         //ImageDecoder model = new ImageDecoder();
        //model.imagedecode(stat_path, stat_name, ext, newFileName);          
         image2.setIcon(ResizeImage(holder));

         Logger imageDecodecodeLog = new Logger();
         imageDecodecodeLog.logger("Image Decoded From Image, File Name: " +" "+ stat_name);
        }
});
      
   //SET UP THE PANEL SIZE FOR DISPLAY   
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()); 
   setResizable(true);
   pa.setLayout(null);
   
   //SET UP THE POSITIONS OF THE OBJECTS ON THE PANEL
   decodingimagelabel.setBounds(200, 10, 200, 20);
   searchbt.setBounds(370, 10, 100, 20);
   image.setBounds(200 ,40, 500, 500);
   image2.setBounds(800 ,40, 500, 500);
   decodeimagebt.setBounds(1200, 750, 100, 20);
   clearimage.setBounds(480, 10, 100, 20);
   clearimage2.setBounds(800, 10, 100, 20);
   back1.setBounds(20, 10, 100, 20);
   zoomone.setBounds(400, 550, 120, 20);
   zoomtwo.setBounds(910, 550, 120, 20);



   //PLACE THE OBJECTS ON THE PANEL
   pa.add(clearimage2);
   pa.add(clearimage);
   pa.add(back1);
   pa.add(zoomone);
   pa.add(zoomtwo);
   pa.add(decodingimagelabel);
   pa.add(searchbt);
   pa.add(image);
   pa.add(image2);
   pa.add(decodeimagebt);
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
