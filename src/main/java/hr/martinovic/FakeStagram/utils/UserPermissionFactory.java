package hr.martinovic.FakeStagram.utils;

import hr.martinovic.FakeStagram.model.AdminUsers;
import hr.martinovic.FakeStagram.model.BasicUsers;
import hr.martinovic.FakeStagram.model.Users;


public class UserPermissionFactory
{
    public IPermission getPermission (Users.Roles permission){
        if (permission == null) {return null;}
        if (permission.equals(Users.Roles.Premium)) {return new AdminUsers();}
        if (permission.equals(Users.Roles.Basic)) {return new BasicUsers();}
        return null;
    }

}
