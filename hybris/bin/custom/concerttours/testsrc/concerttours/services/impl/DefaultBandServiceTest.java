package concerttours.services.impl;

import concerttours.dao.BandDAO;
import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.UnitTest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultBandServiceTest extends TestCase {

    @Spy
    @InjectMocks
    DefaultBandService service;

    @Mock
    BandDAO bandDAO;

    @Before
    public void setup(){
        service.setBandDAO(bandDAO);
    }

    @Test
    public void testGetAll(){
        List<BandModel> bands = getBands();

        Mockito.when(bandDAO.getBands()).thenReturn(bands);

        List<BandModel> result = service.getBands();
        assertTrue(!CollectionUtils.isEmpty(result)
                && result.stream().filter(p -> p.getCode().equals("BEATLES")).findFirst().orElse(null) != null);
    }

    @Test
    public void testGetByCode(){
        String code = "ACDC";
        List<BandModel> bandMock = getBandByCode(code);

        Mockito.when(bandDAO.getBandByCode(code)).thenReturn(bandMock);

        BandModel band = service.getBandByCode(code);
        assertTrue(band != null && band.getName().equals("AC/DC"));
    }

    List<BandModel> getBandByCode(String code){
        return getBands().stream().filter(p -> p != null && p.getCode().equals(code)).toList();
    }

    List<BandModel> getBands(){
        List<BandModel> bands = new ArrayList<>();

        BandModel band1 = new BandModel();
        band1.setCode("BEATLES");
        band1.setName("The Beatles");
        bands.add(band1);

        BandModel band2 = new BandModel();
        band2.setCode("ACDC");
        band2.setName("AC/DC");
        bands.add(band2);

        BandModel band3 = new BandModel();
        band3.setCode("WHO");
        band3.setName("The Who");
        bands.add(band3);

        return bands;
    }

}
