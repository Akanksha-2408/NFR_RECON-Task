package com.NFR_RECON.Handler.HandlerImpl;

import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.DTO.UpdateSubscriptionRequest;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import com.NFR_RECON.Handler.ResponseHandler;
import com.NFR_RECON.Service.Einvoice.IRestService;
import com.NFR_RECON.Service.Invoices.SaveInvoicesService;
import com.NFR_RECON.Service.Subscription.IUpdateSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestHandlerImpl implements IRestHandler {

    @Autowired
    private IRestService restService;

    @Autowired
    private SaveInvoicesService saveInvoicesService;

    @Autowired
    private IUpdateSubsService updateSubsService;

    public static Long serviceSubsId = null;
    public static final Logger LOGGER = Logger.getLogger(RestHandlerImpl.class.getName());


    // ----- Update-Einvoice-Username API -----
    @Override
    public ResponseEntity<Map<String, Object>> updateUserName(String gstin, String userName) throws GSPException {

        boolean result;
        LOGGER.info("START >> CLASS >> RestUserHandler >> METHOD >> updateUserName"
                + " >> GSTIN >> " + gstin);

        Long einvGstinDetailsId = restService.getEinvGstinDetailsIdByGstin(gstin);
        if(einvGstinDetailsId != null) {
            result = restService.updateUsername(einvGstinDetailsId, userName);
        } else {
            return new ResponseEntity<>(
                    ResponseHandler.customError(ResponseMessage.GSTIN_NOT_FOUND + gstin),
                    HttpStatus.NOT_FOUND);
        }

        LOGGER.info("END >> CLASS >> RestUserHandler >> METHOD >> updateUserName"
                + " >> GSTIN >> " + gstin);

        if(result) {
            return new ResponseEntity<>(
                    ResponseHandler.success(ResponseMessage.USERNAME_UPDATED_SUCCESSFULLY + gstin),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.FAILED_TO_UPDATE_USERNAME + gstin),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ----- Save-Invoices API -----
    @Override
    public ResponseEntity<Map<String, Object>> saveInvoices(Object object) throws GSPException {
        ResponseEntity<Map<String, Object>> result = null;

        if(saveInvoicesService.saveInvoices(object)) {
            result = new ResponseEntity<>(
                    ResponseHandler.success(ResponseMessage.INVOICES_SAVED_SUCCESSFULLY),
                    HttpStatus.OK);
        }
        return result;
    }


    // ----- Update-Subscription-Details API -----
    /**
     * @author Akanksha Senad
     * @since 15/01/2026
     * @Description Method that Updates subscription details by applying certain checks
     * @param request UpdateSubscriptionRequest(DTO) -> gstin_number, end_date, feature, product_name
     * @return Response: Response Message, HttpStatus
     * @throws GSPException throws GSPException
     */
    @Override
    public ResponseEntity<Map<String, Object>> updateAddonDate(UpdateSubscriptionRequest request) throws GSPException {

        LOGGER.log(Level.INFO, "START >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> gstin: " +
                request.getGstin_number());

        String gstin = request.getGstin_number();
        String feature = request.getFeature();
        String productName = request.getProduct_name();
        String endDate = request.getEnd_date();

        //TODO
        String missingFields = updateSubsService.verifyRequest(gstin, feature, productName, endDate);

        if(Objects.equals(missingFields, "[]")) {

            if (updateSubsService.checkExistance(gstin, feature)) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String updatedAt = formatter.format(date);
                String subscriptionId = updateSubsService.getLatestSubscriptionId(gstin, productName);

                if (subscriptionId != null) {

                    if (updateSubsService.updateAll(serviceSubsId, endDate, subscriptionId, updatedAt)) {

                        LOGGER.log(Level.INFO, "END >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                                "gstin: " + request.getGstin_number() + " " + ResponseMessage.UPDATE_SUCCESSFUL);

                        return new ResponseEntity<>(
                                ResponseHandler.success(ResponseMessage.UPDATE_SUCCESSFUL),
                                HttpStatus.OK);

                    } else {

                        LOGGER.log(Level.SEVERE, "ERROR >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                                "gstin: " + request.getGstin_number() + " >> Error: " + ResponseMessage.UPDATE_FAIL);

                        return new ResponseEntity<>(
                                ResponseHandler.error(ResponseMessage.UPDATE_FAIL),
                                HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                } else {

                    LOGGER.log(Level.SEVERE, "ERROR >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                            "gstin: " + request.getGstin_number() + " >> Error: " + ResponseMessage.NULL_SUBSCRIPTION_ID);

                    return new ResponseEntity<>(
                            ResponseHandler.error(ResponseMessage.NULL_SUBSCRIPTION_ID),
                            HttpStatus.NOT_FOUND);
                }

            } else {

                LOGGER.log(Level.SEVERE, "ERROR >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                        "gstin: " + request.getGstin_number() + " >> Error: " + ResponseMessage.INVALID_ENTRY +
                        " with gstin: " + gstin + " and feature: " + feature);

                return new ResponseEntity<>(
                        ResponseHandler.error(ResponseMessage.INVALID_ENTRY + "with gstin: " + gstin +
                                " and feature: " + feature),
                        HttpStatus.NOT_FOUND);
            }

        } else {

            LOGGER.log(Level.SEVERE, "ERROR >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                    "gstin: " + request.getGstin_number() + " >> Error: " + ResponseMessage.INVALID_REQUEST +
                    missingFields);

            return new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.INVALID_REQUEST + missingFields),
                    HttpStatus.NOT_FOUND);
        }
    }
}