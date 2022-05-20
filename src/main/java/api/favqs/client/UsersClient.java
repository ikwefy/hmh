package api.favqs.client;

import api.favqs.models.request.users.CreateUserRequest;
import api.favqs.models.response.users.CreateUserResponse;
import api.favqs.routes.UsersRoutes;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * User api client
 * Implements http methods to work with user api controller
 */
public class UsersClient extends BaseApiClient {
    private UsersClient(){
    }

    public static UsersClient usersApi(){
        return new UsersClient();
    }

    public CreateUserAction createUser() {
        return new CreateUserAction();
    }

    public class CreateUserAction {
        private CreateUserAction() { }

        /**
         *  Create new user
         *
         * @param  createUserRequest new user request model
         * @return                   response from http request
         */
        public Response execute(CreateUserRequest createUserRequest){
            return doPost(UsersRoutes.Route, createUserRequest);
        }

        public CreateUserResponse executeAs(CreateUserRequest createUserRequest) {
            CreateUserResponse result = execute(createUserRequest).as(CreateUserResponse.class);
            assertThat(result.getLogin()).as("user has not been created").isEqualTo(createUserRequest.getUser().getLogin());
            userTokenValue = result.getUserToken();

            return result;
        }
    }
}
