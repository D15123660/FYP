package com.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

/**
 * Upload tools
 * spring mvn support
 */
public class UploadUtil {
	

	/**
	 * upload image
	 * @return Return relative path
	 * @param photo Image file
	 * @param photoFileName Filename
	 * @param savePath File path
	 * @return
	 * @throws Exception 
	 */
	public static String upload(MultipartFile file) throws Exception{
		// Determine whether there are uploaded files
		if (Objects.isNull(file) || file.isEmpty() || Objects.isNull(file.getOriginalFilename())) {
			return null;
		}
		String savePath = "upload"; // Relative directory for saving files
		String fileName = file.getOriginalFilename();
		// File storage path
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/")+savePath;			
		// Get the current file type
		String type = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        // Get the current system time string
		String time = new SimpleDateFormat("yyMMddssSSS").format(new Date());
		// Build a new file name
		String newFileName = time+"."+type;
		// Rename the build file according to the specified path
		File savefile = new File(path,newFileName);
		// Create if the folder where the file is saved does not exist
		if(!savefile.getParentFile().exists()){
			savefile.getParentFile().mkdirs();
		}
		System.out.println("Absolute path of uploaded file: "+savefile.getPath());
		file.transferTo(savefile);
		return "../"+savePath+"/"+newFileName;
	}

}
