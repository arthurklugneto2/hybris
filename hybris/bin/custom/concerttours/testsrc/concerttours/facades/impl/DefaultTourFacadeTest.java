package concerttours.facades.impl;

import concerttours.data.TourData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.StubLocaleProvider;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.internal.model.impl.LocaleProvider;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Locale;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultTourFacadeTest extends TestCase {

    @Spy
    @InjectMocks
    DefaultTourFacade facade;

    @Mock
    ProductService productService;

    @Mock
    Converter<ProductModel, TourData> tourConverter;

    @Before
    public void setup(){
        facade.setProductService(productService);
        facade.setTourConverter(tourConverter);
    }

    @Test
    public void testGetTourDetails(){
        String tourId = "TOUR_ID";
        ProductModel productModel = generateTestProduct(tourId);
        setLocale(productModel);
        TourData tourData = generateTourData(tourId);

        Mockito.when(productService.getProductForCode(tourId)).thenReturn(productModel);
        Mockito.when(tourConverter.convert(productModel)).thenReturn(tourData);

        TourData result = facade.getTourDetails(tourId);
        assertTrue(result != null && result.getId().equals(productModel.getCode()));
    }

    private TourData generateTourData(String tourId) {
        TourData tourData = new TourData();
        tourData.setId(tourId);
        return tourData;
    }

    private ProductModel generateTestProduct(String code) {
        ProductModel productModel = new ProductModel();
        productModel.setCode(code);
        return productModel;
    }

    private void setLocale(final ItemModel itemModel)
    {
        final LocaleProvider localeProvider = new StubLocaleProvider(Locale.ENGLISH);
        final ItemModelContextImpl itemModelContext = (ItemModelContextImpl) itemModel.getItemModelContext();
        itemModelContext.setLocaleProvider(localeProvider);
    }

}
