package com.NFR_RECON.Service.Recon;

import com.NFR_RECON.DTO.ReturnPeriodResponse;
import java.util.Set;


public interface UpdateReconStatusService {

    ReturnPeriodResponse getGstr2BLatestMMReconDetails(String gstin);
    int updateReconStatus(Long gstinId, String returnType, Set<String> RetPrd);

}
