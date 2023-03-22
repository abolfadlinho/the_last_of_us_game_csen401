package exceptions;

public class NotEnoughActionsException extends GameActionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughActionsException(){
		super();
	}
	
	public NotEnoughActionsException(String s){
		super(s);
	}
}
