import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class MyNotepad extends JFrame implements ActionListener 
{
   	JMenuBar jmb;
   	JMenu fileMenu,editMenu,formatMenu,viewMenu,buildMenu,helpMenu;
   	JMenuItem newItem,openItem,saveItem,saveasItem,pagesetupItem,printItem,exitItem,undoItem,cutItem,	
	copyItem,pasteItem,deleteItem,findItem,findnextItem,replaceItem,gotoItem,selectallItem,timedateItem,forecolorItem,backcolorItem,
	fontItem,compileItem,runItem,viewhelpItem,aboutnotepadItem;
	JCheckBoxMenuItem wordwrapItem,statusbarItem;
   	JTextArea jta;
   	JScrollPane jsp;
	MyFont myFont=null;
	
   	JFileChooser jfc;
	JColorChooser fcolor,bcolor;
	
	JLabel statusBar;
   	Font f;   	
	Runtime r;
	String str=" ";
	String result=" ";
	String app=" - Notepad";
	String currentfileName=" ";

	public MyNotepad() 
	{
      		jfc = new JFileChooser();
		
      		f= new Font("verdana", 3, 30);

		statusBar=new JLabel("||       Ln 1, Col 1  ",JLabel.RIGHT);
		statusBar.setVisible(false);

		jta = new JTextArea();
      		jta.setFont(f);
		jta.addCaretListener(new CaretListener()
				{public void caretUpdate(CaretEvent e)
				{int lineNumber=0, column=0, pos=0;
					try
					{
					pos=jta.getCaretPosition();
					lineNumber=jta.getLineOfOffset(pos);
					column=pos-jta.getLineStartOffset(lineNumber);
					}
					catch(Exception excp){}
					if(jta.getText().length()==0)
					{lineNumber=0; column=0;}	
					statusBar.setText("||       Ln "+(lineNumber+1)+", Col "+(column+1));
				}
				});	
      		int vsp = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
      		int hsp = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
      		jsp = new JScrollPane(jta,vsp,hsp);
      		//add(jsp);
		//setLayout(new BorderLaout());
		add(jsp,BorderLayout.CENTER);
		add(statusBar,BorderLayout.SOUTH);
 
      		jmb = new JMenuBar();
      		setJMenuBar(jmb);

      		fileMenu = new JMenu("File");
      		editMenu = new JMenu("Edit");
      		formatMenu = new JMenu("Format");
      		viewMenu = new JMenu("View");
		buildMenu = new JMenu("Build");
      		helpMenu = new JMenu("Help");
      		
		jmb.add(fileMenu);
      		jmb.add(editMenu);
      		jmb.add(formatMenu);
      		jmb.add(viewMenu);
      		jmb.add(buildMenu);
		jmb.add(helpMenu);
      		
		newItem=new JMenuItem("New");
		newItem.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK));
      		openItem = new JMenuItem("Open...");
		openItem.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK));
      		saveItem = new JMenuItem("Save");
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK));
      		saveasItem = new JMenuItem("Save as...");
		pagesetupItem = new JMenuItem("Page Setup");
		pagesetupItem.setEnabled(false);
      		printItem = new JMenuItem("Print...");
		printItem.setEnabled(false);
		printItem.setAccelerator(KeyStroke.getKeyStroke('P',InputEvent.CTRL_DOWN_MASK));
      		exitItem = new JMenuItem("Exit");
      		
		undoItem = new JMenuItem("Undo");
		undoItem.setEnabled(false);
		undoItem.setAccelerator(KeyStroke.getKeyStroke('Z',InputEvent.CTRL_DOWN_MASK));
      		cutItem = new JMenuItem("Cut");
      		cutItem.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK));
      		copyItem = new JMenuItem("Copy");
     		copyItem.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK));
		pasteItem = new JMenuItem("Paste");
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK));
      		deleteItem = new JMenuItem("Delete");
		//deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Del,0));;
      		findItem = new JMenuItem("Find...");
		findItem.setEnabled(false);
		findItem.setAccelerator(KeyStroke.getKeyStroke('F',InputEvent.CTRL_DOWN_MASK));
      		findnextItem = new JMenuItem("Find Next");
		findnextItem.setEnabled(false);
		findnextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
      		replaceItem = new JMenuItem("Replace...");
		replaceItem.setAccelerator(KeyStroke.getKeyStroke('H',InputEvent.CTRL_DOWN_MASK));
      		replaceItem.setEnabled(false);
		gotoItem = new JMenuItem("GoTo...");
		gotoItem.setAccelerator(KeyStroke.getKeyStroke('G',InputEvent.CTRL_DOWN_MASK));
      		selectallItem = new JMenuItem("Select All");
		selectallItem.setAccelerator(KeyStroke.getKeyStroke('A',InputEvent.CTRL_DOWN_MASK));
      		timedateItem = new JMenuItem("Time/Date");
		timedateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
      		
		wordwrapItem=new JCheckBoxMenuItem("WordWrap",false);
		wordwrapItem.addItemListener(new ItemListener(){public void itemStateChanged(ItemEvent e)
							{jta.setLineWrap(wordwrapItem.isSelected());}});
		forecolorItem=new JMenuItem("Fore Color");
		backcolorItem=new JMenuItem("Back Color");		
      		fontItem=new JMenuItem("Font...");
		
		statusbarItem=new JCheckBoxMenuItem("Status Bar",false);
		statusbarItem.addItemListener(new ItemListener(){public void itemStateChanged(ItemEvent e)
							{statusBar.setVisible(statusbarItem.isSelected());}});
		
		compileItem=new JMenuItem("Compile");
		runItem=new JMenuItem("Run");	
		
		viewhelpItem = new JMenuItem("View Help");
      		aboutnotepadItem = new JMenuItem("About Notepad");
      		
		fileMenu.add(newItem);
      		fileMenu.add(openItem);
      		fileMenu.add(saveItem);
      		fileMenu.add(saveasItem);
      		fileMenu.addSeparator();
      		fileMenu.add(pagesetupItem);
      		fileMenu.add(printItem);
      		fileMenu.addSeparator();
      		fileMenu.add(exitItem);

      		editMenu.add(undoItem);
      		editMenu.addSeparator();
      		editMenu.add(cutItem);
      		editMenu.add(copyItem);
      		editMenu.add(pasteItem);
    	  	editMenu.add(deleteItem);
     		editMenu.addSeparator();
      		editMenu.add(findItem);
      		editMenu.add(findnextItem);
   		editMenu.add(replaceItem);
      		editMenu.add(gotoItem);
      		editMenu.addSeparator();
      		editMenu.add(selectallItem);
      		editMenu.add(timedateItem);
	
      		formatMenu.add(wordwrapItem);
      		formatMenu.add(forecolorItem);
		formatMenu.add(backcolorItem);
      		formatMenu.add(fontItem);
      		
		viewMenu.add(statusbarItem);

		buildMenu.add(compileItem);      
		buildMenu.add(runItem);
		
		//helpMenu.add(viewhelpItem);
		//helpMenu.addSeparator();
      		helpMenu.add(aboutnotepadItem);

		setTitle("Untitled"+app);
      		setVisible(true);
      		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      	      	
		r=Runtime.getRuntime();

		newItem.addActionListener(this);
      		openItem.addActionListener(this);
      		saveItem.addActionListener(this);
      		saveasItem.addActionListener(this);
 	     	exitItem.addActionListener(this);

		cutItem.addActionListener(this);
      		copyItem.addActionListener(this);
      		pasteItem.addActionListener(this);
		deleteItem.addActionListener(this);
		findItem.addActionListener(this);
		replaceItem.addActionListener(this);
		gotoItem.addActionListener(this);
		selectallItem.addActionListener(this);
      		timedateItem.addActionListener(this);

		forecolorItem.addActionListener(this);
		backcolorItem.addActionListener(this);
		fontItem.addActionListener(this);

		compileItem.addActionListener(this);
		runItem.addActionListener(this);
		aboutnotepadItem.addActionListener(this);
   	}

	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==newItem) 
		{
			String s = jta.getText();
         			if(!(s.equals(""))) 
			{
            				int option=JOptionPane.showConfirmDialog(this,"Do u want to save the previous data?y/n");
            				if(option==JOptionPane.YES_OPTION) 
				{
               					jfc.showSaveDialog(this);
               					try 
					{
						currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
						this.setTitle(currentfileName+app);

						FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile());
						PrintWriter pout=new PrintWriter(fout);
						String s1=jta.getText();
						for(String line:s1.split("\\n")) 
							pout.println(line);
						pout.close();
						fout.close();
               					} 
					catch(Exception e) 
					{
    		                       JOptionPane.showMessageDialog(this,"Error in writing to file","Error",JOptionPane.WARNING_MESSAGE);
               					}
            				}
         			} 
			//else
			//{
				jta.setText("");
				this.setTitle("Untitled"+app);
         			//}
		}
      		else if(ae.getSource()==openItem)
		{ 
         			String s = jta.getText();
         			if(!(s.equals(""))) 
			{
            				int option=JOptionPane.showConfirmDialog(this,"Do u want to save the previous data?y/n");
            				if(option==JOptionPane.YES_OPTION) 
				{
               					jfc.showSaveDialog(this);
               					try 
					{
						currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
						this.setTitle(currentfileName+app);

                  					FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile());
						PrintWriter pout=new PrintWriter(fout);
						String s1=jta.getText();
						for(String line:s1.split("\\n")) 
							pout.println(line);
						pout.close();
						fout.close();
               					} 
					catch(Exception e) 
					{                  					                                                             JOptionPane.showMessageDialog(this,"Can't open the file","Error",JOptionPane.WARNING_MESSAGE);
               					}
            				}
				else if(option==JOptionPane.NO_OPTION)
				{
					jta.setText("");
					this.setTitle("Untitled"+app);
					jfc.showOpenDialog(this);
            					try 
					{
						currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
						this.setTitle(currentfileName+app);

               						FileInputStream fin = new FileInputStream(jfc.getSelectedFile());
               						BufferedReader bin = new BufferedReader(new InputStreamReader(fin));
               						String s1 = "";
               						while((s1 = bin.readLine()) != null) 
                  						jta.append(s1 + "\n");
               						fin.close();
            					}	 
					catch (Exception e) 
					{
               		                          JOptionPane.showMessageDialog(this,"Can't open the file","Error",JOptionPane.WARNING_MESSAGE);
            					}
				}
         			} 
			else 
			{
            				jfc.showOpenDialog(this);
            				try 
				{
					currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
					this.setTitle(currentfileName+app);
               				
					FileInputStream fin = new FileInputStream(jfc.getSelectedFile());
               					BufferedReader bin = new BufferedReader(new InputStreamReader(fin));
               					String s1 = "";
               					jta.setText("");
               					while((s1 = bin.readLine()) != null) 
					{
                  					jta.append(s1 + "\n");
               					}
               					fin.close();
            				} 
				catch (Exception e) 
				{
               		                          JOptionPane.showMessageDialog(this,"Can't open the file","Error",JOptionPane.WARNING_MESSAGE);
            				}
         			}
      		}
      		else if(ae.getSource()==saveItem) 
		{
         			jfc.showSaveDialog(this);
         			try 
			{
				currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
				this.setTitle(currentfileName+app);
            			
				FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile());
				PrintWriter pout=new PrintWriter(fout);
				String s=jta.getText();
				for(String line:s.split("\\n")) 
					pout.println(line);
				pout.close();
				fout.close();
         			} 
			catch(Exception e) 
			{}
      		}
		else if(ae.getSource()==saveasItem) 
		{
         			jfc.showSaveDialog(this);
         			try 
			{
				currentfileName=new File(""+jfc.getSelectedFile()+"").getName();
				this.setTitle(currentfileName+app);
            	
				FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile());
				PrintWriter pout=new PrintWriter(fout);
				String s=jta.getText();
				for (String line:s.split("\\n")) 
					pout.println(line);
				pout.close();
				fout.close();
         			} 
			catch(Exception e) 
			{}
      		}
		else if(ae.getSource()==exitItem) 
		{
         			System.exit(0);
		}
		else if(ae.getSource()==cutItem) 
         		{
			jta.cut();
      		}
		else if(ae.getSource()==copyItem) 
         		{
			jta.copy();
		}
		else if(ae.getSource()==pasteItem) 
         		{
			jta.paste();
		}
		else if(ae.getSource()==deleteItem)
		{
			jta.replaceSelection("");		
		}
		else if(ae.getSource()==findItem)
		{
		
		}
		else if(ae.getSource()==replaceItem)
		{
	
		}
		else if(ae.getSource()==gotoItem)
		{
			int lineNumber=0;
			try
			{
				lineNumber=jta.getLineOfOffset(jta.getCaretPosition())+1;
				String tempStr=JOptionPane.showInputDialog(this,"Enter Line Number : ",""+lineNumber);
				if(tempStr==null)
					return;
				lineNumber=Integer.parseInt(tempStr);
				jta.setCaretPosition(jta.getLineStartOffset(lineNumber-1));
			}
			catch(Exception e)
			{}
		}
		else if(ae.getSource()==selectallItem)
		{
			jta.selectAll();
		}	
		else if(ae.getSource()==timedateItem)
		{
			jta.insert(new Date().toString(),jta.getSelectionStart());
		}
		else if(ae.getSource()==forecolorItem)
		{
			Color fcolor=JColorChooser.showDialog(this,"Color",Color.RED);
			if(fcolor!=null)
				jta.setForeground(fcolor);
		}
		else if(ae.getSource()==backcolorItem)
		{
			Color bcolor=JColorChooser.showDialog(this,"Color",Color.RED);
			if(bcolor!=null)
				jta.setBackground(bcolor);
		}
      		else if(ae.getSource()==fontItem)
		{
			myFont = new MyFont(jta.getFont());
			jta.setFont(myFont.createFont());
		}
		else if(ae.getSource()==compileItem) 
         		{
			File file=new File(""+jfc.getSelectedFile()+"");
			String fname=file.getName();			
			String name=fname.substring(0,fname.lastIndexOf(".")); 
			String ext=fname.substring(fname.lastIndexOf(".") + 1,fname.length()); 

			str= " ";
			if(ext.equalsIgnoreCase("java"))
			{
				try
				{
					FileWriter fw=new FileWriter(fname);
					PrintWriter pw=new PrintWriter(fw);
					String s1=jta.getText();
					pw.println(s1);
					pw.flush();

					Process error=r.exec("C:\\Program Files (x86)\\Java\\jdk1.8.0\\bin\\javac.exe -d .  "+fname);
					BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
		
					while(true)
					{
						String temp=err.readLine();
						if(temp != null)
						{
							result += temp;
							result += "\n";
						}
						else
						{
							break;
						}
					}
					if(result.equals(" "))
					{
						JOptionPane.showMessageDialog(this,"Compilation successful "+fname+"!",
									"Message Box",JOptionPane.PLAIN_MESSAGE);
						err.close();
					}
					else
					{
						JOptionPane.showMessageDialog(this,result,"Error message",
										JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please take a java file !","Message Box",
								JOptionPane.PLAIN_MESSAGE);
			}
		}
		else if(ae.getSource()==runItem)
		{
			File file=new File(""+jfc.getSelectedFile()+"");
			String fname=file.getName();			
			String name=fname.substring(0,fname.lastIndexOf(".")); 
			String ext=fname.substring(fname.lastIndexOf(".") + 1,fname.length()); 

			if(ext.equalsIgnoreCase("java"))
			{
				int start=0;
				try
				{
					Process p=r.exec("C:\\Program Files (x86)\\Java\\jdk1.8.0\\bin\\java.exe    "+name);
					BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
					BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
		
					while(true)
					{
						String temp=output.readLine();
						if(temp != null)
						{
							result += temp;
							result += "\n";
						}
						else
						{
							break;
						}
					}
					output.close();
					if(result.equals(" "))
					{
						while(true)
						{	
							String temp=error.readLine();
							if(temp != null)
							{
								result += temp;
								result += "\n";
							}
							else
							{
								break;
							}
						}
						error.close();
						JOptionPane.showMessageDialog(this,result,"Exception",
										JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(this,result,"Output",
										JOptionPane.PLAIN_MESSAGE);
					}
				}
				catch(Exception e2)
				{
					System.out.println(e2);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please take a java file !","Message Box",
								JOptionPane.PLAIN_MESSAGE);
			}
		}
		else if(ae.getSource()==aboutnotepadItem) 
		{
			String s="This is a notepad created by Priya Jauhari(student at B.I.E.T.Jhansi).\nIn this notepad, you can compile and run java file.\nI Used jdk1.8.0 compiler to compile the source code.\n@All rights are reserved\nFor any query, contact at :\npriyajauhari921@gmail.com";
			JOptionPane.showMessageDialog(this,s,"About Notepad",JOptionPane.PLAIN_MESSAGE);
		}
	}
   	
	public static void main(String args[]) 
	{
      		new MyNotepad();
	}
}