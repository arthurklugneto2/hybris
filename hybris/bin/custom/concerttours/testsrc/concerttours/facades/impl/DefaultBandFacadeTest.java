package concerttours.facades.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import concerttours.data.BandData;
import concerttours.model.BandModel;
import concerttours.services.BandService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
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
public class DefaultBandFacadeTest extends TestCase {

    @Spy
    @InjectMocks
    DefaultBandFacade facade;

    @Mock
    BandService bandService;

    @Mock
    Converter<BandModel, BandData> bandConverter;

    @Before
    public void setup(){
        facade.setBandService(bandService);
        facade.setBandConverter(bandConverter);
    }

    @Test
    public void testGetBands(){
        List<BandModel> bands = getBands();
        List<BandData> bandsData = getBandsData();

        Mockito.when(bandService.getBands()).thenReturn(bands);
        Mockito.when(bandConverter.convertAll(bands)).thenReturn(bandsData);

        List<BandData> datas = facade.getBands();

        assertTrue(datas != null && !CollectionUtils.isEmpty(datas) && datas.size() == bands.size());
    }

    @Test
    public void testGetBand(){
        String code = "BEATLES";
        BandModel bandModel = getBandCode(code);
        BandData bandData = getBandDataCode(code);

        Mockito.when(bandService.getBandByCode(code)).thenReturn(bandModel);
        Mockito.when(bandConverter.convert(bandModel)).thenReturn(bandData);

        BandData result = facade.getBand(code);

        assertTrue(result != null && result.getId().equals(code));
    }

    BandModel getBandCode(String code){
        return getBands().stream().filter(p -> p.getCode().equals(code)).findFirst().orElse(null);
    }

    BandData getBandDataCode(String code){
        return getBandsData().stream().filter(p -> p.getId().equals(code)).findFirst().orElse(null);
    }

    private List<BandData> getBandsData() {
        List<BandData> bands = new ArrayList<>();

        BandData band1 = new BandData();
        band1.setId("BEATLES");
        band1.setName("The Beatles");
        bands.add(band1);

        BandData band2 = new BandData();
        band2.setId("ACDC");
        band2.setName("AC/DC");
        bands.add(band2);

        BandData band3 = new BandData();
        band3.setId("WHO");
        band3.setName("The Who");
        bands.add(band3);

        return bands;
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
