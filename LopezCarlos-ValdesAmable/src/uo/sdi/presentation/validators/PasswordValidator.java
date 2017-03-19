package uo.sdi.presentation.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("custom.passwordValidator")
public class PasswordValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	boolean number = false;
    	boolean letter = false;
        if(value == null) {
            return;
        }
        String password = value.toString();
        for(Character c : password.toCharArray()){
        	if(Character.isDigit(c)){
        		number = true;
        	}
        }
        for(Character c : password.toCharArray()){
        	if(Character.isLetter(c)){
        		letter = true;
        	}
        }

        if(!number || !letter) {
        	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                    value + " no contiene letas y numeros;"));
        }
    }
 
}
