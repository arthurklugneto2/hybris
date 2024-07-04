package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.services.BandService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultBandFacade implements BandFacade {

    BandService bandService;
    Converter<BandModel,BandData> bandConverter;

    @Override
    public BandData getBand(String name) {
        return bandConverter.convert(bandService.getBandByCode(name));
    }

    @Override
    public List<BandData> getBands() {
        return bandConverter.convertAll(bandService.getBands());
    }

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    public void setBandConverter(Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }
}
