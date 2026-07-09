package com.wouti.zoom_academia.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wouti.zoom_academia.entities.Images;
import com.wouti.zoom_academia.entities.Produit;
import com.wouti.zoom_academia.repositories.ImageRepository;
import com.wouti.zoom_academia.utils.AppUtils;
import com.wouti.zoom_academia.utils.Response;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static final Logger log = Logger.getLogger(ImageController.class.getName());

    @Autowired
	public ImageRepository imageRepository;

	@PostMapping("/save")
	public Response<List<String>> save(@RequestBody List<Images> images){

        List<String> response;
		response = new ArrayList<>();

            try {
                for (Images image : images) {
                    try {
                        Decoder decoder = Base64.getDecoder();
                        byte[] bytes = decoder.decode(image.getPath());
                        String code = AppUtils.generateImageCode();
                        FileUtils.writeByteArrayToFile(new File(AppUtils.PRODUCT_DIRECTORY + code + ".png"), bytes);
                        image.setPath(code + ".png");
                        imageRepository.save(image);

                    } catch (Exception e) {
                        log.error("Echec lors de l'enregistrement de l'Image", e);
                    }
                }
                log.info("Image enregistré avec success " + response.toString());
                return new Response<>(response,HttpStatus.OK, "Image enregistré avec succes");
            } catch (Exception e) {
                log.error("Echec lors de l'enregistrement de l'Image", e);
                return new Response<>(response,HttpStatus.NOT_MODIFIED, "Echec lors de l'enregistrement de l'Image");
            }

    }

    @PostMapping("/get-product-images")
	public Response<List<Images>> getLogo(@RequestBody Produit produit)
	{


        List<Images> images = imageRepository.findByProduit(produit);

        try {
            for (Images image : images) {
            	Path path = Paths.get(AppUtils.PRODUCT_DIRECTORY + image.getPath());
    			byte[] data = Files.readAllBytes(path);
    			Encoder encoder = Base64.getEncoder();
    			image.setPath("data:image/png;base64," + encoder.encodeToString(data));
            }
            return new Response<>(images,HttpStatus.OK, "Images recuperées");
        } catch (Exception e) {
            return new Response<>(images,HttpStatus.NOT_MODIFIED, "Echec lors de la recupération des images");
        }
    }
}
