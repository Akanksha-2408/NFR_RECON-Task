package com.NFR_RECON.ServiceImpl.Recon;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.DTO.ReturnPeriodResponse;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Service.Recon.UpdateReconStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
public class UpdateReconStatusServiceImpl implements UpdateReconStatusService {

    @Autowired
    private Dao dao;


    /**
     * @author Akanksha Senad
     * @since 22/01/2026
     * @Description returns from_return_period and to_return_period as response
     * @param gstin gstin number
     * @return from_return_period, to_return_period
     */
    @Override
    public ReturnPeriodResponse getGstr2BLatestMMReconDetails(String gstin) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstin", gstin));
        List<Object[]> data = dao.executeSqlQuery(DBQueries.GET_GSTR2B_LATEST_MMRECON_DETAILS, conditions);
        ReturnPeriodResponse response = new ReturnPeriodResponse();
        if (data != null && !data.isEmpty()) {
            Object[] realData = Arrays.stream(data.get(0)).toArray();
            String from_retprd = realData[0].toString();
            String to_retprd = realData[1].toString();

            response.setGstr2bFromRtnprd(from_retprd);
            response.setGstr2bToRtnprd(to_retprd);
        }
        return response;
    }

    /**
     * @author Akanksha Senad
     * @since 22/01/2026
     * @Description Updates recon status by gstinId, returnType, and Return period list
     * @param gstinId gstin id
     * @param returnType return Type
     * @param RetPrd list of all return periods
     * @return count of all the records where status is updated
     */
    @Override
    public int updateReconStatus(Long gstinId, String returnType, Set<String> RetPrd) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstinId", gstinId));
        conditions.add(new KeyValue("returnType", returnType));
        conditions.add(new KeyValue("RetPrd", RetPrd));
        String oldStatus = "RECONCILATION_IN_PROGRESS";
        String newStatus = "READY_TO_PROCESS";
        conditions.add(new KeyValue("oldStatus", oldStatus));
        conditions.add(new KeyValue("newStatus", newStatus));
        return dao.updateBySqlQuery(DBQueries.UPDATE_RECON_STATUS, conditions);
    }
}
