

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
//活动封面上传
/*@RestController
@RequestMapping("/upload")
public class FileUploadController {*/

//    @PostMapping("/image")
//    public AjaxResult uploadCover(@RequestParam("file") MultipartFile file) {
//        try {
//            String uploadDir = RuoYiConfig.getProfile();
//
//            String originalFilename = file.getOriginalFilename();
//            String fileName = System.currentTimeMillis() + "_" + originalFilename;
//            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis()));
//
//            String fullPath = uploadDir + "/profile/" + datePath;
//            new File(fullPath).mkdirs();
//
//            File destFile = new File(fullPath, fileName);
//            file.transferTo(destFile);
//
//            String relativePath = "/profile/" + datePath + "/" + fileName;
//            return AjaxResult.success(relativePath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AjaxResult.error("上传失败: " + e.getMessage());
//        }
//    }

