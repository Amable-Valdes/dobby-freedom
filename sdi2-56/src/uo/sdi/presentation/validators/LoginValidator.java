package uo.sdi.presentation.validators;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.infrastructure.Factories;

@FacesValidator("custom.loginValidator")
public class LoginValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	boolean existe = false;
        if(value == null) {
            return;
        }
        String login = value.toString();
        List<UserDTO> u = null;
        try {
			u = Factories.services.createUserService().listAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        for(UserDTO us : u){
        	if(us.getLogin().equals(login)){
        		existe = true;
        	}
        }
        
        if(existe) {
        	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                    value + " ese usuario ya existe en el sistema"));
        }
    }
 
}
