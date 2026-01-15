package com.NFR_RECON.Service.Einvoice;

import com.NFR_RECON.Exception.GSPException;

public interface IRestService {

    long getEinvGstinDetailsIdByGstinAndUserName(String gstin) throws GSPException;
    boolean updateUsername(Long id, String userName) throws GSPException;

}
