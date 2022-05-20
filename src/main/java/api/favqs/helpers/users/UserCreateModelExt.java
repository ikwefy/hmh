package api.favqs.helpers.users;

import api.favqs.helpers.common.RandomGenerator;
import api.favqs.models.request.users.CreateUserRequest;
import api.favqs.models.request.users.UserModel;

/**
 * Generate user request model
 */
public class UserCreateModelExt {
    public static CreateUserRequest initUserCreateModel(){
        UserModel userModel = new UserModel();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        userModel.setLogin(RandomGenerator.generateRandomName("user"));
        userModel.setEmail(RandomGenerator.generateRandomEmail());
        userModel.setPassword(RandomGenerator.genRandomPassword());
        createUserRequest.setUser(userModel);

        return  createUserRequest;
    }
}
