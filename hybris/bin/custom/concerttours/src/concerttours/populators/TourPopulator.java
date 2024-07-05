package concerttours.populators;

import concerttours.data.ConcertSummaryData;
import concerttours.data.TourData;
import concerttours.enums.ConcertType;
import concerttours.model.ConcertModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.List;
public class TourPopulator implements Populator<ProductModel,TourData> {

    @Override
    public void populate(ProductModel productModel, TourData tourData) throws ConversionException {
        if (productModel == null)
        {
            return;
        }
        // Create a list of ConcertSummaryData from the matches
        final List<ConcertSummaryData> concerts = new ArrayList<>();
        if (productModel.getVariants() != null)
        {
            for (final VariantProductModel variant : productModel.getVariants())
            {
                if (variant instanceof ConcertModel)
                {
                    final ConcertModel concert = (ConcertModel) variant;
                    final ConcertSummaryData summary = new ConcertSummaryData();
                    summary.setId(concert.getCode());
                    summary.setDate(concert.getDate());
                    summary.setVenue(concert.getVenue());
                    summary.setType(concert.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
                    concerts.add(summary);
                }
            }
        }
        // Now we can create the TourData transfer object
        tourData.setId(productModel.getCode());
        tourData.setTourName(productModel.getName());
        tourData.setDescription(productModel.getDescription());
        tourData.setConcerts(concerts);
    }

}
