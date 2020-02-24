package hr.martinovic.FakeStagram.model;

import hr.martinovic.FakeStagram.utils.IPermission;

public class BasicUsers implements IPermission {

    @Override
    public String  addRole() {
        return "ROLE_USER";
    }
}
