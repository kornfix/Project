package pl.university.project.populators.impl;


import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;


public class DefaultUserPopulator implements Populator<User, UserData> {


    @Override
    public void populate(User source, UserData target) {
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setRole(getRole(source.getRole()));
        target.setEnabled(source.isEnabled());
    }


    private String getRole(String role) {
        String result = null;
        switch (role) {
            case "ROLE_ADMIN":
                result = "Admin";
                break;
            case "ROLE_USER":
                result ="Użytkownik";
                break;
        }
        return result;
    }
}
