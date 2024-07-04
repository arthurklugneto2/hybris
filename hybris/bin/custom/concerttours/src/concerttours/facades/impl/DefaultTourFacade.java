package concerttours.facades.impl;

import concerttours.data.TourData;
import concerttours.facades.TourFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultTourFacade implements TourFacade {

    ProductService productService;
    Converter<ProductModel,TourData> tourConverter;

    @Override
    public TourData getTourDetails(String tourId) {
        final ProductModel productModel = productService.getProductForCode(tourId);
        return tourConverter.convert(productModel);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setTourConverter(Converter<ProductModel, TourData> tourConverter) {
        this.tourConverter = tourConverter;
    }
}
