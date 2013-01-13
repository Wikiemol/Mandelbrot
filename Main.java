
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.lang.Math;

public class Main{
	int size = 500;
	double shifti = 0;
	double shiftr = 0;
	double si = 0;
	double sr = 0;
	double zoom = 100;
	double zoomn = 0;
	boolean red = false;
	boolean green = false;
	boolean blue = false;
	boolean gray = true;
	double leftBound = 0;
	double rightBound = 0;
	double topBound = 0;
	double bottomBound = 0;
    int in = 255;
	JFrame frame = new JFrame("MandelBrot Fractal");
	JPanel panel = new Panel();
	public static void main(String[] args){
		Main main = new Main();
		main.init();
	}
	
	public void init(){
		KeyAdapter keyHandler = new KeyHandler();
		frame.setVisible(true);
		frame.setSize(size,size);
		frame.getContentPane().add(panel);
		
		frame.addKeyListener(keyHandler);
	}

	public class Panel extends JPanel{
		@Override
		public void paintComponent(Graphics g){
			for(int i = 0; i <= size; i++){
				for(int r = 0; r <= size; r++){
					double re = (double)r; 
					double im = (double)i;
					ComplexNumber cn = new ComplexNumber((re - size/2 + sr)/zoom, (im - size/2 + si)/zoom);
					ComplexNumber z = new ComplexNumber();
					int iterations = 0;
					while(iterations < in && z.getReal() < 4 && z.getImaginary() < 4 && z.getReal() > -4 && z.getImaginary() > -4){
						z = z.multiply(z).add(cn);
						iterations++;
					}
					if(z.getReal() < 4 && z.getImaginary() < 4 && z.getReal() > -4 && z.getImaginary() > -4){
						g.setColor(Color.black);
						g.drawLine(r,i,r,i);
					}
					if(z.getReal() > 4 || z.getImaginary() > 4 || z.getReal() < -4 || z.getImaginary() < -4){
						if(gray){
							Color col = new Color(Math.abs(255 - iterations/(in/255)), Math.abs(255 - iterations/(in/255)),Math.abs(255 - iterations/(in/255)));
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(red){
							Color col = new Color(iterations/(in/255), 0, 0);
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(green){
							Color col = new Color(0, iterations/(in/255), 0);
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(blue){
							Color col = new Color(0, 0, iterations/(in/255));
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(blue && green){
							Color col = new Color(0, iterations/(in/255), iterations/(in/255));
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(blue && red){
							Color col = new Color(iterations/(in/255), 0, iterations/(in/255));
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						if(green && red){
							Color col = new Color(iterations/(in/255), iterations/(in/255), 0);
							g.setColor(col);
							g.drawLine(r,i,r,i);
						}
						
					}
					if(r == 0 && i == 0){
						leftBound = cn.getReal();
						bottomBound = cn.getImaginary();
					}
					if(r == size && i == size){
						rightBound = cn.getReal();
						topBound = cn.getImaginary();
					}
					
				}
					
			}
			g.setColor(Color.yellow);
			g.drawLine(size/2, size/2, size/2 + 5, size/2);
			g.drawLine(size/2, size/2, size/2, size/2 + 5);
			
			
			
		}
	
	}
	public class KeyHandler extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				shiftr += 50;
				sr = shiftr;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				shiftr -= 50;
				sr = shiftr;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				shifti += 50;
				si = shifti;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				shifti -= 50;
				si = shifti;
			}
			if(e.getKeyCode() == KeyEvent.VK_D){
				shiftr += 10;
				sr = shiftr;
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				shiftr -= 10;
				sr = shiftr;
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				shifti += 10;
				si = shifti;
			}
			if(e.getKeyCode() == KeyEvent.VK_W){
				shifti -= 10;
				si = shifti;
			}
			if(e.getKeyCode() == KeyEvent.VK_R){
				shifti = 0;
				shiftr = 0;
				sr = shiftr;
				si = shifti;
				zoom = 100;
			}
			if(e.getKeyCode() == KeyEvent.VK_X){
				
				zoom *= 2;
				shiftr *= 2;
				shifti *= 2;
				sr = shiftr;
				si = shifti;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_Z){
				zoom *= .5;
				shiftr *= .5;
				shifti *= .5;
				sr = shiftr;
				si = shifti;
			}
			if(e.getKeyCode() == KeyEvent.VK_1){
				gray = true;
				red = false;
				green = false;
				blue = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_2){
				gray = false;
				red = true;
				green = false;
				blue = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_3){
				gray = false;
				red = false;
				green = true;
				blue = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_4){
				gray = false;
				red = false;
				green = false;
				blue = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_5){
				gray = false;
				red = false;
				green = true;
				blue = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_6){
				gray = false;
				red = true;
				green = false;
				blue = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_J){
                ComplexNumber c = new ComplexNumber((rightBound + leftBound)/2, (bottomBound + topBound)/2);
				JuliaSet js = new JuliaSet(c, 500);
			}
			panel.repaint();
		}
	} 
}

//z = z^2 + c