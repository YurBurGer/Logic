package logic;

public class NAnd extends LogEl {

	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = 7753275072040589491L;
	
	public NAnd() {
		super(2, 1, "NAnd", "!&");
	}
	
	public void loop(){
		outs[0]=!(inps[0]&&inps[1]);
	}

}
