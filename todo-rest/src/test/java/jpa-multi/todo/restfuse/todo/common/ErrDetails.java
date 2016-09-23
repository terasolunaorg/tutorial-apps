package todo.restfuse.todo.common;

import java.io.Serializable;

public class ErrDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private String target;

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
