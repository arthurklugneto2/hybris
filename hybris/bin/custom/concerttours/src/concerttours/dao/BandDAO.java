package concerttours.dao;

import concerttours.model.BandModel;

import java.util.List;

public interface BandDAO {
    List<BandModel> getBands();
    List<BandModel> getBandByCode(String code);
}
