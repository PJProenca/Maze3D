package utils;

import java.awt.Component;
import java.net.URL;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;

public class TextureAppearance extends Appearance {
	ImageComponent2D image= null;
	public TextureAppearance(Component obj, String file,Material material, boolean combine, boolean generate) {
		
	
		URL filename = obj.getClass().getClassLoader().getResource(file);
		TextureLoader loader = new TextureLoader(filename,obj);
		image = loader.getImage();
		
		if(image == null) {
			System.out.println("Can't find texture file!!");
		}
		
		//Texture2D texture = new Texture2D(Texture.BASE_LEVEL,Texture.RGBA,image.getWidth(),image.getHeight());
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL,Texture.RGBA,getImageWidth(),getImageHeight());
		texture.setImage(0, image);
		texture.setEnable(true);
		texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
		if(combine){
		TextureAttributes texatt = new TextureAttributes();
		texatt.setTextureMode(TextureAttributes.COMBINE);
		this.setTextureAttributes(texatt);
		this.setMaterial(material);
		}
		
		if(generate) {
			TexCoordGeneration tcg = new TexCoordGeneration();
			tcg.setGenMode(TexCoordGeneration.OBJECT_LINEAR);
			this.setTexCoordGeneration(tcg);
		}
		
		this.setTexture(texture);
		
	}
	
	public int getImageWidth() {
		return image.getWidth();
	}
	
	public int getImageHeight() {
		return image.getHeight();
	}
	
}
