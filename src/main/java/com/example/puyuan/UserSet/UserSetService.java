package com.example.puyuan.UserSet;
import com.example.puyuan.UserSet.Default.DefaultRequest;
import com.example.puyuan.UserSet.Setting.SettingRequest;
import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.UserSet.Default.DefaultEntity;
import com.example.puyuan.UserSet.Default.DefaultRepository;
import com.example.puyuan.UserSet.Setting.SettingEntity;
import com.example.puyuan.UserSet.Setting.SettingRepository;
import com.example.puyuan.UserSet.UserInformation.UserInformationRequest;
import com.example.puyuan.UserSet.UserInformation.UserSetEntity;
import com.example.puyuan.UserSet.UserInformation.UserSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserSetService {
    private final UserSetRepository userSetRepository;
    private final DefaultRepository defaultRepository;
    private final SettingRepository settingRepository;
    private final AppUserRepository appUserRepository;

    /**
     * 個人資訊查詢
     */
    public Map<String, Object> getUserSet() {
        var userDetails = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userData = appUserRepository.findById(userDetails.getId()).orElseThrow();
        var response = new LinkedHashMap<String, Object>();
        response.put("status",StatusResponse.RC.SUCCESS.getCode());
        response.put("user", userData.getUserSet());
        response.put("default",userData.getUserDefault());
        response.put("setting",userData.getUserSetting());
        return response;
    }

    /**
     * 個人資訊更新
     */
    public StatusResponse userSet(UserInformationRequest request) {
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userSet = userSetRepository.findByAppUser(appUser)
            .orElse(
                UserSetEntity.builder()
                    .appUser(appUser)
                    .email(appUser.getEmail())
                    .build()
            );
        BeanUtils.copyProperties(request, userSet);
        userSetRepository.save(userSet);
        return StatusResponse.SUCCESS();
    }

    /**
     * 個人預設值更新
     */
    public StatusResponse defaultSetting(DefaultRequest request) {
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userDefault = defaultRepository.findByAppUser(appUser)
            .orElse(
                DefaultEntity.builder()
                    .appUser(appUser)
                    .build()
            );
        BeanUtils.copyProperties(request, userDefault);
        defaultRepository.save(userDefault);
        return StatusResponse.SUCCESS();
    }

    /**
     * 個人設定更新
     */
    public StatusResponse personalSettings(SettingRequest request) {
        var userApp = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userSetting = settingRepository.findAllByAppUser(userApp)
            .orElse(
                SettingEntity.builder()
                    .appUser(userApp)
                    .build()
            );
        BeanUtils.copyProperties(request, userSetting);
        settingRepository.save(userSetting);
        return StatusResponse.SUCCESS();
    }
}
