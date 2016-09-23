package todo.restfuse.todo.common;

import java.io.Serializable;
import java.util.List;

public class ErrInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private List<ErrDetails> details;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ErrDetails> details) {
		this.details = details;
	}

}
