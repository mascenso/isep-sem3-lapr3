package app.ui.console;

import app.controller.SelectRolesController;
import app.domain.model.UserRole;
import app.interfaces.CONSTANT;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SelectRoleUI implements Runnable{
    private SelectRolesController ctrl;

    public SelectRoleUI()
    {
        ctrl = new SelectRolesController();
    }

    public void run()
    {
            List<UserRole> roles = this.ctrl.getUserRoles();
            if ( (roles == null) || (roles.isEmpty()) )
            {
                System.out.println("There are any roles available.");
            }
            else
            {
                UserRole role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    System.out.println("User did not select a role.");
                }
            }

    }

    private List<MenuItem> getMenuItemForRoles()
    {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(CONSTANT.ROLE_MANAGER, new ManagerUI()));
        rolesUI.add(new MenuItem(CONSTANT.ROLE_FARMER, new ManagerUI()));
        // To complete with other user roles and related RoleUI

        //
        return rolesUI;
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRole role)
    {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
    }

    private UserRole selectsRole(List<UserRole> roles)
    {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRole) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }


}
