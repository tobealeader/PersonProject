package Tetris;
import image.image;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*; 
class RightPanel extends JPanel{
	JButton button_start,button_stop,button_restart,button_one,button_two,button_three;
	JLabel label,label_degree;
	JTextField text_count;
	JPanel panel_picture;
	AudioClip aau1;
	public RightPanel(){
		button_start=new JButton("开始");
		button_start.setBounds(50,40,70,30);
		button_stop=new JButton("停止");
		button_stop.setBounds(50,80,70,30);
		button_restart=new JButton("重新开始");
		button_restart.setBounds(40,120,90,30);
		label=new JLabel("得分",JLabel.CENTER);
		text_count=new JTextField("0",5);
		text_count.setEditable(false); 
		label.setBounds(0,190,70,30);
		text_count.setBounds(70,190,70,30);
		//Font font=new Font("Monospaced",1,20);//字体格式,1代表样式(1是粗体，0是平常的),20是大小
		label_degree=new JLabel("难度等级");
		label_degree.setFont(new Font("Monospaced",1,20));
		label_degree.setForeground(Color.RED);
		label_degree.setBounds(10,260,140,30);
		button_one=new JButton("一级难度");
		button_one.setBounds(40,300,90,30);
		button_one.setFont(new Font("Monospaced",0,12));
		button_one.setForeground(Color.BLUE);
		button_two=new JButton("二级难度");
		button_two.setBounds(40,340,90,30);
		button_two.setFont(new Font("Monospaced",0,12));
		button_two.setForeground(Color.BLUE);
		button_three=new JButton("三级难度");
		button_three.setBounds(40,380,90,30);
		button_three.setFont(new Font("Monospaced",0,12));
		button_three.setForeground(Color.BLUE);
		setLayout(null);
		add(button_start);
		add(button_stop);
		add(button_restart);
		add(label);
		add(text_count);
		add(label_degree);
		add(button_one);
		add(button_three);
		add(button_two);
		/*File soundfile=new File("../image/001.wav");
		URI uri=soundfile.toURI();
		 URL url = null;
		 try { 
		 url = uri.toURL();
		 } catch (MalformedURLException e1) {
		 e1.printStackTrace();
		 }
		 AudioClip clip=Applet.newAudioClip(url);
		 */
		java.net.URL imgURL3 =image.class.getResource("001.wav");
		 AudioClip clip=Applet.newAudioClip(imgURL3);
		 clip.loop();
	}
	protected void paintComponent(Graphics g) {
		java.net.URL imgURL3 =image.class.getResource("clouds.jpg");
    	ImageIcon icon=new ImageIcon(imgURL3);
    	
        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this); 
	}
        
}
class LeftShowCanvas extends Canvas
{
    int maxrows,maxcols;    
    int unitSize; 
    int[][] unitState;
    RightPanel rp;
    int score;
    public LeftShowCanvas(RightPanel rp)
   {
        this.rp=rp;
        score=Integer.valueOf(rp.text_count.getText());
        maxrows=25;
        maxcols=15;
        unitSize=20;
        unitState=new int[maxrows][maxcols];
        initCanvas();
    }
    public void initCanvas() 
    {
        for (int i=0;i<maxrows;i++)
            for (int j=0;j<maxcols;j++)
                unitState[i][j]=0;
    }
    public void paint(Graphics g)
    {
        for (int i=0;i<maxrows;i++)
        {
            for (int j=0;j<maxcols;j++)
                drawUnit(i,j,unitState[i][j]); 
            if (i==3)
            {
                g.setColor(Color.RED);
                g.drawLine(0, (i + 1) * (unitSize + 1) - 1, maxcols
                        * (unitSize + 1) - 1, (i + 1) * (unitSize + 1) - 1);
            }
        }
    }
    public void drawUnit(int row, int col, int tag) 
    {
        unitState[row][col] = tag; 
        Graphics g=getGraphics(); 
        switch (tag)
        {
        case 0: 
            g.setColor(Color.GRAY);
            break;
        case 1:
            g.setColor(Color.RED);
            break;
        case 2:
            g.setColor(Color.BLUE);
            break;
        }
        g.fillRect(col * (unitSize + 1), row * (unitSize + 1), unitSize,
                unitSize);
    }

