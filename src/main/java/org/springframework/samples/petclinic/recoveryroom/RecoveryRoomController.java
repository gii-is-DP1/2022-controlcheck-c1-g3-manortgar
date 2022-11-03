package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecoveryRoomController {
    
	RecoveryRoomService service;
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService service) {
		this.service = service;
	}
	
	@ModelAttribute("recoveryRoomType")
    public List<RecoveryRoomType> populateRecoveryRoomTypes() {
        return this.service.getAllRecoveryRoomTypes();
    }
	
	 @GetMapping("/recoveryroom/create")
	    public ModelAndView createrecoveryroom() {
	        ModelAndView mav = new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm");
	        mav.addObject("recoveryRoom", new RecoveryRoom());
	        return mav;
	    }

	 
	 
	 
	    @PostMapping(value = "/recoveryroom/create")
	    public ModelAndView processCreationForm(@Valid RecoveryRoom recoveryroom, BindingResult result) throws DuplicatedRoomNameException {
	        ModelAndView mav;
	        if (result.hasErrors()) {
		        mav = new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm");
	            mav.addObject("recoveryRoom", recoveryroom);
	            mav.addObject("types", service.getAllRecoveryRoomTypes());
	        }
	        else {
	            this.service.save(recoveryroom);
	            mav = new ModelAndView("welcome");
	        }
	        return mav;
	    }
	
	
}
