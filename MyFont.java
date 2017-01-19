import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.TitledBorder;

class MyFont implements ActionListener,ListSelectionListener
{
	JFrame jf;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jt1,jt2,jt3;
	JList jFont,jStyle,jSize;
	JScrollPane jsp1,jsp2,jsp3;
	JButton jb1,jb2;
	Font f;	

	MyFont(Font f1)
	{
		f=f1;
		jf=new JFrame("Font chooser");
		jf.setLayout(null);
		
		jl1=new JLabel("Font:");
		jl1.setBounds(20,20,120,25);
		jl2=new JLabel("Font style:");
		jl2.setBounds(160,20,100,25);
		jl3=new JLabel("Size:");
		jl3.setBounds(280,20,150,25);

		jt1=new JTextField();
		jt1.setBounds(20,50,120,25);
		jt2=new JTextField();
		jt2.setBounds(160,50,100,25);
		jt3=new JTextField();
		jt3.setBounds(280,50,50,25);
				
		String s1[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		jFont=new JList(s1);
		jFont.setSelectedIndex(0);
		jt1.setText(jFont.getSelectedValue().toString());
		jsp1=new JScrollPane(jFont);
		jsp1.setBounds(20,80,120,130);

		String s2[]={"Regular","Italic","Bold","Bold Italic"};
		jStyle=new JList(s2);
		jStyle.setSelectedIndex(0);
		jt2.setText(jStyle.getSelectedValue().toString());
		jsp2=new JScrollPane(jStyle);
		jsp2.setBounds(160,80,100,130);
				
		String s3[]={"8","9","10","11","12","14","16","18","20","22","24","26"};
		jSize=new JList(s3);
		jSize.setSelectedIndex(0);
		jt3.setText(jSize.getSelectedValue().toString());
		jsp3=new JScrollPane(jSize);
		jsp3.setBounds(280,80,50,130);
		
		jl4=new JLabel("Demo",JLabel.CENTER);
		jl4.setBorder(new TitledBorder("Sample"));
		jl4.setBounds(180,220,130,60);

		jb1=new JButton("OK");
		jb1.setBounds(140,290,80,25);
		jb2=new JButton("Cancel");
		jb2.setBounds(230,290,80,25);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jFont.addListSelectionListener(this);
		jStyle.addListSelectionListener(this);
		jSize.addListSelectionListener(this);
		
		jf.add(jl1);jf.add(jl2);jf.add(jl3);
		jf.add(jt1);jf.add(jt2);jf.add(jt3);
		jf.add(jsp1);jf.add(jsp2);jf.add(jsp3);
		jf.add(jl4);
		jf.add(jb1);jf.add(jb2);

		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		jf.setSize(390,360);
		jf.setVisible(true);
	}

	public Font createFont()
	{
		int x=jStyle.getSelectedIndex();
		String fname=(String)jFont.getSelectedValue();
		int fsize=Integer.parseInt((String)jSize.getSelectedValue());

		f=new Font(fname,x,fsize);
		return f;
	}
	
	public void valueChanged(ListSelectionEvent le)
	{
		jl4.setFont(createFont());
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==jb1)
		{
			jf.dispose();			
		}
		else if(ae.getSource()==jb2)
		{
			jf.dispose();		
		}
	}
}