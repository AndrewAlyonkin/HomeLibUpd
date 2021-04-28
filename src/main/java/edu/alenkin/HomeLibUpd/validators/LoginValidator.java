package edu.alenkin.HomeLibUpd.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@FacesValidator("edu.alenkin.HomeLibUpd.validators.LoginValidator")
public class LoginValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = o.toString();
        if (login.length() < 4) {
            throw checkLogin("login_length_error");
        }
        if (login.matches("\\w*\\d")){
            throw checkLogin("login_contains_digits");
        }
        if (usersNames().contains(login)){
            throw checkLogin("exists_name");
        }
    }

    private ValidatorException checkLogin(String key){
        ResourceBundle bundle = ResourceBundle.getBundle("msg", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        FacesMessage message = new FacesMessage(bundle.getString(key));
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        return new ValidatorException(message);
    }

    private List<String> usersNames(){
        List<String> users = new ArrayList<>();
        users.add("username");
        users.add("login");
        return users;
    }
}
