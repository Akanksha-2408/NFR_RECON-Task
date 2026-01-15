package com.NFR_RECON.Controller.Subscription;

import com.NFR_RECON.DTO.UpdateSubscriptionRequest;
import com.NFR_RECON.Handler.IRestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class updateSubscriptionDetailsController {

    @Autowired
    private IRestHandler restHandler;

    public static final Logger LOGGER = Logger.getLogger(updateSubscriptionDetailsController.class.getName());

    @PutMapping("update-subscription-details")
    public ResponseEntity<Map<String, Object>> updateSubscriptionDetails(@RequestBody UpdateSubscriptionRequest request) {

        LOGGER.log(Level.INFO, "START >> CLASS: updateSubscriptionDetailsController >> METHOD: " +
                "updateSubscriptionDetails >> gstin: " + request.getGstin_number());

        ResponseEntity<Map<String, Object>> result = restHandler.updateSubsDetails(request.getGstin_number(),
                request.getProduct_name(), request.getFeature(), String.valueOf(request.getEnd_date()));

        LOGGER.log(Level.INFO, "END >> CLASS: updateSubscriptionDetailsController >> METHOD: " +
                "updateSubscriptionDetails >> gstin: " + request.getGstin_number());

        return result;
    }
}
