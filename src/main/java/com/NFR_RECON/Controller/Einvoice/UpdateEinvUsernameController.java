package com.NFR_RECON.Controller.Einvoice;

import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@Api(value = "Rest User Controller", description = "For User operations.")
@RequestMapping(value = "NFR-Recon/v1/users")
public class UpdateEinvUsernameController {

    private static final Logger LOGGER = Logger.getLogger(UpdateEinvUsernameController.class.getName());

    @Autowired
    private IRestHandler restHandler;

    @ApiOperation(value = "Update Einvoice username by gstin")
    @RequestMapping(value = "/update-api-username", method = RequestMethod.PUT, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateUsername(
            @ApiParam(value = "GSTIN number", required = true) @RequestParam String gstin,
            @ApiParam(value = "Username", required = true) @RequestParam String user_name)
            throws GSPException {

        LOGGER.info("START >> CLASS >> RestUserController >> METHOD >> updateUserName >> GSTIN number >> " + gstin);
        ResponseEntity<Map<String, Object>> resultMap = restHandler.updateUserName(gstin, user_name);
        LOGGER.info("END >> CLASS >> RestUserController >> METHOD >> updateUserName >> GSTIN number >> " + gstin);

        return resultMap;
    }
}
