package com.NFR_RECON.Controller.Recon;

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
@RequestMapping("/NFR-Recon")
public class UpdateReconStatusController {

    @Autowired
    IRestHandler restHandler;

    private static final Logger LOGGER = Logger.getLogger(UpdateReconStatusController.class.getName());

    /**
     * @author Akanksha Senad
     * @since 22/01/2026
     * @Description Updates reconcilation status from Reconcilation_In_Progress to Ready_To_Process
     * @param gstin gstin number
     * @param gstinId gstin Id
     * @return response
     * @throws GSPException throws GSP Exception
     */
    @PutMapping("/update-recon-status")
    public ResponseEntity<Map<String, Object>> updateReconStatus(@RequestHeader String gstin,
                                                                 @RequestHeader Long gstinId) throws GSPException {

        ResponseEntity<Map<String, Object>> result;

        LOGGER.log(Level.INFO, "START >> CLASS: UpdateReconStatusController >> METHOD: updateReconStatus >> " +
                "gstin: " + gstin);
        try {
            result = restHandler.updateReconStatus(gstin, gstinId);
        } catch(GSPException ex) {
            LOGGER.log(Level.SEVERE, "ERROR >> CLASS: UpdateReconStatusController >> METHOD: updateReconStatus >> " +
                    "Error: ", ex);
            throw new GSPException(ErrorCode.UPDATE_FAILURE);
        }

        LOGGER.log(Level.INFO, "END >> CLASS: UpdateReconStatusController >> METHOD: updateReconStatus >> " +
                "gstin: " + gstin);

        return result;
    }
}
