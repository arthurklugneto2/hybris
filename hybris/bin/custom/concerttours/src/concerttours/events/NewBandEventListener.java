package concerttours.events;

import concerttours.model.BandModel;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private static final String NEW_BAND_HEADLINE = "New band, %s";
    private static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";
    private ModelService modelService;

    @Override
    protected void onEvent(AfterItemCreationEvent event) {

        if( event != null && event.getSource() != null ){

            final Object object = modelService.get(event.getSource());
            if (object instanceof BandModel band)
            {
                final String headline = String.format(NEW_BAND_HEADLINE, band.getName());
                final String content = String.format(NEW_BAND_CONTENT, band.getName());
                final NewsModel news = modelService.create(NewsModel.class);
                news.setDate(new Date());
                news.setHeadline(headline);
                news.setContent(content);
                modelService.save(news);
            }

        }

    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
