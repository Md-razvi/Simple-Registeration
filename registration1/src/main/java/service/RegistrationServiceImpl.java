package service;

import dto.RegisterUser;
import exceptions.EmailAlreadyExistsException;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public RegistrationServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder){
         this.userRepository=userRepository;
         this.passwordEncoder=passwordEncoder;
    }
    @Override
    public void registerUser(RegisterUser request) throws RuntimeException {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User savedUserDetail=new User();
        String fullName=getFullName(request);
        String hashedPassword=passwordEncoder.encode(request.getPassword());

        savedUserDetail.setName(fullName);
        savedUserDetail.setEmail(request.getEmail());
        savedUserDetail.setPassword(hashedPassword);
        savedUserDetail.setDateOfBirth(request.getDateOfBirth());
        userRepository.save(savedUserDetail);
    }
    private String getFullName(RegisterUser request){
        StringBuilder fullName=new StringBuilder();
        if(request.getMiddleName()==null||request.getMiddleName().isBlank()){
            fullName.append(request.getFirstName());
            fullName.append(" ");
            fullName.append(request.getLastName());
        }else{
            fullName.append(request.getFirstName());
            fullName.append(" ");
            fullName.append(request.getMiddleName());
            fullName.append(" ");
            fullName.append(request.getLastName());
        }
        return fullName.toString();
    }
}
