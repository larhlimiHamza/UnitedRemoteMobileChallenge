package android.unitedremote.com.unitedremotemobilechallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class Response {
    @SerializedName("total_count")
    @Expose
    private Integer total_count;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incomplete_results;
    @SerializedName("items")
    @Expose
    private LinkedList<Repo> items;

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public LinkedList<Repo> getItems() {
        return items;
    }

    public void setItems(LinkedList<Repo> items) {
        this.items = items;
    }

    public Response(Integer total_count, Boolean incomplete_results, LinkedList<Repo> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Response{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", items=" + items +
                '}';
    }
}