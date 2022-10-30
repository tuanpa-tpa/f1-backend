package ptit.blog.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(subTypes = {CreateReq.class})
public class CreateReq {
    @ApiModelProperty(value = "Tài khoản ",  name = "email", example = "nvhung6", required = true)
    private String username;
    @ApiModelProperty(value = "Mật khẩu",  name = "password", example = "abcd1234", required = true)
    private String password;
    @ApiModelProperty(value = "Email", name = "email", example = "hunga1k15tv1@gmail.com", required = true)
    private String email;
    @ApiModelProperty(value = "Role", name = "role", example = "USER", required = true)
    private String role;
    private String name;
}
