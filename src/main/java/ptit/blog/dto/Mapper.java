package ptit.blog.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptit.blog.dto.entity.*;
import ptit.blog.dto.response.RaceTeamInfo;
import ptit.blog.dto.response.user.CreateUserResp;
import ptit.blog.model.f1.*;
import ptit.blog.model.user.User;
import ptit.blog.repository.RaceTeamRepo;
import ptit.blog.repository.RacerOfRaceTeamRepo;
import ptit.blog.repository.RacerRepo;
import ptit.blog.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Mapper {
    private final UserRepo userRepo;
    private final RacerOfRaceTeamRepo racerOfRaceTeamRepo;
    private final RacerRepo racerRepo;
    private final RaceTeamRepo raceTeamRepo;

    public static CreateUserResp responseUserFromModel(User user) {
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

    public static SeasonDto responseSeasonDtoFromModel(Season season) {
        return SeasonDto.builder()
                .seasonId(season.getSeasonId())
                .name(season.getName())
                .updatedAt(season.getUpdatedAt())
                .createdAt(season.getCreatedAt())
                .build();
    }

    public static ResultDto responseResultDtoFromModel(Result result) {
        return ResultDto.builder()
                .resultId(result.getResultId())
                .timeFinished(result.getTimeFinished() == null ? "0" : result.getTimeFinished())
                .lapFinished(result.getLapFinished() == null ? 0 : result.getLapFinished())
                .racerName(result.getRacerOfRaceTeam().getRacer().getName())
                .raceTeam(result.getRacerOfRaceTeam().getRaceTeam().getName())
                .createdAt(result.getCreatedAt())
                .updatedAt(result.getUpdatedAt())
                .build();
    }

    public static RaceTeamDto responseRaceTeamDtoFromModel(RaceTeam raceTeam) {
        return RaceTeamDto.builder()
                .raceTeamId(raceTeam.getRaceTeamId())
                .name(raceTeam.getName())
                .description(raceTeam.getDescription())
                .points(raceTeam.getPoints())
                .createdAt(raceTeam.getCreatedAt())
                .updatedAt(raceTeam.getUpdatedAt())
                .build();
    }

    public static RacerDto responseRaceDtoFromModel(Racer racer) {
        return RacerDto.builder()
                .racerId(racer.getRacerId())
                .name(racer.getName())
                .bio(racer.getBio())
                .dateOfBirth(racer.getDateOfBirth())
                .height(racer.getHeight())
                .weight(racer.getWeight())
                .national(racer.getNational())
                .createdAt(racer.getCreatedAt())
                .updatedAt(racer.getUpdatedAt())
                .build();
    }

    public static RaceTeamInfo responseRaceTeamInfoFromModel(RaceTeam raceTeam, List<RacerOfRaceTeam> racerOfRaceTeam) {
        List<Racer> racers = new ArrayList<>();
        racerOfRaceTeam.forEach(racer -> {
            racers.add(racer.getRacer());
        });
        List<RacerDto> racerDtos = racers.stream().map(Mapper::responseRaceDtoFromModel).collect(Collectors.toList());
        return RaceTeamInfo.builder()
                .name(raceTeam.getName())
                .description(raceTeam.getDescription())
                .points(raceTeam.getPoints())
                .racers(racerDtos)
                .createdAt(raceTeam.getCreatedAt())
                .updatedAt(raceTeam.getUpdatedAt())
                .build();
    }
}