    public void deleteFullLine(int row) 
    {
        for (int j=0;j<maxcols;j++)
            if (unitState[row][j] != 2)
                return;

        for (int i=row;i>3;i--)
            for (int j=0;j<maxcols;j++)
            	unitState[i][j]=unitState[i-1][j];
        score++;
        rp.text_count.setText(String.valueOf(score));
        java.net.URL imgURL3 =image.class.getResource("remove.wav");
		 AudioClip clip=Applet.newAudioClip(imgURL3);
		 clip.play();
    }
}
class Block implements Runnable{
	 static final int type = 7, state = 4;
     static final int[][] patten = { 
     { 0x0f00, 0x4444, 0x0f00, 0x4444 },
             { 0x6600, 0x6600, 0x6600, 0x6600 },
             { 0x04e0, 0x0464, 0x00e4, 0x04c4 },
             { 0x08e0, 0x0644, 0x00e2, 0x044c },
             { 0x02e0, 0x0446, 0x00e8, 0x0c44 },
             { 0x0462, 0x006c, 0x0462, 0x006c },
             { 0x0264, 0x00c6, 0x0264, 0x00c6 } }; 
     private int blockType = -1; 
     private int blockState;
     private int row, col; 
     private int oldRow, oldCol;
     private int oldType = -1, oldState; 
     private int isfall; 
     private boolean end = false;
     private int isflag=1;
     private int xunhuan=1;
     private int degree_num;
     LeftShowCanvas lsc;
     java.net.URL imgURL3 =image.class.getResource("fail.wav");
 	AudioClip clip3=Applet.newAudioClip(imgURL3);
 	java.net.URL imgURL2 =image.class.getResource("dock.wav");
	AudioClip clip2=Applet.newAudioClip(imgURL2);
	java.net.URL imgURL1 =image.class.getResource("turn.wav");
	AudioClip clip1=Applet.newAudioClip(imgURL1);
     public void set_degree_num(int num){
    	degree_num=num;
     }
     public void set_xunhuan(int num){
    	 this.xunhuan=num;
     }
     public int get_xunhuan(){
    	 return this.xunhuan;
     }
     public void set_isfall(int num){
    	 this.isfall=num;
     }
     public void set_isflag(int num){
    	 this.isflag=num;
     }
     public Block(LeftShowCanvas lsc){
    	 this.lsc=lsc;
    	 row=0;
    	 col=3;
    	 oldRow=row;
    	 oldCol=col;
    	 degree_num=500;
     }
     public void reInit(){
    	 blockType=-1;
    	 isfall=1;
     }
     public void reInitRowCol(){
    	 row=0;
    	 col=3;
     }
     public void run(){
    	 lsc.requestFocusInWindow();
    	 while(!end){
    		 int blocktype = (int) (Math.random() * 100) % 7;
             drawBlock(blocktype);
    		 do{
    			 
    			 try{
    				 Thread.sleep(degree_num);
    			 }catch (InterruptedException e){
 
                 }
    			 while(isflag!=1);
    		 }while(fallMove());
    		 for(int j=0;j<lsc.maxcols;j++){
    			 if(lsc.unitState[3][j]==2){
    				 this.xunhuan=0;
    			 }
    		 }
    		 if(xunhuan==0){
    			 clip3.stop();
    			 clip3.play();
    		 }
    		 while(xunhuan!=1);
    	 }
     }
     public synchronized boolean isMove(int tag){
    	 int comIndex=0x8000;
    	 try{
    		 for(int i=row;i<row+4;i++){
        		 for(int j=col;j<col+4;j++){
        			 if((patten[blockType][blockState] & comIndex)!=0){
        				 if(tag==0&&(j==0||lsc.unitState[i][j-1]==2))
        					 return false;
        				 else if(tag==1&&(j==lsc.maxcols-1||lsc.unitState[i][j+1]==2))
        					 return false;
        				 else if(tag==2&&(i==lsc.maxrows-1||lsc.unitState[i+1][j]==2))
        					 return false;
        				 else if(tag==3&&(i > lsc.maxrows - 1 || j < 0
                                 || j > lsc.maxcols - 1 || lsc.unitState[i][j] == 2))
        					 return false;
        			 }
        			 comIndex=comIndex>>1;
        		 }
        	 }
    	 }catch (ArrayIndexOutOfBoundsException e){
    		 
    	 }
    	 
    	 return true;
     }
     public synchronized void drawBlock(int blockType){
    	 if (this.blockType != blockType)
             blockState = (int) (Math.random() * 100) % 4; 
    	 this.blockType=blockType;
    	 if(!isMove(3)){
    		 this.blockState=oldState;
    		 this.blockType=oldType;
    		 return;
    	 }
    	 int comIndex = 0x8000;
         if (this.oldType != -1){
             for (int i = oldRow; i < oldRow + 4; i++)
                 for (int j = oldCol; j < oldCol + 4; j++)
                 {
                    if ((patten[oldType][oldState] & comIndex) != 0
                            && lsc.unitState[i][j] == 1)
                        lsc.unitState[i][j]=0;
                    comIndex = comIndex >> 1;
                }
        }
         comIndex = 0x8000;
         try{
        	 for (int i = row; i < row + 4; i++)
                 for (int j = col; j < col + 4; j++)
                 {
                     if ((patten[blockType][blockState] & comIndex) != 0){
                         if (isfall == 1)
                             lsc.unitState[i][j]=1; 
                         else if (isfall == 0)
                         {
                             lsc.unitState[i][j]=2;
                             
                             lsc.deleteFullLine(i);
                             
                         }
                     }
                     comIndex = comIndex >> 1;
                 }
         }catch (ArrayIndexOutOfBoundsException e){
        	 
         }
         Image image; 
         image=lsc.createImage(lsc.getWidth(),lsc.getHeight());
         Graphics g=image.getGraphics();
         lsc.paint(g);
         g.drawImage(image, 0, 0, lsc);            
         if (isfall == 0) 
         {
        	 
             reInit();
             reInitRowCol();
         }
         oldRow = row;
         oldCol = col;
         oldType = blockType;
         oldState = blockState;
     }
     public boolean fallMove(){
    	 
    	 if(this.blockType!=-1){
    		 if(isMove(2)){
    			 row+=1;
    			 
    			 drawBlock(blockType);
    			 return true;
    		 }
    		 else{
    			 clip2.stop();
    			 clip2.play();
    			 isfall=0;
    			 drawBlock(blockType);
                 return false;
    		 }
    	 }
    	 return false;
     }
     public void leftMove(){
         if (this.blockType != -1 && isMove(0)){
             col -= 1;
             drawBlock(blockType);
         }
     }
     public void rightMove(){
         if (this.blockType != -1 && isMove(1)){
             col += 1;
             drawBlock(blockType);
         }
     }
     public void turnMove(){
    	 if (this.blockType != -1)
         {
             blockState = (blockState + 1) % 4;
             if (isMove(3)){
            	 clip1.stop();
    			 clip1.play();
            	 drawBlock(blockType);
             }
             else
                 blockState = (blockState + 3) % 4;
         }
     }
}
class MyActionListener implements ActionListener
{
    RightPanel rp;
    Block bl;
    LeftShowCanvas lsc;
    static int flag=1;
    public MyActionListener(RightPanel rp, Block bl, LeftShowCanvas lsc)
    {
        this.rp = rp;
        this.bl = bl;
        this.lsc = lsc;
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(rp.button_start))
        {
        	if(flag==1){
        		bl.set_isfall(1);
            	Thread th=new Thread(bl);
                th.start();
                rp.button_start.setEnabled(false);
        	}
        	else{
        		flag=1;
        		bl.set_isflag(1);
        		rp.button_start.setEnabled(false);
        		rp.button_stop.setEnabled(true);
        	}
        }
        else if(e.getSource().equals(rp.button_restart)){
        	
        	lsc.initCanvas();
        	if(bl.get_xunhuan()==0){
            	bl.set_xunhuan(1);
            }
        	Random ra=new Random();
   		 	int i=ra.nextInt(7);
   		 	bl.set_isflag(1);
   		 	bl.set_isfall(1);
        	bl.reInitRowCol();
            bl.drawBlock(i);
            lsc.requestFocusInWindow(); 
            rp.text_count.setText(String.valueOf("0"));
            lsc.score=0;
            rp.button_start.setEnabled(false);
            rp.button_stop.setEnabled(true);
        }
        else if(e.getSource().equals(rp.button_stop)){
        	bl.set_isflag(0);
        	flag=0;
        	rp.button_start.setEnabled(true);
        	rp.button_stop.setEnabled(false);
        }
        else if(e.getSource().equals(rp.button_one)){
        	bl.set_degree_num(500);
        }
        else if(e.getSource().equals(rp.button_two)){
        	bl.set_degree_num(300);
        }
        else if(e.getSource().equals(rp.button_three)){
        	bl.set_degree_num(150);
        }
        lsc.requestFocusInWindow();
    }
}
class MyKeyAdapter extends KeyAdapter{
	Block bl;
	public MyKeyAdapter(Block bl){
		this.bl=bl;
	}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			//System.out.println("++");
			bl.leftMove();
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			bl.rightMove();
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			bl.fallMove();
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			bl.turnMove();
		}
	}
}
public class Tetris extends JFrame{
	RightPanel panel;
	LeftShowCanvas canvas;
	
