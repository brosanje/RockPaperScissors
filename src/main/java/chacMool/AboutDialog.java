package chacMool;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9105158079282156775L;

	public AboutDialog(JFrame parent) {
	    super(parent, "About", true);

	    Box bb = Box.createVerticalBox();
	    bb.add(Box.createGlue());
	    JEditorPane ep = new JEditorPane("text/html",
    		"<html>" +
    		    	"<body>"
    		    	+ "<h1>Rock Paper Scissors in Java</h1>"
    		    	+ "<p><a href='https://github.com/brosanje'>My Stuff on Github</a></p>"
    		    	+ "<p>Bro Sanje, 2018</p>"
    		    	+ "</body>"
    		    	+ "</html>");
	    ep.setEditable(false);
	    ep.setOpaque(false);
	    ep.addHyperlinkListener(new HyperlinkListener() {
	    	public void hyperlinkUpdate(HyperlinkEvent hle) {
	    		if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
	    			URL url = hle.getURL();
	    			Desktop dt = Desktop.getDesktop();
	    			try {
	    				URI uri = new URI(url.toString());
	    				dt.browse(uri);
	    			} catch (IOException ee) {
	    				System.out.println(ee.getMessage());
	    			} catch (URISyntaxException ee) {
	    				System.out.println(ee.getMessage());
	    			}
	    		}
	    	}
	    });
	    ep.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    bb.add(ep);
	    bb.add(Box.createGlue());
	    getContentPane().add(bb, "Center");

	    JPanel p2 = new JPanel();
	    JButton ok = new JButton("Ok");
	    p2.add(ok);
	    getContentPane().add(p2, "South");

	    ok.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	        setVisible(false);
	      }
	    });

	    setSize(300, 150);
	}
}
