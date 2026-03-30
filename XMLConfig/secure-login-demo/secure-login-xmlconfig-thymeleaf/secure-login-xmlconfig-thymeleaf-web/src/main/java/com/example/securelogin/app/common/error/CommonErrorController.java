package com.example.securelogin.app.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/common/error")
public class CommonErrorController {

    @RequestMapping("/accessDeniedError")
    public String accessDeniedError() {
        return "common/error/accessDeniedError";
    }

    @RequestMapping("/businessError")
    public String businessError() {
        return "common/error/businessError";
    }

    @RequestMapping("/dataAccessError")
    public String dataAccessError() {
        return "common/error/dataAccessError";
    }

    @RequestMapping("/invalidCsrfTokenError")
    public String invalidCsrfTokenError() {
        return "common/error/invalidCsrfTokenError";
    }

    @RequestMapping("/missingCsrfTokenError")
    public String missingCsrfTokenError() {
        return "common/error/missingCsrfTokenError";
    }

    @RequestMapping("/resourceNotFoundError")
    public String resourceNotFoundError() {
        return "common/error/resourceNotFoundError";
    }

    @RequestMapping("/systemError")
    public String systemError() {
        return "common/error/systemError";
    }

    @RequestMapping("/transactionTokenError")
    public String transactionTokenError() {
        return "common/error/transactionTokenError";
    }

    @RequestMapping("/invalidCharacterError")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidCharacterError(HttpServletResponse response) {
        return "common/error/invalidCharacterError";
    }

    @RequestMapping("/fileUploadError")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String fileUploadError(HttpServletResponse response) {
        return "common/error/fileUploadError";
    }

}
