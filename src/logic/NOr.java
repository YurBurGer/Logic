package logic;

public class NOr extends LogEl {

	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = -3114616716896110011L;

	public NOr() {
		super(2, 1, "NOr", "!||");
	}
	
	public void loop(){
		outs[0]=!(inps[0]||inps[1]);
	}
}
