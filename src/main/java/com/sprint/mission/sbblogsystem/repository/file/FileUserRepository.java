package com.sprint.mission.sbblogsystem.repository.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.mission.sbblogsystem.domain.User;
import com.sprint.mission.sbblogsystem.repository.UserRepository;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class FileUserRepository implements UserRepository {

    private static final String FILE_PATH = "data/users.json";
    private final Map<String, User> userMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public FileUserRepository() {
        loadFromFile();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userMap.values().stream()
                .filter(user -> user.getNickname().equals(nickname))
                .findFirst();
    }

    @Override
    public boolean existsById(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userMap.values().stream()
                .anyMatch(user -> user.getNickname().equals(nickname));
    }

    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
        saveToFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()){
            try {
                Map<String, User> loaded = objectMapper.readValue(file, new TypeReference<>() {
                });
                userMap.clear();
                userMap.putAll(loaded);
            } catch (IOException e) {
                throw new RuntimeException("사용자 파일 로드 실패", e);
            }
        }
    }

    private void saveToFile() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, userMap);
        } catch (IOException e) {
            throw new RuntimeException("사용자 파일 저장 실패", e);
        }
    }
}
