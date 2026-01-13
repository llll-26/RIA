package com.ruoyi.student.member.service.impl;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {
    public byte[] generateProof(String studentId, String collegeAndMajor, int totalHours, List<String> skills) throws IOException {
        return generateProofWithQrCode(studentId, collegeAndMajor, totalHours, skills, null);
    }

    public byte[] generateProofWithQrCode(
            String studentId,
            String collegeAndMajor,
            int totalHours,
            List<String> skills,
            BufferedImage qrImage // 可为 null
    ) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(out);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            PdfFont chineseFont = loadChineseFont();

            // 标题
            Paragraph title = new Paragraph("大学生技能交换平台服务证明")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFont(chineseFont);
            document.add(title);

            // 校徽
            InputStream logoStream = PdfService.class.getClassLoader()
                    .getResourceAsStream("images/school-logo.png");
            if (logoStream != null) {
                Image logo = new Image(ImageDataFactory.create(logoStream.readAllBytes()));
                logo.setFixedPosition(50, 750);
                logo.setWidth(100);
                logo.setHeight(100);
                document.add(logo);
                logoStream.close();
            }

            // 用户信息
            document.add(new Paragraph("学号/用户名：" + studentId).setFont(chineseFont));
            document.add(new Paragraph("学院和专业：" + collegeAndMajor).setFont(chineseFont));
            document.add(new Paragraph("总学时：" + totalHours).setFont(chineseFont));
            document.add(new Paragraph("技能：" + String.join(", ", skills)).setFont(chineseFont));
            document.add(new Paragraph("服务时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).setFont(chineseFont));
            document.add(new Paragraph("以上技能已通过技能审核，并完成技能交换服务。").setFont(chineseFont));

            // ===== 插入防伪二维码（如果提供）=====
            if (qrImage != null) {
                // 将 BufferedImage 转为 byte[]
                ByteArrayOutputStream qrOut = new ByteArrayOutputStream();
                ImageIO.write(qrImage, "png", qrOut);
                byte[] qrBytes = qrOut.toByteArray();
                qrOut.close();

                // 创建 iText Image
                Image qrPdfImage = new Image(ImageDataFactory.create(qrBytes))
                        .setWidth(100)
                        .setHeight(100)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(20);

                // 添加提示文字
                document.add(new Paragraph("\n防伪验证二维码：").setFont(chineseFont).setTextAlignment(TextAlignment.CENTER));
                document.add(qrPdfImage);
                document.add(new Paragraph("请用微信扫码验证本证明真伪").setFont(chineseFont).setTextAlignment(TextAlignment.CENTER));
            }

        } catch (Exception e) {
            throw new RuntimeException("生成PDF失败", e);
        }
        return out.toByteArray();
    }

    private PdfFont loadChineseFont() throws Exception {
        InputStream is = PdfService.class.getClassLoader()
                .getResourceAsStream("fonts/NotoSansCJKsc-Regular.otf");
        if (is == null) {
            throw new RuntimeException("字体文件 fonts/NotoSansCJKsc-Regular.otf 未找到！");
        }
        byte[] fontData = is.readAllBytes();
        is.close();
        return PdfFontFactory.createFont(fontData, PdfEncodings.IDENTITY_H, true);
    }
}
