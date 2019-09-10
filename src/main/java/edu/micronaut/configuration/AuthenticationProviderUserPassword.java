package edu.micronaut.configuration;

import edu.micronaut.model.User;
import edu.micronaut.service.IUserService;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    private IUserService iUserService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();
        Optional<User> user = iUserService.findUserByEmail(email);
        if (user.isPresent() && user.get().passwordMatcher(password)) {
            return Flowable.just(createUserDetails(user.get()));
        }
        return Flowable.just(new AuthenticationFailed());
    }

    private UserDetails createUserDetails(User user){
        return  new UserDetails(user.getEmail(), new ArrayList<>());
    }


}
