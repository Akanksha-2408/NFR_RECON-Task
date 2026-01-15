package com.NFR_RECON.Handler.HandlerImpl;

import com.NFR_RECON.Constants.ResponseMessage;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class RestUserHandler implements IRestHandler {

    @Autowired
    private IRestService restService;

    @Autowired
    private SaveInvoicesService saveInvoicesService;

    @Autowired
    private IUpdateSubsService updateSubsService;

    public static final Logger LOGGER = Logger.getLogger(RestUserHandler.class.getName());

    @Override
    public ResponseEntity<Map<String, Object>> updateUserName(String gstin, String userName) throws GSPException {

        boolean result;
        LOGGER.info("START >> CLASS >> RestUserHandler >> METHOD >> updateUserName"
                + " >> GSTIN >> " + gstin);

        Long einvGstinDetailsId = restService.getEinvGstinDetailsIdByGstinAndUserName(gstin);
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

    @Override
    public ResponseEntity<Map<String, Object>> updateSubsDetails(String gstin, String productName, String feature,
                                                                 String endDate) throws GSPException {

        if(updateSubsService.checkExistance(gstin, feature)) {
            String missingFields = updateSubsService.verifyRequest(gstin, productName, feature, endDate);

            if(!missingFields.isEmpty()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String updatedAt = LocalDateTime.now().format(formatter);
                String subscriptionId = updateSubsService.getLatestSubscriptionId(gstin, productName);

                if (subscriptionId != null) {

                    if(updateSubsService.updateAll(endDate, subscriptionId, updatedAt)) {
                        return new ResponseEntity<>(
                                ResponseHandler.success(ResponseMessage.UPDATE_SUCCESSFUL),
                                HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(
                                ResponseHandler.error(ResponseMessage.UPDATE_FAIL),
                                HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                } else {
                    return new ResponseEntity<>(
                            ResponseHandler.error(ResponseMessage.NULL_SUBSCRIPTION_ID),
                            HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>(
                        ResponseHandler.error(ResponseMessage.INVALID_REQUEST + missingFields),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.INVALID_ENTRY + "with gstin: " + gstin +
                            " and feature: " + feature),
                    HttpStatus.NOT_FOUND);
        }

    }

}
