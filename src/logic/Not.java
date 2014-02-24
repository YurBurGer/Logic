package logic;

public class Not extends LogEl {

	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -2251797030142650665L;

	public Not() {
		super(1, 1,"Not","!");
	}	
	
	protected void loop(){
		outs[0]=!inps[0];
	}
}
 