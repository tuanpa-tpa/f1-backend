package ptit.blog.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptit.blog.dto.entity.GrandPrixDto;
import ptit.blog.dto.entity.UserDto;
import ptit.blog.dto.response.user.CreateUserResp;
import ptit.blog.model.f1.GrandPrix;
import ptit.blog.model.user.User;
import ptit.blog.repository.UserRepo;

@Slf4j
@Service
@RequiredArgsConstructor
public class Mapper {
    private final UserRepo userRepo;

    public static CreateUserResp responseUserFromModel(User user){
        if (user == null) {
            return null;
        }
        return CreateUserResp.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static UserDto responseUserDtoFromModel(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .roles(user.getRoles())
                .name(user.getName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static GrandPrixDto responseGrandPrixDtoFromModel(GrandPrix grandPrix) {
        return GrandPrixDto.builder()
                .grandPrixId(grandPrix.getGrandPrixId())
                .name(grandPrix.getName())
                .time(grandPrix.getTime())
                .laps(grandPrix.getLaps())
                .build();
    }
}
