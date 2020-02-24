
package app.p.bloodbank.data.model.Profile.getprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.p.bloodbank.data.model.User.Client;

public class Data {

    @SerializedName("client")
    @Expose
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
