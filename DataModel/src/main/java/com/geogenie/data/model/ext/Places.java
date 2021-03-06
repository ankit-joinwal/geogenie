package com.geogenie.data.model.ext;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings(value="unused")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Places implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Result> results;
	private String status;
	
	//{
	@JsonIgnoreProperties(ignoreUnknown=true)
	private static final class Result{
		private Geometry geometry;
		public Geometry getGeometry() {
			return geometry;
		}
		public void setGeometry(Geometry geometry) {
			this.geometry = geometry;
		}
		//"geometry" : {
		@JsonIgnoreProperties(ignoreUnknown=true)
		private static final class Geometry{
			private Location location;
			public Location getLocation() {
				return location;
			}
			public void setLocation(Location location) {
				this.location = location;
			}
			//"location" :{
			@JsonIgnoreProperties(ignoreUnknown=true)
			private static final class Location{
				@JsonProperty(value="lat")
				private double lattitude;
				@JsonProperty(value="lng")
				private double longitude;
				
				public double getLattitude() {
					return lattitude;
				}
				public void setLattitude(double lattitude) {
					this.lattitude = lattitude;
				}
				public double getLongitude() {
					return longitude;
				}
				public void setLongitude(double longitude) {
					this.longitude = longitude;
				}
				
				
			}//End of "location"
		}//End of "geometry"
		//"opening hours"
		@JsonIgnoreProperties(ignoreUnknown=true)
		private static final class OpeningHours{
			@JsonProperty("open_now")
			private Boolean openNow;

			public Boolean getOpenNow() {
				return openNow;
			}

			public void setOpenNow(Boolean openNow) {
				this.openNow = openNow;
			}
			
			
		}
		private String icon;
		private String id;
		private String name;
		@JsonProperty("place_id")
		private String placeId;
		private String reference;
		private String scope;
		private String[] types;
		private String vicinity;
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPlaceId() {
			return placeId;
		}
		public void setPlaceId(String placeId) {
			this.placeId = placeId;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public String[] getTypes() {
			return types;
		}
		public void setTypes(String[] types) {
			this.types = types;
		}
		public String getVicinity() {
			return vicinity;
		}
		public void setVicinity(String vicinity) {
			this.vicinity = vicinity;
		}
		
	}
	
	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
