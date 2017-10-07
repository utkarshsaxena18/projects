import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
class Editor implements ActionListener
{
	Pattern p;
	Matcher m;
	MenuBar mb;
	Menu m1,m2,m3,m4,m5,m6,m7,m8;
	MenuItem n,o,s,sa,cl;
	MenuItem cut,copy,paste,fn,fr;
	MenuItem courier,arial,troman;
	MenuItem sizeitem1,sizeitem2,sizeitem3,sizeitem4,sizeitem5,sizeitem6,sizeitem7,sizeitem8,sizeitem9,sizeitem10,sizeitem11,sizeitem12;
	MenuItem bold,plain,italic;
	MenuItem bkcitem[]=new  MenuItem[13];
 	MenuItem frcitem[]=new  MenuItem[13];
 	MenuItem about;
 	int fontStyle=Font.PLAIN;
 	int fontNumeric=12;
 	String fontName="Courier",copystring;
	TextArea ta;
	int x,y,pos;
	Button save,dontsave,cancel,ok1,ok2,login;
	Frame f1,f2,f3,f4,f5,f6,f7,f8;
	TextField tf1,tf2,tr,t1,t2;
	String tavalue="",stvalue="",gf="",topen,tsave,s1;
	boolean firsttime=true,vis,flag1=true,flag2=true,flag3=true,flag4=true,flag5=true,flag6=true,flag=false;
	boolean nf,of,upper,Ifind,user,fnext;
	Button findnext1,findnext2,replace,replaceall,close1,close2;
	int r1,r2;
	
