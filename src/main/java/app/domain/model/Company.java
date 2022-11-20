package app.domain.model;
import app.domain.store.UserRoleStore;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class Company {

    private String designation;
    private UserRoleStore userRoleStore;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.userRoleStore = new UserRoleStore();
        //Default roles added in App/Bootstrap()
    }

    public String getDesignation() {
        return designation;
    }

    public List<UserRole> getUserRoles() {
        return userRoleStore.getUserRoles();
    }

    public UserRoleStore getUserRoleStore() {
        return this.userRoleStore;
    }
}
