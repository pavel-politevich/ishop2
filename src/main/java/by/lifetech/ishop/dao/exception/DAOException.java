package by.lifetech.ishop.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String message, Exception e)  {

        super(message, e);
    }

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    protected DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
