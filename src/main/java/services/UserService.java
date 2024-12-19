package services;

import dto.RequestUserDto;
import dto.ResponseUserDto;
import entity.User;
import exceptions.GlobalExceptionHandler;
import exceptions.NoRegistersFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // POST: Create User
    public ResponseUserDto createUser(RequestUserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.email());
        if (existingUser.isPresent()) {
            throw new GlobalExceptionHandler.DuplicateEmailException(userDto.email());
        }

        User newUser = userDto.toEntity();
        User savedUser = userRepository.save(newUser);
        return ResponseUserDto.fromEntity(savedUser);
    }

    // GET: Get all users
    public List<ResponseUserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoRegistersFoundException();
        }

        return users.stream()
                .map(ResponseUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    // GET: Find user by ID
    public ResponseUserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return ResponseUserDto.fromEntity(user);
    }

    // PUT: Update user
    public ResponseUserDto updateUser(Long id, RequestUserDto request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        // Update fields
        existingUser.setName(request.name());
        existingUser.setEmail(request.email());

        User updatedUser = userRepository.save(existingUser);
        return ResponseUserDto.fromEntity(updatedUser);
    }

    // DELETE: Delete user by ID
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        userRepository.deleteById(id);
    }
}
