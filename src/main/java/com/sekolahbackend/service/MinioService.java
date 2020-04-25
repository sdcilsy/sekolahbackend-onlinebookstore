package com.sekolahbackend.service;

import java.io.InputStream;

public interface MinioService {

	String uploadImage(String source, InputStream file, String contentType) throws Exception;
}
