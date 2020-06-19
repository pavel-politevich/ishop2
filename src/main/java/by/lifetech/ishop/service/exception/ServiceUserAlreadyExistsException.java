package by.lifetech.ishop.service.exception;

public class ServiceUserAlreadyExistsException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceUserAlreadyExistsException() {
        super();
    }

    public ServiceUserAlreadyExistsException(String message) {
        super(message);
    }

    public ServiceUserAlreadyExistsException(String message, Throwable cause) {
        super(message, (Exception) cause);
    }

    public ServiceUserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
