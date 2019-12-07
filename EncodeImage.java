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
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;


public class EncodeImage extends JFrame{

  private String  stat_path = "";
  private String  stat_name = "";
  private String  ext = "";
  private String  stat_path_2 = "";
  private String  stat_name_2 = "";
  private String  ext_2 = "";
  private String newFileName;
   private String method;
   private String imagest;
   private String imagest2;
  
  
  private JLabel image;
  private JLabel image2;
  private JLabel image3;
  
  public EncodeImage()
  {
    super("Encode Image into Image");//NAME THE JFRAME
    JPanel pa = new JPanel(); //CREATE THE PANEL
    JLabel encodingimagelabel1 = new JLabel("Select an Image File to Encode");//LABEL FOR SEARCH BUTTON
    JLabel encodingimagelabel2 = new JLabel("Select an Image File to Contain the Encoded Image");//LABEL FOR THE OTHER SEARCH BUTTON
    image = new JLabel();//IMAGE TO ENCODE
    image2 = new JLabel();//IMAGE TO CONTAIN
    image3 = new JLabel();//ENCODED IMAGE
 
    //ALL THE BUTTONS
    JButton encodeimagebt = new JButton("Encode");
    JButton searchencode1bt = new JButton("Search");
    JButton searchencode2bt = new JButton("Search");
    JButton clearimage = new JButton("Clear");
    JButton clearimage2 = new JButton("Clear");
    JButton zoomone = new JButton("Zoom In/Out");
    JButton zoomtwo = new JButton("Zoom In/Out");
    JButton zoomthree = new JButton("Zoom In/Out");
    JButton back1 = new JButton("Back");
    JComboBox<String> c1 = new JComboBox<String>(new String[] {"3 Bit Encoded Image","4 Bit Encoded Image"});

    //ALL THE LISTENERS
     back1.addActionListener(new ActionListener() {//BACK BUTTON LISTENER
    public void actionPerformed(ActionEvent e) 
    { 
      new BasicSwing();
      dispose(); }
    });
    
     clearimage.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE TO ENCODE
    public void actionPerformed(ActionEvent e) {
       image.setIcon(ResizeImage(""));
       image3.setIcon(ResizeImage(""));
        stat_path = "";
        stat_name = "";
        ext = "";
    }
    });
     
     clearimage2.addActionListener(new ActionListener() {//CLEAR SEARCHED IMAGE TO CONTAIN
    public void actionPerformed(ActionEvent e) {
       image2.setIcon(ResizeImage(""));
       image3.setIcon(ResizeImage(""));
       stat_path_2 = "";
       stat_name_2 = "";
       ext_2 = "";
    }
    });
    
    zoomone.addActionListener(new ActionListener() {//ZOOM BUTTON FOR IMAGE TO ENCODE
    public void actionPerformed(ActionEvent e) {
     try{new ImageZoom( stat_path + "/" + stat_name + "." + ext , "Image Zoom In and Zoom Out Image To Encode");
     }
     catch(Exception except){}
     }
    });
    
    zoomtwo.addActionListener(new ActionListener() {//ZOOM BUTTON FOR IMAGE TO CONTAIN
    public void actionPerformed(ActionEvent e) {
     try{new ImageZoom( stat_path_2 + "/" + stat_name_2 + "." + ext_2 , "Image Zoom In and Zoom Out Image To Contain");
    
     }
     catch(Exception except){
         System.out.println("HELP");
     }
     }
    });
    
    zoomthree.addActionListener(new ActionListener() {//ZOOM BUTTON FOR ENCODED IMAGE
    public void actionPerformed(ActionEvent e) {
     try{new ImageZoom(stat_path + "/"  + newFileName + "." + ext_2, "Image Zoom In and Zoom Out Encoded Image");
 
     }
     catch(Exception except){
     System.out.println("HELP");
     }
     }
    });    
    

    
    c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               JComboBox c1 = (JComboBox)event.getSource();
         method = String.valueOf(c1.getSelectedItem());
            
            }});
      
        
    searchencode1bt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
      public void actionPerformed(ActionEvent e) {
 
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
     
      searchencode2bt.addActionListener(new ActionListener() {//SEARCH BUTTON LISTENER
        public void actionPerformed(ActionEvent e) {
 
      JFileChooser chooser = new JFileChooser("./");
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      chooser.setFileFilter(new Checker());
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION){
      File directory = chooser.getSelectedFile();
        try{
          ext_2  = Checker.getExtension(directory);
          imagest2 = directory.getPath();
          stat_name_2 = directory.getName();
          stat_path_2 = directory.getPath();
          stat_path_2 = stat_path_2.substring(0,stat_path_2.length()-stat_name_2.length()-1);
          stat_name_2 = stat_name_2.substring(0, stat_name_2.length()-4);      
          image2.setIcon(ResizeImage(imagest2));
       }
      
            catch(Exception except) {
        JOptionPane.showMessageDialog(null, "The File cannot be opened!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
   }
  }
});
  
      
     encodeimagebt.addActionListener(new ActionListener() {//ENCODE BUTTON LISTENER
       public void actionPerformed(ActionEvent e) {   
         
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmmss");
         Date date = new Date();
         newFileName = dateFormat.format(date);
         
         newFileName =  newFileName + "Image" + stat_name_2; 
         String holder = stat_path_2 + "/" + newFileName + "." + ext_2;//the entire path

      
        if(stat_path_2 == "" && stat_name_2 ==""|| stat_path == "" && stat_name == "")
        {
        JOptionPane.showMessageDialog(null, "Please select a image to encode and a image to Contain" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
         
         
        //Check the image sizes
        Checker imagetoimagechecker = new Checker();
        boolean tooLarge = imagetoimagechecker.imageChecker(imagest, imagest2);
        if(tooLarge)
        {
        JOptionPane.showMessageDialog(null, "The image to encode must have a smaller width and height than the containing image" , "Error!", JOptionPane.INFORMATION_MESSAGE);
        return;//exit the action listener as to not encode the wrong thing
        }
        if(method == "4 Bit Encoded Image" )//Check the image sizes
        {
        //ImageEncoder model = new ImageEncoder();
        //model.imageencode(stat_path, stat_name, ext, newFileName, stat_path_2, stat_name_2, ext_2);
        }
        else//3 bit method
        { //ImageEncoder3 model3 = new ImageEncoder3();
         //model3.imageencode3(stat_path, stat_name, ext, newFileName, stat_path_2, stat_name_2, ext_2);
        }        
        image3.setIcon(ResizeImage(holder));//display image
         Logger imageEncodecodeLog = new Logger();
         imageEncodecodeLog.logger("Image Encoded Into Image, File Name: " +" "+ newFileName);
        
        }
});
   
    //SET UP THE PANEL SIZE FOR DISPLAY   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());     
    setResizable(true);
    pa.setLayout(null);
   
   //SET UP THE POSITIONS OF THE OBJECTS ON THE PANEL
   encodingimagelabel1.setBounds(150, 10, 200, 20);
   searchencode1bt.setBounds(150, 40, 100, 20);
   encodingimagelabel2.setBounds(550, 10, 300, 20);
   searchencode2bt.setBounds(550, 40, 100, 20);  
   image.setBounds(100 ,70, 400, 400);
   image2.setBounds(550 ,70, 400, 400);
   image3.setBounds(1000 ,70, 400, 400);
   encodeimagebt.setBounds(1200, 750, 100, 20);
   clearimage.setBounds(260, 40, 100, 20);
   clearimage2.setBounds(660, 40, 100, 20);
   back1.setBounds(20, 10, 100, 20);
   zoomone.setBounds(300, 550, 120, 20);
   zoomtwo.setBounds(750, 550, 120, 20);
   zoomthree.setBounds(1200, 550, 120, 20);
   c1.setBounds(1040, 750, 150, 20);

   //PLACE THE OBJECTS ON THE PANEL
     pa.add(c1);
     pa.add(clearimage);
     pa.add(clearimage2);
     pa.add(back1);
     pa.add(zoomone);
     pa.add(zoomtwo);
     pa.add(zoomthree);
     pa.add(encodingimagelabel1);
     pa.add(encodingimagelabel2);
     pa.add(searchencode1bt);
     pa.add(image);
     pa.add(searchencode2bt);
     pa.add(image2);   
     pa.add(encodeimagebt);
     pa.add(image3);
     //ADD THE PANLE TO THE JFRAME
     add(pa);
     //SET CLOSE OPERATION  
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     //MAKE VISABLE
     setVisible(true);
  }
  
   //RESIZE THE IMAGE FOR THE DISPLAY
   public ImageIcon ResizeImage(String ImagePath)
  {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        double width = MyImage.getIconWidth();
        double height = MyImage.getIconHeight();
        if(width > height)
        {height = (400/width)*(height); width = 400;}
        else
        {width = (400/height)*(width); height = 400;}
 
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}