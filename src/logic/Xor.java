package logic;

public class Xor extends LogEl {
	
	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -680906741168412541L;

	public Xor() {
		super(2, 1, "Xor", "+");
	}
	
	protected void loop(){
		outs[0]=inps[0]^inps[1];
	}
}
