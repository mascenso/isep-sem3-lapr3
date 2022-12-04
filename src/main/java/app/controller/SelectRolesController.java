package app.controller;
import app.domain.model.UserRole;
import java.util.List;

public class SelectRolesController {

    private App app;
    public SelectRolesController()
    {
        this.app = App.getInstance();
    }


    public List<UserRole> getUserRoles() {
        return app.getCompany().getUserRoleStore().getUserRoles();
    }
}