	public Editor()
	{
		f7 =new Frame("Login");
       	f7.setSize(400,300);
       	f7.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window wi=we.getWindow();wi.setVisible(false);wi.dispose();}});
       	Panel p=new Panel();
       	p.setLayout(new GridBagLayout());
       	GridBagConstraints gbc=new GridBagConstraints();
       	gbc.gridx=0; gbc.gridy=0;
       
       	Label l1=new Label("Username");
       	p.add(l1,gbc);
       
       	 t1=new TextField(20);
       	gbc.gridx=1;
       	p.add(t1,gbc);
       
       	Label l2=new Label("Password");
       	gbc.gridx=0;
       	gbc.gridy=1;
       	gbc.weighty=1.0;
       	p.add(l2,gbc);
       
       	 t2=new TextField(20);
       	t2.setEchoChar('*');
       	gbc.gridx=1;
       	p.add(t2,gbc);
       
       	login=new Button("Login");
       	login.addActionListener(this);
       	gbc.gridx=0;
       	gbc.gridy=2;
       	gbc.gridwidth=2;
       	p.add(login,gbc);
       	f7.add(p,BorderLayout.CENTER);
       	f7.setVisible(true);
       	 
	}
	
	public void window()
	{
		f7.setVisible(false);
		f1=new Frame("<< Text Editor 1.0>> By << Utkarsh Saxena >>");
		f1.setSize(600,600);
		f1.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){
		tavalue=ta.getText();
		if(tavalue.equals(stvalue))
			System.exit(1);
		else
		{
			upper=true;
			nf=false;
			of=false;
			checking();
		}
		}
		});
		
		ta=new TextArea();
		ta.setSize(f1.getSize());
		ta.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent me){x=ta.getCaretPosition(); pos=ta.getCaretPosition();}});
		ta.addTextListener(new TextListener(){public void textValueChanged(TextEvent e){flag=true;
		//System.out.print("Flag:True ");
		}});
		
		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		m3=new Menu("Font");
		m4=new Menu("Size");
		m5=new Menu("Style");
		m6=new Menu("Background Color");
		m7=new Menu("Text Color");
		m8=new Menu("Help");
		
		n=new MenuItem("New");
		o=new MenuItem("Open");
		s=new MenuItem("Save");
		sa=new MenuItem("Save As");
		cl=new MenuItem("Close");
		
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		fn=new MenuItem("Find");
		fr=new MenuItem("Find & Replace");
		
		courier=new MenuItem("Courier");
		arial=new MenuItem("Arial");
		troman=new MenuItem("TimesRoman");
		
		sizeitem1=new MenuItem("8");
		sizeitem2=new MenuItem("10");
		sizeitem3=new MenuItem("12");
		sizeitem4=new MenuItem("14");
		sizeitem5=new MenuItem("16");
		sizeitem6=new MenuItem("18");
		sizeitem7=new MenuItem("20");
		sizeitem8=new MenuItem("22");
		sizeitem9=new MenuItem("24");
		sizeitem10=new MenuItem("26");
		sizeitem11=new MenuItem("28");
		sizeitem12=new MenuItem("30");
		
		bold=new MenuItem("Bold");
		plain=new MenuItem("Plain");
		italic=new MenuItem("Italic");
		
		String colname[]= {"Red","Blue","Green","Cyan","Black","Dark Gray","Gray","Light Gray","Magenta","Orange","Pink","White","Yellow"};
		String frcolname[]={" Red"," Blue"," Green"," Cyan"," Black"," Dark Gray"," Gray"," Light Gray"," Magenta"," Orange"," Pink"," White"," Yellow"};
		for(int i=0;i<=12;i++)
		{
			m6.add(bkcitem[i]=new MenuItem(colname[i]));
			m7.add(frcitem[i]=new MenuItem(frcolname[i]));
		}
		
		about=new MenuItem("About..");
		
		n.addActionListener(this);
		o.addActionListener(this);
		s.addActionListener(this);
		sa.addActionListener(this);
		cl.addActionListener(this);
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		fn.addActionListener(this);
		fr.addActionListener(this);
		
		courier.addActionListener(this);
		arial.addActionListener(this);
		troman.addActionListener(this);
		
		sizeitem1.addActionListener(this);
		sizeitem2.addActionListener(this);
		sizeitem3.addActionListener(this);
		sizeitem4.addActionListener(this);
		sizeitem5.addActionListener(this);
		sizeitem6.addActionListener(this);
		sizeitem7.addActionListener(this);
		sizeitem8.addActionListener(this);
		sizeitem9.addActionListener(this);
		sizeitem10.addActionListener(this);
		sizeitem11.addActionListener(this);
		sizeitem12.addActionListener(this);
		
		bold.addActionListener(this);
		plain.addActionListener(this);
		italic.addActionListener(this);
		
		for(int i=0;i<=12;i++)
		{
			bkcitem[i].addActionListener(this);
			frcitem[i].addActionListener(this);
		}
		
		about.addActionListener(this);
		
		m1.add(n);
		m1.add(o);
		m1.add(s);
		m1.add(sa);
		m1.addSeparator();
		m1.add(cl);
		
		m2.add(cut);
		m2.add(copy);
		m2.add(paste);
		m2.add(fn);
		m2.add(fr);
		
		m3.add(courier);
		m3.add(arial);
		m3.add(troman);
		
		m4.add(sizeitem1);
		m4.add(sizeitem2);
		m4.add(sizeitem3);
		m4.add(sizeitem4);
		m4.add(sizeitem5);
		m4.add(sizeitem6);
		m4.add(sizeitem7);
		m4.add(sizeitem8);
		m4.add(sizeitem9);
		m4.add(sizeitem10);
		m4.add(sizeitem11);
		m4.add(sizeitem12);
		
		m5.add(bold);
		m5.add(plain);
		m5.add(italic);
		
		m8.add(about);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		mb.add(m5);
		mb.add(m6);
		mb.add(m7);
		mb.add(m8);
		
		f1.setMenuBar(mb);
		f1.add(ta);
		f1.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		
		String args=e.getActionCommand();
		if(e.getSource()==login)
		{
			
			if(t1.getText().equals("admin") && t2.getText().equals("admin"))
				window();
			else
				error();
		}
			
		if(e.getSource()==n)
		{
			tavalue=ta.getText();
			nf=true;
			if(tavalue.equals(stvalue))				
			{
			/*	if(flag)
				{
					checking();
					flag=false;
					System.out.print("Flag:False ");
				}
				else
				{*/
					of=false;
					upper=false;
					firsttime=true;
					x=0;
					ta.setText("");
					stvalue=ta.getText();
					f1.setTitle("<< Text Editor 1.0>> By << Utkarsh Saxena >>");
					//System.out.print("Flag: Becomes False ");
			//	}
				}
			else
			{//	flag=false;							
				checking();
			//	System.out.print("Flag: Again False");
				
			}
			/*if(flag)
			{
				checking();
				flag=false;
			}*/
			//System.out.print("Flag forcibly false");
			
			
		}
		
		if(e.getSource()==o)
		{
			tavalue=ta.getText();
			of=true;
			if(tavalue.equals(stvalue))
			{
			//	if(flag)
			//	{
			//		checking();	
			//	}
			//	else
			//	{	
					openBox();
					
			//	}
			}
			else									
				checking();
			//System.out.print("Flag becomes false");
			//flag=false;
		}
		
		if(e.getSource()==s)
		{
			if(firsttime)
				saveBox();
			else
				saving();
			if(vis)
				f2.setVisible(false);
		}
		
		if(e.getSource()==sa)
			saveBox();
		
		if(e.getSource()==cl)
		{
			tavalue=ta.getText();
			if(tavalue.equals(stvalue))
				System.exit(1);
			else
			{
				upper=true;
				nf=false;
				of=false;
				checking();
			}
		}
		
		if(e.getSource()==cut)
			cut();
			
		if(e.getSource()==copy)
			copy();
			
		if(e.getSource()==paste)
			paste();		
		
		if(e.getSource()==fn)
			finding();
			
		if(e.getSource()==fr)
			replacing();	
			
		if(e.getSource()==findnext1)
		{
			findingValue1();
			fnext=true;
		}
		
		if(e.getSource()==findnext2)
		{
			findingValue2();
			fnext=true;
		}
		
		if(e.getSource()==close1)
		{
			if(Ifind)
				f3.setVisible(false);
			else
				f4.setVisible(false);
			flag1=true;
		}
		
		if(e.getSource()==close2)
		{
			if(Ifind)
				f3.setVisible(false);
			else
				f4.setVisible(false);
			flag2=true;
		}
		
		if(e.getSource()==replace)
		{
			
			//if(user)
			//{ 
			
				
				ta.requestFocus();
				if(!ta.getSelectedText().equals(""))		
				{
					ta.replaceRange(tr.getText(),ta.getSelectionStart(),ta.getSelectionEnd());
				//	user=false;
				}
				findingValue2();
			//if(fnext)
			//	fnext=false;
			//else
			//	findingValue2();
		//}
		}			
				
		if(e.getSource()==replaceall)
		{
			tavalue=ta.getText();
			p=Pattern.compile(tf2.getText());
			m=p.matcher(tavalue);
			while(m.find())
			{
				ta.setText(m.replaceAll(tr.getText()));
			}	
		}
		
		if(e.getSource()==save)
		{
			flag3=true;
			if(firsttime)
			{
				f2.setVisible(false);
				saveBox();
			}
			else
			{
				saving();
			}
				
			if(nf)
			{
				ta.setText("");
				nf=false;
				x=0;
				of=false;
				upper=false;
				firsttime=true;
				tavalue=ta.getText();
				stvalue=tavalue;	
				f1.setTitle("Untitled");
			}		
			if(of)
			{
				f2.setVisible(false);
				openBox();
				of=false;
				nf=false;
				upper=false;
				f1.setTitle(topen);
			}
			if(upper)
				System.exit(1);	
			f2.setVisible(false);
			//f1.setTitle(tsave);
		}
		
		if(e.getSource()==dontsave)
		{
			flag3=true;
			if(nf)
			{
				ta.setText("");
				nf=false;
				of=false;
				upper=false;
				firsttime=true;
				x=0;
				tavalue=ta.getText();
				stvalue=tavalue;
				f1.setTitle("<< Text Editor 1.0>> By << Utkarsh Saxena >>");
			}
			if(of)
			{
				f2.setVisible(false);
				openBox();
				of=false;
				nf=false;
				upper=false;
				f1.setTitle(topen);
			}
			if(upper)
				System.exit(0);
			//f1.setTitle(tsave);	
			f2.setVisible(false);
		}
		
		if(e.getSource()==cancel)
		{
			f1.setTitle(topen);
			/*if(of)
			{
				f1.setTitle(topen);
			}
			if(nf)
			{
				f1.setTitle(tsave);
			}*/
			flag3=true;
			f2.setVisible(false);
		}
			
		if(e.getSource()==ok1)
		{
			flag4=true;
			f5.setVisible(false);
		}
		
		if(e.getSource()==ok2)
		{
			flag6=true;
			f8.setVisible(false);
		}
		
		if(e.getSource()==courier)
		{
			fontName="Courier";
			textfont();
		}
		
		if(e.getSource()==arial)
		{
			fontName="Arial";
			textfont();
		}
		
		if(e.getSource()==troman)
		{
			fontName="TimesRoman";
			textfont();
		}
		
		if(e.getSource()==sizeitem1){fontNumeric=8;textfont();}
		if(e.getSource()==sizeitem2){fontNumeric=10;textfont();}
		if(e.getSource()==sizeitem3){fontNumeric=12;textfont();}
		if(e.getSource()==sizeitem4){fontNumeric=14;textfont();}
		if(e.getSource()==sizeitem5){fontNumeric=16;textfont();}
		if(e.getSource()==sizeitem6){fontNumeric=18;textfont();}
		if(e.getSource()==sizeitem7){fontNumeric=20;textfont();}
		if(e.getSource()==sizeitem8){fontNumeric=22;textfont();}
		if(e.getSource()==sizeitem9){fontNumeric=24;textfont();}
		if(e.getSource()==sizeitem10){fontNumeric=28;textfont();}
		if(e.getSource()==sizeitem11){fontNumeric=30;textfont();}
		if(e.getSource()==sizeitem12){fontNumeric=32;textfont();}
		
		if(e.getSource()==bold){fontStyle=Font.BOLD;textfont();}
		if(e.getSource()==plain){fontStyle=Font.PLAIN;textfont();}
		if(e.getSource()==italic){fontStyle=Font.ITALIC;textfont();}
		
		if(args.equals("Red")){ta.setBackground(Color.red);}
    	if(args.equals("Blue")){ta.setBackground(Color.blue);}
    	if(args.equals("Green")){ta.setBackground(Color.green);}
    	if(args.equals("Cyan")){ta.setBackground(Color.cyan);}
    	if(args.equals("Black")){ta.setBackground(Color.black);}
	  	if(args.equals("Dark Gray")){ta.setBackground(Color.darkGray);}
	  	if(args.equals("Gray")){ta.setBackground(Color.gray);}
    	if(args.equals("Light Gray")){ta.setBackground(Color.lightGray);}
    	if(args.equals("Magenta")){ta.setBackground(Color.magenta);}
    	if(args.equals("Orange")){ta.setBackground(Color.orange);}
    	if(args.equals("Pink")){ta.setBackground(Color.pink);}
     	if(args.equals("White")){ta.setBackground(Color.white);}
    	if(args.equals("Yellow")){ta.setBackground(Color.yellow);}
    	
    	if(args.equals(" Red")){ta.setForeground(Color.red);}
    	if(args.equals(" Blue")){ta.setForeground(Color.blue);}
    	if(args.equals(" Green")){ta.setForeground(Color.green);}
    	if(args.equals(" Cyan")){ta.setForeground(Color.cyan);}
    	if(args.equals(" Black")){ta.setForeground(Color.black);}
    	if(args.equals(" Dark Gray")){ta.setForeground(Color.darkGray);}
    	if(args.equals(" Gray")){ta.setForeground(Color.gray);}
    	if(args.equals(" Light Gray")){ta.setForeground(Color.lightGray);}
    	if(args.equals(" Magenta")){ta.setForeground(Color.magenta);}
    	if(args.equals(" Orange")){ta.setForeground(Color.orange);}
    	if(args.equals(" Pink")){ta.setForeground(Color.pink);}
     	if(args.equals(" White")){ta.setForeground(Color.white);}
    	if(args.equals(" Yellow")){ta.setForeground(Color.yellow);}
		
		if(e.getSource()==about)
		{
			aboutdialog();
		}
		
	}
	
	public void openBox()
	{
		FileDialog fd1=new FileDialog(f1,"Open File",FileDialog.LOAD);
		fd1.setVisible(true);
		gf=fd1.getDirectory()+fd1.getFile();
		if(gf.length()<=8)
		{
			nf=false;
			of=false;
			upper=false;
		}
		else
		{
			firsttime=false;
			openning();
			x=0;
		}
		if(vis)
			f2.setVisible(false);
		topen=fd1.getFile();
		f1.setTitle(fd1.getFile());
	}
	
	public void openning()
	{
		try{
		File f=new File(gf);
		FileInputStream fis=new FileInputStream(f);
		int ch;
		String svl="";
		while((ch=fis.read())!=-1)
			svl+=(char)ch;
		fis.close();
		ta.setText(svl);
		tavalue=ta.getText();
		stvalue=tavalue;
		}
		catch(Exception ex){ex.getMessage();}
	}

	public void saveBox()
	{
		FileDialog fd2=new FileDialog(f1,"Save File",FileDialog.SAVE);
		fd2.setVisible(true);
		gf=fd2.getDirectory()+fd2.getFile();
		if(gf.length()<=9)
		{
			nf=false;
			of=false;
			upper=false;
		}
		else
		{
			stvalue=tavalue;
			saving();
			firsttime=false;	
		}
		if(vis)
		f2.setVisible(false);
		tsave=fd2.getFile();
		f1.setTitle(fd2.getFile());
	}
	
	public void saving()
	{
		try{
		File f=new File(gf);
		FileOutputStream fos=new FileOutputStream(f);
		char ch[]=ta.getText().toCharArray();
		for(int i=0;i<ch.length;i++)
			fos.write((byte)ch[i]);
		fos.close();
		stvalue=tavalue;
		}
		catch(Exception ex){}
	}
	
	public void finding()
	{
		if(flag1)
		{
		f3=new Frame("Find What:");
		f3.setSize(400,400);
		Panel p=new Panel();
		findnext1=new Button("Find Next");
		findnext1.addActionListener(this);
		close1=new Button("Close");
		close1.addActionListener(this);
		Ifind=true;
		p.setLayout(new FlowLayout());
		p.add(findnext1);
		p.add(close1);
		tf1=new TextField(20);
		f3.add(new Label("Find What:"),"North");
		f3.add(tf1);
		f3.add(p,"South");
		f3.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window dw=we.getWindow();
		dw.setVisible(false);
		dw.dispose();
		flag1=true;
		}
		});
		f3.setVisible(true);
		f3.pack();
		f3.setLocation(500,150);
		flag1=false;
		}
	}
	
	public void cut()
	{
		copystring =ta.getSelectedText();
 		ta.replaceText("",ta.getSelectionStart(),ta.getSelectionEnd());
 		ta.requestFocus();
	}
	
	public void copy()
	{
		copystring=ta.getSelectedText();
 		ta.requestFocus();
	}
	
	public void paste()
	{
		if(copystring.length()>0)
 		ta.insertText(copystring,ta.getSelectionStart());
 		ta.requestFocus();
	}
	
	public void findingValue1()
	{
		tavalue=ta.getText();
		//tf.setText=p.quote(tf.getText());
		p=Pattern.compile(p.quote(tf1.getText()));
		m=p.matcher(tavalue);
		if(m.find(x))
		{
			y=0;
			char ch[]=tavalue.toCharArray();
			for(int i=0;i<m.start();i++)
				if(ch[i]=='\n')
					y++;
			ta.select(m.start()-y,m.end()-y);
			r1=m.start()-y;
			r2=m.end()-y;
			x=m.end();
			user=true;
		}	
		else
			cannotFound();
		f1.toFront();
	}
	
	public void findingValue2()
	{
		try{
			s1=ta.getText();
			s1=s1.replaceAll("\r","");
			ta.setText(s1);
			ta.setCaretPosition(pos);
			p=Pattern.compile(tf2.getText());
			m=p.matcher(s1);
			if(m.find(pos))
			{
				ta.requestFocus();
				ta.select(m.start(),m.end());
				String str1,str2;
				str1=tr.getText();
				str2=tf2.getText();
				if(str1.length()>str2.length())
					pos=m.end()+1;
				else
					pos=m.start()+1;
			}
			else
				cannotFound();
			
			}
			catch(Exception ex){cannotFound();}
		//tf.setText=p.quote(tf.getText());
	/*	p=Pattern.compile(tf2.getText());
		m=p.matcher(ta.getText());
		if(m.find(x))
		{
			y=0;
			char ch[]=tavalue.toCharArray();
			for(int i=0;i<m.start();i++)
				if(ch[i]=='\r')
					y++;
			ta.select(m.start()-y,m.end()-y);
			r1=m.start()-y;
			r2=m.end()-y;
			x=m.end();
			user=true;
		}	
		else
			cannotFound();
		f1.toFront();*/
	}
		
	public void replacing()
	{
		if(flag2)
		{
		f4=new Frame("Find & Replace");
		f4.setSize(500,400);
		Panel p=new Panel();
		p.setLayout(new GridLayout(4,0));
		Panel p1=new Panel();
		p1.setLayout(new FlowLayout());
		p.add(new Label("Find What:"));
		tf2=new TextField(20);
		p.add(tf2);
		p.add(new Label("Replace with:"));
		tr=new TextField(20);
		p.add(tr);
		findnext2=new Button("Find Next");
		findnext2.addActionListener(this);
		close2=new Button("Close");
		close2.addActionListener(this);
		replace=new Button("Replace");
		replace.addActionListener(this);
		replaceall=new Button("Replace All");
		replaceall.addActionListener(this);
		p1.add(findnext2);
		p1.add(replace);
		p1.add(replaceall);
		p1.add(close2);
		f4.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window wi=we.getWindow();
		wi.setVisible(false);
		wi.dispose();
		flag2=true;
		}
		});
		f4.add(p);
		f4.add(p1,"South");
		f4.setVisible(true);
		f4.pack();
		Ifind=false;
		f4.setLocation(500,150);
		flag2=false;
		}
	}	
	
		
	public void checking()
	{
		if(flag3)
		{
			vis=true;												
			f2=new Frame("Save Changes");								
			f2.setSize(400,400);	
			f2.setLocation(500,200);								
			f2.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){
			Window ew=we.getWindow();
			ew.setVisible(false);
			ew.dispose();
			flag3=true;
			}
			});									
			Panel p=new Panel();									
			save=new Button("Save");							
			save.addActionListener(this);						
			dontsave=new Button("Dont Save");							
			dontsave.addActionListener(this);						
			cancel=new Button("Cancel");							
			cancel.addActionListener(this);							
			p.setLayout(new FlowLayout());							
			p.add(save);												
			p.add(dontsave);										
			p.add(cancel);												
			f2.add(new Label("Do You want to save changes ?"),"North");	
			f2.add(p,"South");											
			f2.setVisible(true);										
			f2.pack();													
			flag3=false;
		}		
	}										
						

	public void cannotFound()	
	{
		if(flag4)
		{
			f5=new Frame("Cannot find Symbol");
			f5.setSize(500,400);
			f5.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window wi=we.getWindow();
			wi.setVisible(false);
			wi.dispose();
			flag4=true;
			}
			});
			f5.add(new Label("Cannot find Symbol"));
			ok1=new Button("OK");
			ok1.addActionListener(this);
			f5.add(ok1,"South");
			f5.setVisible(true);
			f5.pack();
			f5.setLocation(500,200);
			flag4=false;
		}
	}
	
	public void error()	
	{
		if(flag6)
		{
			f8=new Frame("Error");
			f8.setSize(500,400);
			f8.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window wi=we.getWindow();
			wi.setVisible(false);
			wi.dispose();
			flag6=true;
			}
			});
			f8.add(new Label("Invalid Credentials..."));
			ok2=new Button("OK");
			ok2.addActionListener(this);
			f8.add(ok2,"South");
			f8.setVisible(true);
			f8.pack();
			f8.setLocation(500,200);
			flag6=false;
		}
	}
	
	private void textfont()
	{
		ta.setFont(new Font(fontName,fontStyle,fontNumeric));
	}
	
	public void aboutdialog()
	{
		if(flag5)
		{
		f6=new Frame("About..");
		f6.setSize(300,300);
		f6.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){Window wi=we.getWindow();wi.setVisible(false);wi.dispose();flag5=true;}});
		f6.setLayout(new GridLayout());
		TextArea t=new TextArea("\nDeveloped by Utkarsh Saxena\n"+"\nMaharishi Arvind College of Engineering and Research Center\n"+"\nThis Text Editor was developed in Java.\n"+"\nThanks for using this product.\n");
		t.setEditable(false);
		f6.add(t);
		f6.pack();
		f6.setVisible(true);
		flag5=false;
		}
	}
	
	public static void main(String args[])
	{	
		Editor e=new Editor();
	}
	
}
