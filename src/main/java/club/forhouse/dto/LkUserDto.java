package club.forhouse.dto;

import club.forhouse.entities.LkUser;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LkUserDto {
    private Long id;
    private String name;
    private String photo;
    private String position;

    public LkUserDto(LkUser lkUser) {
        this.id = lkUser.getLkUserId();
        this.name = lkUser.getUser().getUserName();
        this.photo = lkUser.getUser().getPhoto();
        this.position = lkUser.getUserPosition();
    }
}
