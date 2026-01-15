package com.NFR_RECON.Service.Invoices;

import com.NFR_RECON.Exception.GSPException;

public interface SaveInvoicesService {

    boolean saveInvoices(Object object) throws GSPException;
}
