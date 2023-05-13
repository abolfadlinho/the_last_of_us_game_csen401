package exceptions;
@SuppressWarnings("serial")

public class InvalidTargetException extends GameActionException {

	public InvalidTargetException() {
		// TODO Auto-generated constructor stub
	}
	public InvalidTargetException(String message) {
		super(message);
	}
}
