package exceptions;

public class NoAvailableResourcesException extends GameActionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoAvailableResourcesException(){
		super();
	}
	
	public NoAvailableResourcesException(String s){
		super(s);
	}
}
