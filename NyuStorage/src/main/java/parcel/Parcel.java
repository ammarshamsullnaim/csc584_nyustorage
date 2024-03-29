package parcel;

public class Parcel {
    private String tracking_num;
    private String receipient_name;
    private String tel_num;
    private String received_date;
    private String collection_date;
    private int weight;
    private String remark;
    private String userId;
    private String shelf_id;
    private String courier_id;

    public Parcel(String tracking_num, String receipient_name, String tel_num, String received_date,
                  String collection_date, int weight, String remark, String userId, String shelf_id, String courier_id) {
        this.tracking_num = tracking_num;
        this.receipient_name = receipient_name;
        this.tel_num = tel_num;
        this.received_date = received_date;
        this.collection_date = collection_date;
        this.weight = weight;
        this.remark = remark;
        this.userId = userId;
        this.shelf_id = shelf_id;
        this.courier_id = courier_id;
    }

    // Getters and setters

    public String getTracking_num() {
        return tracking_num;
    }

    public void setTracking_num(String tracking_num) {
        this.tracking_num = tracking_num;
    }

    public String getReceipient_name() {
        return receipient_name;
    }

    public void setReceipient_name(String receipient_name) {
        this.receipient_name = receipient_name;
    }

    public String getTel_num() {
        return tel_num;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public String getReceived_date() {
        return received_date;
    }

    public void setReceived_date(String received_date) {
        this.received_date = received_date;
    }

    public String getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(String collection_date) {
        this.collection_date = collection_date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }
}
