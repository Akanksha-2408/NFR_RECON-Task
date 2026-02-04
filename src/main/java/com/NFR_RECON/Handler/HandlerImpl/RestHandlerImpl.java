package com.NFR_RECON.Handler.HandlerImpl;

import com.NFR_RECON.Constants.Enum.ReturnTypes;
import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.DTO.AddonDateExtensionRequest;
import com.NFR_RECON.DTO.ReturnPeriodResponse;
import com.NFR_RECON.DTO.SubscriptionRequest;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import com.NFR_RECON.Handler.ResponseHandler;
import com.NFR_RECON.Service.Einvoice.IRestService;
import com.NFR_RECON.Service.Invoices.SaveInvoicesService;
import com.NFR_RECON.Service.Addon.IUpdateAddonService;
import com.NFR_RECON.Service.Recon.UpdateReconStatusService;
import com.NFR_RECON.Service.Subscription.DeleteSubscriptionService;
import com.NFR_RECON.Util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestHandlerImpl implements IRestHandler {

    @Autowired
    private IRestService restService;

    @Autowired
    private SaveInvoicesService saveInvoicesService;

    @Autowired
    private IUpdateAddonService updateAddonService;

    @Autowired
    private DeleteSubscriptionService deleteSubscriptionService;

    @Autowired
    private UpdateReconStatusService updateReconStatusService;

    public static Long serviceSubsId = null;
    public static final Logger LOGGER = Logger.getLogger(RestHandlerImpl.class.getName());


    // ----- Update-Einvoice-Username API -----
    /**
     * @author Akanksha Senad
     * @since
     * @Description Updates username by gstin_number
     * @param gstin gstin number
     * @param userName username
     * @return response
     * @throws GSPException throws GSPException
     */
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
    /**
     * @author Akanksha Senad
     * @since
     * @Description Accepts input data from random service and saves it to database
     * @param object Generalized object since method will get input from random service
     * @return response
     * @throws GSPException throws GSPException
     */
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


    // ----- Update-Addon-End-Date API -----
    /**
     * @author Akanksha Senad
     * @since 15/01/2026
     * @Description Method that Updates subscription details by applying certain checks
     * @param request UpdateSubscriptionRequest(DTO) -> gstin_number, end_date, feature, product_name
     * @return Response: Response Message, HttpStatus
     * @throws GSPException throws GSPException
     */
    @Override
    public ResponseEntity<Map<String, Object>> updateAddonDate(AddonDateExtensionRequest request) throws GSPException {

        LOGGER.log(Level.INFO, "START >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> gstin: " +
                request.getGstin_number());

        ResponseEntity<Map<String, Object>> result;

        String gstin = request.getGstin_number();
        String feature = request.getFeature();
        String productName = request.getProduct_name();
        String endDate = request.getEnd_date();

        String data = updateAddonService.verifyRequest(gstin, feature, productName, endDate);
        // Recommended to use isBlank() directly from StringUtil class
        String inner = data.substring(1, data.length()-1);
        String[] invalidFields = inner.split("], \\[");
        invalidFields[0] = invalidFields[0] + "]";
        invalidFields[1] = "[" + invalidFields[1];
        String missingFields = invalidFields[0];
        String nullFields = invalidFields[1];

        if(Objects.equals(missingFields, "[]") && Objects.equals(nullFields, "[]")) {

            if (updateAddonService.checkExistance(gstin, feature)) {

                String subscriptionId = updateAddonService.getLatestSubscriptionId(gstin, productName);

                if (subscriptionId != null) {

                    if (updateAddonService.updateAll(serviceSubsId, endDate, subscriptionId)) {

                        LOGGER.log(Level.INFO, "END >> CLASS : RestHandlerImpl >> METHOD : updateAddonDate >> " +
                                "gstin: " + request.getGstin_number() + " " + ResponseMessage.UPDATE_SUCCESSFUL);

                        result =  new ResponseEntity<>(
                                ResponseHandler.success(ResponseMessage.UPDATE_SUCCESSFUL),
                                HttpStatus.OK);

                    } else {
                        result =  new ResponseEntity<>(
                                ResponseHandler.error(ResponseMessage.UPDATE_FAIL),
                                HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                } else {
                    result =  new ResponseEntity<>(
                            ResponseHandler.error(ResponseMessage.NULL_SUBSCRIPTION_ID),
                            HttpStatus.NOT_FOUND);
                }

            } else {
                result =  new ResponseEntity<>(
                        ResponseHandler.error(ResponseMessage.INVALID_ENTRY + "with gstin: " + gstin +
                                " and feature: " + feature),
                        HttpStatus.NOT_FOUND);

            }

        } else if(!Objects.equals(missingFields, "[]") && Objects.equals(nullFields, "[]")) {
            result = new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.INVALID_REQUEST_MISSING_FIELDS + missingFields),
                    HttpStatus.NOT_FOUND);

        }  else if(!Objects.equals(nullFields, "[]") && Objects.equals(missingFields, "[]")) {
            result = new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.INVALID_REQUEST_NULL_FIELDS + nullFields),
                    HttpStatus.NOT_FOUND);

        } else {
            result = new ResponseEntity<>(
                    ResponseHandler.error(ResponseMessage.INVALID_REQUEST + "Missing fields: " + missingFields +
                            " and Null fields: " + nullFields), HttpStatus.NOT_FOUND);
        }
        return result;
    }


    // ----- Update-Recon-Status -----
    /**
     * @author Akanksha Senad
     * @since 22/01/2026
     * @Description Updates reconcilation status of all return periods from from_return_period to to_return_period
     * @param gstinNumber gstin number
     * @param gstinId gstin id
     * @return response
     * @throws GSPException throws GSP Exception
     */
    @Override
    public ResponseEntity<Map<String, Object>> updateReconStatus(String gstinNumber, Long gstinId) throws GSPException {
        LOGGER.log(Level.INFO, "START >> CLASS: RestHandlerImpl >> METHOD: updateReconStatus >> gstin number: "
                + gstinNumber + " and gstinId: " + gstinId);

        ResponseEntity<Map<String, Object>> result;

        // 1. Get from_return_period and to_return_period
        ReturnPeriodResponse retPrd  = updateReconStatusService.getGstr2BLatestMMReconDetails(gstinNumber);
        String fromRetprd = retPrd.getGstr2bFromRtnprd();
        String toRetprd = retPrd.getGstr2bToRtnprd();

        // 2. get Return period list
        Set<String> returnPrd = GeneralUtil.getReturnPeriodList(fromRetprd, toRetprd);

        // Set returnType = GSTR2B
        String returnType = ReturnTypes.GSTR2B.toString();

        // 3. update Recon status
        int count = updateReconStatusService.updateReconStatus(gstinId, returnType, returnPrd);
        if(count > 0) {
            LOGGER.log(Level.INFO, "FOUND >> CLASS: RestHandlerImpl >> METHOD: updateReconStatus >> gstin: "
                    + gstinNumber + " and gstinId: " + gstinId + " >> Status of " + count + " records updated " +
                    "successfully !");

            result = new ResponseEntity<>(ResponseHandler.success(ResponseMessage.STATUS_UPDATED_SUCCESSFULLY),
                    HttpStatus.OK);
        } else {
            LOGGER.log(Level.INFO, "NOT FOUND >> CLASS: RestHandlerImpl >> METHOD: updateReconStatus >> gstin: "
                    + gstinNumber + " and gstinId: " + gstinId + " Error: " + ResponseMessage.NO_RECORDS_FOUND);

            result = new ResponseEntity<>(ResponseHandler.error(ResponseMessage.NO_RECORDS_FOUND),
                    HttpStatus.NOT_FOUND);
        }

        LOGGER.log(Level.INFO, "END >> CLASS: RestHandlerImpl >> METHOD: updateReconStatus >> gstin: "
                + gstinNumber + " and gstinId: " + gstinId);

        return result;
    }

    // ----- Subscription-cleanUp API -----
    @Override
    public ResponseEntity<Map<String, Object>> deleteSubcription(SubscriptionRequest request) throws GSPException {
        LOGGER.log(Level.INFO, "START >> CLASS : RestHandlerImpl >> METHOD : deleteSubcription >> gstin: " +
                request.getGstin());
        ResponseEntity<Map<String, Object>> result;

        String gstin = request.getGstin();

        // get gstinId from gstinNumber
        Long gstinId = deleteSubscriptionService.getGstinId(gstin);
        String product = request.getProductName();

        deleteSubscriptionService.getTableName(gstinId, product);
        LOGGER.log(Level.INFO, "END >> CLASS : RestHandlerImpl >> METHOD : deleteSubcription >> gstin: " +
                request.getGstin());
        return null;
    }

}