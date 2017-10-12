package graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logic.DataCreator;
import logic.GridTranslator;

public class Grid extends JFrame {

	JMenuBar menubar;
	JMenu menu1;
	
	JMenuItem new_data, avg, my_avg;
	boolean avg_visible, my_avg_visible;
	
	
	
	ArrayList<Point> pointlist;
	Point avgPoint;
	Point line_start, line_end;
		double alpha, beta, my_alpha, my_beta, o_alpha, o_beta;
	
	DataCreator dc;
	GridTranslator gt;
	
	public Grid() {
		avg_visible = my_avg_visible = false;
		initGUI();
		pointlist = new ArrayList<Point>();

		gt = new GridTranslator(1000, 500, 100, 100);
		
		createDatas();
	}
	
	private void createDatas ()
	{
		dc = new DataCreator();
		for (int i=0; i<100; i++)
			insetPoint(gt.getTransformedXY(i, dc.getNormalDistributedY(i)));

	}
	private void initGUI() {
	
		setSize(1000, 500);
		
		menubar = new JMenuBar();
		menu1 = new JMenu("연산");
		
		avg = new JMenuItem("데이터 평균점 표시");
		avg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				avg_visible = !avg_visible;
				repaint();
			}
		});
		menu1.add(avg);
		
		new_data = new JMenuItem("새로운 데이터");
		new_data.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pointlist.removeAll(pointlist);
				createDatas();
				repaint();
			}
		});
		menu1.add(new_data);
		
		
		menubar.add(menu1);
		
		my_avg = new JMenuItem("직관적 도출식");
		my_avg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				my_avg_visible = !my_avg_visible;
				repaint();
			}
		});
		menu1.add(my_avg);
		

		
		setJMenuBar(menubar);
		
		createAndShowGUI();
	}


	
    public void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        setVisible(true);
    }

    
    public void insetPoint (Point newpoint)
    {
    	pointlist.add(newpoint);
    	repaint();
    }

    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	paintComponents(g);
    }
    @Override
    public void paintComponents(Graphics g) {
    	System.out.println("?");
    	for (Point point: pointlist) {
			g.drawString("x", point.x, point.y);
		}
    	
    	Point origin_start = gt.getTransformedXY(0,0);
    	Point origin_end = gt.getTransformedXY(100, 100*dc.alpha);
    	g.drawLine(origin_start.x, origin_start.y, origin_end.x, origin_end.y);
    	
		int x = 0,y = 0, xy=0, xx=0;
		
    	if(!pointlist.isEmpty())
    	{
    		for (Point point: pointlist) {
    			x+=point.x;
    			y+=point.y;
    			xy+=point.x*point.y;
    			xx+=point.x*point.x;
    		}
    		
    		//avg Point
    		x /= pointlist.size();
    		y /= pointlist.size();
        	xy /= pointlist.size();
        	xx /= pointlist.size();
        	

    		avgPoint = new Point(x,y);
        	beta = xy - x*y;
        	beta /= xx - x*x;
        	alpha = y - beta*x;
    		
    	}
    	
    	if(my_avg_visible)
    	{
    		int cnt = 0;
    		my_alpha=my_beta=0;
    		for (Point point: pointlist) {
    			double result = point.x - x;
    			
    			if(result ==0 )
    				cnt ++;
    			else
    				my_beta+= (point.y-y)/result;
    			}
    		
    		my_beta /=pointlist.size()-cnt;
    		my_alpha = y - my_beta*x;
    		
    		g.setColor(Color.blue);
    		g.drawLine(0, (int)my_alpha, 1000, (int)(my_alpha + 1000*my_beta));
    	}
    	
    	if(avgPoint!=null && avg_visible)
    	{
    		g.setColor(Color.red);
    		g.drawString("x", avgPoint.x, avgPoint.y);
    		

    		g.setColor(Color.green);
    		g.drawLine(0, (int)alpha, 1000, (int)(alpha + 1000*beta));
    	}
    }

}
