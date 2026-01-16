package com.NFR_RECON.Controller.Subscription;

import com.NFR_RECON.DTO.UpdateSubscriptionRequest;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/NFR")
public class AddonDateExtensionController {

    @Autowired
    private IRestHandler restHandler;

    public static final Logger LOGGER = Logger.getLogger(AddonDateExtensionController.class.getName());

    /**
     * @author Akanksha Senad
     * @since 14/01/2026
     * @Description Updates end_date, subscription_id and updated_at fields in Value_Added_Service_Subscription Table
     * @param request UpdateSubscriptionRequest(DTO) -> gstin_number, end_date, feature, product_name
     * @return Response: Response Message, HttpStatus
     */
    @PutMapping("addon-date-extension")
    public ResponseEntity<Map<String, Object>> addonDateExtension(@RequestBody UpdateSubscriptionRequest request) {

        LOGGER.log(Level.INFO, "START >> CLASS : updateSubscriptionDetailsController >> METHOD : " +
                "updateSubscriptionDetails >> gstin : " + request.getGstin_number());

        ResponseEntity<Map<String, Object>> result;

        try {
            result = restHandler.updateAddonDate(request);

        } catch(GSPException ex) {
            LOGGER.log(Level.INFO, "ERROR >> CLASS : updateSubscriptionDetailsController >> METHOD : " +
                    "updateSubscriptionDetails >> gstin: " + request.getGstin_number() + " >> Error : " + ex);
            throw new GSPException(ErrorCode.UNKNOWN_ERROR);
        }

        LOGGER.log(Level.INFO, "END >> CLASS : updateSubscriptionDetailsController >> METHOD : " +
                "updateSubscriptionDetails >> gstin : " + request.getGstin_number());

        return result;
    }
}
