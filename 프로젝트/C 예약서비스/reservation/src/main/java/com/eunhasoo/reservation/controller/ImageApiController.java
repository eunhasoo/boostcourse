package com.eunhasoo.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageApiController {

	private final String rootPath = "c:/tmp/reservation-web/";

	@GetMapping("/api/reviewimage/{reservationInfoId}")
	public ResponseEntity<byte[]> reviewimage(@PathVariable Integer reservationInfoId) throws IOException {
		File dir = new File(rootPath + "user_img/");
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(reservationInfoId + "_");
			}
		});

		InputStream in = new FileInputStream(files[0]);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@GetMapping("/api/image/{filetype}/{productId}")
	public ResponseEntity<byte[]> detailimage(@PathVariable Integer productId,
			@PathVariable(required = false) String filetype) throws IOException {
		File dir;
		if (filetype.equals("map")) {
			dir = new File(rootPath + "img_map/");
		} else {
			dir = new File(rootPath + "img/");
		}
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(productId + "_" + filetype);
			}
		});

		if (files != null) {
			InputStream in = new FileInputStream(files[0]);
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG);

				return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			} finally {
				IOUtils.closeQuietly(in);
			}
		} else {
			throw new RuntimeException("type match error");
		}
	}
}