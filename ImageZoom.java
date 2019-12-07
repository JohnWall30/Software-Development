import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

public class ImageZoom {
    private JFrame frmImageZoomIn;
    private String inputImage; // give image path here
    private String zoomlabel;//labels the jframe
    private JLabel label = null; 
    private double zoom = 1.0;  // zoom factor
    private BufferedImage image = null;
    
   // private static final String inputImage = "E:\\Hood College\\Hood College Fall 2019\\CS 324 Software Engineering\\New folder\\Project/Big cat.png "; // give image path here
    public ImageZoom(String imagepath, String labelpass) throws IOException {
      inputImage = imagepath;
      zoomlabel = labelpass;
      //System.out.println(inputImage);
        initialize();
       //  window.frmImageZoomIn.setVisible(true);
    }
    private void initialize() throws IOException {
        frmImageZoomIn = new JFrame();

        frmImageZoomIn.setTitle(zoomlabel);
        frmImageZoomIn.setBounds(100, 100, 500, 500);
        //frmImageZoomIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JScrollPane scrollPane = new JScrollPane();
        frmImageZoomIn.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        image = ImageIO.read(new File(inputImage));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // display image as icon 
        Icon imageIcon = new ImageIcon(inputImage);
        label = new JLabel( imageIcon );
        panel.add(label, BorderLayout.CENTER);
     
        panel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                double temp = zoom - (notches * 0.2);
                // minimum zoom factor is 1.0
                temp = Math.max(temp, 1.0);
                System.out.print("TEMP:   "+temp);
                if (temp > 5) temp = 5;//this is to keep it from going too far!!!
                if (temp != zoom) {
                    zoom = temp;
                    
                    Image newImage = image.getScaledInstance((int)(image.getWidth()*zoom), (int)(image.getHeight()*zoom), Image.SCALE_DEFAULT);
                    Icon imageIcon = new ImageIcon(newImage);
                    label.setIcon(imageIcon);
                    //resizeImage();
                }
            }
        });
        frmImageZoomIn.setVisible(true);
       scrollPane.setViewportView(panel);
    }
        public static void main(String[] args) {

    }
}