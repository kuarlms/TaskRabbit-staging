package killbit.taskrabbit.objects;

/**
 * Created by kural on 11/11/17.
 */

public class vehicle_list_data {
    String vehicle_name,vehicle_id;

    public vehicle_list_data(String vehicle_name, String vehicle_id) {
        this.vehicle_name = vehicle_name;
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
}
