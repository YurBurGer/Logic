package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.ElemList;
import logic.LogEl;
import net.miginfocom.swing.MigLayout;

import javax.swing.JCheckBox;

public class Window extends JFrame {

	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -9116869666558166609L;
	private JPanel contentPane;
	private boolean add=false,remove=false,oconnect=false,iconnect=false,connect=false;
	private Boolean inp,out;
	private JCheckBox inpbox,outbox;
	private TreeMap<JCheckBox,JCheckBox> io,oi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		io=new TreeMap<>();
		oi=new TreeMap<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		final JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
			
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		MigLayout m=new MigLayout("", "[70.00,grow]", "[][][][fill][]"); 
		panel_1.setLayout(m);
		
		final JComboBox<LogEl> comboBox = new JComboBox<>();
		for(ElemList l:ElemList.values()){
			comboBox.addItem(l.getElem());
		}
		panel_1.add(comboBox, "cell 0 0,growx");
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add=true;
				connect=false;
				remove=false;
			}
		});
		panel_1.add(btnNewButton, "cell 0 1,grow");
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove=true;
				connect=false;
				add=false;
				
			}
		});
		panel_1.add(btnDelete, "cell 0 2,growx");
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect=true;
				add=false;
				remove=false;
			}
		});
		panel_1.add(btnConnect, "cell 0 3");
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(add){
					Class<? extends Object> cls=comboBox.getSelectedItem().getClass();
					LogEl toAdd;
					try {
						toAdd =	(LogEl)cls.newInstance();
						toAdd.setBounds(e.getX()-toAdd.getWidth()/2, e.getY()-toAdd.getHeight()/2, toAdd.getWidth(), toAdd.getHeight());
						toAdd.start();						
						panel.add(toAdd);
						panel.repaint();
						repaint();						
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}					
				}
				if(remove){
					Component c=panel.getComponentAt(e.getX(), e.getY());
					try {
						LogEl el=(LogEl)c;
						el.setRemove(true);
						el.stop();					
						panel.remove(c);
						panel.repaint();
					} catch (ClassCastException e1) {						
					}
				}
				if(connect){
					Component c=panel.getComponentAt(e.getX(), e.getY());
					try {
						LogEl el=(LogEl)c;
						Component c1=el.getComponentAt(e.getX(),e.getY());
						JLabel lb=(JLabel)c1;
						if(lb.getText().startsWith("O")){
							if(!oconnect){
								oconnect=true;
								int n=Integer.parseInt(lb.getName().substring(1));
								outbox=el.getOutputBox(n);
								out=el.getOutput(n);
							}
						}
						if(lb.getText().startsWith("I")){
							if(!iconnect){
								iconnect=true;
								int n=Integer.parseInt(lb.getName().substring(1));
								inpbox=el.getOutputBox(n);
								inp=el.getInput(n);
							}
						}
						if(iconnect&&oconnect){
							iconnect=false;
							oconnect=false;
							inpbox.setEnabled(false);
							inp=out;
						}
					} catch (ClassCastException e1) {						
					}
				}
				add=false;
				remove=false;
				connect=false;
			}
		});
	}
}
