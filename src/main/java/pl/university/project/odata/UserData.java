package pl.university.project.odata;

import lombok.Data;

@Data
public class UserData {
    private String id;
    private String username;
    private String role;
    private boolean enabled;
}