	Block bl;
	Tetris(){
		super("俄罗斯方块");
		
		this.setSize(508,554);
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width/2; // 获取屏幕的宽
        int screenHeight = screenSize.height/2; // 获取屏幕的高
        int height = this.getHeight();
        int width = this.getWidth();
        this.setLocation(screenWidth-width/2, screenHeight-height/2);
        
        panel=new RightPanel();
        panel.setBounds(315,0,193,564);
        canvas=new LeftShowCanvas(panel);
        canvas.setBounds(0,0,315,564);
        bl=new Block(canvas);
        
        panel.button_start.addActionListener(new MyActionListener(panel,bl,canvas));
        panel.button_restart.addActionListener(new MyActionListener(panel,bl,canvas));
        panel.button_stop.addActionListener(new MyActionListener(panel,bl,canvas));
        panel.button_one.addActionListener(new MyActionListener(panel,bl,canvas));
        panel.button_two.addActionListener(new MyActionListener(panel,bl,canvas));
        panel.button_three.addActionListener(new MyActionListener(panel,bl,canvas));
        canvas.addKeyListener(new MyKeyAdapter(bl));
              
        this.setLayout(null);
        this.add(canvas);
        this.add(panel);        
        setResizable(false);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
	}
	public static void main(String[] args) {
		new Tetris();
	}

}
