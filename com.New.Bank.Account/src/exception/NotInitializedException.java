package exception;

public class NotInitializedException extends Exception{
private static final long serialVersionUID = 1L;
	
	public NotInitializedException() {
		super();
	}

	public NotInitializedException(String msg) {
		super(msg);
	}
}
