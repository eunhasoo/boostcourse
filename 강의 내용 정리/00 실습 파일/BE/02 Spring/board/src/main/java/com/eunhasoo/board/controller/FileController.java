package com.eunhasoo.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/uploadform")
	public String uploadform() {
		return "uploadform";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		logger.debug("getOriginalFilename: {}, getSize: {}", file.getOriginalFilename(), file.getSize());
		logger.debug("getContentType: {}, getName: {}", file.getContentType(), file.getName());

		// I/O를 이용한 파일 업로드
//		try ( // try-with-resources 문법
//			// 해당 경로에 지정된 이름의 파일이 저장됨
//			// 윈도우의 경우에 해당. Mac일 경우에는 시작 경로를 /tmp/ 로 지정
//			FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
//			InputStream is = file.getInputStream();
//		) {
//			int readCount = 0;
//			byte[] buffer = new byte[1024];
//			while ((readCount = is.read(buffer)) != -1) {
//				fos.write(buffer, 0, readCount);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("file save error!");
//		}

		// transferTo 메소드 이용한 파일 업로드
		String folderDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String fileTime = new SimpleDateFormat("HHmmss").format(new Date());

		String path = "c:/tmp/" + folderDate + "/" + fileTime + "-" + file.getOriginalFilename();
		File savingFile = new File(path);
		if (!savingFile.exists()) { // 파일이 경로에 존재하지 않으면
			savingFile.mkdirs(); // 경로에 맞는 이름을 갖는 폴더를 생성
		}
		try {
			file.transferTo(savingFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return "uploadsuccess";
	}

	@GetMapping("/download")
	public void download(HttpServletResponse response) {
		// 이 부분은 직접 지정해주었지만, 데이터베이스에서 읽어왔다고 가정한다.
		String fileName, saveFileName, contentType;
		int fileLength = 3694;
		fileName = "sql.txt";
		saveFileName = "c:/tmp/sql.txt"; // Mac일 경우 /tmp/로 수정
		contentType = "text/plain";

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (FileInputStream fis = new FileInputStream(saveFileName); 
				OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error!");
		}
	}
}
