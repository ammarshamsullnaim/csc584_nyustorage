package courier;

public class Courier {

	private String courier_id;
	private String courier_name;
	
	  public Courier(String courier_id, String courier_name) {
	        this.courier_id = courier_id;
	        this.courier_name = courier_name;
	    }
	
	
	public String getCourier_ID() {
		return courier_id;
	}
	public void setcourier_id(String courier_id) {
		this.courier_id = courier_id;
	}
	public String getcourier_name() {
		return courier_name;
	}
	public void setcourier_name(String courier_name) {
		this.courier_name = courier_name;
	}
}
