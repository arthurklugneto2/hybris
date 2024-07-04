package concerttours.populators;

import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.model.BandModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class BandPopulator implements Populator<BandModel, BandData> {

    @Override
    public void populate(BandModel bandModel, BandData bandData) throws ConversionException {
        // Create a list of genres
        final List<String> genres = new ArrayList<>();
        if (bandModel.getTypes() != null)
        {
            for (final MusicType musicType : bandModel.getTypes())
            {
                genres.add(musicType.getCode());
            }
        }
        // Create a list of TourSummaryData from the matches
        final List<TourSummaryData> tourHistory = new ArrayList<>();
        if (bandModel.getTours() != null)
        {
            for (final ProductModel tour : bandModel.getTours())
            {
                final TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(Locale.ENGLISH));
                // making the big assumption that all variants are concerts and ignore product catalogs
                summary.setNumberOfConcerts(Integer.toString(tour.getVariants().size()));
                tourHistory.add(summary);
            }
        }
        // Now we can create the BandData transfer object
        bandData.setId(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setAlbumsSold(bandModel.getAlbumSales());
        bandData.setDescription(bandModel.getHistory());
        bandData.setGenres(genres);
        bandData.setTours(tourHistory);
    }

}
