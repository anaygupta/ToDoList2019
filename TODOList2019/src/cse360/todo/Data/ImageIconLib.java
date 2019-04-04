package cse360.todo.Data;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageIconLib {

	private List<Image> pictures;
	
	public ImageIconLib() {
		pictures = new ArrayList<>();
		try {
			pictures.add(ImageIO.read(getClass().getResource("/res/todoLogo16.png")));
			pictures.add(ImageIO.read(getClass().getResource("/res/todoLogo32.png")));
			pictures.add(ImageIO.read(getClass().getResource("/res/todoLogo64.png")));
			pictures.add(ImageIO.read(getClass().getResource("/res/todoLogo128.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Image> getIcons(){
		return pictures;
	}

}
