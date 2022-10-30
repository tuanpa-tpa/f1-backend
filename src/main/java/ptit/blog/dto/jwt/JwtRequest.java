package ptit.blog.dto.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(subTypes = {JwtRequest.class})
public class JwtRequest {
    //    private String username;
    @NotNull(message = "password not empty")
    @ApiModelProperty(value = "Mật khẩu tài khoản", name = "password", example = "CmcPass97", required = true)
    private String password;
    @ApiModelProperty(value = "Tài khoản email",  name = "email", example = "superadmin@cist.cmc.vn", required = true)
    @NotNull(message = "email not empty")
    private String email;
//    private String role;
    @Override
    public String toString() {
        return this.email + " : " + this.password;
    }
}
