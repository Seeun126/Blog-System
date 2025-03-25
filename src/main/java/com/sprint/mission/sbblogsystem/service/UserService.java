package com.sprint.mission.sbblogsystem.service;

import com.sprint.mission.sbblogsystem.domain.User;
import com.sprint.mission.sbblogsystem.dto.UserRegisterRequest;
import com.sprint.mission.sbblogsystem.repository.UserRepository;
import com.sprint.mission.sbblogsystem.util.ValidationUtil;
import org.apache.el.util.Validation;
import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.Bcrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    private final UserRegistory userRegistory;

    public UserService(UserRepository userRepository) {
        this.userRegistory = userRepository;
    }

    public void register(UserRegisterRequest request) {
        if (!ValidationUtil.isValidId(request.getId())) {
            throw new IllegalArgumentException("ID는 6~30자여야 합니다.");
        }
        if (!ValidationUtil.isValidPassword(request.getPassword())){
            throw new IllegalArgumentException("비밀번호는 12~50자이며, 영문/숫자/특수문자를 각각 2자 이상 포함해야 합니다.");
        }
        if (!ValidationUtil.isValidEmail(request.getEmail())){
            throw new IllegalArgumentException("이메일 형식이 유효하지 않거나 100자 초과입니다.");
        }
        if (!ValidationUtil.isValidNickname(request.getNickname())){
            throw new IllegalArgumentException("닉네임은 50자 이하여야 합니다.");
        }

        if (userRegistory.existsById(request.getId())){
            throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
        }

        if (userRegistory.existsByNickname(request.getNickname())){
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

        User user = new User(
                request.getId(),
                hashedPassword,
                request.getEmail(),
                request.getNickname(),
                Instant.now()
        );

        userRepository.save(user);

    }
}
