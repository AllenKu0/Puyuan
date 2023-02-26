package com.example.puyuan.UserSet.Setting;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_setting")
@EntityListeners(AuditingEntityListener.class)
public class SettingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUserEntity appUser;

    private boolean after_recording;
    private boolean no_recording_for_a_day;
    private boolean over_max_or_under_min;
    private boolean after_meal;
    private boolean unit_of_sugar;
    private boolean unit_of_weight;
    private boolean unit_of_height;
    private String created_at;
    private String updated_at;


}
