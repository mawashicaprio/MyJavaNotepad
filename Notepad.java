import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class MyCanvas extends Canvas
{
  int ln=1,col=0;

  MyCanvas()
  {
    setSize(750,20);
  }
  public void paint(Graphics g)
  {
    g.drawString("Ln "+ln+",Col "+(col+1),600,15);
  }
}

class Notepad extends WindowAdapter implements ActionListener,ListSelectionListener,ItemListener,CaretListener
{
  Font fo;
  JFrame jf;
  MyCanvas m; 
  JMenuBar menuBar;
  JMenu menu1,menu2,menu3,menu4,menu5,menu6;
  JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7, menuItem8,menuItem9,menuItem10,menuItem11,menuItem12,menuItem13,menuItem14,menuItem15, menuItem16,menuItem17,menuItem18,menuItem19,menuItem20,menuItem21,menuItem22,menuItem23;
  JCheckBoxMenuItem cbmenuItem1,cbmenuItem2;
  JPopupMenu p= new JPopupMenu("Pop");
  JMenuItem i1;
  JMenuItem i2;
  JMenuItem i3;
  JMenuItem i4;
  JMenuItem i5;
  JMenuItem i6;
  JTextArea jt;
  JDialog jd;
  JFileChooser jfc1,jfc2;
  JList jl1,jl2,jl3;
  JLabel l1,l2,l3,l4;
  JTextField jf1,jf2,jf3,jf4;
  JScrollPane jp1,jp2,jp3;
  JButton jb1,jb2;
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  String f[]=ge.getAvailableFontFamilyNames();
  String st[]={"Plain","Bold","Italic"};
  String si[]={"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48", "72"};

