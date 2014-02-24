package logic;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class LogEl extends JPanel implements Runnable{
	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -7085476449180068970L;
	protected int inpc,outc;
	protected Boolean[] inps,outs;
	protected String name="base", text="base";
	protected JCheckBox[] input,output;
	protected Thread th;
	protected boolean stop=false,remove=false;
	protected int width=120,height;
	public LogEl(int inpc, int outc,String name, String text) {
		super();
		this.inpc = inpc;
		this.outc = outc;
		this.name=name;
		this.text=text;
		this.height=35*Math.max(inpc, outc)+10;		
		inps=new Boolean[inpc];
		input=new JCheckBox[inpc];
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JLabel textLabel=new JLabel();
		textLabel.setText(text);
		textLabel.setBounds(width/2-11, height/2-11, 20, 20);
		this.add(textLabel);
		for(int i=0;i<inpc;i++){
			inps[i]=false;
			input[i]=new JCheckBox("");
			input[i].putClientProperty("index", i);
			input[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					JCheckBox chb=(JCheckBox)e.getSource();
					int index=(int) chb.getClientProperty("index");
					inps[index]=chb.isSelected();					
				}
			});
			input[i].setBounds(2, (i+1)*this.height/(inpc+1)-6, 20, 15);
			this.add(input[i]);
			JLabel inpLabel=new JLabel("I"+i);
			inpLabel.setBounds(22, (i+1)*this.height/(inpc+1)-6,40,15);
			this.add(inpLabel);
		}
		outs=new Boolean[outc];
		output=new JCheckBox[outc];
		for(int i=0;i<outc;i++){
			outs[i]=false;
			output[i]=new JCheckBox("");
			output[i].putClientProperty("index", i);
			output[i].setEnabled(false);
			output[i].setBounds(width-52, (i+1)*this.height/(outc+1)-6, 20, 15);
			this.add(output[i]);
			JLabel outLabel=new JLabel("O"+i);
			outLabel.setBounds(width-27, (i+1)*this.height/(outc+1)-6,25,15);
			this.add(outLabel);
		}
		this.setBounds(0,0,this.width,this.height);
	}
	@Override
	public String toString() {
		return name;
	}
	protected void loop(){
		
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Boolean getInput(int i){
		return inps[i];
	}
	public Boolean getOutput(int i){
		return outs[i];
	}
	public JCheckBox getInputBox(int i){
		return input[i];
	}
	public JCheckBox getOutputBox(int i){
		return output[i];
	}
	public void setRemove(boolean arg){
		remove=arg;
	}
	public void run(){
		while(!stop){
			for(int i=0;i<outc;i++){
				output[i].setSelected(outs[i]);
			}
			loop();
		}					
	}
	public void start(){
		th=new Thread(this);
		th.setDaemon(true);
		th.start();
	}
	public void stop(){
		stop=true;
	}	
}