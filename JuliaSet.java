import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JuliaSet{
	ComplexNumber c;
	double shiftr = 0;
    double shifti = 0;
    double zoom = 100;
	int size;
    JPanel jp = new JuliaPanel();
    boolean red = false;
	boolean green = false;
	boolean blue = false;
	boolean gray = true;
    int in = 255;
	public JuliaSet(ComplexNumber cn, int s){
		size = s;
		c = cn;
		go();
	}
	
	public void go(){
        
        KeyAdapter keyHandler = new KeyHandler();
		JFrame frame = new JFrame("JuliaSet for " + c.getNumber());
		frame.setVisible(true);
		frame.setBounds(size, 0, size, size);
        frame.getContentPane().add(jp);
        frame.addKeyListener(keyHandler);
	}
    
    public class JuliaPanel extends JPanel{
        
        public void paintComponent(Graphics g){
            for(int i = 0; i <= size; i++){
                for(int r = 0; r <= size; r++){
                    double re = (double)r;
                    double im = (double)i;
                    int iterations = 0;
                    ComplexNumber z = new ComplexNumber((re - size/2 + shiftr)/zoom, (im - size/2 + shifti)/zoom);
                    while(iterations < in && z.getReal() < 4 && z.getImaginary() < 4 && z.getReal() > -4 && z.getImaginary() > -4){
                        z = z.multiply(z).add(c);
                        iterations++;
                    }
                    if(z.getReal() < 4 && z.getImaginary() < 4 && z.getReal() > -4 && z.getImaginary() > -4){
						g.setColor(Color.black);
						g.drawLine(r,i,r,i);
					}
                    if(z.getReal() > 4 || z.getImaginary() > 4 || z.getReal() < -4 || z.getImaginary() < -4){
						//Color col = new Color(Math.abs(255 - iterations), Math.abs(255 - iterations),Math.abs(255 - iterations));
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
				
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				shiftr -= 50;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				shifti += 50;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				shifti -= 50;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_D){
				shiftr += 10;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				shiftr -= 10;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				shifti += 10;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_W){
				shifti -= 10;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_R){
				shifti = 0;
				shiftr = 0;
				
				zoom = 100;
			}
			if(e.getKeyCode() == KeyEvent.VK_X){
				
				zoom *= 2;
				shiftr *= 2;
				shifti *= 2;
				
				
			}
			if(e.getKeyCode() == KeyEvent.VK_Z){
				zoom *= .5;
				shiftr *= .5;
				shifti *= .5;
				
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

            jp.repaint();
        }
    }
}