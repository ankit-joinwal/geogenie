package com.geogenie.geo.service.business;

import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.ext.Places;
import com.geogenie.data.model.requests.TextSearchRequest;

public interface TextSearchService {

	public Places search(TextSearchRequest textSearchRequest) throws ServiceException;
}
