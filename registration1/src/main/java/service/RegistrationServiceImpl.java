package service;

import dto.RegisterUser;
import model.User;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    public RegistrationServiceImpl(UserRepository userRepository){
         this.userRepository=userRepository;
    }
    @Override
    public void registerUser(RegisterUser request) throws RuntimeException {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        User savedUserDetail=new User();
        String fullName=getFullName(request);

        savedUserDetail.setName(fullName);
        savedUserDetail.setEmail(request.getEmail());
        savedUserDetail.setPassword(request.getPassword());
        savedUserDetail.setDateOfBirth(request.getDateOfBirth());
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
