package asteroids_test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PauseMenu extends JPanel implements ActionListener{
	int x=(int) ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()*(3.0/7.0));
	int y=(int) ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*(2.0/6.0));
	int width=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/7;
	int height=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;

	JButton resume;
	public PauseMenu(){
	resume=new JButton(){
	protected void paintComponent(Graphics gr){super.paintComponent(gr);
		setContentAreaFilled(false);
		
		final Graphics2D g2d1=(Graphics2D)gr.create();
		 RenderingHints hints1 = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					RenderingHints render1 =new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
					hints1.add(render1);
					g2d1.setRenderingHints(hints1);
					
				if(resume.getModel().isRollover()){//detects if the mouse is hovering over the button
					g2d1.setColor(new Color(100,100,100));
					g2d1.fillRoundRect(3,3, getWidth()-5, getHeight()-5, 50, 100);
				}
					g2d1.setColor(new Color(0,0,0));
					g2d1.fillRect(resume.getX(), resume.getY(), resume.getWidth(), resume.getHeight());
					g2d1.setColor(new Color(255,255,255));
					g2d1.setStroke(new BasicStroke(3));
					g2d1.drawRoundRect(3,3, getWidth()-5, getHeight()-5, 50, 100);
					g2d1.setColor(Color.white);
					g2d1.drawString("RESUME", 30,34);
					
	}};
	//Allows to detect for mouse over button
	/*resume.getModel().addChangeListener(new ChangeListener() {
		
	    public void stateChanged(ChangeEvent e) {
	       // ButtonModel model = (ButtonModel) e.getSource();
	    	//System.out.println("SIIIIIIIIIIIK");
	    }
	}); */
		setBounds(x,y,width,height);
		setBackground(new Color(0,80,0,0));
		resume.setBounds(
				(int)(((1.0/6.0)*width)),
				(int)(((1.0/6.0)*y)),
				(int)(width*(4.0/6.0)+5),
				(int)(height*(1.0/8.0))
				);
		resume.setBorderPainted(false);
		resume.setFocusPainted(false);
		//resume.setContentAreaFilled(false);
		//resume.addActionListener(AsteroidsGame.getasteroidz());
		//resume.addMouseMotionListener(AsteroidsGame.getasteroidz());
		resume.setBackground(new Color(0,0,0));
		resume.setForeground(new Color(255,255,255));
/*Font f;
	try {
		f = Font.createFont(Font.TRUETYPE_FONT, new File("PetMe2Y.ttf"));//resume.setFont(new Font(f.getFontName(),1,20));
	} catch (FontFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};*/
	//resume.setText("RESUME");
	//resume.setText("RESUME");
	resume.setFont(new Font("Segoe UI Symbol",1,30));

	String names[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	for(String n:names){
		System.out.println(n);
	}
	System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
	this.setLayout(null);
	this.add(resume);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
