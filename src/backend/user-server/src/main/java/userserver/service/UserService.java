package userserver.service;

import org.springframework.http.ResponseEntity;
import userserver.domain.User;
import userserver.payload.request.EmailAuthCodeRequest;
import userserver.payload.request.EmailVerifyRequest;
import userserver.payload.request.PasswordChangeRequest;
import userserver.payload.request.SignUpRequest;

public interface UserService {
    ResponseEntity<?> sendAuthCodeByEmail(EmailAuthCodeRequest request);
    ResponseEntity<?> verifyAuthCode(EmailVerifyRequest request);
    ResponseEntity<?> signUp(SignUpRequest request);

    ResponseEntity<?> passwordChange(User user, PasswordChangeRequest request);

}
