import java.io.File; 
import javax.swing.ImageIcon;

public class Checker extends javax.swing.filechooser.FileFilter
 {
  public boolean textChecker(String mess)
  { 
    if (mess.length() > 500) 
      return true;
                 
    return false;                            
  }
   
  public boolean imageChecker(String image1path, String image2path)
  {
    ImageIcon MyImage1 = new ImageIcon(image1path);
    ImageIcon MyImage2 = new ImageIcon(image2path);
   
    double width1 = MyImage1.getIconWidth();
    double height1 = MyImage1.getIconHeight();
    double width2 = MyImage2.getIconWidth();
    double height2 = MyImage2.getIconHeight();
             
    if(width1 < width2 && height1 < height2)
      return false;
    else
      return true;
  }
   
   public boolean textimageChecker(String imagepath)
  {
    ImageIcon MyImage = new ImageIcon(imagepath);
    
    double width = MyImage.getIconWidth();
    double height = MyImage.getIconHeight();

    if(height>(167/width))
      return false;
    else
      return true;
  }
   
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   protected boolean isImageFile(String ext)
  {return (ext.equals("jpg")||ext.equals("png"));}
 
   public boolean accept(File f)
  {
      if (f.isDirectory())
        {return true;}

      String extension = getExtension(f);
      if (extension.equals("jpg")||extension.equals("png"))
        {return true;}
    return false;
  }
 
  public String getDescription()
  {return "Supported Image Files";}
 
  protected static String getExtension(File f)
  {
  String s = f.getName();
  int i = s.lastIndexOf('.');
    if (i > 0 &&  i < s.length() - 1) 
      return s.substring(i+1).toLowerCase();
  return "";
  } 
 }
    