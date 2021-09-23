package club.forhouse.dto;

import club.forhouse.entities.LkUser;
import club.forhouse.services.CompanyServices;

public class LkUserDto {
    private Long id;
    private String name;
    private String photo;
    private CompanyDto company;
    private String position;

    public LkUserDto(LkUser lkUser) {
        this.id = lkUser.getLkUserId();
        this.name = lkUser.getUser().getUserName();
        this.photo = lkUser.getUser().getPhoto();
        this.position = lkUser.getUserPosition();
    }
}
