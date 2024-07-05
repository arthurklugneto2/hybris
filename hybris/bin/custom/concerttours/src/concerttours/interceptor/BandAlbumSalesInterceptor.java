package concerttours.interceptor;

import concerttours.events.BandAlbumSalesEvent;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor {

    private static final long BIG_SALES = 5000L;
    private static final long NEGATIVE_SALES = 0L;

    EventService eventService;

    @Override
    public void onPrepare(Object model, InterceptorContext interceptorContext) throws InterceptorException {

        // Acionar uma exceção não permitindo salvar uma nova banda
        // com menos de 0 albuns vendidos
        if (model instanceof BandModel band)
        {
            final Long sales = band.getAlbumSales();
            if (sales != null && sales.longValue() < NEGATIVE_SALES)
            {
                throw new InterceptorException("Não pode ser negativo!!!!!!!");
            }
        }

    }

    @Override
    public void onValidate(Object model, InterceptorContext ctx) throws InterceptorException {

        // Publicar um evento customizado, caso a banda seja muito popular
        if (model instanceof BandModel band)
        {
            if (hasBecomeBig(band, ctx))
            {
                eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
            }
        }

    }

    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx)
    {
        final Long sales = band.getAlbumSales();
        if (sales != null && sales.longValue() >= BIG_SALES)
        {
            if (ctx.isNew(band))
            {
                return true;
            }
            else
            {
                final Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);
                if (oldValue == null || oldValue.intValue() < BIG_SALES)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