  Notepad()
  {
    fo=new Font("Lucida Console",Font.PLAIN,14);
    jf=new JFrame("Java Notepad");
    m=new MyCanvas();
    jt=new JTextArea();
    jd=new JDialog(jf,"Font",true);
    jfc1=new JFileChooser("C:\\Sumit");
    jfc2=new JFileChooser("C:\\Sumit");
    l1=new JLabel("Font:");
    l2=new JLabel("Font style:");
    l3=new JLabel("Size:");
    l4=new JLabel("Sample:");
    jl1=new JList(f);
    jl2=new JList(st);
    jl3=new JList(si);
    jf1=new JTextField();
    jf2=new JTextField();
    jf3=new JTextField();
    jf4=new JTextField("Sumit");
    jp1=new JScrollPane(jl1);
    jp2=new JScrollPane(jl3);
    jp3=new JScrollPane(jt);
    jb1=new JButton("OK");
    jb2=new JButton("Cancel");

    jt.setLineWrap(true);
    jt.setFont(fo);
    jf4.setFont(fo);

    jf1.setEnabled(false);
    jf2.setEnabled(false);
    jf3.setEnabled(false);
    jf4.setEnabled(false);

    i1= new JMenuItem("Undo");
    i2= new JMenuItem("Cut");
    i3= new JMenuItem("Copy");
    i4= new JMenuItem("Paste");
    i5= new JMenuItem("Delete");
    i6= new JMenuItem("Select All");

    //i2.setEnabled(false);
    //i3.setEnabled(false);
    //i4.setEnabled(false);
    //i5.setEnabled(false);

    p.add(i1);
    p.addSeparator();
    p.add(i2);
    p.add(i3);
    p.add(i4);
    p.add(i5);
    p.addSeparator();
    p.add(i6);

    menuBar=new JMenuBar();

    menu1=new JMenu("File");
    menu1.setMnemonic(KeyEvent.VK_F);
    menuBar.add(menu1);

    menuItem1=new JMenuItem("New",KeyEvent.VK_N);
    menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,2));
    menu1.add(menuItem1);

    menuItem2=new JMenuItem("Open",KeyEvent.VK_O);
    menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,2));
    menu1.add(menuItem2);

    menuItem3=new JMenuItem("Save",KeyEvent.VK_S);
    menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,2));
    menu1.add(menuItem3);

    menuItem4=new JMenuItem("Save As",KeyEvent.VK_A);
    menu1.add(menuItem4);

    menu1.addSeparator();

    menuItem5=new JMenuItem("Page Setup",KeyEvent.VK_U);
    menu1.add(menuItem5);

    menuItem6=new JMenuItem("Print",KeyEvent.VK_P);
    menuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,2));
    menu1.add(menuItem6);

    menu1.addSeparator();

    menuItem7=new JMenuItem("Exit",KeyEvent.VK_X);
    menuItem7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,2));
    menu1.add(menuItem7);

    menu2=new JMenu("Edit");
    menu2.setMnemonic(KeyEvent.VK_E);
    menuBar.add(menu2);

    menuItem8=new JMenuItem("Undo",KeyEvent.VK_U);
    menuItem8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,2));
    menu2.add(menuItem8);

    menu2.addSeparator();

    menuItem9=new JMenuItem("Cut",KeyEvent.VK_T);
    menuItem9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,2));
    menu2.add(menuItem9);
    //menuItem9.setEnabled(false);

    menuItem10=new JMenuItem("Copy",KeyEvent.VK_C);
    menuItem10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,2));
    menu2.add(menuItem10);
    //menuItem10.setEnabled(false);

    menuItem11=new JMenuItem("Paste",KeyEvent.VK_P);
    menuItem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,2));
    menu2.add(menuItem11);

    menuItem12=new JMenuItem("Delete",KeyEvent.VK_L);
    menuItem12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
    menu2.add(menuItem12);
    //menuItem12.setEnabled(false);

    menu2.addSeparator();

    menuItem13=new JMenuItem("Find",KeyEvent.VK_F);
    menuItem13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,2));
    menu2.add(menuItem13);
    //menuItem13.setEnabled(false);

    menuItem14=new JMenuItem("Find Next",KeyEvent.VK_N);
    menuItem14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
    menu2.add(menuItem14);
    //menuItem14.setEnabled(false);

    menuItem15=new JMenuItem("Replace",KeyEvent.VK_R);
    menuItem15.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,2));
    menu2.add(menuItem15);

    menuItem16=new JMenuItem("Go To",KeyEvent.VK_G);
    menuItem16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,2));
    menu2.add(menuItem16);

    menu2.addSeparator();

    menuItem17=new JMenuItem("Select All",KeyEvent.VK_A);
    menuItem17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,2));
    menu2.add(menuItem17);

    menu2.addSeparator();

    menuItem18=new JMenuItem("Time/Date",KeyEvent.VK_D);
    menuItem18.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
    menu2.add(menuItem18);

    menu3=new JMenu("Format");
    menu3.setMnemonic(KeyEvent.VK_O);
    menuBar.add(menu3);

    cbmenuItem1=new JCheckBoxMenuItem("Word Wrap");
    cbmenuItem1.setMnemonic(KeyEvent.VK_W);
    menu3.add(cbmenuItem1);

    menuItem19=new JMenuItem("Font",KeyEvent.VK_F);
    menu3.add(menuItem19);

    menu4=new JMenu("Program");
    menu4.setMnemonic(KeyEvent.VK_P);
    menuBar.add(menu4);

    menuItem20=new JMenuItem("Compile",KeyEvent.VK_C);
    menuItem20.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,2));
    menu4.add(menuItem20);

    menuItem21=new JMenuItem("Run",KeyEvent.VK_R);
    menuItem21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,2));
    menu4.add(menuItem21);

    menu5=new JMenu("View");
    menu5.setMnemonic(KeyEvent.VK_V);
    menuBar.add(menu5);

    cbmenuItem2=new JCheckBoxMenuItem("Status Bar");
    cbmenuItem2.setMnemonic(KeyEvent.VK_S);
    menu5.add(cbmenuItem2);
    cbmenuItem2.setSelected(true);

    menu6=new JMenu("Help");
    menu6.setMnemonic(KeyEvent.VK_H);
    menuBar.add(menu6);

    menuItem22=new JMenuItem("Help Topics",KeyEvent.VK_H);
    menu6.add(menuItem22);

    menu6.addSeparator();

    menuItem23=new JMenuItem("About Notepad",KeyEvent.VK_A);
    menu6.add(menuItem23);

    l1.setBounds(20,20,40,20);
    l2.setBounds(20,170,80,20);
    l3.setBounds(140,170,40,20);
    jf1.setBounds(20,40,220,20);
    jf2.setBounds(20,190,100,20);
    jf3.setBounds(140,190,40,20);
    jp1.setBounds(20,65,220,90);
    jl2.setBounds(20,215,100,80);
    jp2.setBounds(140,215,40,80);
    jb1.setBounds(260,40,80,25);
    jb2.setBounds(260,70,80,25);
    l4.setBounds(220,190,80,25);
    jf4.setBounds(220,215,80,25);

    jd.add(l1);
    jd.add(l2);
    jd.add(l3);
    jd.add(jf1);
    jd.add(jf2);
    jd.add(jf3);
    jd.add(jp1);
    jd.add(jl2);
    jd.add(jp2);
    jd.add(jb1);
    jd.add(jb2);
    jd.add(l4);
    jd.add(jf4);

    jl1.setSelectedIndex(59);
    jl2.setSelectedIndex(0);
    jl3.setSelectedIndex(5);

    jf1.setText(f[59]);
    jf2.setText(st[0]);
    jf3.setText(si[5]);

    jf.addWindowListener(this);
    i1.addActionListener(this);
    i2.addActionListener(this);
    i3.addActionListener(this);
    i4.addActionListener(this);
    i5.addActionListener(this);
    i6.addActionListener(this);
    menuItem1.addActionListener(this);
    menuItem2.addActionListener(this);
    menuItem3.addActionListener(this);
    menuItem4.addActionListener(this);
    menuItem5.addActionListener(this);
    menuItem6.addActionListener(this);
    menuItem7.addActionListener(this);
    menuItem8.addActionListener(this);
    menuItem9.addActionListener(this);
    menuItem10.addActionListener(this);
    menuItem11.addActionListener(this);
    menuItem12.addActionListener(this);
    menuItem13.addActionListener(this);
    menuItem14.addActionListener(this);
    menuItem15.addActionListener(this);
    menuItem16.addActionListener(this);
    menuItem17.addActionListener(this);
    menuItem18.addActionListener(this);
    menuItem19.addActionListener(this);
    menuItem20.addActionListener(this);
    menuItem21.addActionListener(this);
    menuItem22.addActionListener(this);
    menuItem23.addActionListener(this);
    cbmenuItem1.addItemListener(this);
    cbmenuItem2.addItemListener(this);
    jb1.addActionListener(this);
    jb2.addActionListener(this);

    jl1.addListSelectionListener(this);
    jl2.addListSelectionListener(this);
    jl3.addListSelectionListener(this);

    jt.addCaretListener(this);

    Notepad1 n1=new Notepad1(this);
    jt.addMouseListener(n1);

    jf.add(menuBar,BorderLayout.NORTH);
    jf.add(jp3,BorderLayout.CENTER);
    jf.add(m,BorderLayout.SOUTH);

    jt.setLineWrap(false);
    jd.setSize(370,350);
    jd.setLayout(null);
    jd.setVisible(false);
    m.setVisible(true);
    jf.setSize(750,535);
    jf.setVisible(true);
  }

  public void windowClosing(WindowEvent ae)
  {
    jf.setDefaultCloseOperation(2);
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==menuItem1)
    {
      new Notepad();
    }

    if(ae.getSource()==menuItem2)
    {
      jfc1.showOpenDialog(jf);
    }

    if(ae.getSource()==menuItem3)
    {
      jfc2.showSaveDialog(jf);
    }

    if(ae.getSource()==menuItem4)
    {
      jfc2.showSaveDialog(jf);
    }

    if(ae.getSource()==menuItem5)
    {

    }

    if(ae.getSource()==menuItem6)
    {

    }

    if(ae.getSource()==menuItem7)
    {
      jf.setDefaultCloseOperation(2);
    }

    if(ae.getSource()==menuItem8)
    {

    }

    if(ae.getSource()==menuItem9 || ae.getSource()==i2)
    {
      jt.cut();
    }

    if(ae.getSource()==menuItem10 || ae.getSource()==i3)
    {
      jt.copy();
    }

    if(ae.getSource()==menuItem11 || ae.getSource()==i4)
    {
      jt.paste();
    }

    if(ae.getSource()==menuItem12 || ae.getSource()==i5)
    {
      jt.replaceSelection("");
    }

    if(ae.getSource()==menuItem13)
    {

    }

    if(ae.getSource()==menuItem14)
    {

    }

    if(ae.getSource()==menuItem15)
    {

    }

    if(ae.getSource()==menuItem16)
    {

    }

    if(ae.getSource()==menuItem17 || ae.getSource()==i6)
    {
      jt.selectAll();
    }

    if(ae.getSource()==menuItem18)
    {

    }

    if(ae.getSource()==menuItem19)
    {
      jd.setVisible(true);
    }

    if(ae.getSource()==menuItem20)
    {

    }

    if(ae.getSource()==menuItem21)
    {

    }

    if(ae.getSource()==menuItem22)
    {

    }

    if(ae.getSource()==menuItem23)
    {

    }

    if(ae.getSource()==jb1)
    {
      int i1=Font.PLAIN;;
      String x=jf1.getText();
      String y=jf2.getText();
      String z=jf3.getText();
      if(y.equals("Plain"))
      {
        i1=Font.PLAIN;
      }
      if(y.equals("Bold"))
      {
        i1=Font.BOLD;
      }
      if(y.equals("Italic"))
      {
        i1=Font.ITALIC;
      }
      int i2=Integer.parseInt(z);
      fo=new Font(x,i1,i2);
      jt.setFont(fo);
      jd.setVisible(false);
    }
    if(ae.getSource()==jb2)
    {
      jd.setVisible(false);
    }
  }

  public void valueChanged(ListSelectionEvent le)
  {
    if(le.getSource()==jl1 || le.getSource()==jl2 || le.getSource()==jl3)
    {
      if(le.getSource()==jl1)
      {
        int i=jl1.getSelectedIndex();
        jl1.setSelectedIndex(i);
        jf1.setText(f[i]);

      }
      if(le.getSource()==jl2)
      {
        int i=jl2.getSelectedIndex();
        jl2.setSelectedIndex(i);
        jf2.setText(st[i]);
      }
      if(le.getSource()==jl3)
      {
        int i=jl3.getSelectedIndex();
        jl3.setSelectedIndex(i);
        jf3.setText(si[i]);
      }
      int i1=Font.PLAIN;;
      String x=jf1.getText();
      String y=jf2.getText();
      String z=jf3.getText();
      if(y.equals("Plain"))
      {
        i1=Font.PLAIN;
      }
      if(y.equals("Bold"))
      {
        i1=Font.BOLD;
      }
      if(y.equals("Italic"))
      {
        i1=Font.ITALIC;
      }
      int i2=Integer.parseInt(z);
      fo=new Font(x,i1,i2);
      jf4.setFont(fo);
    }
  }

  public void itemStateChanged(ItemEvent ie)
  {
    if(ie.getSource()==cbmenuItem1)
    {
      if(cbmenuItem1.isSelected())
      {
        cbmenuItem2.setEnabled(false);
        m.setVisible(false);
        jt.setLineWrap(true);
      }
      if(!cbmenuItem1.isSelected())
      {
        cbmenuItem2.setEnabled(true);
        if(cbmenuItem2.isSelected())
        {
          m.setVisible(true);
        }
        jt.setLineWrap(false);
      }
    }

    if(ie.getSource()==cbmenuItem2)
    {
      if(cbmenuItem2.isSelected())
      {
        m.setVisible(true);
      }
      if(!cbmenuItem2.isSelected())
      {
        m.setVisible(false);
      }
    }
  }

  public void caretUpdate(CaretEvent ce)
  {
    m.col=ce.getMark();
    m.repaint();
  }

  public static void main(String s[])
  {
    new Notepad();
  }
}

class Notepad1 extends MouseAdapter
{
  Notepad n;

  Notepad1(Notepad n)
  {
    this.n=n;
  }

  public void mouseClicked(MouseEvent me)
  {
    int x=me.getX();
    int y=me.getY();
    if(me.getButton()==MouseEvent.BUTTON3)
      n.p.show(me.getComponent(),me.getX(),me.getY());
  }
}

/*class Notepad1 extends KeyAdapter
{
  Notepad n;

  Notepad1(Notepad n)
  {
    this.n=n;
  }

  public void*/