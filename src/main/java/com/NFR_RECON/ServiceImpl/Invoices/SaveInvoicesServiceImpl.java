package com.NFR_RECON.ServiceImpl.Invoices;

import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.Entity.VO.InvoiceVO;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Repository.saveInvoicesRepo;
import com.NFR_RECON.Service.Invoices.SaveInvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SaveInvoicesServiceImpl implements SaveInvoicesService {

    @Autowired
    private saveInvoicesRepo saveInvoicesRepo;

    public static final Logger LOGGER = Logger.getLogger(SaveInvoicesServiceImpl.class.getName());

    @Override
    public boolean saveInvoices(Object object) {
        LOGGER.log(Level.INFO, "START >> CLASS: ServiceImpl >> METHOD: saveInvoices");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(object);
            saveInvoicesRepo.save(InvoiceVO.builder().invoice(json).build());

            LOGGER.log(Level.INFO, "END >> CLASS: ServiceImpl >> METHOD: saveInvoices");
            return true;

        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, ResponseMessage.FAILED_TO_SAVE_INVOICES, e);
            throw new GSPException(ErrorCode.FAILED_TO_SAVE_INVOICES);
        }
    }
}

