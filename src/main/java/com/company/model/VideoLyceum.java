package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoLyceum {
    private Integer id;
    private Integer lyceumId;
    private String description;
    private String video;
    private boolean deleted=false;

    public VideoLyceum(Integer lyceumId, String video, String description) {
        this.lyceumId = lyceumId;
        this.description = description;
        this.video = video;
    }
}
