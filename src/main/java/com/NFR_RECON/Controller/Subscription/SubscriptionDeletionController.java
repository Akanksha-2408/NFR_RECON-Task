package com.NFR_RECON.Controller.Subscription;

import com.NFR_RECON.DTO.SubscriptionRequest;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/NFR")
public class SubscriptionDeletionController {

    @Autowired
    private IRestHandler restHandler;

    public static final Logger LOGGER = Logger.getLogger(SubscriptionDeletionController.class.getName());

    @PostMapping("deleteSubscription")
    public ResponseEntity<Map<String, Object>> deleteSubscription(@RequestBody SubscriptionRequest request)
            throws GSPException {

        LOGGER.log(Level.INFO, "START >> CLASS : SubscriptionDeletionController >> METHOD : deleteSubscription" +
                " >> gstin : " + request.getGstin());

        ResponseEntity<Map<String, Object>> result;
        try {
            result = restHandler.deleteSubcription(request);
        } catch (GSPException ex) {
            LOGGER.log(Level.SEVERE, "ERROR >> CLASS : SubscriptionDeletionController >> METHOD : " +
                    "deleteSubscription >> gstin : " + request.getGstin() + " Error: " + ex);
            throw new GSPException(ErrorCode.DELETE_FAILURE);
        }

        LOGGER.log(Level.INFO, "END >> CLASS : SubscriptionDeletionController >> METHOD : deleteSubscription " +
                ">> gstin : " + request.getGstin());
        return result;
    }
}
