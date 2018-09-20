package httpanalyzer;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;    
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Sare
 */
public class HttpAnalyzer extends JFrame implements ActionListener {
    public JFrame frame;
    public JButton btn1;
    public JButton btn2;
    public JTextField textField;
    public JTextArea Response;
    public JScrollPane scroll;
    
    public HttpAnalyzer() throws Exception {   
       frame = new JFrame("Http Analyzer");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(600,650);
       frame.setLayout(null);
       
       JPanel panel = new JPanel();
       panel.setBounds(10,10,600,600);
       panel.setLayout(null);
        
       JLabel l1= new JLabel("Http Analyzer");
       l1.setLocation(10, 10);
       l1.setSize(400, 30);
       l1.setFont(new Font("Serif", Font.BOLD,30));
       panel.add(l1);
        
       JLabel l2= new JLabel("Please Enter your URL:");
       l2.setLocation(5,5);
       l2.setSize(400,140);
       l2.setFont(new Font("Serif", Font.PLAIN,14));
       panel.add(l2);
        
       textField = new JTextField();
       textField.setLocation(145,65);
       textField.setSize(400,25);
       textField.setFont(new Font("Serif", Font.BOLD,14));
       textField.addActionListener(this);
       panel.add(textField);
      
       btn1 = new JButton("OPTIONS");
       btn1.setLocation(200,140);
       btn1.setSize(100,30);
       btn1.addActionListener(this);
       panel.add(btn1);
        
       btn2 = new JButton("GET");
       btn2.setLocation(340,140);
       btn2.setSize(100,30);
       btn2.addActionListener(this);
       panel.add(btn2);
        
       Response = new JTextArea();
       Response.setLocation(145,200);
       Response.setSize(350,350);
       Response.setFont(new Font("Serif", Font.BOLD,12));

       scroll = new JScrollPane(Response,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       scroll.setBounds(145,200, 350, 300);
       panel.add(Response);
       panel.add(scroll);
        
       frame.add(panel);
       frame.setVisible(true);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btn1){
             URL url = null;
                try{
                    url = new URL(textField.getText());
                }catch (MalformedURLException ex){
                    Logger.getLogger(HttpAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
                }
                try{
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("OPTIONS");
                    connection.connect();
                    System.out.println("info: "+connection.getHeaderFields());
                    Response.append("Server: "+connection.getHeaderField("Server")+"\n");
                    Response.append("Methods: "+connection.getHeaderField("Allow")+"\n");
                    Response.append("Cookies Info: "+connection.getHeaderField("Set-Cookie")+"\n");
                    Response.append("Expire: "+connection.getHeaderField("Expires")+"\n");
                    Response.append("Last-Modified: "+connection.getHeaderField("Last-Modified")+"\n");
                    Response.append("Cache-Control: "+connection.getHeaderField("Cache-Control")+"\n");
                    Response.append("Status code: "+connection.getResponseCode()+"\n");
                    Response.append("Status Message: "+connection.getResponseMessage()+"\n");
                    Response.append("Authenticate: "+connection.getHeaderField("WWW-Authenticate")+"\n");
                }catch (IOException ex) {
                    Logger.getLogger(HttpAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
        if(e.getSource()== btn2) {
              URL url = null;
                try {
                    url = new URL(textField.getText());
                } catch(MalformedURLException ex){
                    Logger.getLogger(HttpAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    System.out.println("Info "+connection.getHeaderFields());
                    Response.append("Server: "+connection.getHeaderField("Server")+"\n");
                    Response.append("Methods: "+connection.getHeaderField("Allow")+"\n");
                    Response.append("Cookies Info: "+connection.getHeaderField("Set-Cookie")+"\n");
                    Response.append("Expire: "+connection.getHeaderField("Expires")+"\n");
                    Response.append("Last-Modified: "+connection.getHeaderField("Last-Modified")+"\n");
                    Response.append("Cache-Control: "+connection.getHeaderField("Cache-Control")+"\n");
                    Response.append("Status code: "+connection.getResponseCode()+"\n");
                    Response.append("Status Message: "+connection.getResponseMessage()+"\n");
                    Response.append("Authenticate: "+connection.getHeaderField("WWW-Authenticate")+"\n");
                } catch(IOException ex){
                    Logger.getLogger(HttpAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
                  }
        }
    }
    public static void main(String[] args)throws Exception {
        HttpAnalyzer HttpAnalyzer = new HttpAnalyzer();
        HttpAnalyzer.setVisible(true);
    }
   
}
