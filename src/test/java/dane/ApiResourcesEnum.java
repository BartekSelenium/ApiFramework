package dane;

public enum ApiResourcesEnum {
	
	RESOURCE_ADD ("/maps/api/place/add/json"),
	RESOURCE_GET ("/maps/api/place/get/json"),
	RESOURCE_DELETE ("/maps/api/place/delete/json");
	
	public String postUrl;
	
	private ApiResourcesEnum (String postUrl) {
		this.postUrl = postUrl;
	}
	
	public String getPostUrl () {
		return postUrl;
	}

}
