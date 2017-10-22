import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;




import java.awt.*;

public class JavaBrowser {

    public static void main(String[] args){
        // TODO Auto-generated method stub

//        WebBrowser webBrowser = new WebBrowser.RequestBuilder("gaia.cs.umass.edu/wireshark-labs/HTTP-wireshark-file3.html").build();
        WebBrowser webBrowser = new WebBrowser.RequestBuilder("nearby.cf").build();

        if(webBrowser.connect()){
            System.out.println(webBrowser.getHeader());
            System.out.println("====================");
            System.out.println(webBrowser.getContent());
        }else{
            System.out.println("Error");
        }


//		BrowserPane swingbox = new BrowserPane();
//		swingbox.setContentType("text/html");
//		swingbox.setText(webBrowser.getContent());
//
//
//		JFrame frame = new JFrame("JxBrowser");
//	    frame.add(swingbox, BorderLayout.CENTER);
//	    frame.setSize(700, 500);
//	    frame.setVisible(true);

        // add the component to your GUI
//		yourcontainer.add(swingbox);
        // display the page
//	    swingbox.setText(webBrowser.getContent());
//		swingbox.setPage(new URL("http://cssbox.sourceforge.net"));

//        JEditorPane jep = new JEditorPane();
//        jep.setEditable(false);
//
//        jep.setContentType("text/html");
//        jep.setText(webBrowser.getContent());

//		try {
//		  jep.setContentType("text/html");
//		  jep.setText(webBrowser.getContent());
//		}catch (IOException e) {
//		  jep.setContentType("text/html");
//		  jep.setText("<html>Could not load</html>");
//		}

//        BrowserPane swingbox = new BrowserPane();
//        swingbox.setContentType("text/html");
//        swingbox.setText(webBrowser.getContent());

//        JScrollPane scrollPane = new JScrollPane(jep);
//        JFrame f = new JFrame("Test HTML");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.getContentPane().add(scrollPane);
//        f.setPreferredSize(new Dimension(800,600));
//        f.setVisible(true);
        showWepPage(webBrowser.getContent());

    }

    public static void showWepPage(String data)
    {
        // create jeditorpane
        JEditorPane jEditorPane = new JEditorPane();

        // make it read-only
        jEditorPane.setEditable(false);

        // create a scrollpane; modify its attributes as desired
        JScrollPane scrollPane = new JScrollPane(jEditorPane);

        // add an html editor kit
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);

        // add some styles to the html
        StyleSheet styleSheet = kit.getStyleSheet();
//        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
//        styleSheet.addRule("h1 {color: blue;}");
//        styleSheet.addRule("h2 {color: #ff0000;}");
//        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

        // create some simple html as a string
        String htmlString = data;

        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);

        // now add it all to a frame
        JFrame j = new JFrame("HtmlEditorKit Test");
        j.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // make it easy to close the application
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // display the frame
        j.setSize(new Dimension(1000,800));

        // pack it, if you prefer
        //j.pack();

        // center the jframe, then make it visible
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }

}
