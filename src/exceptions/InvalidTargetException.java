package exceptions;

public class InvalidTargetException extends GameActionException{

	private static final long serialVersionUID = 1L;

	public InvalidTargetException(){
		super();
	}
	
	public InvalidTargetException(String s){
		super(s);
	}
}
