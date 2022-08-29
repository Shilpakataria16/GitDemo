package resources;

public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String strResource;

	APIResources(String strResource) {
		// TODO Auto-generated constructor stub
		this.strResource = strResource;
	}
	public String getResource() {
		return strResource;
	}
}
