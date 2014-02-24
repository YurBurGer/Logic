/**
 * 
 */
package logic;


/**
 * @author Yuriy Gerasimov
 *
 */
public enum ElemList {
	 Not{ 
	       public LogEl getElem() { return new logic.Not(); } 
	 }, 
	 And{ 
	       public LogEl getElem() { return new logic.And(); }
	 },
	 NAnd{ 
	       public LogEl getElem() { return new logic.NAnd(); }
	 },
	 Or{ 
	       public LogEl getElem() { return new logic.Or(); }
	 },
	 NOr{ 
	       public LogEl getElem() { return new logic.NOr(); }
	 },
	 Xor{ 
	       public LogEl getElem() { return new logic.Xor(); }
	 };
	public abstract LogEl getElem();
}
