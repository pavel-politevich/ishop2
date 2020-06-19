package by.lifetech.ishop.dao.exception;

public class DAOUserAlreadyExistsException extends DAOException {

	private static final long serialVersionUID = 1L;

	public DAOUserAlreadyExistsException() {
        super();
    }

    public DAOUserAlreadyExistsException(String message) {
        super(message);
    }

    public DAOUserAlreadyExistsException(String message, Throwable cause) {
        super(message, (Exception) cause);
    }

    public DAOUserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
