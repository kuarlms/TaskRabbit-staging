package killbit.taskrabbit.objects;

/**
 * Created by kural on 11/10/17.
 */

public class active_tasks_data {
    String tasker_name,task_name,requested_time,active_time,location,vehicle,profile_pic,cost;
    String tasker_id;
    String booking_id,booking_day,booking_month;



    public active_tasks_data(String tasker_name, String task_name, String requested_time, String active_time,
                             String location, String vehicle, String profile_pic, String cost,
                             String tasker_id, String booking_id ,String booking_day,String booking_month) {
        this.tasker_name = tasker_name;
        this.task_name = task_name;
        this.requested_time = requested_time;
        this.active_time = active_time;
        this.location = location;
        this.vehicle = vehicle;
        this.profile_pic = profile_pic;
        this.cost = cost;
        this.tasker_id = tasker_id;
        this.booking_id = booking_id;
        this.booking_day=booking_day;
        this.booking_month=booking_month;
    }

    public String getTasker_name() {
        return tasker_name;
    }

    public void setTasker_name(String tasker_name) {
        this.tasker_name = tasker_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getRequested_time() {
        return requested_time;
    }

    public void setRequested_time(String requested_time) {
        this.requested_time = requested_time;
    }

    public String getActive_time() {
        return active_time;
    }

    public void setActive_time(String active_time) {
        this.active_time = active_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getTasker_id() {
        return tasker_id;
    }

    public void setTasker_id(String tasker_id) {
        this.tasker_id = tasker_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public void setBookingDay() {
        this.booking_day=booking_day;
    }
    public String getBookingDay() {
        return booking_day;
    }
    public void setBookingMonth() {
        this.booking_month=booking_month;
    }
    public String getBookingMonth() {
        return booking_month;
    }

}
