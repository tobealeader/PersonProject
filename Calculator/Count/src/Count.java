
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Jinzhi extends JPanel implements ActionListener{
	R4 c;
	Count cc;
	Jinzhi(R4 c,Count cc){
		setLayout(new GridLayout());
		this.setBackground(Color.WHITE);
		setBounds(10,40,260,30);
		ButtonGroup bg = new ButtonGroup();//创建按钮组
		JRadioButton jrb1 = new JRadioButton("十六进制");
		jrb1.setBounds(0,0,75,30);
		JRadioButton jrb2 = new JRadioButton("十进制"); 
		jrb2.setSelected(true);
		jrb2.setBounds(75,0,60,30);
		JRadioButton jrb3 = new JRadioButton("八进制");
		jrb3.setBounds(135,0,60,30);
		JRadioButton jrb4 = new JRadioButton("二进制");
		jrb4.setBounds(195,0,60,30);
		jrb1.addActionListener(this);
		jrb2.addActionListener(this);
		jrb3.addActionListener(this);
		jrb4.addActionListener(this);
		jrb1.setMargin(new Insets(0, 0, 0, 0));
		jrb2.setMargin(new Insets(0, 0, 0, 0));
		jrb3.setMargin(new Insets(0, 0, 0, 0));
		jrb4.setMargin(new Insets(0, 0, 0, 0));
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
		bg.add(jrb4);
		JPanel p=new JPanel();
		p.setLayout(null);
		p.add(jrb1);
		p.add(jrb2);
		p.add(jrb3);
		p.add(jrb4);
		add(p);
		this.c=c;
		this.cc=cc;
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("十六进制")){
			cc.num=16;
			for(int i=0;i<5;i++)
				for(int j=0;j<3;j++){
					c.bt[i][j].setEnabled(true);
				}
			c.bt[4][3].setEnabled(true);
			c.bt[4][4].setEnabled(true);
			c.bt[4][5].setEnabled(true);
		}
		else if(e.getActionCommand().equals("十进制")){
			cc.num=10;
			for(int i=0;i<4;i++)
				for(int j=0;j<3;j++){
					c.bt[i][j].setEnabled(true);
				}
			for(int i=0;i<6;i++)
				c.bt[4][i].setEnabled(false);
		}
		else if(e.getActionCommand().equals("八进制")){
			cc.num=8;
			for(int i=0;i<4;i++)
				for(int j=0;j<3;j++){
					c.bt[i][j].setEnabled(true);
				}
			c.bt[0][1].setEnabled(false);
			c.bt[0][2].setEnabled(false);
			for(int i=0;i<6;i++)
				c.bt[4][i].setEnabled(false);
		}
		else if(e.getActionCommand().equals("二进制")){
			cc.num=2;
			for(int i=0;i<4;i++)
				for(int j=0;j<3;j++){
					c.bt[i][j].setEnabled(false);
				}
			c.bt[2][0].setEnabled(true);
			for(int i=0;i<3;i++)
				c.bt[3][i].setEnabled(true);
			for(int i=0;i<6;i++)
				c.bt[4][i].setEnabled(false);
		}
	}
}
class Zi extends JPanel{
	Zi(){
		setBounds(275,40,195,30);
		setLayout(new GridLayout(1,4));
		ButtonGroup bg = new ButtonGroup();//创建按钮组
		JRadioButton jrb1 = new JRadioButton("四字");
		JRadioButton jrb2 = new JRadioButton("双字"); 
		JRadioButton jrb3 = new JRadioButton("单字");
		JRadioButton jrb4 = new JRadioButton("字节");
		jrb1.setMargin(new Insets(0, 0, 0, 0));
		jrb2.setMargin(new Insets(0, 0, 0, 0));
		jrb3.setMargin(new Insets(0, 0, 0, 0));
		jrb4.setMargin(new Insets(0, 0, 0, 0));
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
		bg.add(jrb4);
		add(jrb1);
		add(jrb2);
		add(jrb3);
		add(jrb4);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
}
class Inv_Hyp extends JPanel{
	Inv_Hyp(){
		setBounds(10,75,100,30);
		setLayout(new GridLayout(1,2,8,5));
		JCheckBox cb1=new JCheckBox("Inv");
		JCheckBox cb2=new JCheckBox("Hyp");
		cb1.setMargin(new Insets(0, 0, 0, 0));
		cb2.setMargin(new Insets(0, 0, 0, 0));
		add(cb1);
		add(cb2);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
}
class Kong1 extends JPanel{
	Kong1(){
		setBounds(120,75,27,30);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}
}
class Kong2 extends JPanel{
	Kong2(){
		setBounds(160,75,27,30);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}
}
class Kong3 extends JPanel implements ActionListener{
	Count c;
	Kong3(Count c){
		setBounds(260,75,210,30);
		setLayout(new GridLayout(1,3,4,4));
		JButton bt1=new JButton("Backspce");
		JButton bt2=new JButton("CE");
		bt2.addActionListener(this);
		JButton bt3=new JButton("C");
		bt3.addActionListener(this);
		bt1.setMargin(new Insets(0,0,0,0));
		bt2.setMargin(new Insets(0,0,0,0));
		bt3.setMargin(new Insets(0,0,0,0));
		add(bt1);
		add(bt2);
		add(bt3);
		this.c=c;
	}
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s=="C"){
			c.s="";
			c.text.setText("0");
			c.k1=0;
			c.k2=0;
		}
		else if(s=="CE"){
			c.s=c.s.substring(0,c.s.length()-1);
			c.text.setText(c.s);
		}
	}
}
class R1 extends JPanel{
	R1(){
		setLayout(new GridLayout(5,1,4,4));
		setBounds(10,110,32,150);
		JButton bt1=new JButton("Sta");
		bt1.setSize(50,40);
		JButton bt2=new JButton("Ava");
		JButton bt3=new JButton("Sum");
		JButton bt4=new JButton("S");
		JButton bt5=new JButton("Dat");
		bt1.setMargin(new Insets(0, 0, 0, 0));
		bt2.setMargin(new Insets(0, 0, 0, 0));
		bt3.setMargin(new Insets(0, 0, 0, 0));
		bt4.setMargin(new Insets(0, 0, 0, 0));
		bt5.setMargin(new Insets(0, 0, 0, 0));
		add(bt1);
		add(bt2);
		add(bt3);
		add(bt4);
		add(bt5);
	}
}
class R2 extends JPanel implements ActionListener{
	Count c;
	R2(Count c){
		setBounds(50,110,100,150);
		setLayout(new GridLayout(5,3,4,4));
		JButton[][] bt=new JButton[5][3];
		String s[][]={{"F-E","(",")"},
				{"dns","Exp","In"},
				{"sin","x^y","log"},
				{"cos","x^3","n!"},
				{"tan","x^2","1/x"}};
		for(int i=0;i<5;i++)
			for(int j=0;j<3;j++){
				bt[i][j]=new JButton(s[i][j]);
				bt[i][j].setMargin(new Insets(0,0,0,0));
				bt[i][j].addActionListener(this);
				add(bt[i][j]);
			}
		this.c=c;
	}

	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s=="("||s==")"){
			if(s=="(")
				c.k1++;
			if(!(s==")"&&c.k2+1>c.k1)){
				if(s==")")
				c.k2++;
				if(c.flag==1)
					c.s=c.s+s;
				else{
					c.s=s;
					c.flag=1;
				}					
				c.text.setText(c.s);
			}
		}
	}
}
class R3 extends JPanel{
	R3(){
		setLayout(new GridLayout(5,1,4,4));
		setBounds(160,110,30,150);
		JButton bt1=new JButton("MC");
		JButton bt2=new JButton("MR");
		JButton bt3=new JButton("MS");
		JButton bt4=new JButton("M+");
		JButton bt5=new JButton("pi");
		bt1.setMargin(new Insets(0, 0, 0, 0));
		bt2.setMargin(new Insets(0, 0, 0, 0));
		bt3.setMargin(new Insets(0, 0, 0, 0));
		bt4.setMargin(new Insets(0, 0, 0, 0));
		bt5.setMargin(new Insets(0, 0, 0, 0));
		add(bt1);
		add(bt2);
		add(bt3);
		add(bt4);
		add(bt5);
	}
}
class R4 extends JPanel implements ActionListener{
	Count c;
	char[] str=new char[1000];
	float[] val=new float[1000];
	char[] fu=new char[1000];
	JButton[][] bt;
	int n,i,up;
	R4(Count c){
		setBounds(270,110,200,150);
		setLayout(new GridLayout(5,3,4,4));
		bt=new JButton[5][6];
		String s[][]={{"7","8","9","/","Mod","And"},
				{"4","5","6","*","Or","Xor"},
				{"1","2","3","-","Lsh","Not"},
				{"0","+/-",".","+","=","Int"},
				{"A","B","C","D","E","F"}};
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++){
				bt[i][j]=new JButton(s[i][j]);
				bt[i][j].setMargin(new Insets(0,0,0,0));
				bt[i][j].addActionListener(this);
				add(bt[i][j]);
			}
		for(int i=0;i<6;i++){
			bt[4][i].setEnabled(false);
		}
		this.c=c;
	}
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s=="0"||s=="1"||s=="2"||s=="3"||s=="4"||s=="5"||s=="6"||s=="7"||s=="8"||s=="9"||s=="A"
				||s=="B"||s=="C"||s=="D"||s=="E"||s=="F"){
			if(c.flag==1){
				c.s=c.s+s;
				c.text.setText(c.s);
			}
			else{
				c.flag=1;
				c.s=s;
				c.text.setText(c.s);
			}
		}
		else if(s=="+"||s=="-"||s=="*"||s=="/"){
			if(c.flag==1){
				c.s=c.s+s;
				c.text.setText(c.s);
			}
		}
		else if(s=="."){
			if(c.flag==1){
				c.s=c.s+s;
				c.text.setText(c.s);
			}
		}
		else if(s=="="){
			c.s=c.s+s;
			n=0;
			for(i=0;i<c.s.length();i++){
				char k=c.s.charAt(i);
				str[n++]=k;
			}
			int a=0,j=0;
			float b=0,d=0,g=c.num;
			n=0;
		    for(i=0;str[i]!=0&&i<1000;i++)
		    {
		        if(str[i]>='0'&&str[i]<='9'||(str[i]>='A'&&str[i]<='Z'))
		        {
		             
		            while((str[i]>='0'&&str[i]<='9')||(str[i]>='A'&&str[i]<='Z'))
		            {
		            	if(str[i]>='A'&&str[i]<='Z'){
		            		 b=(str[i]-'A'+10)+b*c.num;
				                i++;
		            	}
		            	else{
		            		 b=(str[i]-'0')+b*c.num;
				                i++;
		            	}
		            }
		            if(str[i]=='.')
		            {
		                i++;
		                while(str[i]>='0'&&str[i]<='9'||(str[i]>='A'&&str[i]<='Z'))
		                {
		                	if(str[i]>='A'&&str[i]<='Z'){
		                		d=d+(str[i]-'A'+10)/g;
			                    g*=c.num;
			                    i++;
		                	}
		                	else{
		                		d=d+(str[i]-'0')/g;
			                    g*=c.num;
			                    i++;
		                	}
		                	
		                }
		            } 
		        val[n]=b+d;		        		      
		        n++;
		        }
		      b=0;d=0;g=c.num;
		    }
		     
		    for(i=0;str[i]!=0;i++)
		        {
		            if(str[i] < '0' || str[i] > '9')
		            fu[j++]=str[i];
		        }  
		    up=j;
		    j=n; 
		    i=0;
		    n=0;  
		    float num=digui();
		    String numm;
		    if(c.num==16){
		    	 numm=Integer.toHexString((int) num);
		    }
		    else if(c.num==8){
		    	numm= Integer.toOctalString((int) num);
		    }
		    else if(c.num==2){
		    	numm=Integer.toBinaryString((int)num);
		    }
		    else
		    	numm=String.valueOf(num);  
		    c.s=numm;
		    c.text.setText(numm);
		    c.k1=0;c.k2=0;
		    c.flag=0;
		    for(int tt=0;tt<100;tt++){
		    	val[tt]=0;
		    	fu[tt]=' ';
		    	str[tt]=' ';
		    }
		}
	}
	float digui()
	{
	    float he=0,ji=0;  
	    char hefu='+';       
	    for( ;fu[i]!=')'&&fu[i]!='='&&i<up;i++)
	    {
	        switch(fu[i])
	        {
	            case '(':
	            i++;
	            digui();  
	            break;            
	            case '+':
	            if(hefu=='+') {
	            	val[n] = he + val[n];he = val[n];
	            }  
	            else {
	            	val[n] = he - val[n];
	            	he = val[n];
	            }  
	            hefu = '+';                                        
	            n++;
	            break;	             
	            case '-':
	            if(hefu=='+')  {val[n] = he + val[n];he = val[n];}  
	            else           {val[n] = he - val[n];he = val[n];}  
	            hefu = '-';
	            n++;
	            break;	             
	            case '*':  
	            {
	            ji=val[n];  
	            while(true)
	            {
	               if(fu[i] == '*')
	               {
	                  if(fu[i+1] == '(')  
	                    {
	                            i+=2;n++;
	                            ji = ji * digui();
	                    }
	                  else
	                    {
	                            ji = ji*val[n+1];
	                            i++;n++;
	                    }
	                }
	                else if(fu[i] == '/')
	                {
	                    if(fu[i+1] == '(')
	                    {
	                            i+=2;n++;
	                            ji = ji / digui();
	                    }
	                    else
	                    {
	                            ji = ji/val[n+1];
	                            i++;n++;
	                    }
	                }
	                else break;  
	            }
	            val[n] = ji;  
	            if(fu[i]=='+'||fu[i]=='-')
	            i--;
	            break;
	            }	             
	            case '/':   
	            {
	                ji = val[n];
	                while(true)
	                {
	                    if(fu[i] == '*')
	                    {
	                        if(fu[i+1] == '(')
	                        {
	                            i+=2;n++;
	                            ji = ji * digui();
	                        }
	                        else
	                        {
	                            ji = ji*val[n+1];
	                            i++;n++;
	                        }
	                    }
	                    if(fu[i] == '/')
	                    {
	                        if(fu[i+1] == '(')
	                        {
	                            i+=2;n++;
	                            ji = ji / digui();	                             
	                        }
	                        else
	                        {
	                            ji = ji/val[n+1];
	                            i++;n++;
	                        }
	                    }
	                    else break;  
	                }
	            } 
	            val[n] = ji;
	            if(fu[i]=='+'||fu[i]=='-')
	            i--;
	            break;
	            }
	    }    
	    if(hefu == '+') val[n] = he + val[n];  
	    else val[n] = he - val[n];	     
	    return val[n];  
	}
}
public class Count extends JFrame implements ActionListener{
	JTextField text;
	String s="";
	int flag=1;
	int k1=0;
	int k2=0;
	float num=10;
	Count(){
		super("计算机");
		JMenuBar menubar=new JMenuBar();
		JMenu menu1=new JMenu(" 查看(V) ");
		JMenu menu2=new JMenu(" 编辑(E) ");
		JMenu menu3=new JMenu(" 帮助(H) ");
		JMenuItem it=new JMenuItem("关于");
		it.addActionListener(this);
		menu3.add(it);
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);
		setJMenuBar(menubar);
		setLayout(null);
		text=new JTextField("0",30);
		text.setHorizontalAlignment(JTextField.RIGHT);
		//text.setEnabled(false);
		text.setEditable(false);
		text.setBounds(10, 5, 460, 30);
		add(text);
		R4 panel7=new R4(this);
		add(panel7);
		Jinzhi panel1=new Jinzhi(panel7,this);
		add(panel1);
		Zi panel2=new Zi();
		add(panel2);
		Inv_Hyp panel3=new Inv_Hyp();
		add(panel3);
		R1 panel4=new R1();
		add(panel4);
		R2 panel5=new R2(this);
		add(panel5);
		R3 panel6=new R3();
		add(panel6);
		
		add(new Kong1());
		add(new Kong2());
		add(new Kong3(this));
		this.setResizable(false);
		this.setSize(485,320);
		this.setVisible(true);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		System.out.println("s");
		String msg = "此计算器目前只可进行各个进制的加减乘除运算，可带括号！"+"\n"+"\n"
				+ "                                                                          ---洪东楗";
		String title = "Message Dlg";
		//int type = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Count();
	}
}
