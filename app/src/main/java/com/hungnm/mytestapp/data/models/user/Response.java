
package com.hungnm.mytestapp.data.models.user;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;

public class Response {

    @Expose
    @ColumnIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
