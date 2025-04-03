package nl.han.soex.prototype.identityprovider.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class ProtectedController {

    @GetMapping("protected")
    public String protectedMethod() {
        return "protected endpoint";
    }

}
