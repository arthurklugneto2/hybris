package concerttours.dao.impl;

import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.impl.SearchResultImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultBandDAOTest extends TestCase{

    @Spy
    @InjectMocks
    DefaultBandDAO dao;

    @Mock
    FlexibleSearchService flexibleSearchService;

    @Before
    public void setup(){
        dao.setFlexibleSearchService(flexibleSearchService);
    }

    @Test
    public void testGetAllBands(){
        final SearchResult<Object> searchResult = testResult();

        when(flexibleSearchService.search((FlexibleSearchQuery) any())).thenReturn(searchResult);

        List<BandModel> result = dao.getBands();

        assertTrue(!CollectionUtils.isEmpty(result)
                && result.get(0).getName().equals("Beatles"));
    }

    @Test
    public void testGetByCode(){
        final SearchResult<Object> searchResult = testResult();

        when(flexibleSearchService.search((FlexibleSearchQuery) any())).thenReturn(searchResult);

        List<BandModel> result = dao.getBandByCode("CODE");

        assertTrue(!CollectionUtils.isEmpty(result)
                && result.get(0).getName().equals("Beatles"));
    }

    SearchResult<Object> testResult(){
        BandModel bandModel = new BandModel();
        bandModel.setCode("CODE");
        bandModel.setName("Beatles");

        List<Object> bands = new ArrayList<>();
        bands.add(bandModel);
        return new SearchResultImpl<>(bands,1,1,1);
    }

}
