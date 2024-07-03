package concerttours.dao.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultBandDAO implements BandDAO {

    FlexibleSearchService flexibleSearchService;

    static final String SEARCH_ALL_QUERY = //
            "SELECT {p:pk} "//
                    + "FROM {" + BandModel._TYPECODE + " AS p} ";

    static final String SEARCH_BY_CODE_QUERY = //
            "SELECT {p:pk}" //
                    + "FROM {" + BandModel._TYPECODE + " AS p} "//
                    + "WHERE " + "{p:" + BandModel.CODE + "}=?code ";

    @Override
    public List<BandModel> getBands() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SEARCH_ALL_QUERY);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }

    @Override
    public List<BandModel> getBandByCode(String code) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SEARCH_BY_CODE_QUERY);
        query.addQueryParameter("code",code);
        return flexibleSearchService.<BandModel> search(query).getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
