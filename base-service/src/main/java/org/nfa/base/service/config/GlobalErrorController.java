package org.nfa.base.service.config;

import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.nfa.base.model.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorController implements ErrorController {

	@Autowired
	private ServerProperties serverProperties;
	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return this.serverProperties.getError().getPath();
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(WebRequest webRequest) {
		Object uri = webRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI, RequestAttributes.SCOPE_REQUEST);
		Integer code = (Integer) webRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, RequestAttributes.SCOPE_REQUEST);
		Throwable e = errorAttributes.getError(webRequest);
		Map<String, Object> body = errorAttributes.getErrorAttributes(webRequest, true);
		String uriString = null != uri ? uri + " " : new String();
		String message = null != e ? e.getMessage() : String.valueOf(body.get("message"));
		throw new ApplicationException(uriString + message, code, e);
	}

}
