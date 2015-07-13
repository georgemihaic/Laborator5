package com.company;

/**
 * Created by Mihai on 6/13/2015.
 */
public class Login {
    String user;
    String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    Login(String u) {
        this.setUser(u);
    }

    Login(String u, String p) {
        // System.out.println("User \"" + u + "\" created");
        this.setUser(u);
        this.setPassword(p);
    }

    @Override
    public boolean equals(Object obj) {
        boolean sameUser = false;
        boolean samePass = false;

        if (obj != null && obj instanceof Login)
        {
            sameUser = this.user.equalsIgnoreCase(((Login) obj).getUser());
            samePass = this.password.equals(((Login) obj).getPassword());
        }

        return sameUser && samePass;
    }
}
