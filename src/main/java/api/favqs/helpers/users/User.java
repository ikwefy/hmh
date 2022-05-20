package api.favqs.helpers.users;

import api.favqs.client.UsersClient;
import api.favqs.models.request.users.CreateUserRequest;

/**
 * Single responsibility User class
 * Store logic relates to work with user client
 */
public class User {
    public CreateUserRequest createModel;
    private static UsersClient client;

    public User(UsersClient client){
        this.client = client;
        this.createModel = UserCreateModelExt.initUserCreateModel();
    }

    public User Create(){
        client.createUser().executeAs(createModel);

        return this;
    }
}
