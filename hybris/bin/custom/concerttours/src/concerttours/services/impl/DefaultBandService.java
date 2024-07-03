package concerttours.services.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import concerttours.services.BandService;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class DefaultBandService implements BandService {

    BandDAO bandDAO;

    @Override
    public List<BandModel> getBands() {
        return bandDAO.getBands();
    }

    @Override
    public BandModel getBandByCode(String code) {
        List<BandModel> bands = bandDAO.getBandByCode(code);

        if( !CollectionUtils.isEmpty(bands)){
            return bands.get(0);
        }

        return null;
    }

    public void setBandDAO(BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }
}
