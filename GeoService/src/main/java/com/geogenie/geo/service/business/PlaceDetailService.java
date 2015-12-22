package com.geogenie.geo.service.business;

import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.ext.PlaceDetails;
import com.geogenie.data.model.requests.PlaceDetailsRequest;

public interface PlaceDetailService {
	public PlaceDetails getPlaceDetails(PlaceDetailsRequest placeDetailsRequest) throws ServiceException;
}
