package com.farmersol.backend.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String analysisText;
    private String analysisImage;
    private String analysisVideo;
    private String analysisAudio;
    private String analysisVideoUrl;
    private String analysisAudioUrl;
    private String analysisImageUrl;
    private String suggestion;

    @ManyToOne
    private Farmer_User farmerUser;
}
