package com.example.farmeasyserver.dto;

import com.example.farmeasyserver.entity.board.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private String originName;
    private String uniqueName;

    public static ImageDto toDto(Image image){
        return new ImageDto(
                image.getId(),
                image.getOriginName(),
                image.getUniqueName()
        );
    }
}
