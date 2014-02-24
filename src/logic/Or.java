package logic;

public class Or extends LogEl {

	/**
	 * @author Yuriy Gerasimov
	 */
	private static final long serialVersionUID = 8516563935173118708L;

	public Or() {
		super(2, 1,"Or","||");
	}
	
	protected void loop(){
		outs[0]=inps[0]||inps[1];
	}	
}
