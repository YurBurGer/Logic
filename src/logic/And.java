package logic;

public class And extends LogEl{
	
	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -7150132953727275620L;
	
	public And() {
		super(2, 1,"And","&");
	}
	
	protected void loop(){
		outs[0]=inps[0]&&inps[1];
	}
}
