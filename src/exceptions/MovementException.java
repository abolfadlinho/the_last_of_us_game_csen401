package exceptions;

public class MovementException extends GameActionException{
	
	private static final long serialVersionUID = 1L;

	public MovementException(){
		super();
	}
	
	public MovementException(String s){
		super(s);
	}
}
