package Cat_catch_Mouse;
import image.image;
import java.io.IOException;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
class struct{
	int x,y;
	struct(int x,int y){
		this.x=x;
		this.y=y;
	}
}
class Queue   
{ 
private int maxSize; 
private struct[] queArray;
private int front;  
private int rear; 
private int nItems;  
public Queue(int s)           
   { 
   maxSize = s; 
   queArray = new struct[maxSize]; 
   front = 0; 
   rear = -1; 
   nItems = 0; 
   } 
public void push(struct j)    
   { 
	rear++;
   queArray[rear] = j;    
   nItems++;      
   } 
public struct  remove()        
   { 
	
   struct temp = queArray[front]; 
   front++;
   nItems--;                      
   return temp; 
   } 
 
public struct Front()       
   { 	
   return queArray[front]; 
   } 
public boolean empty()    
   { 
   return (nItems==0); 
   } 
public boolean isFull()     
   { 
   return (nItems==maxSize); 
   } 
public int size()            
   { 
   return nItems; 
   }  
}  
class Next_Move{
	BackgroundPanel lsc;
	struct p1,p2;
	int[][] dir={{0,1},{0,-1},{1,0},{-1,0}}; 
	struct[][] pre;
	int len;
	int[][] map;
	struct[] cat;
	Next_Move(BackgroundPanel lsc,int mrow,int mcol,int mrows,int mcols){
		this.lsc=lsc;
		this.p1=new struct(mrow,mcol);
		this.p2=new struct(mrows,mcols);
		pre=new struct[100][100];
		cat=new struct[100];
		map=new int[18][18];
		for(int i=0;i<18;i++){
			for(int j=0;j<18;j++){
				map[i][j]=lsc.unitState[i][j];
			}
		}
		len=0;
	}
	void find(struct now)  
	{  
		if(now.x==p2.x&&now.y==p2.y){
			cat[len++]=new struct(now.x,now.y);
			return;
		}
	    find(pre[now.x][now.y]); 
	    cat[len++]=new struct(now.x,now.y);
	    //System.out.println(now.x+" "+now.y);
	}
	void get(){
		try{
			lsc.xx=cat[1].x;
			lsc.yy=cat[1].y;
		}catch(NullPointerException e){
			
		}
		
	}
	void bfs()  
	{  
		Queue q=new Queue(1000);		
		struct st=new struct(0,0);
		struct ed=new struct(0,0);	
	    int i;  
	    q.push(p2);  
	    map[p2.x][p2.y]=1;
	    while(!q.empty())  
	    {  
	    	
	        st=q.Front();  
	        q.remove();  
	        if(st.x==p1.x &&st.y==p1.y){	        	
	        	find(st);
	        	get();
	        	break;
	        }
	        for(i=0;i<4;i++)  
	        {  
	            ed.x=st.x+dir[i][0];  
	            ed.y=st.y+dir[i][1];  
	            if(check(ed.x,ed.y)==false)  
	                continue;  
	            map[ed.x][ed.y]=1;  
	            pre[ed.x][ed.y]=st;  
	            q.push(new struct(ed.x,ed.y));  
	        }  
	    }
	}  
	public boolean check(int a,int b){
		if(a>=0&&a<=lsc.maxrows-1&&b>=0&&b<=lsc.maxcols-1&&map[a][b]==0){
			return true;
		}
		return false;
	}
}
class Mouse implements Runnable{
	int row,col;
	int rows,cols;
	BackgroundPanel lsc;
	Next_Move nm;
	int flag;
	java.net.URL imgURL3 =image.class.getResource("turn.wav");
	AudioClip clip=Applet.newAudioClip(imgURL3);
	Mouse(BackgroundPanel lsc){
		this.lsc=lsc;
		row=0;
		col=1;
		rows=10;
		cols=8;
		flag=1;
	}
	public void init(){
		row=0;
		col=1;
		rows=10;
		cols=8;
		lsc.x=0;
		lsc.y=1;
		lsc.xx=10;
		lsc.yy=8;
		flag=1;
		Image image; 
		image=lsc.createImage(lsc.getWidth(),lsc.getHeight());
		Graphics g=image.getGraphics();
		lsc.repaint();
		g.drawImage(image, 0, 0, lsc);
	}
	public void run(){
		lsc.requestFocusInWindow();
		init();		
			do{
	   			 try{
	   				 Thread.sleep(10);
	   			 }catch (InterruptedException e){

	             }
	   			Image image; 
	   			image=lsc.createImage(lsc.getWidth(),lsc.getHeight());
	   			Graphics g=image.getGraphics();
	   			lsc.repaint();
	   			g.drawImage(image, 0, 0, lsc);
	   			while(flag==0);
	   			if(lsc.x==lsc.xx&&lsc.y==lsc.yy){
	   				java.net.URL imgURL3 =image.class.getResource("fail.wav");
	   				AudioClip clip=Applet.newAudioClip(imgURL3);
	   				clip.play();
	   				System.out.println(lsc.x+" "+lsc.y);
	   				break;
	   			}
	   			if(lsc.x==17&&lsc.y==16){
	   				java.net.URL imgURL3k =image.class.getResource("6365.wav");
	   				AudioClip clipk=Applet.newAudioClip(imgURL3k);
	   				clipk.play();
	   				break;
	   			}
	   		 	}while(true);
	}
	public void upMove(){
		if(isMove(0)){
			row-=1;
			Move();
		}
	}
	public void downMove(){
		if(isMove(1)){
			row+=1;
			Move();
		}
	}
	public void leftMove(){
		if(isMove(2)){
			col-=1;
			Move();
		}
	}
	public void rightMove(){
		if(isMove(3)){
			col+=1;
			Move();
		}
	}
	public synchronized boolean isMove(int flagg){
		if(flag==1&&flagg==0&&row>=1&&lsc.unitState[row-1][col]==0&&!(rows==row&&col==cols)&&!(row==17&&col==16))
			return true;
		else if(flag==1&&flagg==1&&row<=16&&lsc.unitState[row+1][col]==0&&!(rows==row&&col==cols)&&!(row==17&&col==16))
			return true;
		else if(flag==1&&flagg==2&&col>=1&&lsc.unitState[row][col-1]==0&&!(rows==row&&col==cols)&&!(row==17&&col==16))
			return true;
		else if(flag==1&&flagg==3&&col<16&&lsc.unitState[row][col+1]==0&&!(rows==row&&col==cols)&&!(row==17&&col==16))
			return true;
		return false;
		
	}
	public synchronized void Move(){
		lsc.x=row;lsc.y=col;
		nm=new Next_Move(lsc,row,col,rows,cols);
		nm.bfs();
		rows=lsc.xx;
		cols=lsc.yy;
		//java.net.URL imgURL3 =image.class.getResource("turn.wav");
		 //AudioClip clip=Applet.newAudioClip(imgURL3);
		clip.stop();
		 clip.play();
	}
}
class BackgroundPanel extends JPanel {  
	int maxrows,maxcols,unitSize;
	int h;
	int x,y;
	int xx,yy;
	private static Color color = new Color(108, 108, 183);
	private static Font font = new Font("DialogInput", 1, 50);
	private static String s = "Stop";
	int[][] unitState={{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					   {1,0,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1},
					   {1,0,1,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1},
					   {1,0,1,0,1,1,1,1,0,0,0,0,0,1,0,1,0,1},
					   {1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,1},
					   {1,0,0,1,1,0,1,0,0,1,0,0,0,1,0,0,0,1},
					   {1,0,0,0,0,0,1,1,0,1,0,1,0,1,0,1,0,1},
					   {1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,1,0,1},
					   {1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1},
					   {1,0,1,0,1,0,1,1,0,1,0,1,1,1,1,0,0,1},
					   {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
					   {1,0,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1},
					   {1,0,1,1,1,0,0,0,0,1,0,0,1,0,1,0,0,1},
					   {1,0,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,1},
					   {1,0,1,1,1,0,0,1,1,1,1,0,1,0,0,1,0,1},
					   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
					   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},};
    public BackgroundPanel() {
    	maxrows=18;
    	maxcols=18;
    	unitSize=20;
    	x=0;
    	y=1;
    	xx=10;
    	yy=8;
    	h=0;
    }
    public void addItem(){
    	h=1;
    }
    public void deleteItem(){
    	h=0;
    }
    public void paint(Graphics g) {
    	java.net.URL imgURL3 =image.class.getResource("clouds.jpg");
    	ImageIcon icon=new ImageIcon(imgURL3);
        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        java.net.URL imgURL2 =image.class.getResource("1.png");
        ImageIcon icon_mouse=new ImageIcon(imgURL2);
        g.drawImage(icon_mouse.getImage(), y*20, x*20, 20, 20, this);
        java.net.URL imgURL1 =image.class.getResource("2.png");
        ImageIcon icon_cat=new ImageIcon(imgURL1);
        g.drawImage(icon_cat.getImage(),yy*20 ,xx*20 , 20, 20, this);
        for (int i=0;i<maxrows;i++)
        {
        	for (int j=0;j<maxcols;j++){
        		g.setColor(Color.GRAY);
                if(unitState[i][j]==1)
                g.fillRect(j * unitSize , i * unitSize, unitSize,
                        unitSize);
        	}
        }
        if(h==1){
        	g.setFont(font);
            g.setColor(color);
            g.drawString(s, 130, 160);
        }
        
    }
}
class MyKeyAdapter extends KeyAdapter{
    Mouse bl;
    JMenuItem it,it1;
    public MyKeyAdapter(Mouse bl,JMenuItem it,JMenuItem it1)
    {
        this.bl = bl;
        this.it=it;
        this.it1=it1;
    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            bl.leftMove();
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            bl.rightMove();
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            bl.downMove();
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            bl.upMove();
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
        	if(bl.flag==1){
        		bl.flag=0;
        		it.setEnabled(true);
        		it1.setEnabled(false);
        	}  		
        	else{
        		bl.flag=1; 
        		it.setEnabled(false);
        		it1.setEnabled(true);
        	}       		    	
        }
    }
}
class MyActionListener implements ActionListener{
	BackgroundPanel lsc;
	Mouse bl;
	JMenuItem it,it1,it2;
	MyActionListener(BackgroundPanel lsc,Mouse bl,JMenuItem it,JMenuItem it1,JMenuItem it2){
		this.lsc=lsc;
		this.bl=bl;
		this.it=it;
		this.it1=it1;
		this.it2=it2;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("start")){
			if(bl.flag==1){
				Thread th=new Thread(bl);
				th.start();
			}else{
				lsc.deleteItem();
				bl.flag=1;
			}
			it.setEnabled(false);
			it1.setEnabled(true);
		}
		else if(e.getActionCommand().equals("stop")){
			lsc.addItem();
			bl.flag=0;
			it1.setEnabled(false);
			it.setEnabled(true);
		}
		else if(e.getActionCommand().equals("restart")){
			lsc.h=0;
			Thread th=new Thread(bl);
			th.start();
			it.setEnabled(false);
			it1.setEnabled(true);
		}
		else if(e.getActionCommand().equals("quit")){
			System.exit(0);
		}
		else if(e.getActionCommand().equals("about")){
			String msg = "洪东楗的作品，请指点！";
			String title = "Message Dlg";
			//int type = JOptionPane.INFORMATION_MESSAGE;
			JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
		}
		lsc.requestFocusInWindow();
	}
}
public class Cat_catch_Mouse extends JFrame{
	BackgroundPanel p;
	Mouse m;
	Cat_catch_Mouse(){		
		super("map"); 
		setSize(366, 409); 
		JMenuBar mb=new JMenuBar();
		JMenu mm=new JMenu("Menu");
		JMenu mmm=new JMenu("Help");
		JMenuItem it=new JMenuItem("start");
		JMenuItem it1=new JMenuItem("stop");
		it1.setEnabled(false);
		JMenuItem it2=new JMenuItem("restart");
		JMenuItem it3=new JMenuItem("quit");
		mm.add(it);
		mm.add(it1);
		mm.add(it2);
		mm.add(it3);
		JMenuItem it4=new JMenuItem("about");
		mmm.add(it4);
		mb.add(mm);
		mb.add(mmm);
		setJMenuBar(mb);
		p = new BackgroundPanel();
		add(p);
		m=new Mouse(p);
		it.addActionListener(new MyActionListener(p,m,it,it1,it2));
		it1.addActionListener(new MyActionListener(p,m,it,it1,it2));
		it2.addActionListener(new MyActionListener(p,m,it,it1,it2));
		it3.addActionListener(new MyActionListener(p,m,it,it1,it2));
		it4.addActionListener(new MyActionListener(p,m,it,it1,it2));
		p.addKeyListener(new MyKeyAdapter(m,it,it1));
		java.net.URL imgURL3 =image.class.getResource("001.wav");
		AudioClip clip=Applet.newAudioClip(imgURL3);
		clip.loop();
		setVisible(true);  
		this.setLocationRelativeTo(null); //设置窗口居中
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Cat_catch_Mouse();
	}
}
