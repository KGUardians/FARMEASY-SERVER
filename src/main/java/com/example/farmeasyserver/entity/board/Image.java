package com.example.farmeasyserver.entity.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originName;
    @Column(nullable = false)
    private String uniqueName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private final static String supportedExtension[] = {"jpg","jpeg","gif","bmp","png"};

    public Image(String imageName){
        this.uniqueName = generatedUniqueName(extractExtension(imageName));
    }

    private String generatedUniqueName(String extension){
        return UUID.randomUUID().toString()+"."+extension;
    }

    private String extractExtension(String imageName){
        try {
            String ext = imageName.substring(imageName.lastIndexOf(".")+1);
            if(isSupportedFormat(ext)) return ext;
        }catch (StringIndexOutOfBoundsException e){}
        throw new UnsupportedOperationException("잘못된 이미지 형식입니다.");
    }

    private boolean isSupportedFormat(String ext){
        return Arrays.stream(supportedExtension).anyMatch(e -> e.equalsIgnoreCase(ext));
    }

    public void setPost(Post post){
        this.post = post;
        post.getImageList().add(this);
    }

}
